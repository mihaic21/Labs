#include "header.h"

using namespace std;

struct sockaddr_in listen_addr, multicast_addr;
int sock;

set<char*> subscribedRooms;
set<char*> otherRooms;
int roomsWereRequested = 0;
char nickname[32];

pthread_mutex_t list_mtx = PTHREAD_MUTEX_INITIALIZER;

void err(char* msg){
		perror(msg);
		exit(1);
}

int roomExists(set<char*> *list, char roomName[]){
		int found = 1;
		for(set<char*>::iterator it = list->begin(); it != list->end(); ++it){
				if(strcmp(*it,roomName) == 0){
					found = 0;
					break;
					}
			}
		return found;	
}
	
//list of rooms separated by NULLs	
void addAvailableRooms(char rooms[], int size){
		char *room;
		int begin = 0;
		int end;
		for(int i = 0; i < size - 1; i++){
			if(rooms[i] == 0){
				end = i;
				room = (char*)malloc(end - begin + 1);
				strncpy(room, rooms + begin, end - begin + 1);
				begin = end + 1;
				
				pthread_mutex_lock(&list_mtx);
				if(roomExists(&otherRooms, room) == 1)
					otherRooms.insert(room);
				pthread_mutex_unlock(&list_mtx);
				}
			}		
}	

