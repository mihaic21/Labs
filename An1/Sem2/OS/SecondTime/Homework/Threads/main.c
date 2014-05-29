#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <string.h>

#define null '\0'

pthread_t threads[10];
pthread_mutex_t mutex;

int totalSum = 0;
char command [30];

void *threadFunction(void *var){

    char *word = (char *) var;
    char finalCommand[30];
    int result;

    strcpy(finalCommand, command);
    strcat(finalCommand, word);

    FILE *output = popen(finalCommand, "r");
    fscanf(output, "%d", &result);
    
    pthread_mutex_lock(&mutex);
    totalSum += result;
    pthread_mutex_unlock(&mutex);

    return null;
}


int main(int argc, char* args[]){

    if (argc < 2){
        perror("Invalid number of arguments\n");
        return 1;
    }

    pthread_mutex_init(&mutex,null);
    char* fileName = args[1];

    strcpy(command, "./shell.sh ");
    strcat(command, fileName);
    strcat(command, " ");

    int i;
    for (i=2; i<argc; i++){
        pthread_create(&threads[i-2], null, threadFunction, args[i]);
    }

    for (i=2; i<=argc; i++){
        pthread_join(threads[i-2], null);
    }

    printf("Total number of appearances is %d\n",totalSum);

    pthread_mutex_destroy(&mutex);

    return 0;
}
