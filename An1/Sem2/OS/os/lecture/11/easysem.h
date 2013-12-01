#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/sem.h>

#ifndef EASYSEM_H
#define EASYSEM_H

int esemget(key_t key, int val);
int esemconn(key_t key);
int esemkill(int semid);
int esemop(int semid, int op);

#endif

