#include <stdlib.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <unistd.h>
#include <netinet/in.h>
#include <arpa/inet.h>

int main(){
    
    int sock = socket(AF_INET, SOCK_STREAM, 0);

    struct sockaddr_in addr;

    addr.sin_addr.s_addr = inet_addr("127.0.0.1");
    addr.sin_port = htons(1234);
    addr.sin_family = AF_INET;

    

    int server = connect(sock, (struct sockaddr*) &addr, (socklen_t) sizeof(addr));

    if (server < 0){
        perror("nu pusca ceva\n");
    }

    int n = 5;
    int m;

    send(sock, &n, sizeof(int), 0);
    recv(sock, &m, sizeof(int), 0);

    printf("number: %d\n", m);

    close(sock);

    return 0;
}
