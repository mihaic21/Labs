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

    shm = shmget(KEY, sizeof(struct myshm), IPC_CREAT|IPC_EXCL|00600);
    if(shm < 0) {
        perror("Failed to create SHM");
    }

    esem = esemget(EKEY, 1);
    if(esem < 0) {
        perror("Failed to get the ESEM");
        exit(1);
    }
    fsem = esemget(FKEY, 0);
    if(fsem < 0) {
        perror("Failed to get the FSEM");
        exit(1);
    }

    p = (struct myshm*)shmat(shm, 0, 0);

    for(i=0; i<10; i++) {
        esemop(fsem, -1);
        p->sum = p->a + p->b;
        printf("%d + %d = %d\n", p->a, p->b, p->sum);
        esemop(esem, 1);
    }

    shmdt(p);

    if(esemkill(esem) < 0) {
        perror("Failed to kill ESEM");
    }
    if(esemkill(fsem) < 0) {
        perror("Failed to kill FSEM");
    }
    shmctl(shm, IPC_RMID, 0);

    return 0;
}
