#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

int main() {
    int p[2]; /* p[0] is for reading only, p[1] for writing only */
    int sum = 0;

    pipe(p);

    sum = sum + 1;
    if(fork() == 0) {
        sum = sum + 2;
        write(p[1], &sum, sizeof(int));
        close(p[0]);
        close(p[1]);
        exit(0);
    }
    wait(0);

    read(p[0], &sum, sizeof(int));

    close(p[0]);
    close(p[1]);

    sum = sum + 3;

    printf("sum=%d\n", sum);

    return 0;
}
