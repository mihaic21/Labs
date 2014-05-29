#include <iostream>
#include <stdlib.h>
#include <unistd.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <term.h>
#include <errno.h>
#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include <stddef.h>

#include <vector>

char *NBCAST;
pthread_t _listenThread;
pthread_t _timequeryThread;
pthread_t _datequeryThread;

typedef struct {
    struct sockaddr_in addr;
    int numberOfFailedTries;
    char lastDate[20];
    char lastTime[20];
    
} Peer;

std::vector<Peer *> _peers;
std::vector<std::string> _malformedData;

void clearScreen() {
    if (!cur_term) {
        int result;
        setupterm(NULL, STDOUT_FILENO, &result);
        if (result <= 0) {
            return;
        }
    }
    
    char clear[6];
    strcpy(clear, "clear");
    
    putp(tigetstr(clear));
}

void printLists() {
    clearScreen();
    
    for (int it=0; it<_peers.size(); it++) {
        std::cout << inet_ntoa(_peers[it]->addr.sin_addr) << " - " << _peers[it]->lastTime << " " << _peers[it]->lastDate << std::endl;
    }
    
    for (int it=0; it<_malformedData.size(); it++) {
        std::cout << _malformedData[it] << std::endl;
    }
}

void checkPeers() {
    for (std::vector<Peer *>::iterator it = _peers.begin(); it != _peers.end(); ++it) {
        Peer *peer = *it;
        
        if (peer->numberOfFailedTries >= 3) {
            _peers.erase(it);
        }
    }
}

void *listenThreadFunction(void *args) {
    int *argPtr = (int *)args;
    int listenSock = *argPtr;
    
    char *buffer = (char *)malloc(20 * sizeof(char));
    
    while (1) {
        struct sockaddr_in clientAddr;
        memset(&clientAddr, 0, sizeof(struct sockaddr_in));
        socklen_t len = (socklen_t)sizeof(struct sockaddr_in);
        
        if (recvfrom(listenSock, buffer, 20 * sizeof(char), 0, (struct sockaddr *)&clientAddr, &len) > 0) {
            if (!strcmp(buffer, "TIMEQUERY")) {
                // TIMEQUERY response
                
                char *currentTime = (char *)malloc(20 * sizeof(char));
                time_t t = time(0);
                struct tm *now = localtime(&t);
                sprintf(currentTime, "TIME %d:%d:%d", now->tm_hour, now->tm_min, now->tm_sec);
                
                memset(buffer, 0, 20 * sizeof(char));
                if (sendto(listenSock, currentTime, 20 * sizeof(char), 0, (struct sockaddr *)&clientAddr, len) < 0) {
                    printf("Failed to send TIMEQUERY response!\n");
                }
                
                for (int it=0; it<_peers.size(); it++) {
                    _peers[it]->numberOfFailedTries++;
                }
                
            } else if (!strcmp(buffer, "DATEQUERY")) {
                // DATEQUERY response

                char *currentTime = (char *)malloc(20 * sizeof(char));
                time_t t = time(0);
                struct tm *now = localtime(&t);
                sprintf(currentTime, "DATE %d:%d:%d", now->tm_mday, now->tm_mon, 1900 + now->tm_year);
                
                memset(buffer, 0, 20 * sizeof(char));
                if (sendto(listenSock, currentTime, 20 * sizeof(char), 0, (struct sockaddr *)&clientAddr, len) < 0) {
                    printf("Failed to send DATEQUERY response!\n");
                }
                
                for (int it=0; it<_peers.size(); it++) {
                    _peers[it]->numberOfFailedTries++;
                }
                
                
            
            } else {
                // Query responses
                char *type = (char *)malloc(5);
                for (int it=0; it<4; it++) {
                    type[it] = buffer[it];
                }
                
                type[4] = '\0';
                
                Peer *peer = NULL;
                bool newPeer = true;
                
                for (int it=0; it<_peers.size(); it++) {
                    char *localPeer = inet_ntoa(_peers[it]->addr.sin_addr);
                    char *currentPeer = inet_ntoa(clientAddr.sin_addr);
                    if (!strcmp(localPeer, currentPeer)) {
                        peer = _peers[it];
                        newPeer = false;
                        break;
                    }
                }
                
                if (newPeer) {
                    peer = (Peer *)malloc(sizeof(Peer));
                    peer->numberOfFailedTries = 0;
                    peer->addr = clientAddr;
                }
                
                if (!strcmp(type, "TIME")) {
                    strcpy(peer->lastTime, buffer);
                } else if (!strcmp(type, "DATE")) {
                    strcpy(peer->lastDate, buffer);
                } else {
                    // Malformed response
                    std::string message;
                    message.append(inet_ntoa(clientAddr.sin_addr));
                    message.append(" - ");
                    message.append(buffer);
                }
                
                if (newPeer) {
                    _peers.push_back(peer);
                } else {
                    peer->numberOfFailedTries--;
                }
                
                checkPeers();
                printLists();
                
                memset(buffer, 0, 20 * sizeof(char));
            }
        }
    }
    
    return NULL;
}

