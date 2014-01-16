#include <sys/types.h>
#include <sys/socket.h>
#include <stdio.h>
#include <netinet/in.h>
#include <netinet/ip.h>
#include <string.h>

int sock, nofStr, port;
// nofStr - number of entries stored
struct sockaddr_in server;
char done[4];
char msg[50];

int main(){
	
	printf("Give port:\n");
	scanf("%d", &port);	
	
	sock = socket(AF_INET, SOCK_STREAM, 0);
	if (sock < 0)
	{printf("Error on create socket");
		return 1;}
	
	

	memset(&server, 0, sizeof(server));
	server.sin_family = AF_INET;
	server.sin_addr.s_addr = inet_addr("127.0.0.1");
	server.sin_port = htons(port); // the port
	
	
	// try to connect to server
	if(connect(sock, (struct sockaddr*)&server, sizeof(struct sockaddr_in))<0){
		printf("Error on connection\n");
		return 1;}
		
	printf("Give the message: ");
	scanf("%s", msg);
	
	// send msg to server
	
	send(sock, &msg, sizeof(msg), 0);
	
	//receive data from server
	
	
	recv(sock, &nofStr,sizeof(nofStr),0);
	recv(sock, &done,sizeof(done),0);
	
	nofStr=ntohl(nofStr);
	
	
	printf("Number of logs: %d \n",nofStr);
	printf("%s \n",done);
	
	close(sock);
	}
