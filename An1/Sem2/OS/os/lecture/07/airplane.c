#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <sys/wait.h>
#include <sys/file.h>
#include <fcntl.h>
#include <unistd.h>

int main() {
    int i, child, k;
    int fd, value;

    // Create file and write 100 in it
    fd = open("airplane.dat", O_CREAT|O_RDWR, 00600);
    if(fd < 0) {
        perror("Failed to open file initially");
        exit(0);
    }
    value = 100;
    write(fd, &value, sizeof(int));
    close(fd);

    for(i=0; i<10; i++) {
        child = fork();
        if(child == 0) {
            fd = open("airplane.dat", O_RDWR);
            if(fd < 0) {
                perror("Failed to open file in child");
                exit(0);
            }
            flock(fd, LOCK_EX);
            printf("Locked\n");
            for(k=60; k>=0; k--) {
                printf("Seconds left: %d\n", k);
                sleep(1);
            }
            read(fd, &value, sizeof(int));
            printf("Reserved seat: %d\n", value);
            value--;
            lseek(fd, 0, SEEK_SET);
            write(fd, &value, sizeof(int));
            flock(fd, LOCK_UN);
            close(fd);

            exit(0);
        }
    }

    for(i=0; i<10; i++) {
        wait(0);
    }

    fd = open("airplane.dat", O_RDONLY);
    if(fd < 0) {
        perror("Failed to open file at the end");
        exit(0);
    }
    read(fd, &value, sizeof(int));
    printf("Final value: %d\n", value);
    close(fd);

    return 0;
}
