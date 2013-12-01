#include <stdio.h>
#include <signal.h>
#include <unistd.h>

void nostop(int sgn) {
    printf("I don;t want to stop\n");
    signal(SIGINT, nostop);
    signal(SIGSTOP, nostop);
}

int main() {
    signal(SIGINT, nostop);
    signal(SIGSTOP, nostop);

    while(1) {
        printf("I'm still working\n");
        sleep(5);
    }
    return 0;
}
