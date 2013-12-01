//CLIENT
#include <stdlib.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>


int main() {
    int a,b;
    printf ("a = ");
    scanf ("%d",&a);
    printf ("b = ");
    scanf ("%d", &b);

    int c2s;
    int s2c;
    
    c2s = open ("c2s",O_WRONLY);
    s2c = open ("s2c",O_RDONLY);

    write (c2s,&a,sizeof(int));
    write (c2s,&b,sizeof(int));
    
    int result;
    read (s2c,&result,sizeof(int));
    printf ("%d\n",result);
    
    close (c2s);
    close (s2c);

    return 0;
}
