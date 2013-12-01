#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/msg.h>

#include "m1.h"

int main() {
    int mq;
    struct mymsg m;

    mq = msgget(KEY, IPC_CREAT|IPC_EXCL|00600);
    if(mq < 0) {
        perror("Faield to create MQ");
        exit(1);
    }

    while(1) {
        msgrcv(mq, &m, sizeof(struct mymsg)-sizeof(long), REQ, 0);
        if(m.value < 0) {
            break;
        }
        m.value *= 2;
        m.mtype = RES;
        msgsnd(mq, &m, sizeof(struct mymsg)-sizeof(long), 0);
    }

    msgctl(mq, IPC_RMID, 0);

    return 0;
}
