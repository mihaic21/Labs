#include <stdlib.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <stdio.h>
#include "Message.h"
#include <netinet/in.h>
#include <string.h>

int main() {
	int port, sock, error;
	struct sockaddr_in serverAddress, clientAddress;
	char buffer[2024];
	struct Message sentMessage, receivedMessage;
	printf("Enter port: ");
	scanf("%d", &port);
	sock = socket(AF_INET, SOCK_STREAM, 0);
	if (sock < 0) {
		perror("Failed to create socket");
		exit(1);
	}	
	memset(&serverAddress, 0, sizeof(struct sockaddr_in));
	serverAddress.sin_family = AF_INET;
	serverAddress.sin_addr.s_addr = inet_addr("127.0.0.1");
	serverAddress.sin_port = htons(port);
	error = connect(sock, (struct sockaddr*) &serverAddress, sizeof(struct sockaddr_in));
	if (error < 0) {
		perror("Error while connecting to the server");
		exit(1);
	}
	while(1) {
		printf("Enter message: ");
		scanf("%s", sentMessage.content);
		memcpy(buffer, &sentMessage, sizeof(sentMessage));
		error = send(sock, &buffer, sizeof(buffer), 0);
		if (error < 0) {
			perror("Error while sending the message");
			exit(1);
		}
		error = recv(sock, &buffer, sizeof(buffer), 0);
		if (error < 0) {
			perror("Error while receiving message");
			exit(1);	
		}
		memcpy(&sentMessage, buffer, sizeof(buffer));
		printf("Received: %s\n", sentMessage.content); 
	}
	close(sock);
	return 0;
}
