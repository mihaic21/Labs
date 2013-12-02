//#include "StdAfx.h"
#include <windows.h>
#include <stdio.h>
#include <string.h>

int main() {
	HANDLE f1, f2;
	char s[128];
	DWORD x;

	printf("I am the client \n");

	// connect to pipes created by server
	f1=CreateFile(TEXT("\\\\.\\PIPE\\fifo1"), GENERIC_WRITE, FILE_SHARE_WRITE, NULL, OPEN_EXISTING, 0, NULL);
	f2=CreateFile(TEXT("\\\\.\\PIPE\\fifo2"), GENERIC_READ, FILE_SHARE_READ, NULL, OPEN_EXISTING, 0, NULL);
	
	// send message through pipe
	strcpy_s(s, 128, "Hello world!");
	if (WriteFile(f1, s, strlen(s)+1, &x, NULL)==0) {
		printf("writing error..%d\n", x);
	}

	// receive message through pipe
	if (ReadFile(f2, s, sizeof(s), &x, NULL)==0) {
		printf("reading error..%d\n", x);
	}

	printf("read from pipe: %s\n", s);
	// scanf("%s", s);

	// close connections
	CloseHandle(f1);
	CloseHandle(f2);
}