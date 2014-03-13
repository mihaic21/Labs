//2 procese comunica cu pipe
//primul trimite un nume de executabil
//al doilea verifica, cati useri folosesc executabilul respectiv si returneaza numarul

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<unistd.h>
#include<sys/types.h>
#include<sys/wait.h>

int main() {
    char* name;
    int p2c[2];
    int c2p[2];
    int child, length,result;
    pipe(p2c);
    pipe(c2p);

    name = (char*) malloc (10);
    
    printf ("name = ");
    scanf ("%s",name);
    length = strlen(name);

    child = fork();
    if (child == 0) {
        char* thename;
        char* command;
        int thelength;
        int theresult;
        read (p2c[0],&thelength,sizeof(int));
        thename = (char*) malloc (thelength);
        read (p2c[0],thename,thelength);
        command = (char*) malloc (30);
        sprintf(command,"./cautare.sh %s", thename);
        FILE* output =  popen (command,"r");
        fscanf(output,"%d",&theresult);
        write (c2p[1],&theresult,sizeof(int));
        free (thename);
        pclose(output);
        exit(0);
    }

    write (p2c[1],&length,sizeof(int));
    write (p2c[1],name,length);
    read (c2p[0],&result,sizeof(int));
    printf ("%d\n",result);

    close(p2c[0]);
    close(p2c[1]);
    close(c2p[0]);
    close(c2p[1]);
    
    free(name);

    wait(0);
    return 0;
}
