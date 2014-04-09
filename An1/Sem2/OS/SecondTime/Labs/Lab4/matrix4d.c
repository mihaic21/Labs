#include <stdio.h>
#include <stdlib.h>

int main(){

    int**** matrix;
    int d1,d2,d3,d4;
    int i,j,k,l;
    
    printf ("d1 = ");
    scanf ("%d",&d1);
    printf ("d2 = ");
    scanf ("%d",&d2);
    printf ("d3 = ");
    scanf ("%d",&d3);
    printf ("d4 = ");
    scanf ("%d",&d4);


    //allocate memory for the matrix
    matrix = (int****) malloc (d1*sizeof(int***));
    for (i=0; i<d1; i++){
        matrix[i] = (int***) malloc (d2*sizeof(int**));
        for (j=0; j<d2; j++){
            matrix[i][j] = (int**) malloc (d3*sizeof(int*));
            for (k=0; k<d3; k++){
                matrix[i][j][k] = (int*) malloc (d4*sizeof(int));
            }
        }
    }


    //give values to matrix
    for (i=0; i<d1; i++){
        for (j=0; j<d2; j++){
            for (k=0; k<d3; k++){
                for (l=0; l<d4; l++){
                    matrix[i][j][k][l] = i+j+k+l;
                }
            }
        }
    }

   
   //display matrix
   for (i=0; i<d1; i++){
        for (j=0; j<d2; j++){
            for (k=0; k<d3; k++){
                for (l=0; l<d4; l++){
                    printf("%d\n",matrix[i][j][k][l]);
                }
            }
        }
    }

    
    //free memory
    for (i=0; i<d1; i++){
        for (j=0; j<d2; j++){
            for (k=0; k<d3; k++){
                free(matrix[i][j][k]);
            }
            free(matrix[i][j]);
        }
        free(matrix[i]);
    }
    free(matrix);

    return 0;

}
