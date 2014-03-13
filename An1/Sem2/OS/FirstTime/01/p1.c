#include <stdio.h>
#include <stdlib.h>

#define N 30

#ifdef FORDEBUG
#define log(msg) printf("%s:%d - %s\n", __FILE__, __LINE__, msg)
#else
#define log(msg)
#endif

int main() {
    int i;
    int sum;
    int arr[N];

    log("");
    for(i=0; i<N; i++) {
        log("");
        arr[i] = rand() % 10;
        log("");
    }

    log("");
    sum = 0;
    for(i=0; i<N; i++) {
        sum += arr[i];
    }

    printf("Sum is %d\n", sum);

    return 0;
}
