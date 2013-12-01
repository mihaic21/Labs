
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>


int gamestatus(char m[3][3]){
    int i, j;
    int complete = 0;
    int marked = 1;
    for (i = 0; i < 3; i++){
        if ((m[i][0] == m[i][1]) && (m[i][1] == m[i][2])){
            if (m[i][0] == 'X'){
                complete = 1;
            }else{
                complete = 2;
            }
        }
        if ((m[0][i] == m[1][i]) && (m[1][i] == m[2][i])){
            if (m[0][i] == 'X'){
                complete = 1;
            }else{
                complete = 2;
            }
        }
    }
    if ((m[0][0] == m[1][1]) && (m[1][1] == m[2][2])){
            if (m[0][0] == 'X'){
                complete = 1;
            }else{
                complete = 2;
            }
    }
    if ((m[0][2] == m[1][1]) && (m[1][1] == m[2][0])){
         if (m[2][0] == 'X'){
                complete = 1;
            }else{
                complete = 2;
            }
    }
    if(complete == 0){
        for (i = 0; i < 3; i++){
            for (j = 0; j < 3; j++){
                if ((m[i][j] != 'X') && (m[i][j] != 'O')){
                    marked = 0;
                }
            }
        }
        if (marked != 0){
            complete = 3;
        }
    }
    return complete;
}


void displaygame(char m[3][3]){
    int i, j;
    for (i = 0; i < 3; i++){
        for (j = 0; j < 3; j++){
            if ((m[i][j] != 'X') && (m[i][j] != 'O')){
                printf(" ");
            }else{
                printf("%c", m[i][j]);
            }
        }
        printf("\n");
    }
}



int main(int argc, char *argv[]){
    int fd1[2];
    int fd2[2];
    char m[3][3] = {{'a', 'b', 'c'}, {'d', 'e', 'f'}, {'h', 'i', 'j'}};
    pipe(fd1);
    pipe(fd2);
    if (fork() == 0){
        int request;
        int i,j;
        int complete;
        srand(time(NULL));
        read(fd1[0], &request, sizeof(int));
        while (request){
            i = rand() % 3;
            j = rand() % 3;
            write(fd2[1], &i, sizeof(int));
            write(fd2[1], &j, sizeof(int));
            read(fd1[0], &request, sizeof(int));
        }
        read(fd1[0], &complete, sizeof(int));
        if (complete == 1){
            printf("Parent wins!\n");
        }
        if (complete == 2){
            printf("Child wins!\n");      
        }
        if (complete == 3){
            printf("It's draw!\n");
        }
        exit(0);
    }
    int i = rand() % 3;
    int j = rand() % 3;
    int endsignal = 0;
    int complete = 0;
    int Xed = 0;
    int Oed = 0;
    int askno = 1;
    srand(time(NULL));
    while (!complete){
        Xed = 0;
        while (!Xed){
            i = rand() % 3;
            j = rand() % 3;
            if ((m[i][j] != 'X') && (m[i][j] != 'O')){
                m[i][j] = 'X';
                Xed = 1;
            }
        }
        complete = gamestatus(m);
        if (complete){
            break;
        }
        Oed = 0;
        while (!Oed){
            write(fd1[1], &askno, sizeof(int));
            read(fd2[0], &i, sizeof(int));
            read(fd2[0], &j, sizeof(int));
            if ((m[i][j] != 'X') && (m[i][j] != 'O')){
                m[i][j] = 'O';
                Oed = 1;
            }
        }
        complete = gamestatus(m);
    }
    write(fd1[1], &endsignal, sizeof(int));
    displaygame(m);
    write(fd1[1], &complete, sizeof(int));
    return 0;
}


