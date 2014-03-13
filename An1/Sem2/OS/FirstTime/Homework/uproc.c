#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <string.h>
#include <unistd.h>

int numar(char * sir) {
	int j;
	int lung = strlen(sir);
	int rez = 0;

	for(j=0; j<lung; j++) {
		if (sir[j]<48 || sir[j]>58) return -1;
		rez = rez*10+sir[j]-48;	
	}
	return rez;
}

int prime(int n, int m) {
	int j;
	for(j=2;j<=n;j++)
		if (n%j==0 && m%j==0) return 0;
	return 1;
}


int main(int argc, char * argv[]) {
	int i,child;
	int nr1,nr2, status;
	int nrp=0, nr=0;
	for(i=1; i<=(argc-1)/2; i++ ) {		
		nr1 = numar(argv[i]);	
		nr2 = numar(argv[argc-i]);
		child = fork();
		if (child==0) {
			if ( (nr1>0) && (nr2>0) && prime(nr1,nr2) ) exit(0);
			if ( (nr1<0) && (nr2>0) ) exit(1);
			if ( (nr1>0) && (nr2<0) ) exit(2);
			if ( (nr1<0) && (nr2<0) ) exit(3);
			exit(4);
		}
	}
	for(i=1; i<= (argc-1)/2; i++) {
		waitpid(-1, &status, 0);
		status >>=8;
		if (status==0) {
			nrp++;
			nr+=2;
		}
		if (status==1 || status==2)
			nr++;
		if (status==4)
			nr+=2;
//		printf("Status: %d\n",status);
	}
	if ( (argc-1)%2==1)
		if (numar(argv[((int)(argc-1)/2)+1])>0)
			nr++;
	printf("Prime pairs: %d\nNumbers: %d\n",nrp,nr);
	return 0;
}
