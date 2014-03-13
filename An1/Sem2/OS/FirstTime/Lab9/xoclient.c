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


void closeserver(){
    int key = 9999;
    Table *table = malloc(sizeof(Table));
    table->complete = -1;
    table->mtype = getpid();
    table->matrix[0][0] = 'a';
    table->matrix[0][1] = 'b';
    table->matrix[0][2] = 'c';
    table->matrix[1][0] = 'd';
    table->matrix[1][1] = 'e';
    table->matrix[1][2] = 'f';
    table->matrix[2][0] = 'g';
    table->matrix[2][1] = 'h';
    table->matrix[2][2] = 'i';
    int id = msgget(key, 0666);
    msgsnd(id, table, sizeof(Table), 0);
    printf("Server closed!\n");
    exit(0);
}


int main(int argc, char *argv[]){
    int b, k;
    int arg = atoi(argv[1]);
    if (arg == -1){
        closeserver();
    }
    if (arg == 0){
        printf("Please give the number of clients\n");
        exit(0);
    }
    arg = arg-2;
    b = fork();
    if (b != 0){
        for (k = 0; k < arg; k++){
            if (b == 0){
                continue;
            }else{
                b = fork();
            }
        }
    }
    Table *table = malloc(sizeof(Table));
    int i, j;
    int key = 9999;
    int key2 = 8888;
    table->mtype = getpid();
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
    int id = msgget(key, 0666);
    int id2 = msgget(key2, 0666);
    if (id < 0 || id2 < 0){
        perror("Something went wrong!");
    }
    while (!table->complete){
        msgsnd(id, table, sizeof(Table), 0);
        msgrcv(id2, table, sizeof(Table), getpid(), 0);
        if (b == 0){sleep(0.001);}
        //printf("\n-------|%d|-------\n", getpid());
        //displaygame(table->matrix);
        //printf("\n---------------\n");
        table->complete = gamestatus(table->matrix);
        if (table->complete == 1){
            //displaygame(table->matrix);
            printf("In game: %d, server won!\n", getpid());
            break;
        }
        if (table->complete == 2){
            //displaygame(table->matrix);
            printf("In game: %d. client  %d won!\n", getpid(), getpid());
            break;
        }
        if (table->complete == 3){
            //displaygame(table->matrix);
            printf("In game: %d, it's draw!\n", getpid());
            break;
        }
        int Oed = 0;
        while (!Oed){
            i = rand() % 3;
            j = rand() % 3;
            if ((table->matrix[i][j] != 'X') && (table->matrix[i][j] != 'O')){
                table->matrix[i][j] = 'O';
                Oed = 1;
            }
        }
    }
    if (b != 0){
        for (k = 0; k < arg; k++){
            wait(0);
        }
    }else{
        exit(0);
    }
    return 0;
}
