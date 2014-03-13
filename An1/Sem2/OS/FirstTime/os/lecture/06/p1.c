#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

int main() {
    int n = 0;
    int child;

    child = fork();
    n++;
    if(child == 0) { //this is child code
        n++;
        printf("CHILD(%d) n=%d\n", getpid(), n);
//        exit(0);
    }
    wait(0);
    printf("PARENT(%d) created child (%d) n=%d\n", getpid(), child, n);
    
    return 0;
}
