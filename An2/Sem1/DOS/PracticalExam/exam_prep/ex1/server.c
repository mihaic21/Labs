/**
	Problem statement
	Implement a chat which can be used by a server and a single client
*/
#include <stdlib.h>
#include <netinet/in.h>
#include <netinet/ip.h>
#include <sys/types.h>
#include <sys/socket.h>
#include "Message.h"
#include <string.h>
#include <stdio.h>
int main() {
	struct sockaddr_in serverAddress, clientAddress;
	int rvsocket, port, sock, error;
	unsigned int sockaddrInSize = sizeof(struct sockaddr_in);
	struct Message receivedMessage, sentMessage;
	printf("Enter port:");
	scanf("%d", &port);
	rvsocket = socket(AF_INET, SOCK_STREAM, 0);
	char buffer[2024];
	if (rvsocket < 0) {
		perror("Rv socket could not be created");
		exit(1);
	}
	memset(&serverAddress, 0, sizeof(serverAddress));
	serverAddress.sin_family = AF_INET;
	serverAddress.sin_addr.s_addr = INADDR_ANY;
	serverAddress.sin_port = htons(port);
	error = bind(rvsocket,
			(struct sockaddr *)&serverAddress,
			sockaddrInSize
		);
	if (error < 0) {
		perror("Error when binding");
		exit(1);
	}
	error = listen(rvsocket, 10);
	if (error < 0) {
		perror("Error when listening");
		exit(1);
	};
		sock = accept(rvsocket, (struct sockaddr *)&serverAddress, &sockaddrInSize);
		if (sock < 0) {
			perror("Falied to accept");
			exit(1);
		}
	while(1){
		error = recv(sock, &buffer, sizeof(buffer), 0); 
		if (error < 0) {
			perror("Error when receiving");
			exit(1);
		}
		memcpy(&receivedMessage, buffer, sizeof(receivedMessage));
		printf("Received: %s\n", receivedMessage.content); 
		printf("Enter message to send:");
		scanf("%s", sentMessage.content);
		memcpy(&buffer, &sentMessage, sizeof(buffer));
		error = send(sock, &buffer, sizeof(buffer), 0);
		if (error < 0) {
			perror("Error while sending");
			exit(1);
		}
	}
	close(sock);
	close(rvsocket);
	return 0;
}
