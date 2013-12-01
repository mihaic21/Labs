#include <stdio.h>

void print(int** m, int xdim, int ydim) {
    int i, j;

    for(i=0; i<xdim; i++) {
        for(j=0; j<ydim; j++) {
            printf("%3d ", m[i][j]);
        }
        printf("\n");
    }
}
