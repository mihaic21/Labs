
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>


int main(int argc, char* argv[]){
    int i;
    for (i = 0; i < argc; i++){
        if (i > 2){
            if (fork() == 0 ){
                char str[20];
                sprintf(str, "%d", i);
                execl("rmlet","rmlet",argv[i],str,NULL);
            }else{
                wait(0);
            }
        }
    }
    return 0;
}
