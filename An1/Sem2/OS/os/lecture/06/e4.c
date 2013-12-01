#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <time.h>

int main(int argc, char** argv) {
    FILE* p;
    int i;

    srand(time(NULL));

    p = popen("sort -n", "w");

    for(i=0; i<10; i++) {
        fprintf(p, "%d\n", rand()); 
    }
    pclose(p);

    return 0;
}
