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

    mq = msgget(KEY, 0);
    if(mq < 0) {
        perror("Faield to get the MQ");
        exit(1);
    }

    while(1) {
        printf("Value: ");
        scanf("%d", &m.value);
        m.mtype = REQ;
        msgsnd(mq, &m, sizeof(struct mymsg)-sizeof(long), 0);
        if(m.value < 0) {
            break;
        }

        msgrcv(mq, &m, sizeof(struct mymsg)-sizeof(long), RES, 0);

        printf("Got back %d\n", m.value);
    }

    return 0;
}
