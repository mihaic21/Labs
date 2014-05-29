#include <stdlib.h>
#include <stdio.h>
#include <error.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <string.h>
#include <unistd.h>
#include <pthread.h>
#include <netdb.h>
#include <sys/stat.h>
#include <iostream>
#include <ctime>
#include <map>
#include <list>

#define PORT 7777
using namespace std;
int sock, counter=0, county=0, lile=10; //broadcast socky, counter
struct sockaddr_in appBrod,serv;
pthread_mutex_t mtx = PTHREAD_MUTEX_INITIALIZER;
char dat[50], tim[50]; //time and date containers
//map content container
typedef struct{
    char ti[50];
    char da[50];
    int co;
} val;
//list errors content container
typedef struct{
  int ip;
  char msg[50];
} err;
//map/list declarations
map<int,val> peers;
list<err> errs;
//thread arguments
typedef struct args{
    int type; //time=0 or date=1
    struct sockaddr_in sender; //ip address to whom to respond
} targs, *ptargs;



void printMap();

void upMap(struct sockaddr_in,char*);

//3 seconds request TIMEQUERY\0 thread
void cli1(void * a){
    int length;
    length = sizeof(struct sockaddr_in);
    int n;
    while(1){
        pthread_mutex_lock(&mtx);
        char req[20] = "TIMEQUERY\0";
        counter++;
        n = sendto(sock, req, 10, 0,(struct sockaddr*) &appBrod, length);
        if(n <0) { perror("sent to from cli1!"); exit(0);}
        pthread_mutex_unlock(&mtx);
        sleep(3);

    }
}
//10 seconds request DATEQUERY\0 thread
void cli2(){
    int length;
    length = sizeof(struct sockaddr_in);
    cout << "In cli1 b4 socky" << endl;
    int n;
    counter++;
    while(1){
        pthread_mutex_lock(&mtx);
        char req[10] = "DATEQUERY";
        req[10] ='\0';
        n = sendto(sock, req, 10, 0,(struct sockaddr*) &appBrod, length);
        if(n <0) {perror("sent to from cli1!"); exit(0);}
        pthread_mutex_unlock(&mtx);
        sleep(10);
    }
}
//get time function
char* getFTime(){
    time_t t = time(0);
    struct tm * now = localtime( &t );
    sprintf(tim, "TIME %02d:%02d:%02d", now->tm_hour, now->tm_min, now->tm_sec);
    return tim;
}
//get date function
char* getFDate(){
    time_t t = time(0);
    struct tm * now = localtime( &t );
    sprintf(dat, "DATE %02d:%02d:%04d", now->tm_mday, (now->tm_mon + 1), (now->tm_year + 1900));
    return dat;
}
//Thread in charge of responses
void* respond(void *argtype){
    targs ar = *((ptargs)argtype);
    char ms[20];
    pthread_mutex_lock(&mtx);
    if (ar.type ==0){
        strcpy(ms, getFTime());
    }
    else{
        strcpy(ms, getFDate());
    }
    if(sendto(sock, ms, strlen(ms) + 1, 0,(sockaddr *)&ar.sender, sizeof(ar.sender)) < 0){
        perror("sendto 2");
        exit(1);
    }
    pthread_mutex_unlock(&mtx);


}

//Update map function
void upMap(struct sockaddr_in sent, char* msg){
    int b =sent.sin_addr.s_addr;
    map<int, val>::iterator it = peers.find(b);
    val v;
    char emp[]="";
    strcpy(v.ti,emp);
//    strcpy(v.da,emp);
    string m = msg;
    if( m.compare(0,4,"TIME",0,4)==0){
        strcpy(v.ti, msg);
    }
    else{
        if( m.compare(0,4,"DATE",0,4)==0){
            strcpy(v.da, msg);
        }
    }
    v.co=counter;
    pthread_mutex_lock(&mtx);
    if(it == peers.end()){
         peers.insert(pair<int, val>(b,v));
    }
    else{
         it = peers.begin();
         while(it!=peers.end()){
              if(counter - it->second.co>3) { peers.erase(it++);}
              else{ it++;}
         }
         it = peers.find(b);
         it->second.co = counter;
         if( m.compare(0,4,"TIME",0,4)==0){
             strcpy(it->second.ti, msg);
         }
         else{
             if(m.compare(0,4,"DATE",0,4)==0){
                 strcpy(it->second.da, msg);
             }
         }

    }
    pthread_mutex_unlock(&mtx);
}

