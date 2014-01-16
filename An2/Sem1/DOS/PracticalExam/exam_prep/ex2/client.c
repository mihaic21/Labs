#include <stdio.h>
#include "Number.h"
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <string.h>
#include <stdlib.h>

int main() {
	int port, error, sock;
	struct Number sentNumber, receivedNumber;
	struct sockaddr_in serverAddress, clientAddress;
	char buffer[2024];
	printf("Enter port: ");
	scanf("%d", &port);
	sock = socket(AF_INET, SOCK_STREAM, 0);	
	if (sock < 0) {
		perror("Failed to create socket");
		exit(1);
	}
	memset(&serverAddress, 0, sizeof(serverAddress));
	serverAddress.sin_family = AF_INET;
	serverAddress.sin_addr.s_addr = inet_addr("127.0.0.1");
	serverAddress.sin_port = htons(port);
	error = connect(sock, (struct sockaddr *)&serverAddress, sizeof(serverAddress));
	if (error < 0) {
		perror("Error while connecting to server");
		exit(1);
	}	
	printf("Enter number you want to send:");
	scanf("%d", &sentNumber.value);
	sentNumber.value = htons(sentNumber.value);
	memcpy(&buffer, &sentNumber, sizeof(sentNumber));
	error = send(sock, buffer, sizeof(buffer), 0);
	if (error < 0) {
		perror("Error whiel sending");
		exit(1);
	}
	error = recv(sock, buffer, sizeof(buffer), 0);
	if (error < 0) {
		perror("error while receving");
		exit(1);
	}
	memcpy(&receivedNumber, &buffer, sizeof(buffer));
	receivedNumber.value = ntohs(receivedNumber.value);
	printf("Received number: %d", receivedNumber.value);
	close(sock);
	//create sock
	//memset serveraddress
	//serveraddress setup
	//connect
	//send 
	//recv
	//close socket
	return 0;
}
