#include <stdbool.h>

typedef struct Node{
	int				ip;
	int				counter;
	char			lastMsg[20];	
	struct Node* 	next;
} Node;

Node* CreateNode(int ip, int counter, char* msg, Node* next){
	Node* e = malloc(sizeof(Node));
	e->ip = ip;
	e->counter = counter;
	strcpy(e->lastMsg, msg);
	e->next = next;
	return e;
}

Node* updateSend(Node* list){
	while(list != NULL && list->counter == 0){
		Node* temp = list;
		list = list->next;
		free(temp);
	}
	if(list == NULL){ return NULL;}
	else{ list->counter--; }

	Node* temp1 = list;
	Node* temp2 = list->next;
	while(temp2 != NULL){
		if(temp2->counter == 0){
			temp1->next = temp2->next;
			free(temp2);
			temp2 = temp1->next;	
		}else{
			temp2->counter--;
			temp1 = temp2;
			temp2 = temp2->next;	
		}
	}
	return list;
}

Node* updateRecv(Node* list, int ip, char* msg){
	if(list == NULL){
		return CreateNode(ip, 3, msg, NULL);
	}
	
	Node* e = list;
	while(e->next != NULL){
		if(e->ip == ip){
			e->counter = 3;
			strcpy(e->lastMsg, msg);
			return list;		
		}
		e = e->next;
	}
	if(e->ip == ip){
		e->counter = 3;
		strcpy(e->lastMsg, msg);
	}else{
		e->next = CreateNode(ip, 3, msg, NULL);
	}
	return list;	
}

void printIP(int addr){
	printf("%d.%d.%d.%d", (addr&0xFF000000)>>24, (addr&0xFF0000)>>16, (addr&0xFF00)>>8, addr&0xFF );
}

void printList(Node* list){
	Node* e = list;	
	while(e != NULL){
		printIP(e->ip);
		printf("\t\t%d\t\t\t\t", e->counter);
		printf("%s\n", e->lastMsg);
		e = e->next;
	}
	printf("\n");
}


//echo 0 >/proc/sys/net/ipv4/icmp_echo_ignore_broadcasts //if no ping

