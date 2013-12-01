#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/ipc.h>
#include <sys/shm.h>

#include "ss.h"
#include "easysem.h"

int main() {
    int shm;
    int esem;
    int fsem;
    struct myshm* p;
    int i;

    shm = shmget(KEY, 0, 0);
    if(shm < 0) {
        perror("Failed to get the SHM");
        exit(1);
    }
    
    esem = esemconn(EKEY);
    if(esem < 0) {
        perror("Failed to get the ESEM");
        exit(1);
    }
    fsem = esemconn(FKEY);
    if(fsem < 0) {
        perror("Failed to get the FSEM");
        exit(1);
    }

    p = (struct myshm*)shmat(shm, 0, 0);

    for(i=0; i<10; i++) {
        esemop(esem, -1);
        printf("%d + %d = %d\n", p->a, p->b, p->sum);
        p->a = rand() % 10;
        p->b = rand() % 10;
        esemop(fsem, 1);
    }

    shmdt(p);

    return 0;
}
