#include <stdio.h>
#include <stdlib.h>
#include "common.h"

#define N 10

int main() {
    int** m;
    int i, j;

    m = (int**)malloc(N*sizeof(int*));
    for(i=0; i<N; i++) {
        m[i] = (int*)malloc(N*sizeof(int));
    }

    for(i=0; i<N; i++){ 
        for(j=0; j<N; j++) {
            m[i][j] = 3;
        }
    }

    print(m, N, N);

    for(i=0; i<N; i++) {
        free(m[i]);
    }
    free(m);

    return 0;
}
