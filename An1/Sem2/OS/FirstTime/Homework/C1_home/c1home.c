#include<stdio.h>
int main(){
	int n,i;
	printf("n= ");
	scanf("%d",&n);
	for (i=1;i<n;i++){
		if (gcd(i,n) == 1){
			printf("%d\n",i);
		}
	}
}

int gcd(int x,int y){
	if (x == 0){
		return y;
	} else if (y == 0){
		return x;
	} else {
		while (x != y) {
			if (x>y) {
				x = x-y;
			} else {
				y = y-x;
			}
		}
		return x;
	}
}
