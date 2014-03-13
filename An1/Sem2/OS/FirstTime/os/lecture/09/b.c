#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/types.h>
#include <sys/stat.h>

#define D(_m) printf("%s\n", _m)

int main() {
    int n = 0;
    int a2b;
    int b2a;

    a2b = open("a2b", O_RDONLY);
    b2a = open("b2a", O_WRONLY);

    read(a2b, &n, sizeof(int));
    n *= 2;
    write(b2a, &n, sizeof(int));

    close(b2a);
    close(a2b);

    return 0;
}
