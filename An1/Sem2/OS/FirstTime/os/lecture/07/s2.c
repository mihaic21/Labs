#include <stdio.h>
#include <signal.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <stdlib.h>

void waitforchild(int sgn) {
    wait(0);
    signal(SIGCHLD, waitforchild);
}

int main() {
    signal(SIGCHLD, waitforchild);

    while(1) {
        // get request
        if(fork() == 0) {
            // server request
            exit(0);
            sleep(2);
        }
        sleep(4);
    }
}
