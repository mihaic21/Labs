#include <stdio.h>
#include <stdlib.h>


int main(int argc, char* argv[]) {
    int i;
    FILE* fin = fopen(argv[1],"r");
    char c;
    while (fgetc(c,fin) != NULL) {
        if (strchr("%/\'\"$",c) != 0) {
            
        }
    }
    return 0;

}
