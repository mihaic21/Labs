#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

int main() {
    int sum = 0;

    sum = sum + 1;
    if(fork() == 0) {
        sum = sum + 2;
        exit(0);
    }
    wait(0);
    sum = sum + 3;

    printf("sum=%d\n", sum);

    return 0;
}
