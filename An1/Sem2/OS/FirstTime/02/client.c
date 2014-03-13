#include "common.h"

int main(int argc, char** argv) {
    struct rvmsg rv;
    struct reqmsg req;
    int fdrv;
    int fdfc;
    int fdtc;
    char* buf;
    int len;

    if(argc < 4) {
        error("Wrong parameters");
        return 1;
    }

    printf("Client %d\n", getpid());

    sleep(1);

    sprintf(rv.fromClient, "./fc%d.fifo", getpid());
    sprintf(rv.toClient, "./tc%d.fifo", getpid());
    rv.pid = getpid();

    if(mkfifo(rv.fromClient, 0666) < 0) {
        error("CLIENT - Create from client fifo");
    }

    if(mkfifo(rv.toClient, 0666) < 0) {
        error("CLIENT - Create to client fifo");
    }

    fdrv = open(RV, O_WRONLY);
    if(fdrv < 0) {
        error("CLIENT - Open RV fifo");
    }

    if(flock(fdrv, LOCK_EX) < 0) {
        error("CLIENT - Lock RV");
    }

    write(fdrv, &rv, sizeof(struct rvmsg));

    if(flock(fdrv, LOCK_UN) < 0) {
        error("CLIENT - Unlock RV");
    }

    close(fdrv);

    strcpy(req.path, argv[1]);
    strcpy(req.user, argv[2]);
    strcpy(req.pass, argv[3]);

    fdfc = open(rv.fromClient, O_WRONLY);
    if(fdfc < 0) {
        error("CLIENT - Open from client fifo");
    }

    fdtc = open(rv.toClient, O_RDONLY);
    if(fdtc < 0) {
        error("CLIENT - Open to client fifo");
    }

    write(fdfc, &req, sizeof(struct reqmsg));

    while(1) {
        read(fdtc, &len, sizeof(int));
        if(len == 0) {
            break;
        }
        buf = (char*)malloc(len);
        read(fdtc, buf, len);
        write(1, buf, len);
        free(buf);
    }

    close(fdtc);
    close(fdfc);

    unlink(rv.fromClient);
    unlink(rv.toClient);

    return 0;
}
