#include<stdio.h>
#include<stdlib.h>
#include<unistd.h>
#include<sys/types.h>
#include<sys/wait.h>

int main(){
	int a,b,child,sum;
 	int p2c[2];
 	int c2p[2];
	printf("a = ");
 	scanf("%d",&a);
	printf("b = ");
	scanf("%d",&b);
	pipe(p2c);
	pipe(c2p);
	
	child=fork();
	if (child==0){
		int a,b,sum;
		read(p2c[0],&a,sizeof(int));
		read(p2c[0],&b,sizeof(int));
		sum=a+b;
		write(c2p[1],&sum,sizeof(int));
		exit(0);
	}
	write(p2c[1],&a,sizeof(int));
	write(p2c[1],&b,sizeof(int));
	read(c2p[0],&sum,sizeof(int));
	printf("sum = %d\n",sum);
	close(p2c[0]);
	close(p2c[1]);
	close(c2p[0]);
	close(c2p[1]);
	wait(0);
	return 0;		
}

	

 
