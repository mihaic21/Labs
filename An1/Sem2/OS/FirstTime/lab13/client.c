#include<stdio.h>
#include<sys/types.h>
#include<sys/ipc.h>
#include<stdlib.h>
#include<string.h>
#include<sys/msg.h>
#include<unistd.h>
#include<sys/types.h>
#include<sys/wait.h>

typedef struct{
	long mtype;
	int nr;
}Msg;

int main(int argc, char* argv[]) {
	int key = 1392;
	int i;
	Msg *msg = malloc(sizeof(Msg));
	msg->mtype = 2;
	printf ("Number: ");
	scanf ("%d", &i);
	msg->nr=i;
	int id = msgget(key,0666);
	if (id<0) {
		perror("Something went wrong!");
	}
	msgsnd(id,msg,sizeof(Msg),0);
	msgrcv(id,msg,sizeof(Msg),1,0);
	printf("Got back: %d\n",msg->nr);
	free(msg);
	return 0;
}