void *readCmd(void *param){
	char cmd[MAXLEN], room[32], nick[32], buff[MAXLEN], *aux;
	msg message;
	int needToSend;
	
	while(1){
		bzero(cmd, sizeof(cmd));
		bzero(room, sizeof(room));
		bzero(nick, sizeof(nick));
		bzero(buff, sizeof(buff));
		bzero(&message, sizeof(message));
		
		printf("Command: \n");
		//read anything but enter
		scanf("%[^\n]",cmd);
		getchar();
		
		if(sscanf(cmd, "NICK %s", nick) == 1){
				strcpy(nickname,nick);
				printf("You have set your nickname to: %s\n", nickname);
				needToSend = 0;
				
		}else if(sscanf(cmd, "JOIN %s", room) == 1){
			
				if(nickname[0] == 0) { // aka nikename not set
					printf("\nPlease choose a nickname first!\n");
					//back to where you were
					continue;
				}
				
				needToSend = 1;
				message.oper = 1;
				strcpy(message.roomName,room);
				
				aux = (char*)malloc(strlen(room) + 1);
				strncpy(aux, room, strlen(room) + 1);
				if(roomExists(&subscribedRooms, aux) == 1) {
						printf("You've been subscribed to room: %s\n",room);
						subscribedRooms.insert(aux);
				}else{
						needToSend = 1;
						printf("You're already subscribed to room: %s\n",room);
					 }
				
				printf("You are subscribed to %u rooms now!\n",(unsigned int)subscribedRooms.size());
				
		}else if(sscanf(cmd, "LEAVE %s", room)){
			
				if(nickname[0] == 0) { // aka nikename not set
					printf("\nPlease choose a nickname first!\n");
					//back to where you were
					continue;
				}
				
				if(roomExists(&subscribedRooms,room) == 1) {
					printf("You need to join the room in order to leave!\n");
					needToSend = 0;
					continue;
				}
				
				needToSend = 1;
				message.oper = 2;
				strcpy(message.roomName,room);
				
				aux = (char*)malloc(strlen(room) + 1);
				strncpy(aux, room, strlen(room) + 1);
				
				set<char*>::iterator it;
				for(it = subscribedRooms.begin(); it != subscribedRooms.end(); ++it)
					if(strcmp(*it, aux) == 0)
						subscribedRooms.erase(it);
					
				printf("You have left the room %s\n", room);
				
		}else if(strcmp(cmd, "QUIT") == 0){
			message.oper = 2;
			strcpy(message.ver, VERSION);
			strcpy(message.id, nickname);
			
			if(nickname[0] != 0){ //aka nickname is not NULL
				//send unsubscribe req for all subscribtions!!
				set<char*>::iterator it;
				for(it = subscribedRooms.begin(); it != subscribedRooms.end(); ++it){
					bzero(message.roomName, sizeof(message.roomName));
					strcpy(message.roomName, *it);
					sendto(sock, &message, sizeof(message), 0, (struct sockaddr*)&multicast_addr, sizeof(multicast_addr));
				}
			}
			
			sleep(1);
			break;
			
		}else if(sscanf(cmd, "MSG %s %s", room, buff) == 2){
			
			if(nickname[0] == 0) { // aka nikename not set
					printf("\nPlease choose a nickname first!\n");
					//back to where you were
					continue;
			}
		
			if(roomExists(&subscribedRooms,room) == 1) {
					printf("You need to join the room in order to send a message!\n");
					needToSend=0;
					continue;
			}
			
			needToSend = 1;
			message.oper = 3;
			
			strcpy(message.roomName, room);
			strcpy(message.buff, cmd + strlen(room) + 5); 
			message.len = strlen(message.buff);
		
		}else if(strcmp(cmd, "LIST") == 0){
			
			needToSend = 1;
			message.oper = 4;
			roomsWereRequested = 1;
		
		}else if (strcmp(cmd, "HELP") == 0){
			printf("Commands list:\n");
			printf("1. NICK <nickname>\n");
			printf("2. JOIN <roomName>\n");
			printf("3. MSG <roomName> <message>\n");
			printf("4. LEAVE <roomName>\n");
			printf("5. LIST\n");
			printf("6. QUIT\n");
		
		}else{
		
			printf("Invalid Command!\n");
			needToSend = 0;
		}
		
		if(needToSend == 1){
			strcpy(message.ver, VERSION);
			strcpy(message.id, nickname);
			sendto(sock, &message, sizeof(message), 0, (struct sockaddr*) &multicast_addr, sizeof(multicast_addr));
			
			if(roomsWereRequested == 1){
			
				sleep(2);
				pthread_mutex_lock(&list_mtx);
				if(subscribedRooms.empty()) 
					printf("There are no subscriptions!\n");
				else{
					printf("You are subscribed to: \n");
					
					for(set<char*>::iterator it = subscribedRooms.begin(); it != subscribedRooms.end(); ++it)
					printf("	-%s\n", *it);
					}
					
				if(otherRooms.empty()) 
					printf("There are no rooms available!\n");
				else{
					printf("Available rooms: \n");
					
					for(set<char*>::iterator it = otherRooms.begin(); it != otherRooms.end(); ++it)
					printf("	-%s\n", *it);
					}
					
				pthread_mutex_unlock(&list_mtx);
			}
			roomsWereRequested = 0;
		}
	}
	close(sock);
	exit(0);
}

void *processCmd(void *param){

	arg *tharg = (arg*) param;
	int good = 0;
	
	if(strcmp(VERSION, tharg->message.ver) == 0){
		if(tharg->message.oper == 1 || tharg->message.oper == 2 || tharg->message.oper == 3){
			for(set<char*>::iterator it = subscribedRooms.begin(); it != subscribedRooms.end(); it++){
				if(strcmp(*it, tharg->message.roomName) == 0){
					good = 1;
					break;
				}
			}
		}else if(tharg->message.oper == 4 || tharg->message.oper == 5) 
			good = 1;
	}
	
	if(good == 0){
		free(tharg);
		pthread_exit(0);
	}

	if(tharg->message.oper == 1) {
		printf("\n<%s> %s has joined the room!\n", tharg->message.roomName, tharg->message.id);
	}
	else if(tharg->message.oper == 2) {
		printf("\n<%s> %s has left the room!\n", tharg->message.roomName, tharg->message.id);
	}
	else if(tharg->message.oper == 3) {
		printf("\n<%s> %s: %s\n", tharg->message.roomName, tharg->message.id, tharg->message.buff);
	}
	else if(tharg->message.oper == 4){
		
		msg listOfRooms;
		bzero(&listOfRooms, sizeof(listOfRooms));
		
		printf("%s from %s:%d asked for my rooms\n",tharg->message.id, inet_ntoa(tharg->address.sin_addr), ntohs(tharg->address.sin_port));

		uint16_t size = 0;
		for(set<char*>::iterator it = subscribedRooms.begin(); it != subscribedRooms.end(); ++it) {
			strcpy(listOfRooms.buff + size, *it);
			size += strlen(*it) + 1;
		}
		size++;

		strcpy(listOfRooms.ver, VERSION);
		strcpy(listOfRooms.id, nickname);
		listOfRooms.oper = 5;
		listOfRooms.len = size;

		sendto(sock, &listOfRooms, sizeof(struct msg), 0, (struct sockaddr*)&tharg->address, sizeof(tharg->address));
	}
	else if(tharg->message.oper == 5) {
		if(roomsWereRequested) 
			addAvailableRooms(tharg->message.buff,tharg->message.len);
		
	}
	free(tharg);
	pthread_exit(0);
}

