#include <sys/socket.h>
#include <netinet/in.h>
#include <stdio.h>
#include <stdint.h>
#include <signal.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <arpa/inet.h>

int c;


void time_out(int semnal) {
  int32_t r = -1;
  r = htonl(r);
  printf("Time out.\n");
  send(c, &r, sizeof(int32_t), 0);
  close(c);
  exit(1);
}


void handle(){
	
	int cod;
	int32_t noOfDiv = 0;
	int32_t div[50];
	int i,k;
	
	//uint8_t b;
	int32_t number;
  
	//struct sockaddr_in server;

	if (c < 0) {
   		fprintf(stderr, "Error in establishing the connection with the client.\n");
 		exit(1);
 	} else
    		printf("A new client has successfully connected.\n");

	signal(SIGALRM, time_out);
	alarm(15);
	
	cod = recv(c, &number, 1, 0);
	if (cod != 1){
		printf("Didn't manage to read number\n");
	} else {
		
		if (number <= 0){
			noOfDiv = -2;	
		}else if (number == 1){
            noOfDiv = 1;
            div[0]=1;
        }
        else {
			
			noOfDiv = 2;
			int32_t k=1,i;
			div[0]=1;
			for (i=2;i<=number/2;i++){
      				if (number%i==0){
			        noOfDiv++;
			        div[k]=i;
			        k++;
      				}
    			}
			div[k] = number;
		}
	}

	//start sending result
	cod = send(c, &noOfDiv, sizeof(int32_t), 0);
  	if (cod<=0){
    		printf("Send error");
  	}
	
	for (i=0; i < noOfDiv; i++){
		k = send(c,&div[i],sizeof(int),0);
		if (k <= 0){
			printf("Send error\n");
		}
	}

	close(c);

	if (noOfDiv >= 0)
   		printf("The connection was successfully closed.\n");
  	else {
    		printf("There was an error when closing the connection. Error code %d.\n", noOfDiv);
    		exit(1);
  	}
    
	exit(0);
}


int main(){

    struct sockaddr_in client, server;
    int sock;
    socklen_t l;

    sock = socket(PF_INET, SOCK_STREAM, 0);

    if (sock < 0){
        printf("Error at creating socket\n");
        return 1;
    }

    server.sin_family = AF_INET;
    server.sin_port = htons(1392);
    server.sin_addr.s_addr = INADDR_ANY;

    if (bind(sock, (struct sockaddr *) &server, sizeof(struct sockaddr_in)) < 0){
        printf("Error at binding. Port already in use\n");
        return 1;
    }

    if (listen(sock, 5) < 0){
        printf("Error at listening\n");
        return 1;
    }

    while (1){

        memset(&server, 0, sizeof(struct sockaddr_in));
	l = sizeof(client);
	printf("Waiting for a client to connect\n");
	c = accept(sock, (struct sockaddr *) &client, &l);
	printf("The client having the address %s and the port %d is now connected.\n", inet_ntoa(client.sin_addr), ntohs(client.sin_port));
	
	if (fork() == 0){
		handle();
	}

    }

    return 0;
}
