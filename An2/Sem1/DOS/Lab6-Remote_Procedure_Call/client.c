#include "utils.h"
#include <stdio.h>
MsgSnd st;
MsgSnd rcv;
 
 
int main(){
        printf("Client\n");    
        struct sockaddr_in addr;
        get_myaddress(&addr);
        char* c = inet_ntoa(addr.sin_addr);
        strcpy(st.ip, c);
        printf("IP= %s\n", st.ip);
        st.port = addr.sin_port;
        printf("port = %d\n", st.port);
 
        printf("String to pass: \n");
        scanf("%s",st.str);
        callrpc("localhost", PROGRAM_EXEC, VERSIUNE_EXEC, EXEC_MAX,
                (xdrproc_t)xdr_msgsnd, (char*)&st, (xdrproc_t)xdr_msgsnd, (char*)&rcv);
        printf("Max: %s\n", rcv.str);
        printf("IP : %s\n", rcv.ip);
        printf("Port : %d\n", rcv.port);
 
 
return 0;
}