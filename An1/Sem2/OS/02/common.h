#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/file.h>
#include <signal.h>
#include <sys/wait.h>

#define MAX_PATH_LEN 100
#define MAX_USER_LEN 10
#define MAX_PASS_LEN 50

#define info(msg) printf("INFO %s:%d - %s\n", __FILE__, __LINE__, msg)
#define error(msg) printf("ERROR %s:%d - %s\n", __FILE__, __LINE__, msg); perror("");

#define REPO "./repo"
#define RV "./rv.fifo"

struct rvmsg {
    char fromClient[MAX_PATH_LEN];
    char toClient[MAX_PATH_LEN];
    int pid;
};

struct reqmsg {
    char path[MAX_PATH_LEN];
    char user[MAX_USER_LEN];
    char pass[MAX_PASS_LEN];
};

