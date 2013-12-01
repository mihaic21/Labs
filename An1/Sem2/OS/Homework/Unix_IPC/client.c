//CLIENT

#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/msg.h>

#include "common.h"

int main() {
    int msgqueue = msgget (KEY, 0);
    if (msgqueue < 0) {
        perror ("could not connect");
        exit(1);
    }

    struct Msg message;
 
    printf ("nr1 = ");
    scanf ("%d",&message.nr1);
    printf ("nr2 = ");
    scanf ("%d",&message.nr2);

    message.type = REQ;

    msgsnd(msgqueue,&message,sizeof(struct Msg) - sizeof (long), 0);
    msgrcv(msgqueue,&message,sizeof(struct Msg) - sizeof (long),RES, 0);
    printf ("%d\n",message.result);

    return 0;
}
