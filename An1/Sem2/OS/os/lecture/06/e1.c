#include <stdio.h>
#include <unistd.h>

int main() {
    printf("This is me! (e1)\n");


    execl("/bin/lsssss", "/bin/ls", "-l", "/home/rares/course/os/lecture", NULL);

    printf("This is still me? (e1)\n");
    return 0;
}
