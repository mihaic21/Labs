/*
sa se scrie un program care creaza o ierarhie de procese astfel incat
procesul initial sa fie strabunic al celui mai tanar proces
*/

#include <stdio.h>
#include <stdlib.h>
main() {
	int p1;
	p1 = fork();
	if (p1 == 0) {
		int p2;
		p2 = fork();
		if (p2 == 0) {
			int p3;
			p3 = fork();
			if (p3 == 0) {
				printf ("Stranepot: %d; ppid=%d\n", getpid(), getppid());
				exit(0);
			}
			wait(0);
			printf("Nepot: %d; ppid=%d\n", getpid(), getppid());
			exit(0);
		}
		wait(0);
		printf("Fiu: %d; ppid=%d\n", getpid(), getppid());
		exit(0);
	}
	wait(0);
	printf("Parinte: %d\n", getpid());
	return 0;
}

