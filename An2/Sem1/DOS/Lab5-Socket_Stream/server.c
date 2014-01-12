#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <inttypes.h>
#include <netdb.h>
#include <string.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <signal.h>
#include <arpa/inet.h>
pthread_rwlock_t rwlock;
 
struct thrarg {
  int sock;
  int index;
  uint16_t port;
  char ip[20];
};
typedef struct{
  char str[100];
  uint16_t port;
  char ip[20]; 
}Msg;
 
 
Msg Max;
 
int rvSock;
pthread_t* thr[100];
pthread_mutex_t mtx = PTHREAD_MUTEX_INITIALIZER;

//in case of CTRL + C 
void handler(int sig) {
  if (close (rvSock) < 0) {
    perror("\nCould not close socket\n");
    return;
  }
}

//returns first free thread, -1 otherwise 
int findFreeThread() {
  int i;
 
  pthread_mutex_lock(&mtx);
  for(i=0; i<100; i++) {
    if(thr[i] == NULL) {
      pthread_mutex_unlock(&mtx);
      return i;
    }
  }
  pthread_mutex_unlock(&mtx);
 
  return -1;
}

//main thread function; parameters: struct containing port, ip, index, socket 
void serve(struct thrarg* arg) {
  int k;
  char buf[1024];
  printf("I R THREAD!\n");
  k = recv(arg->sock, buf, 1024, 0);
  if(k > 0) {
    buf[k] = 0;
    printf("Received: %s\n", buf);
    pthread_rwlock_rdlock(&rwlock);
    if(strlen(buf) > strlen(Max.str)){
        printf("Locking stuff\n");
        pthread_rwlock_unlock(&rwlock);
        pthread_rwlock_wrlock(&rwlock);
        strcpy(Max.str,buf);   
        printf("Assigned string\n");
        Max.port = arg->port;
        printf("Assigned port\n");
        strcpy(Max.ip, arg->ip);
        printf("Assigned IP\n");
        pthread_rwlock_unlock(&rwlock);
        pthread_rwlock_rdlock(&rwlock);
    }
  }
  send(arg->sock, &Max, sizeof(Max), 0);
  printf("Message sent\n");
  pthread_rwlock_unlock(&rwlock);
  pthread_mutex_lock(&mtx);
  printf("Unlocking stuff and killing/freeing thread\n");
  free(thr[arg->index]);
  thr[arg->index] = NULL;
  close(arg->sock);
  free(arg);
  pthread_mutex_unlock(&mtx);
}
 
int main(int argc, char *argv[]) {
  int sock;
  int port;
  unsigned int len;
  int i;
  int k;
  struct sockaddr_in addr;
 
  pthread_rwlock_init(&rwlock,NULL);
 
 
  if (argc < 2) {
    printf ("\nUsage: ./server port\n");
    return 0;
  }
  else {
    sscanf(argv[1], "%d", &port);
  }
 
  rvSock = socket(AF_INET, SOCK_STREAM, 0);
  if (rvSock < 0) {
    perror("\nCould not make a socket\n");
    return 1;
  }
 
  addr.sin_addr.s_addr = INADDR_ANY;
  addr.sin_port = htons (port);
  addr.sin_family = AF_INET;
 
  if (bind (rvSock, (struct sockaddr *) &addr, sizeof (addr)) < 0) {
    perror("\nCould not bind socket\n");
    return 1;
  }
 
  if (listen(rvSock, 5) < 0) {
    perror ("\nCould not listen\n");
    return 1;
  }
 
  for(i=0; i<100; i++)
    thr[i] = NULL;
 
  signal(SIGINT, handler);
 
  while(1) {
    sock = accept (rvSock, (struct sockaddr *)&addr,  &len);
 
    if(sock < 0) {
      break;
    }
 
    printf("SERVER: Connection from %s:%d\n", inet_ntoa(addr.sin_addr), ntohs(addr.sin_port));
 
    k = findFreeThread();
    if(k < 0) {
      close(sock);
      continue;
    }
 
    struct thrarg* ta = (struct thrarg*)malloc(sizeof(struct thrarg));
    ta->sock = sock;
    ta->index = k;
    ta->port = ntohs(addr.sin_port);
    strcpy(ta->ip, inet_ntoa(addr.sin_addr));
    thr[k] = (pthread_t*)malloc(sizeof(pthread_t));
 
    pthread_create(thr[k], NULL, (void *(*)(void*))serve, ta);
  }
 
  for(i=0; i<100; i++) {
    pthread_t* t = thr[i];
    if(t != NULL) {
      pthread_join(*t, NULL);
    }
  }
       
 
  return 0;
}
