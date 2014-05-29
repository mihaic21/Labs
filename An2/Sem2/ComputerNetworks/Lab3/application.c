#include <stdio.h>
#include <unistd.h>
#include <signal.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <sys/stat.h>
#include <netinet/in.h>
#include <pthread.h>
#include "list.h"
#include "validation.h"

#define OUT(s) { printf("%s\n",s); exit(1); }
#define PORT 7777
#define TIME "TIMEQUERY\0"
#define DATE "DATEQUERY\0"

struct sockaddr_in	_send, _recv;
pthread_mutex_t 	mtx;
int 	sock, len, port;
Node*	list;

void stop(int sig){
    close(sock);	
    pthread_mutex_destroy(&mtx);
	_exit(1);
}

void* child3sHandler(void* p){
	while(1){
		sleep(3);
		sendto(sock, TIME, strlen(TIME)+1, 0, (struct sockaddr *)&_send, len);	
    	pthread_mutex_lock(&mtx);
		list = updateSend(list);
	    pthread_mutex_unlock(&mtx);
	}
}

void* child10sHandler(void* p){
	while(1){
		sleep(10);
		sendto(sock, DATE, strlen(DATE)+1, 0, (struct sockaddr *)&_send, len);
    	pthread_mutex_lock(&mtx);
		list = updateSend(list);
	    pthread_mutex_unlock(&mtx);
	}
}

void* childPrintHandler(void* p){
	while(1){
		sleep(2);
	    pthread_mutex_lock(&mtx);
		system("clear");
		printf("Client \t\t\t\t\t\t\t Message\n");
		printf("======================================================================\n");
		printList(list);	
		printf("===================================Go=Home=Message=...=you're=drunk===\n");
	    pthread_mutex_unlock(&mtx);	
	}
}

int main(int argc, char** argv){

    pthread_mutex_init(&mtx, NULL);
	signal(SIGINT, stop);
	if(argc != 2){ OUT("To start we need <BROADCAST ADDR>\n"); }

	//Getting a socket	
	if((sock = socket(PF_INET, SOCK_DGRAM, 0)) < 0){ OUT("Failed to create socket!"); }

	int broadcast = 1;
	if(setsockopt(sock,SOL_SOCKET,SO_BROADCAST,&broadcast,sizeof(broadcast)) < 0){ perror("Fail");OUT("Failed to set socket options"); }

	len = sizeof(struct sockaddr_in);

	//Setup the recv
	memset(&_recv, 0, sizeof(struct sockaddr_in));
	_recv.sin_family 		= AF_INET;
	_recv.sin_addr.s_addr 	= INADDR_ANY;
	_recv.sin_port 			= htons(PORT);

	//Setup the send
	memset(&_send, 0, sizeof(struct sockaddr_in));
	_send.sin_family 		= AF_INET;
	_send.sin_addr.s_addr 	= inet_addr(argv[1]);
	_send.sin_port 			= htons(PORT);

	//threads;
	pthread_t sig3s, sig10s, print;
	pthread_create(&sig3s, 0, child3sHandler, 0);
	pthread_create(&sig10s, 0, child10sHandler, 0);
	pthread_create(&print, 0, childPrintHandler, 0);

	if(bind(sock, (struct sockaddr*) &_recv, sizeof _recv) < 0){ OUT("Failed to bind!"); }

	time_t t;
	//int i = 1;
	char msg[20];
	while(1){
		strcpy(msg," ");
		recvfrom(sock, msg, sizeof(msg), 0, (struct sockaddr *)&_recv, &len);
		
		t = time(NULL);
		struct tm dt = *localtime(&t);

		if(strcmp(msg, TIME) == 0){
			sprintf(msg, "%s %02d:%02d:%02d", "TIME", dt.tm_hour, dt.tm_min, dt.tm_sec);
			sendto(sock, msg, strlen(msg), 0, (struct sockaddr *)&_recv, len);	
			continue;		
		}else if(strcmp(msg, DATE) == 0){
			sprintf(msg, "%s %02d:%02d:%04d", "DATE", dt.tm_mday, dt.tm_mon+1, dt.tm_year+1900);				
			sendto(sock, msg, strlen(msg), 0, (struct sockaddr *)&_recv, len);		
			continue;	
		}

		if(validateAnswer(msg) == true){
			//if(i < 0){ continue; } else {i--;}
   			pthread_mutex_lock(&mtx);
			list = updateRecv(list, ntohl(_recv.sin_addr.s_addr), msg);			
			pthread_mutex_unlock(&mtx);		
			continue;
		}

		printf("From: "); printIP(ntohl(_recv.sin_addr.s_addr));
		printf("\t\tMessage: %s\n", msg);
	}

	return 0;
}
