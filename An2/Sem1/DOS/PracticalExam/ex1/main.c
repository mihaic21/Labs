/*Recieves as command line parameters strings (file names).
Create a thread for each arg. Each thread will open the file
and print the n-th line of the file (n = current arg no)
*/

#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

pthread_t threads[10];
pthread_mutex_t mut;

typedef struct{
    int count;
    char* name;
} Msg;

void* threadFunction(void* var){
    Msg* message = (Msg*) var;
    FILE* file = fopen(message->name,"r");
    char* line;
    int i = 1;
    size_t len = 0;

    while (i <= message->count){
        line = NULL;
        getline(&line, &len, file);
        i++;
    }
    
    free(message);

    pthread_mutex_lock(&mut);
    printf("%s\n",line);
    pthread_mutex_unlock(&mut);

    return NULL;
}

int main(int argc, char* argv[]){
    
    pthread_mutex_init(&mut,NULL);
    
    int i;

    for (i=1; i<argc; i++){
        Msg* message = (Msg*) malloc (sizeof(Msg));
        message->count = i;
        message->name = argv[i];
        pthread_create(&threads[i-1], NULL, threadFunction, message);
    }

    for (i=1; i<=argc; i++){
        pthread_join(threads[i-1], NULL);
    }

    pthread_mutex_destroy(&mut);

    return 0;
}
