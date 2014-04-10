#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <fcntl.h>
#include <string.h>

int SRVARG;
int serverSock;
struct sockaddr_in clientAddr;


void handleClient(char message[256]){
    int fileDescriptor = open(message, O_RDONLY);
    char* buffer = (char*) malloc (SRVARG);

    size_t bytesRead = 0;

    while ((bytesRead = read(fileDescriptor, buffer, SRVARG)) > 0){
        sendto(serverSock, buffer, bytesRead, 0, (struct sockaddr *)&clientAddr, (socklen_t) sizeof(struct sockaddr_in));
        memset(buffer, 0, SRVARG);
        usleep(15000);
    }

    free(buffer);
    close(fileDescriptor);

    exit(0);
}

int main(int argc, const char* argv[]){
    
    if (argc != 2){
        return -1;
    }

    SRVARG = atoi(argv[1]);

    struct sockaddr_in serverAddr;
    
    memset(&serverAddr, 0, sizeof(struct sockaddr_in));
    memset(&clientAddr, 0, sizeof(struct sockaddr_in));

    serverAddr.sin_family = AF_INET;
    serverAddr.sin_addr.s_addr = INADDR_ANY;
    serverAddr.sin_port = htons (1392);

    if ((serverSock = socket(AF_INET, SOCK_DGRAM, 0)) < 0){
        printf("Failed to create socket!\n");
        return -2;
    }

    if (bind(serverSock, (struct sockaddr *) &serverAddr, (socklen_t) sizeof(struct sockaddr_in)) < 0){
        printf("Failed to bind!\n");
        return -2;
    }

    while (1){
        struct sockaddr_in newClientAddr;
        memset(&newClientAddr, 0, sizeof(struct sockaddr_in));
        size_t recvLen;
        char message[256];
        socklen_t len = (socklen_t) sizeof (struct sockaddr_in);

        recvLen = recvfrom(serverSock, &message, 256, 0, (struct sockaddr *) &newClientAddr, &len);
        if (recvLen > 0){
            clientAddr = newClientAddr;
            if (fork() == 0){
                handleClient(message);
            }
        }
    }

    return 0;
}
