#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

int main() {
    char* name;
    int c2s;
    int s2c;
    name = (char*) malloc (30);

    printf("name = ");
    scanf("%s",name);
    int length = 0;
    length = strlen(name);
    c2s = open ("c2s",O_WRONLY);
    s2c = open ("s2c",O_RDONLY);
    
    write (c2s,&length,sizeof(int));
    write (c2s,name,length);
    int result;
    read (s2c,&result,sizeof(int));
    printf ("%d\n",result);
    close(c2s);
    close(s2c);
    free (name);
    return 0;
}
