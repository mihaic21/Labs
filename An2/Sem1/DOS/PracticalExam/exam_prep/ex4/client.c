#include <stdlib.h>
#include <stdio.h>
#include <netinet/ip.h>
#include <netinet/in.h>
#include <sys/types.h>
#include <sys/socket.h>
#include "Message.h"
#include <string.h>
#include <arpa/inet.h>
#include <unistd.h>


int main() {
	int port, sock, error, receivedNumber;
	struct Message sentMessage;
	struct sockaddr_in serverAddress;
	char buffer[1024];
	printf("Enter port");
	scanf("%d", &port);
	sock = socket(AF_INET, SOCK_STREAM, 0);
	if (sock < 0) {
		perror("Error while creating socket");
		exit(1);
	}
	serverAddress.sin_family = AF_INET;
	serverAddress.sin_addr.s_addr = inet_addr("127.0.0.1");
	serverAddress.sin_port = htons(port);
	error = connect(sock, (struct sockaddr*)&serverAddress, sizeof(serverAddress));
	if (error < 0) {
		perror("Error while connecting");
		exit(1);
	}
	printf("Enter number: ");
	scanf("%d", &sentMessage.number);
	printf("Enter operation 0 fo substraction, 1 for addition");
	scanf("%d", &sentMessage.operation);
	sentMessage.number = htons(sentMessage.number);
	sentMessage.operation = htons(sentMessage.operation);
	memcpy(buffer, &sentMessage, sizeof(sentMessage));
	error = send(sock, buffer, sizeof(buffer), 0);
	if (error < 0) {
		perror("Error while sending");
		exit(1);
	}
	error = recv(sock, buffer, sizeof(buffer), 0);
	if (error < 0) {
		perror("Error while receiving");
		exit(1);
	}
	memcpy(&receivedNumber, buffer, sizeof(receivedNumber));
	receivedNumber = ntohs(receivedNumber);
	printf("%d", receivedNumber);
	close(sock);
	return 0;
}
