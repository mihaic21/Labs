#include "utils.h"
#include <sys/socket.h>
#include<netinet/in.h>
#include<arpa/inet.h>
#include <string.h>
 
MsgSnd snd;
char Max[100];
 
MsgSnd *max(MsgSnd *s){
       
        printf("Recieved %s\n", s->str);
        printf("From IP %s\n", s->ip);
        printf("Port %d\n", s->port);
        if(strlen(snd.str) < strlen(s->str)){
                strcpy(snd.str,s->str);
                strcpy(snd.ip, s->ip);
                snd.port = s->port;    
                printf("bigger string recieved\n");
        }
        return &snd;
}
 
int main() {
        struct sockaddr_in addr;
        get_myaddress(&addr);
        char ip[20];
        char* c = inet_ntoa(addr.sin_addr);
        strcpy(ip, c);
        printf("Server IP= %s\n", ip);
        int port = addr.sin_port;
        printf("Server port = %d\n", port);
        registerrpc(PROGRAM_EXEC, VERSIUNE_EXEC, EXEC_MAX, max, xdr_msgsnd,             xdr_msgsnd);
        svc_run();
        return 0;
}