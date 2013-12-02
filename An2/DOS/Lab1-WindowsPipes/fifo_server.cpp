//#include "StdAfx.h"
#include <windows.h>
#include <stdio.h>
#include <string.h>

int main() {
	HANDLE f1, f2;
	char s[128];
	DWORD x;
	
	printf("I am the server \n");

	// creating pipes
	f1=CreateNamedPipe(TEXT("\\\\.\\PIPE\\fifo1"), PIPE_ACCESS_INBOUND,PIPE_TYPE_BYTE|PIPE_WAIT, 3, 0, 0, 0, NULL);
	f2=CreateNamedPipe(TEXT("\\\\.\\PIPE\\fifo2"), PIPE_ACCESS_OUTBOUND,PIPE_TYPE_BYTE|PIPE_WAIT, 3, 0, 0, 0, NULL);
	ConnectNamedPipe(f1, NULL);
	ConnectNamedPipe(f2, NULL);
	
	// reading from pipe
	x = 0;
	if (ReadFile(f1, s, sizeof(s), &x, NULL)==0) {
		printf("reading error.. %d\n", x);
	}
	printf("read from pipe: %s\n", s);

	// writing to pipe
	strcpy(s, "Howdy!\n");
	if (WriteFile(f2, s, strlen(s)+1, &x, NULL)==0) {
		printf("writing error..%d\n", x);
	}
	
	// close pipes
	DisconnectNamedPipe(f1);
	DisconnectNamedPipe(f2);
	CloseHandle(f1);
	CloseHandle(f2);

}