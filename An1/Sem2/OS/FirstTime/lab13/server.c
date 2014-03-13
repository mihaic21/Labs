#include<stdio.h>
#include<sys/types.h>
#include<sys/ipc.h>
#include<sys/msg.h>
#include<stdlib.h>
#include<string.h>
#include<unistd.h>
#include<sys/wait.h>
#include<signal.h>

typedef struct{
	long mtype;
	int nr;
}Msg;

int killserver = 0;

void kills(){
	killserver = 1;
}

int main(){
	signal(SIGINT,kills);
	int key = 1392;
	Msg* msg = malloc(sizeof(Msg));
	msg->mtype = 1;
	msg->nr = 0;
	int id = msgget(key,0666|IPC_CREAT);
	if (id<0) {
		perror ("Error creating message queue!");
		exit(0);
	}
	while (1){
		if (killserver == 1){
			msgctl(id,IPC_RMID,NULL);
			exit(0);
		}
		msgrcv(id,msg,sizeof(Msg),2,0);
		printf("Got the number %d\n", msg->nr);
		msg->mtype = 1;
		msg->nr = msg->nr * 2;
		msgsnd(id,msg,sizeof(Msg),0);
	}
	msgctl(id,IPC_RMID,NULL);
	free(msg);
	return 0;
}
