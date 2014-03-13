#include "common.h"

char* processContent(char* content) {
    return content;
}

int main(int argc, char** argv) {
    char* fcpath = argv[1];
    char* tcpath = argv[2];
    int fdfc;
    int fdtc;
    FILE* fd;
    char* buf;
    int len;
    struct reqmsg req;

    fdfc = open(fcpath, O_RDONLY);
    if(fdfc < 0) {
        error("WORKER - Open from client fifo");
    }

    fdtc = open(tcpath, O_WRONLY);
    if(fdtc < 0) {
        error("WORKER - Open to client fifo");
    }

    read(fdfc, &req, sizeof(struct reqmsg));

    fd = fopen(req.path, "rt");
    if(fd == NULL) {
        error("WORKER - Open content file");
    }

    buf = (char*)malloc(1024);

    while(fgets(buf, 1024, fd)) {
        buf = processContent(buf); 
        len = strlen(buf)+1;
        write(fdtc, &len, sizeof(int));
        write(fdtc, buf, len);
    }
    len = 0;
    write(fdtc, &len, sizeof(int));
 
    free(buf);
    fclose(fd);
    close(fdfc);
    close(fdtc);

    return 0;
}
