#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>

int main(int argc, char** argv) {
    int i;
    int child;

    for(i=1; i<argc; i++) {
        child = fork();
        if(child == 0) {
            printf("\n");
            execl("/usr/bin/file", "/usr/bin/file", argv[i], NULL);
            exit(0);
        }
    }

    for(i=1; i<argc; i++) {
        wait(0);
    }

    return 0;
}
