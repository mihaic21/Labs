#include <sys/socket.h>
#include <netinet/in.h>
#include <stdio.h>
#include <string.h>
#include <stdint.h>
#include <unistd.h>
#include <stdlib.h>
#include <signal.h>
#include <arpa/inet.h>

int c;

void time_out(int signal){
	printf("Time-out\n");
	close(c);
	exit(1);
}


int main() {
	
	int cod, number;
	int32_t result;
	struct sockaddr_in server;
	
	c = socket(PF_INET, SOCK_STREAM, 0);
	if (c < 0) {
		printf("Error creating the client's socket.\n");
		return 1;
	}

	memset(&server, 0, sizeof(struct sockaddr_in));
	server.sin_family = AF_INET;
	server.sin_port = htons(1392);
	server.sin_addr.s_addr = inet_addr("127.0.0.1");

	cod = connect(c, (struct sockaddr *) &server, sizeof(struct sockaddr_in));

	if (cod < 0) {
		printf("Error connecting to the server.\n");
		return 1;
	}

	printf("Give an integer number: ");
	scanf("%d",&number);

    if ((number < 1) || (number > 255)) {
        printf("Number not valid!\n");
        return 1;
    }

	//start sending data to server	
	cod = send(c, &number, sizeof(int), 0);
	
	if (cod != sizeof(int)) {
		printf("Error sending data to the server\n");
		return 1;
	}
	
	//start receiving the output
	signal(SIGALRM, time_out);
	alarm(15);

	cod = recv(c, &result, sizeof(int32_t), 0);
	alarm(15);
	
	if (result >= 0){
		int i,x;
		int k = result;
		printf("Number of divisors %d\n",result);
		printf("List of divisors: ");
		for (i=0;i<result;i++){
        		cod = recv(c, &x, sizeof(int),0);
        		alarm(15);
        		if (k<=0){
				printf ("receive error");
			}
        		printf("%d ",x);
      		}
      		printf("\n");
	} else if (result == -1) {
		printf("Time out!\n");
	}
	else {
		printf("Number format exception\n");
	}

	if (cod != sizeof(int)){
		printf("Error receiving data from server\n");
		return 1;
	}	

	close(c);
	return 0;
}
