#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <string.h>
#include <unistd.h>
#include <stdio.h>
#include <arpa/inet.h>
 
 
#define SOCKET_ERROR        -1
#define BUFFER_SIZE         100
#define HOST_NAME_SIZE      255
 
typedef struct{
        char str[100];
        uint16_t port;
        char ip[20];
       
       
}Msg;
 
 
 
int main (int argc, char *argv[]) {
  int sock;
  int port;
  Msg msg;
  int k;
  char ip[20];
  struct sockaddr_in addr;
  char buf[1024];
 
  if (argc < 3) {
    printf ("\nUsage: ./client ip port\n");
    return 1;
  }
  else {
    strcpy(ip, argv[1]);
    sscanf(argv[2], "%d", &port);
  }
 
 
  sock = socket(AF_INET, SOCK_STREAM, 0);
  if (sock < 0) {
    perror ("\nCould not create socket\n");
    return 1;
  }
 
  addr.sin_addr.s_addr = inet_addr(ip);
  addr.sin_port = htons(port);
  addr.sin_family = AF_INET;
 
  if (connect (sock, (struct sockaddr *) &addr, sizeof(addr)) < 0) {
    perror("\nCould not connect to host\n");
    return 1;
  }
 
  fgets(buf,sizeof(buf),stdin);
  send(sock, buf, strlen(buf), 0);
 
  while(1) {
    k = recv(sock, &msg, sizeof(msg),0);
    if(k <= 0) {
      break;
    }
  }
  printf("String: %s\n, IP: %s\n, Port: %d\n", msg.str, msg.ip, msg.port);
  if (close(sock) < 0) {
    perror("\nCould not close socket\n");
    return 1;
  }
 
  return 0;
}