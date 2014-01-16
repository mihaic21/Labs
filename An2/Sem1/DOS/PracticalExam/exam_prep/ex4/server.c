/**
	Problem statement:
	Server holds a number which is initialized with 0
	Each client can substract or can add a number sent to the total sum
	The server must handle multiple clients using workers
*/
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <netinet/ip.h>
#include <netinet/in.h>
#include <sys/types.h>
#include <sys/socket.h>
#include "Message.h"

int currentValue;
pthread_mutex_t mutex;

#define SUBSTRACT  0
#define ADD  1;

void handleClient(void *socketPointer) {
	int sock = (int) socketPointer;
	int error, sentNumber;
	char buffer[1024];
	struct	Message receivedMessage;
	error = recv(sock, buffer, sizeof(buffer), 0);
	if (error < 0) {
		perror("Error while receiving");
		exit(1);
	} 
	memcpy(&receivedMessage, buffer, sizeof(receivedMessage));
	receivedMessage.number = ntohs(receivedMessage.number);
	receivedMessage.operation = ntohs(receivedMessage.operation);
	pthread_mutex_lock(&mutex);
	if (receivedMessage.operation == SUBSTRACT) {
		currentValue -= receivedMessage.number;
	} else {
		currentValue += receivedMessage.number;	
	}
	sentNumber = currentValue;
	sentNumber = htons(sentNumber);
	pthread_mutex_unlock(&mutex);
	memcpy(buffer, &sentNumber, sizeof(sentNumber));
	error = send(sock, buffer, sizeof(buffer), 0);
	if (error < 0) {
		perror("Error while sending");
		exit(1);
	}
	close(sock);	
}

int main() {
	//rv socket
	//memset serverAddress
	//serverAddress sinfamily, s_addr, port
	//bind
	//listen
	//memset client
	//accept
	//recv
	//send
	//close sock
	//close rv sock
	int port, sock, rvsock, currentWorker, error;
	pthread_t workers[100];
	struct sockaddr_in serverAddress, clientAddress;
	unsigned int addrSize;	
	currentValue = 0;
	pthread_mutex_init(&mutex, NULL);
	rvsock = socket(AF_INET, SOCK_STREAM, 0);
	printf("Enter port: ");
	currentValue = 0;
	scanf("%d", &port);
	if (rvsock < 0) {
		perror("Error while creating rv socket");
		exit(1);	
	}	
	memset(&serverAddress, 0, sizeof(serverAddress));
	serverAddress.sin_family = AF_INET;
	serverAddress.sin_addr.s_addr = INADDR_ANY;
	serverAddress.sin_port = htons(port);
	error = bind(rvsock, (struct sockaddr *)&serverAddress, sizeof(serverAddress));
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
		memset(&clientAddress, 0, sizeof(clientAddress));
		sock = accept(rvsock, (struct sockaddr *)&clientAddress, &addrSize);
		if (sock < 0) {
			perror("Error at accept");
			exit(1);
		}
		pthread_create(&workers[currentWorker], 0, ((void *)handleClient), ((void *)sock));
		currentWorker++;

	}
	for(currentWorker = 0; currentWorker < 100; currentWorker++) {
		pthread_join(&workers[currentWorker], NULL);
	}
	close(rvsock);
	pthread_mutex_destroy(mutex);
	return 0;
}
