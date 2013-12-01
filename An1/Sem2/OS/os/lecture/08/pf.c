#include<stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>

int main(int argc, char** argv) {
    int fd[10];
    int i;

    for(i=0; i<10; i++) {
        fd[i] = -1;
    }

    printf("STDIN %d\n", stdin->_fileno);
    printf("STDOUT %d\n", stdout->_fileno);
    printf("STDERR %d\n", stderr->_fileno);


    for(i=1; i<argc; i++) {
        fd[i-1] = open(argv[i], O_RDONLY);
        printf("Opened \"%s\" with descriptor number %d\n", argv[i], fd[i-1]);
    }

    for(i=0; i<10; i++) {
        if(fd[i] >= 0) {
            close(fd[i]);
        }
    }

    return 0;
}
