#include <stdio.h>

int main() {
    int a[3];
    int i;

    for(i=0; i<3; i++) {
        scanf("%d", &a[i]);
    }

    for(i=0; i<3; i++) {
        printf("a[%d]=%d\n", i, a[i]);
    }
    return 0;
}
