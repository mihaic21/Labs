#include <stdio.h>
#include <unistd.h>


int main(){

    int c2p[2];
    int p2c[2];

    int number;
    int numberr;
    int i;
    int counter = 0;
    
    pipe (c2p);
    pipe (p2c);

    pid_t pid = fork();
/*
    for (i = 0; i < 10; i++){
        
        if (pid == 0){

            printf("Give number: ");
            scanf ("%d",&number);
            write (c2p[1], &number, sizeof(int));
            printf("I gave written %d",number);
            read(p2c[0], &number, sizeof(int));
            if (number != 0){
                printf("Out!\n");
                return -1;
            }

        } else{
            
            read(c2p[0], &numberr, sizeof(int));

            printf("I have received %d", numberr);

        }

    }
*/
    while (counter++ < 10){

//        printf("counter is at %d\n", counter);

        if (pid == 0){

            printf ("Give number: ");
            scanf("%d", &number);
            //printf("%d",number);

        } else {

            read(c2p[0],&numberr,sizeof(int));
            printf("this is the read number %d\n",numberr);

        }

    }


    close(p2c[0]);
    close(p2c[1]);
    close(c2p[0]);
    close(c2p[1]);

    return 0;
}
