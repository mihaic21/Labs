#include "common.h"

int go = 1;

void waiter(int sig) {
    wait(0);
    signal(SIGCHLD, waiter);
}

void die(int sig) {
    go = 0;
}

int main(int argc, char** argv) {
    int fdrv;
    struct rvmsg rv;

    if(mkfifo(RV, 0666) < 0) {
        error("SERVER - Create RV fifo");
    }

    fdrv = open(RV, O_RDONLY);
    if(fdrv < 0) {
        error("SERVER - Open RV fifo");
    }

    signal(SIGCHLD, waiter);
    signal(SIGINT, die);

    printf("Waiting for connections ...\n");
    while(go) {
        if(read(fdrv, &rv, sizeof(struct rvmsg)) == 0) {
            sleep(1);
            continue;
        }
        printf("Connection from %d\n", rv.pid);

        if(fork() == 0) {
            execl("./worker", "worker",
                  rv.fromClient, rv.toClient, NULL);
            error("SERVER - Failed to create worker");
            exit(1);
        }
    }

    close(fdrv);
    unlink(RV);

    return 0;
}
