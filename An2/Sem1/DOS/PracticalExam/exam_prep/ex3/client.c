#include <stdlib.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/ip.h>
#include <netinet/in.h>
#include <string.h>
#include "Message.h"

int main() {
	int port, sock, error;
	struct sockaddr_in serverAddress, clientAddress;
	struct Message receivedMessage, sentMessage;
	char buffer[1024];
	printf("Enter port: ");
	scanf("%d", &port);
	sock = socket(AF_INET, SOCK_STREAM, 0);
	if (sock < 0) {
		perror("Error while creating socket");
		exit(1);
	}
	memset(&serverAddress, 0, sizeof(serverAddress));
	serverAddress.sin_family = AF_INET;
	serverAddress.sin_addr.s_addr = inet_addr("127.0.0.1");
	serverAddress.sin_port = htons(port);	
	error = connect(sock, (struct sockaddr *)&serverAddress, sizeof(serverAddress));
	if (error < 0) {
		perror("Error while connecting");
		exit(1);
	}
	printf("Enter file name: ");
	scanf("%s", sentMessage.content);
	memcpy(buffer, &sentMessage, sizeof(sentMessage));
	error = send(sock, &buffer, sizeof(buffer), 0);
	if (error < 0) {
		perror("Error while sending");
		exit(1);
	}
	error = recv(sock, &buffer, sizeof(buffer), 0);
	if (error < 0) {
		perror("Error while receiving");
		exit(1);
	}
	memcpy(&receivedMessage, buffer, sizeof(receivedMessage));
	printf("Received %s\n", receivedMessage.content);
	return 0;
}
