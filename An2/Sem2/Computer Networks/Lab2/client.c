#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <signal.h>
#include <string.h>
#include <netdb.h>
#include <unistd.h>
#include <netdb.h>
#include <fcntl.h>

int fileDescriptor, clientSock, CLTARG;
char* buffer;

void timeout(int signal){
    close(fileDescriptor);
    close(clientSock);
    free(buffer);
    exit(0);
}


int main(int argc, const char* argv[]){
    if (argc != 2){
        return -1;
    }

    CLTARG = atoi(argv[1]);

    struct sockaddr_in serverAddr;
    memset(&serverAddr, 0, sizeof(struct sockaddr_in));

    if ((clientSock = socket(AF_INET, SOCK_DGRAM, 0)) < 0){
        printf("Failed to create socket!\n");
        return -2;
    }

    struct hostent *host;
    host = gethostbyname("localhost");

    if (!host){
        printf("Could not obtain host!\n");
        return -2;
    }

    memcpy(&serverAddr.sin_addr, host->h_addr_list[0], host->h_length);
    serverAddr.sin_family = AF_INET;
    serverAddr.sin_port = htons(1392);

    char message[256] = "picture.jpg";

    sendto(clientSock, &message, 256, 0, (struct sockaddr*) &serverAddr, (socklen_t)sizeof(struct sockaddr_in));

    buffer = (char*) malloc(CLTARG);

    memset(&serverAddr, 0, sizeof(struct sockaddr_in));
    socklen_t sockLen = sizeof(struct sockaddr_in);

    char fileName[256] = "newpicture.jpg";
    fileDescriptor = open(fileName, O_CREAT|O_APPEND|O_WRONLY);

    signal(SIGALRM, timeout);

    alarm(10);

    while (1){
        alarm(10);
        size_t receivedBytes = recvfrom(clientSock, buffer, CLTARG, 0, (struct sockaddr*) &serverAddr, &sockLen);
        alarm(0);

        write(fileDescriptor, buffer, receivedBytes);
        memset(buffer, 0, CLTARG);
    }


    return 0;
}