void *timequeryThreadFunction(void *args) {
    int *argPtr = (int *)args;
    int broadcastSock = *argPtr;
    
    struct sockaddr_in broadcastAddr;
    broadcastAddr.sin_family = AF_INET;
    broadcastAddr.sin_addr.s_addr = inet_addr(NBCAST);
    broadcastAddr.sin_port = htons(7777);
    
    char timequeryString[20];
    strcpy(timequeryString, "TIMEQUERY");
    
    while (1) {
        if (sendto(broadcastSock, timequeryString, 20 * sizeof(char), 0, (struct sockaddr *)&broadcastAddr, (socklen_t)sizeof(struct sockaddr_in)) < 0) {
            printf("Failed to send!\n");
        }
        
        sleep(3);
    }
    
    return NULL;
}

void *datequeryThreadFunction(void *args) {
    int *argPtr = (int *)args;
    int broadcastSock = *argPtr;
    
    struct sockaddr_in broadcastAddr;
    broadcastAddr.sin_family = AF_INET;
    broadcastAddr.sin_addr.s_addr = inet_addr(NBCAST);
    broadcastAddr.sin_port = htons(7777);
    
    char datequeryString[20];
    strcpy(datequeryString, "DATEQUERY");
    
    while (1) {
        if (sendto(broadcastSock, datequeryString, 20 * sizeof(char), 0, (struct sockaddr *)&broadcastAddr, (socklen_t)sizeof(struct sockaddr_in)) < 0) {
            printf("Failed to send!\n");
        }
        
        sleep(10);
    }
    
    return NULL;
}

int main(int argc, const char * argv[])
{
    if (argc != 2) {
        return -1;
    }
    
    int broadcastSock;
    
    NBCAST = (char *)malloc(strlen(argv[1]));
    strcpy(NBCAST, argv[1]);
    
    struct sockaddr_in listenAddr;
    memset(&listenAddr, 0, sizeof(struct sockaddr_in));
    
    listenAddr.sin_family = AF_INET;
    listenAddr.sin_addr.s_addr = INADDR_ANY;
    listenAddr.sin_port = htons(7777);
    
    if ((broadcastSock = socket(AF_INET, SOCK_DGRAM, 0)) < 0) {
        printf("Failed to create socket!\n");
        return errno;
    }
    
    int broadcast = true;
    
    if (setsockopt(broadcastSock, SOL_SOCKET, SO_BROADCAST, &broadcast, sizeof(broadcast)) < 0) {
        printf("Failed to set broadcast option!\n");
        return errno;
    }
    
    if (bind(broadcastSock, (struct sockaddr *)&listenAddr, (socklen_t)sizeof(struct sockaddr_in)) < 0) {
        printf("Failed to bind!\n");
        return errno;
    }
    
    // Listen for connections on a different thread
    pthread_create(&_listenThread, NULL, listenThreadFunction, (void *)&broadcastSock);
    
    // 3 second timequery message loop
    pthread_create(&_timequeryThread, NULL, timequeryThreadFunction, (void *)&broadcastSock);
    
    // 10 second datequery message loop
    pthread_create(&_datequeryThread, NULL, datequeryThreadFunction, (void *)&broadcastSock);
    
    // Block this thread
    pthread_mutex_t mutex;
    pthread_mutex_init(&mutex, NULL);
    
    pthread_mutex_lock(&mutex);
    pthread_mutex_lock(&mutex);
    
    return 0;
}
