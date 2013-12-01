#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>

int main(int argc, char** argv) {
    FILE* p;
    char u[100];

    p = popen("ps -ef|awk '{print $1}'|sort|uniq", "r");
    while(fgets(u, 100, p) != NULL) {
        printf("Here is user: %s\n", u);
    }
    pclose(p);

    return 0;
}
