#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

void handler(int sgn) {
    wait(0);
    signal(SIGCHLD, handler);
}

void stop(int sgn) {
    printf("I totally refuse to stop\n");
//    exit(0);
}

int main() {
    int child;

    signal(SIGCHLD, handler);
    signal(SIGINT, stop);

    while(1) {
        // wait for a new request
        // get request
        child = fork();
        if(child == 0) {
            // proces request
            // send back answer
            exit(0);
        }
    }

    return 0;
}
