
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main(int argc, char* argv[]){
    FILE* fl = fopen(argv[1], "r");
    int divisor = atoi(argv[2]);
    FILE* tmp = tmpfile();
    char c;
    int counter = 1;
    if (fl == NULL){
        printf("File doesn't exist");
    }else{
        do{
            c = fgetc(fl);
            if ((counter % divisor) != 0){
                fputc(c, tmp);
            }
            counter++;
        }while(c != EOF);
    }
    fclose(fl);
    rewind(tmp);
    if (!(fl = fopen(argv[1], "w"))){
        printf("Error in writing");
    }
    while((c = getc(tmp)) != EOF){
        fputc(c, fl);
    }
    fclose(fl);
    fclose(tmp);
    return 0;
}
