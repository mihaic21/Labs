#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <string.h>
#include <unistd.h>

int main(int argc, char * argv[]) {
	int i,child;
	char* s1;
    char* s2;
    char* command;
    FILE* output;
	s1 = (char*) malloc (30);
	s2 = (char*) malloc (30);
    command = (char*) malloc (70);
	for(i=1; i<=(argc); i+=2 ) {	
		s1 = argv [i];
		s2 = argv [i+1];
		child = fork();
		if (child==0) {
            sprintf (command,"gcc -Wall -o %s %s",s1,s2);
			output = popen (command,"r");
            pclose(output);
			exit(0);
		}
	}
    int status;
	for(i=1; i<=argc; i+=2) {
		waitpid(-1, &status, 0);
	}

    free (s1);
    free (s2);
    free (command); 
	return 0;
}
