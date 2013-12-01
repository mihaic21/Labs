#include <stdlib.h>
#include "common.h"
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/msg.h>
#include <stdio.h>

int main () {
    struct Msg message;
    message.name = (char*) malloc (30);
    int msgqueue = msgget(KEY,IPC_CREAT | IPC_EXCL | 00600);

    if (msgqueue < 0){
        perror ("could not create");
        exit(1);
    }
    
    msgrcv (msgqueue,&message, sizeof(struct Msg) - sizeof (long), REQ,0);
    char* command;
    command = (char*) malloc (60);

    printf ("%s", message.name);
   // sprintf(command,"./cautare.sh %s",message.name);
    /*

    FILE* output = popen (command,"r");
    fscanf (output,"%d",&message.result);

    message.type = RES;
    
    msgsnd (msgqueue,&message, sizeof(struct Msg) - sizeof (long), 0);

    msgctl (msgqueue,IPC_RMID,0);//stergere coada de mesaje
*/
    free (message.name); 
    return 0;
}
