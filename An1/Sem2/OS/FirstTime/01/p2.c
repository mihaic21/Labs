#include <stdio.h>
#include <stdlib.h>

#define log(msg) printf("%s:%d - %s\n", __FILE__, __LINE__, msg)

#define ma(p,len) { \
    p = malloc(len); \
    printf("%s:%d - Allocated %d at %p\n", \
           __FILE__, __LINE__, len, p); \
}

#define mf(p) { \
    printf("%s:%d - Deallocate 1 %p\n", \
           __FILE__, __LINE__, p); \
    free(p); \
    printf("Deallocate 2 address %p\n", p); \
}


struct node {
    int value;
    struct node* left;
    struct node* right;
};

int arr[] = {1, 2, 4, 6, 7, 0, 0, 8, 0, 0, 0, 0,
             3, 5, 0, 9, 0, 0, 0};

struct node *root = NULL;

void printPRE(struct node* p) {
    if(p == NULL) {
        printf("0 ");
        return;
    }

    printf("%d ", p->value);

    printPRE(p->left);
    printPRE(p->right);
}

struct node* loadPRE(int* k) {
    struct node* p;

    if(arr[*k] == 0) {
        return NULL;
    }

    ma(p, sizeof(struct node));

    p->value = arr[*k];

    *k = *k + 1;
    p->left = loadPRE(k);

    *k = *k + 1;
    p->right = loadPRE(k);

    return p;
}

void deallocateTree(struct node* p) {
    if(p == NULL) {
        return;
    }

    deallocateTree(p->left);
    deallocateTree(p->right);

    mf(p);
}

int main() {
    int n = 0;

    root = loadPRE(&n);
    printPRE(root);
    printf("\n");
    deallocateTree(root);

    return 0;
}
