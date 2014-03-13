//SERVER
#include <unistd.h>
#include <stdlib.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

int main() {
    int c2s;
    int s2c;
    c2s = open ("c2s",O_RDONLY);
    s2c = open ("s2c",O_WRONLY);
    if (c2s < 0 || s2c < 0){
        perror ("could not open fifo");
        exit(1);
    }
    int a;
    int b;
    read (c2s,&a,sizeof(int));
    read (c2s,&b,sizeof(int));
    
    if (a == 0 || b == 0){
        a = 0;
        b = 0;
    }else {
        while (a != b) {
            if (a > b) {
                a = a-b;
            } else {
                b = b-a;
            }
        }
    }

    write(s2c,&a,sizeof(int));

    close(c2s);
    close(s2c);
    return 0;
}
