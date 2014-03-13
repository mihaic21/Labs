#include <stdio.h>

int main() {
    int* a;
    int n;
    int i;

    printf("number of ints=");
    scanf("%d", &n);

    a = (int*)malloc(n*sizeof(int));

    for(i=0; i<n; i++) {
        printf("a[%d]=", i);
        scanf("%d", &a[i]);
    }
    
    for(i=n-1; i>=0; i--) {
        printf("%d\n", a[i]);
    }

    free(a);

	return 0;
}
