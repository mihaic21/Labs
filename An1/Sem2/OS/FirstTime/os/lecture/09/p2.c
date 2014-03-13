#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

#define D(_m) printf("%s\n", _m)

int main() {
    int p[2];
    int n = 7;

    pipe(p);

    write(p[1], &n, sizeof(int));
    if(fork() == 0) {
        read(p[0], &n, sizeof(int));
        n *= 2;
        write(p[1], &n, sizeof(int));
        close(p[0]);
        close(p[1]);
        exit(0);
    }
    wait(0);

    read(p[0], &n, sizeof(int));

    close(p[0]);
    close(p[1]);

    printf("n=%d\n", n);

    return 0;
}
