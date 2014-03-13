#include <stdio.h>

void update(int* sequence, int* len) {
	int i = 0,j;
	while (i < *len) {
		if (sequence[i] % 2 == 1) {
			for (j=i;j<*len-1;j++) {
				sequence[j] = sequence[j+1];
			}
			*len = *len - 1;
			i--;
		}
		i++;
	}
}

int main() {
	int sequence[100];
	int len = 0;
	int i = 0;
	printf ("length = ");
	scanf ("%d",&len);
	for (i=0;i<len;i++) {
		printf ("number = ");
		scanf ("%d",&sequence[i]);
	}
	update(sequence,&len);
	for (i=0;i<len;i++) {
		printf("%d\n",sequence[i]);
	}
	return 0;
}

