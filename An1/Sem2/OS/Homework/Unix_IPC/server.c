//SERVER

#include <stdlib.h>
#include <stdio.h>
#include <sys/ipc.h>
#include <sys/types.h>
#include <sys/msg.h>
#include "common.h"

int main () {
    struct Msg message;
    int msgqueue = msgget (KEY,IPC_CREAT | IPC_EXCL | 00600);

    if (msgqueue < 0) {
        perror ("could not create");
        exit(1);
    }

    msgrcv (msgqueue,&message, sizeof(struct Msg) - sizeof (long), REQ, 0);
    //int a = message.nr1;
    //int b = message.nr2;
    if (message.nr1 == 0 || message.nr2 == 0) {
        message.result = 0;
        //b = 0;
    }else{
        while (message.nr1 != message.nr2) {
            if (message.nr1 > message.nr2) {
                message.nr1 = message.nr1-message.nr2;
            }else{
                message.nr2 = message.nr2-message.nr1;
            }
        }
    }
    
    message.result = message.nr1;
    msgsnd(msgqueue,&message,sizeof(struct Msg) - sizeof (long), 0);
    msgctl (msgqueue,IPC_RMID,0);

    return 0;
}
