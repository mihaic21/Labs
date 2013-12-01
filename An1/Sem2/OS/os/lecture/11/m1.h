#define KEY 1234

#define REQ 1
#define RES 2

struct mymsg {
    long mtype; /* This must always be the first thing in the struct */
    int value;
};
