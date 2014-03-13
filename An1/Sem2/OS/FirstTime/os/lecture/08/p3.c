#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

int main() {
    int p2c[2];
    int c2p[2];
    int n = 7;

    pipe(p2c);
    pipe(c2p);

    write(p2c[1], &n, sizeof(int));
    if(fork() == 0) {
        read(p2c[0], &n, sizeof(int));
        n *= 2;
        write(c2p[1], &n, sizeof(int));
        close(p2c[0]);
        close(p2c[1]);
        close(c2p[0]);
        close(c2p[1]);
        exit(0);
    }
    wait(0);

    read(c2p[0], &n, sizeof(int));
    close(p2c[0]);
    close(p2c[1]);
    close(c2p[0]);
    close(c2p[1]);

    printf("n=%d\n", n);

    return 0;
}
