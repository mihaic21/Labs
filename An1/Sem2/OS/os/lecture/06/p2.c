#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

int main() {
    int k, pid;

    for(k=0; k<5; k++) {
        sleep(2);
        pid = fork();
        if(pid == 0) {
            sleep(5);
//            exit(0);
        }
    }

    sleep(40);

    for(k=0; k<5; k++) {
        wait(0);
    }

    return 0;
}
