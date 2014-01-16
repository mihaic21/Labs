#include <sys/types.h>
#include <sys/socket.h>
#include <stdio.h>
#include <netinet/in.h>
#include <netinet/ip.h>
#include <string.h>
#include <pthread.h>
#include <stdlib.h>
#include <signal.h>
#include <arpa/inet.h>
#include <semaphore.h>
#include <string.h>

int sock, nofStr, nofThr,err, port,newsock;
int thrCount = 0;
// nofStr - number of entries stored
struct sockaddr_in server, client;
struct in_addr ip;
char* ip_str;
sem_t sem;
pthread_mutex_t mtx;
int semaphore;
pthread_t thr[100];
unsigned short port_s;

void* worker(void *data){
	
	int* sock = (int*)data;
	
	sem_wait(&sem);
	
	char msg[50];
	char done[4] = "DONE";
	int nofLog=0;
	
	printf("Client connected\n");
	
	err = recv(*sock, &msg, sizeof(msg), 0);
	
	//handle error
	if(err<0){
		printf("Failed to recieve data from client!\n");
		close(*sock);
		return NULL;
		}
	
	printf("Recieved: %s\n",msg);
	
	//need to store ip and port
	
	pthread_mutex_lock(&mtx);
	char* file_name = "log.txt";
	char aux;
	
	FILE *f = fopen(file_name, "r");
	
	//find the number of logs
	while(!feof(f))
	{
			char line[100];
			fgets(line,100,f);
			nofLog++;
	};
	
	fclose(f);
	
	//write msg in file
	
	f= fopen(file_name, "a");
	
	fputs(msg,f);
	fputs(" IP: ",f);
	ip=client.sin_addr;
	ip_str=inet_ntoa(ip);
	fputs(ip_str,f);
	fputs(" PORT: ",f);
	port_s=client.sin_port;
	port_s=ntohs(port_s);
	fprintf(f,"%d",port_s);
	fputs("\n",f);
	
	
	nofLog = htonl(nofLog);
	
	//need to send the number of logs
	send(*sock, &nofLog, sizeof(nofLog), 0);
	send(*sock, &done, sizeof(done), 0);
	
	fclose(f);
	
	pthread_mutex_unlock(&mtx);
	
	sem_post(&sem);
	
	return NULL;
	
	}


int main(){
	
	printf("Give the port:\n");
	scanf("%d", &port);
	
	printf("Give the MAX number of threads: ");
	scanf("%d",&nofThr);
	
	//initialize semaptore with the max no of threads	

	semaphore=sem_init(&sem, 0, nofThr);

	sock = socket(AF_INET, SOCK_STREAM, 0);
	if (sock < 0)
	{printf("Error on create socket");
		return 1;}

	memset(&server, 0, sizeof(server));
	server.sin_family = AF_INET;
	server.sin_addr.s_addr = INADDR_ANY; // ip address of host
	server.sin_port = htons(port); // the port
	
	//bind socket to server addr
	if (bind(sock, (struct sockaddr*) &server, sizeof(struct sockaddr_in))<0){
		printf("Error on bind");
		return 1;} 
	
	
	int err = listen(sock, nofThr); // set backloh queue to nofThr
	if(err < 0) {
        perror("Failed to listen");
        close(sock);
        exit(1);
    }
	
	int len = sizeof(struct sockaddr_in);
	memset(&client, 0, sizeof(client));


	while(1){
		
		//accept conncection from client, blocks execution until a cilent successfuly connects to server
		newsock = accept(sock, (struct sockaddr*)&client,&len);
		
		if(newsock < 0) {
            perror("Failed to accept");
            break;
        }
		
		pthread_create(&thr[thrCount],NULL,worker,&newsock);
		thrCount++;
		
		int i;
		for (i=0;i<thrCount;i++){
			pthread_join(thr[i],0);
			}
		}
		
		pthread_mutex_destroy(&mtx);
		
		//sem_destroy(&sem);
		
		close(sock);
		close(newsock);
	
		return 0;
	}
