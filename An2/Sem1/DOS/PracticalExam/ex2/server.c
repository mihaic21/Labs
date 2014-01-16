#include <pthread.h>
#include <stdlib.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <unistd.h>
#include <netinet/in.h>

typedef struct {
    pthread_t thread;
    int    allocated;
} thread_t;

typedef struct {
    int client;
    int thread;
} arg_t;

thread_t threads[10];
int rvSock;

void handler (int sig){
    if (close(rvSock) < 0){
        perror("\ncould not close socket\n");
        return;
    }
}

void* threadFunction(void* args){
    arg_t* argptr = (arg_t*) args;
    arg_t arg = *argptr;
    int n;
    printf("am intrat\n");
    recv(arg.client, &n, sizeof(int), 0);
    n++;
    send(arg.client, &n, sizeof(int), 0);

    threads[arg.thread].allocated = 0;
    pthread_exit(NULL);
    return NULL;
}

int main(){
    
    int port = 1234;
    int sock;
    struct sockaddr_in addr;

    sock = socket(AF_INET, SOCK_STREAM, 0);

    if (sock < 0){
        perror("\nerror creating socket\n");
    }



    addr.sin_addr.s_addr = INADDR_ANY;
    addr.sin_port = htons(port);
    addr.sin_family = AF_INET;

    
    socklen_t len = (socklen_t) sizeof(addr);

    if (bind(sock, (struct sockaddr*) &addr, len) < 0){
        perror("problem binding\n");
    }


    
    listen(sock, 10);

    int i;
    for (i=0; i<10;i++) {
        threads[i].allocated = 0;
    }

    while (1){
        struct sockaddr_in clientAddr;
        socklen_t lenclient = (socklen_t) sizeof(clientAddr);
        int client = accept (sock, (struct sockaddr*) &clientAddr, &lenclient); //sock, null, null if client address not needed
        for (i=0; i<10; i++){
            if (!threads[i].allocated){
                arg_t arg;
                arg.thread = i;
                arg.client = client;
                pthread_create(&threads[i].thread, NULL, threadFunction, &arg);
                break;
            }
        }
    }
        for (i=0; i<10; i++){
            pthread_join(threads[i].thread,NULL);
            threads[i].allocated = 0;
        }

    
    return 0;
}
