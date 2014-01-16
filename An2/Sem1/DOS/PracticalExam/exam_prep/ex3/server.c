/* 
	Multiple clients send at once a file name and the server will
	read all lines from the file and will return the longest line 
*/
#include <stdlib.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netinet/ip.h>
#include <string.h>
#include <pthread.h>
#include "Message.h"

char longestLine[1024];
pthread_mutex_t mutex;

void handleClient(void *socketPointer) {
	int sock = (int)socketPointer;
	int error;
	char buffer[1024];
	char line[1024];
	struct Message receivedMessage, sentMessage;
	FILE *file;
	error = recv(sock, buffer, sizeof(buffer), 0);
	if (error < 0) {
		perror("Failed to receive");
		exit(1);
	}
	memcpy(&receivedMessage, buffer, sizeof(receivedMessage));
	
	file = fopen(receivedMessage.content, "r");
	while (fgets(line, sizeof(line), file)) {
		pthread_mutex_lock(&mutex);
		if (strlen(line) > strlen(longestLine)) {
			strcpy(longestLine, line);
		}
		strcpy(sentMessage.content, longestLine);
		pthread_mutex_unlock(&mutex);
	}
	fclose(file);
	memcpy(buffer, &sentMessage, sizeof(sentMessage));
	error = send(sock, buffer, sizeof(buffer), 0);
	if (error < 0) {
		perror("Failed to send");
		exit(1);
	}
	close(sock);
}

int main() {
	//rv socket
	//metset
	//server address, family, addr_s, port
	//bind
	//listen
	//accept
	//receiv
	//send
	//close sock
	//close rv sock
	int port, error, rvsock, sock, currentWorker;
	struct sockaddr_in serverAddress, clientAddress;
	unsigned int addrSize = sizeof(struct sockaddr_in);
	pthread_t workers[100];
	strcpy(longestLine, "");
	pthread_mutex_init(&mutex, NULL);

	printf("Port: ");
	scanf("%d", &port);
	rvsock = socket(AF_INET, SOCK_STREAM, 0);
	if (rvsock < 0) {
		perror("Error while creating rvsock");
		exit(1);
	}	
	memset(&serverAddress, 0, sizeof(serverAddress));	
	serverAddress.sin_family = AF_INET;
	serverAddress.sin_addr.s_addr = INADDR_ANY;
	serverAddress.sin_port = htons(port);
	error = bind(rvsock, (struct sockaddr *) &serverAddress, sizeof(serverAddress));
	if (error < 0) {
		perror("Error while binding");
		exit(1);
	}
	error = listen(rvsock, 10);
	if (error < 0) {
		perror("Error while listening");
		exit(1);
	}
	currentWorker = 0;
	while (1) {
		sock = accept(rvsock, (struct sockaddr*) &clientAddress, &addrSize);
		if (sock < 0) {
			perror("Error while accepting");
			exit(1);
		}	 
		pthread_create(&workers[currentWorker], 0, ((void *)handleClient), ((void *)sock));		   
		
		currentWorker++;
		
	}
	for(currentWorker = 0; currentWorker < 100; currentWorker++) {
		pthread_join(workers[currentWorker], 0);
	}
	pthread_mutex_destroy(&mutex);
	close(rvsock);	
	return 0;
}
