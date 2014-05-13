#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <signal.h>


int main(){

    int p2c[2];    //parent to child pipe
    int c2p[2];    //child to parent pipe

    signed long int auxParent, auxChild, randomNumberParent, randomNumberChild;

    pipe(p2c);
    pipe(c2p);

    srand(time(0));

    pid_t pid = fork();

    //the child process generates the starting number and sends it to its parent
    if (pid == 0){

        signed long int number = rand() % 10000 + 5000;
        printf("Child: starting number is %ld\n",number);
        write(c2p[1],&number,sizeof(long));

    }

    while (1){
        
        if (pid == 0){    //child process reads, subtracts and sends
            
            read(p2c[0],&auxChild,sizeof(signed long int));
            printf("Child received the number %ld\n",auxChild);
            randomNumberChild = rand() % 950 + 50;
            auxChild -= randomNumberChild;
            write(c2p[1],&auxChild,sizeof(signed long int));

        } else {    //parent process reads, analyses then send the subtracted number
                    //to its child or kills the child process and exits loop if number<0

            read(c2p[0],&auxParent,sizeof(signed long int));
            if (auxParent >= 0){
                printf("Parent received the number %ld\n",auxParent);
            }
            randomNumberParent = rand() % 950 + 50;
            auxParent -= randomNumberParent;
            if (auxParent < 0){
                kill(0,SIGKILL);
                break;
            } else {
                write(p2c[1],&auxParent,sizeof(signed long int));
            }
        }

    }

    close(p2c[0]);
    close(p2c[1]);
    close(c2p[0]);
    close(c2p[1]);

    return 0;
}
