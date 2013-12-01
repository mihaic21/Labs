#include "table.h"

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


int main(){
    Table *table = malloc(sizeof(Table));
    int i,j;
    int key = 9999;
    int key2 = 8888;
    //table->mtype = 1;
    table->complete = 0;
    table->matrix[0][0] = 'a';
    table->matrix[0][1] = 'b';
    table->matrix[0][2] = 'c';
    table->matrix[1][0] = 'd';
    table->matrix[1][1] = 'e';
    table->matrix[1][2] = 'f';
    table->matrix[2][0] = 'g';
    table->matrix[2][1] = 'h';
    table->matrix[2][2] = 'i';
    srand(time(NULL));
    int id = msgget(key, 0666|IPC_CREAT);
    int id2 = msgget(key2, 0666|IPC_CREAT);
    if (id < 0 || id2 < 0){
        perror("Error creating message queue!");
        exit(0);
    }
    //msgrcv(id, table, sizeof(Table), 1, 0);
    while (1){
        msgrcv(id, table, sizeof(Table), 0, 0);
        if (table->complete == -1){
            break;
        }
        printf("Got table from: %lu\n", table->mtype);
        displaygame(table->matrix);
        printf("\n");
        table->complete = gamestatus(table->matrix);
        if (!table->complete){
            int Xed = 0;
            while (!Xed){
                i = rand() % 3;
                j = rand() % 3;
                if ((table->matrix[i][j] != 'X') && (table->matrix[i][j] != 'O')){
                    table->matrix[i][j] = 'X';
                    Xed = 1;
                }
            }   
        }
        msgsnd(id2, table, sizeof(Table), 0);
    }
    msgctl(id, IPC_RMID, NULL);
    msgctl(id2, IPC_RMID, NULL);
    return 0;
}
