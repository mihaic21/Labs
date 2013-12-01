#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/stat.h>

#define D(_m) printf("%s\n", _m)

int main() {
    int n = 7;
    int a2b;
    int b2a;

    a2b = open("a2b", O_WRONLY);
    b2a = open("b2a", O_RDONLY);

    write(a2b, &n, sizeof(int));
    read(b2a, &n, sizeof(int));

    printf("%d\n", n);

    close(b2a);
    close(a2b);

    return 0;
}
