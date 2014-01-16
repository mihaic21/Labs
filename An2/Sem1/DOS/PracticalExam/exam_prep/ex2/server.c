/**
	Problem statement:
	Multiple clients will send numbers to the server
	The server will return the maximum number received until then
	The clients will be handled using workers
*/
#include <stdlib.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include "Number.h"
#include <string.h>
#include <pthread.h>


struct Number maxNumber;
pthread_rwlock_t maxNumberLock;

void handleClient(void *sockPointer) {
	int sock = (int)sockPointer;
	struct Number receivedNumber, sentNumber;	
	int error;
	char buffer[2024];
	error = recv(sock, &buffer, sizeof(buffer), 0);
	if (error < 0) {
		perror("Error while receiving");
		exit(1);
	}
	memcpy(&receivedNumber, buffer, sizeof(receivedNumber));
	receivedNumber.value = ntohs(receivedNumber.value);
	pthread_rwlock_rdlock(&maxNumberLock);
	if (receivedNumber.value > maxNumber.value) {
		pthread_rwlock_unlock(&maxNumberLock);
		pthread_rwlock_wrlock(&maxNumberLock);
		maxNumber.value = receivedNumber.value;
	}
	pthread_rwlock_unlock(&maxNumberLock);
	sentNumber.value = htons(maxNumber.value);
	memcpy(&buffer, &sentNumber, sizeof(sentNumber));
	error = send(sock, &buffer, sizeof(buffer), 0);
	if (error < 0) {
		perror("Error while sending");
		exit(1);
	}
	close(sock);
}
int main() {
	//rvsocket
	//memset serverAddress
	//serverAddress setup family, address port
	//bind
	//listen
	//sock = accept
	//handle request
	//close sock
	//close rvsock
	pthread_rwlock_init(&maxNumberLock, NULL);
	struct sockaddr_in serverAddress, clientAddress;
	int port, sock, rvsocket, error, currentWorker, counter;
	unsigned int addrSize = sizeof(struct sockaddr_in);
	pthread_t workers[100];
	printf("Enter port: ");
	scanf("%d", &port);
	rvsocket = socket(AF_INET, SOCK_STREAM, 0);
	
	if (rvsocket < 0) {
		perror("Error while creating rvsocket");
		exit(1);
	}
	memset(&serverAddress, 0, sizeof(serverAddress));
	serverAddress.sin_family = AF_INET;
	serverAddress.sin_addr.s_addr = INADDR_ANY;
	serverAddress.sin_port = htons(port);
	error = bind(rvsocket, (struct sockaddr *) &serverAddress, sizeof(serverAddress));
	if (error < 0) {
		perror("Error while binding");
		exit(1);
	}
	error = listen(rvsocket, 10);
	if (error < 0) {
		perror("Error while listening");
		exit(1);
	}
	currentWorker = 0;
	while(1) {
		sock = accept(rvsocket, (struct sockaddr *) &clientAddress, &addrSize);
		if (sock < 0) {
			perror("Error while acepting");
			exit(1);
		}
		pthread_create(&workers[currentWorker], NULL,((void *) handleClient), ((int *) sock));	
		currentWorker++;
		sleep(20);
	}
	close(rvsocket);
	pthread_rwlock_destroy(&maxNumberLock);
	for(counter = 0; counter < 100; counter++) {
		pthread_join(workers[counter], NULL);
	}
	//listen
	//sock = accept
	//handle sock
	//close sock
	//close rvsock	
	return 0;
}
