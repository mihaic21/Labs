#include <stdio.h>
#include <stdlib.h>

#define debug(m) printf("%s:%d %s\n", __FILE__, __LINE__, m)

#define ma(p,n) { \
  p = (void*) malloc(n); \
  printf("%s:%d allocated %d bytes at address %p\n", __FILE__, __LINE__, n, p);\
}

#define fr(p) {\
  free(p);\
  printf("%s:%d freed address %p\n", __FILE__, __LINE__, p);\
}

int main() {
    int* t = (int*)&main;
    int i;
    int n;

    scanf("%d", &n);
    int arr[n];
    int r = 0x01010101;

    printf("%u\n", &r-arr);

    for(i=0; i<n; i++) {
        arr[i] = i;
    }
    printf("%x\n", r);

    int* p;
    ma(p, 100*sizeof(int));

    for(i=0; i<100; i++) {
      p[i] = i;
    }
    fr(p);

    t[10] = 5;

    return 0;
}
