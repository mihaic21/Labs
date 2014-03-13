#include <stdio.h>
#include <stdlib.h>

int main(){
    
    char* result = (char*) malloc (30);
    char* command = (char*) malloc (30);
    sprintf(command, "wc -l passwd");
    FILE* output = popen(command, "r");

    fscanf(output,"%s",result);

    printf("Number of lines: %s\n", result);

    sprintf(command, "wc -m passwd");
    output = popen(command, "r");

    fscanf(output,"%s",result);

    printf("Number of characters: %s\n",result);

    free(result);
    free(command);
    free(output);

    return 0;

}
