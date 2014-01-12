#include <rpc/rpc.h>
#include <rpc/xdr.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <fcntl.h>
 
#define PROGRAM_EXEC  ((u_long)0x40000000)
#define VERSIUNE_EXEC ((u_long)1)
#define EXEC_MAX     ((u_long)1)
 
typedef struct {
        char str[100];
        char ip[20];
        int port;
}MsgSnd;
 
int xdr_msgsnd(XDR *xdr, MsgSnd *snd){
        /*int i;
        for (i = 0; i <= strlen(snd->ip); i++)
                if (xdr_char(xdr, &snd->ip[i]) == 0) return 0;
 
        for (i = 0; i <= strlen(snd->str); i++)
                if (xdr_char(xdr, &snd->str[i]) == 0) return 0;
        */
        if(xdr_str(xdr,snd->str) == 0) return 0;
        if(xdr_str(xdr,snd->ip) == 0) return 0;
        if(xdr_int(xdr, &(snd->port)) == 0) return 0;
        return 1;
}
 
int xdr_str(XDR *xdr, char *s) {
    int i;
    for (i = 0; i <= strlen(s); i++)
        if (xdr_char(xdr, &s[i]) == 0) return 0;
    return 1;
}