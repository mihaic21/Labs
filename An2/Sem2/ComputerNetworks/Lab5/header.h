#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <string.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <arpa/inet.h>
#include <errno.h>
#include <pthread.h>
#include <set>
#include <iostream>

#define PORT 7777
#define VERSION "CHATv1"
#define MAXLEN 1024


#pragma pack(1)
struct msg{
	char ver[8];
	char roomName[32];
	char id[32];
	uint8_t oper;
	uint16_t len;
	char buff[MAXLEN];
};

struct arg{
	msg message;
	struct sockaddr_in address;
};


