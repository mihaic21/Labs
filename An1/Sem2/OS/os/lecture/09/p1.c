#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

#define D(_m) printf("%s\n", _m)

int main() {
    int p2c[2];
    int c2p[2];
    int n = 7;

    pipe(p2c);
    pipe(c2p);

    write(p2c[1], &n, sizeof(int));
    if(fork() == 0) {
        D("C1");
        read(p2c[0], &n, sizeof(int));
        D("C2");
        n *= 2;
        write(c2p[1], &n, sizeof(int));
        D("C3");
        close(p2c[0]);
        close(p2c[1]);
        close(c2p[0]);
        close(c2p[1]);
        exit(0);
    }
    read(c2p[0], &n, sizeof(int));

    close(p2c[0]);
    close(p2c[1]);
    close(c2p[0]);
    close(c2p[1]);

    printf("n=%d\n", n);

    wait(0);

    return 0;
}