int main(int argc, char* argv[]){

		if(argc < 2) err((char*)"Please enter multicast address!\n");
		printf("Welcome to the multicast chat!\n");
		printf("Type HELP for commands...\n");

		pthread_t thInput, thProcess;
		int sock_option = 1;
		socklen_t slen = sizeof(listen_addr);
		int ttl = 1;
		struct ip_mreq multicast_req;
		bzero(&nickname, sizeof(nickname));
		
		if((sock = socket(PF_INET, SOCK_DGRAM, IPPROTO_UDP)) == -1)
		err((char*)"Problems socket(...)\n");

		if (setsockopt(sock, SOL_SOCKET, SO_REUSEADDR, &sock_option, sizeof(sock_option)) < 0)
		err((char*)"Problems setsockopt(...) SO_REUSEADDR\n");
		
		bzero(&listen_addr, sizeof(listen_addr));
		listen_addr.sin_family = AF_INET;
		listen_addr.sin_port = htons(PORT);
		listen_addr.sin_addr.s_addr = htonl(INADDR_ANY);

		bzero(&multicast_addr, sizeof(multicast_addr));
		multicast_addr.sin_family = AF_INET;
		multicast_addr.sin_port = htons(PORT);
		multicast_addr.sin_addr.s_addr = inet_addr(argv[1]);
		
		if (bind(sock, (struct sockaddr* ) &listen_addr, sizeof(listen_addr)) == -1)
			err((char*)"Problems bind(...)\n");

		multicast_req.imr_multiaddr.s_addr = inet_addr(argv[1]);
		multicast_req.imr_interface.s_addr = htonl(INADDR_ANY);

		if(setsockopt(sock, IPPROTO_IP, IP_ADD_MEMBERSHIP, (void*)&multicast_req, sizeof(multicast_req)) < 0)
			err((char*)"Problems setsockopt(...) IP_ADD_MEMBERSHIP");
		if(setsockopt(sock, IPPROTO_IP, IP_MULTICAST_TTL, (void*)&ttl, sizeof(ttl)) < 0)
			err((char*)"Problems setsockopt(...) IP_MULTICAST_TTL");

		//this little thread reads commands
		pthread_create((pthread_t*)&thInput, NULL, readCmd, NULL);
		
		msg data;
		while(1){
			bzero(&data, sizeof(data));
			
			if(recvfrom(sock, &data, sizeof(data), 0, (struct sockaddr*)&listen_addr, &slen)  == -1)
				err((char*)"ERR: recvfrom()\n");
			
			arg *tharg = (arg*) malloc(sizeof(arg));
			tharg->message = data;
			tharg->address = listen_addr;
			
			//this little thread interprets commands
			pthread_create((pthread_t*)&thProcess, NULL, processCmd, (void*)tharg);
		}
		
	close(sock);
	return 0;
}

