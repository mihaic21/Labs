#include "easysem.h"

int esemget(key_t key, int val) {
    int sem = semget(key, 1, IPC_CREAT|IPC_EXCL|00600);
    if(sem < 0) {
        return sem;
    }
    int k = semctl(sem, 0, SETVAL, val);
    if(k < 0) {
        return k;
    }
    return sem;
}

int esemconn(key_t key) {
    return semget(key, 0, 0);
}

int esemkill(int semid) {
    return semctl(semid, 0, IPC_RMID, 0);
}

int esemop(int semid, int op) {
    struct sembuf sb;

    sb.sem_num = 0;
    sb.sem_op = op;
    sb.sem_flg = 0;

    return semop(semid, &sb, 1);
}


