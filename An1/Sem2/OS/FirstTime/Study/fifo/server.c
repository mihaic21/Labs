#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdio.h>

int main() {
    char* name;
    int length;
    int c2s;
    int s2c;
    int result;
    
    c2s = open ("c2s",O_RDONLY);
    s2c = open ("s2c",O_WRONLY);
    if (c2s < 0 || s2c < 0){
        perror("could not open fifo");
        exit(1);
    }
    name = (char*) malloc (30);
    read (c2s,&length,sizeof(int));
    read (c2s,name,length);

    char* command;
    command = (char*) malloc (30);
    sprintf (command,"./cautare.sh %s",name);
    FILE* output = popen (command,"r");
    fscanf(output,"%d",&result);
    write (s2c,&result,sizeof(int));

    pclose(output);
    close(c2s);
    close(s2c);
    free(command);
    free(name);
    return 0;
}
