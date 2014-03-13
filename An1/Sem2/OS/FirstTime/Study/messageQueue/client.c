#include <stdlib.h>
#include <string.h>
#include "common.h"
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <stdio.h>

int main () {
    char* name;
    int msgqueue = msgget(KEY, 0);
    if (msgqueue < 0) {
        perror ("could not connect");
        exit (1);
    }
    name = (char*) malloc (30);
    printf ("name = ");
    scanf ("%s",name);
    int length = strlen(name)+1;
    printf ("%s", name);    

    struct Msg message;
    message.name = (char*) malloc (length*sizeof(char));
    strcpy(message.name,name);
    printf("%s", message.name);

    message.type = REQ;
    
    msgsnd(msgqueue,&message,sizeof(struct Msg) - sizeof(long),0);
/*
    msgrcv(msgqueue,&message,sizeof(struct Msg) - sizeof(long),RES,0);

    printf("%d\n",message.result);
*/
    return 0;
}