void printMap(){
    map<int, val>::iterator it;
    struct sockaddr_in something;
    pthread_mutex_lock(&mtx);
    cout << "~~~~~-------~~~~~~----~~~~~~-------~~~~~~~"<< endl;
    cout << "            List aka retarded map                    " << endl;
    it = peers.begin();
    while(it!= peers.end()){
        something.sin_addr.s_addr= it->first;
        printf("%s: %s: %s \n", inet_ntoa(something.sin_addr), it->second.ti, it->second.da);
        it++;
    }
    pthread_mutex_unlock(&mtx);

}

void printList(){
    pthread_mutex_lock(&mtx);
    struct sockaddr_in some;
    cout << "Error list" << endl;
    list<err>::iterator it = errs.begin();
    while(it!=errs.end()){
        some.sin_addr.s_addr=it->ip;
        printf("%s: %s\n", inet_ntoa(some.sin_addr), it->msg);
        it++;
    }
    pthread_mutex_unlock(&mtx);

}

int main(int argc, char* argv[]){
    int length, msgType, erc =0;
    char brIP[20];
    char requ[50];
    struct hostent *hp;
    int tid;
    struct sockaddr_in send;
    pthread_t client1, client2,resp;
    if(argc <2){
        fprintf(stderr, "Error no ip provided\n");
        return 1;
    }
    int broadcast = 1;
    sock = socket(PF_INET, SOCK_DGRAM,0);
    if( sock <0) {perror ("Opening socket"); exit(0);}
    memset(&appBrod, 0, sizeof(struct sockaddr_in));
    if(setsockopt(sock,SOL_SOCKET,SO_BROADCAST,(char*) &broadcast,sizeof(broadcast)) < 0)
    {
        cout<<"Error in setting Broadcast option";
        close(sock);
        return 0;
    }
    length = sizeof(appBrod);
    bzero(&appBrod, length);
    bzero(&serv, length);
    bzero(requ,50);
    appBrod.sin_family = AF_INET;
    hp = gethostbyname(argv[1]);
    if(hp == 0) { perror("Unknown host");exit(0);}
    bcopy((char*)hp->h_addr, (char*) &appBrod.sin_addr,hp->h_length);
    appBrod.sin_port = htons(PORT);

    serv.sin_family = AF_INET;
    serv.sin_port = htons(PORT);
    serv.sin_addr.s_addr = INADDR_ANY;

    if(bind(sock, (struct sockaddr*) &serv, length)<0) {perror("binding1");exit(0);}
    pthread_create ((pthread_t *) & client1, NULL, (void *(*)(void*)) cli1, &tid);
    pthread_create ((pthread_t *) & client2, NULL, (void *(*)(void*)) cli2, &tid);
    while(1){
        int n=0;
        n=recvfrom(sock, requ, 50,0, (struct sockaddr*) &send, (socklen_t*) &length);
        if(n<0) {perror("recv in main app!"); exit(0);}
        cout <<requ << endl;
        if(strcmp(requ,"TIMEQUERY")==0){
            targs arg;
            printf("Received time query request from %s\n", inet_ntoa(send.sin_addr));
            arg.type =0;
            arg.sender = send;
            pthread_create((pthread_t*) &resp, NULL, (void*(*)(void*)) respond,&arg);
        }
        else{
            if(strcmp(requ,"DATEQUERY")==0){
                targs arg;
                printf("Received date query request from %s\n", inet_ntoa(send.sin_addr));
                arg.type =1;
                arg.sender = send;
                pthread_create((pthread_t*) &resp, NULL, (void*(*)(void*)) respond,&arg);

            }
            else{
                  int g,h,i,aux1,aux2,aux3;

                  if (sscanf(requ,"TIME %2d:%2d:%2d",&aux1,&aux2,&aux3)==3 || sscanf(requ,"DATE %2d:%2d:%4d",&g,&h,&i)==3){
                      if(n<0) { perror("recv in cli1\n"); exit(0); }
                      cout <<"The message received "<< requ << endl;
                      upMap(send,requ);
                  }
                  else{
                      pthread_mutex_lock(&mtx);
                      err va;
                      va.ip=send.sin_addr.s_addr;
                      strcpy(va.msg, requ);
                      errs.push_back(va);

                      erc++;
                      if(erc==lile){
                          errs.pop_front();
                          erc--;
                      }
                      pthread_mutex_unlock(&mtx);
                  }
            }
       }
        cout << endl;
        cout << endl;
        //system("clear");
        cout << "Map size: "<<peers.size()<<endl;
        printMap();
        cout << endl;
        cout << "Error list size " << errs.size()<<endl;
        printList();
        bzero(requ,50);
    }

    return 0;
}
