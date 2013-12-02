#include <windows.h>
#include <time.h>
#include <cstdio>
#include <cstdlib>

int main () {
	DWORD x;
	char result[MAX_PATH];
	srand(time(NULL));
	int n = rand() % 1000 + 500;
	printf("I am the client!\n");
	
	HANDLE hSlot;
	//char *slot = TEXT("\\\\.\\mailslot\\lab2_mailslot");
	hSlot = CreateFile(TEXT("\\\\.\\mailslot\\lab2_mailslot"), GENERIC_WRITE, FILE_SHARE_WRITE, NULL, OPEN_EXISTING, 0, NULL);
	
	HANDLE hSlot2;
	hSlot2 = CreateMailslot (TEXT("\\\\.\\mailslot\\lab2_mailslot2"), 0, MAILSLOT_WAIT_FOREVER, NULL);
	if (hSlot2 == INVALID_HANDLE_VALUE)
		printf("CreateMailslot2 failed with %d\n", GetLastError());
	else printf ("Mailslot2 created successfully.\n");
	
	
	//write to mailslot
	bool fResult = WriteFile(hSlot, &n, sizeof(int), &x, NULL);
	if (!fResult)
		printf("WriteFile failed with %d.\n", GetLastError());
	else printf("Slot written to successfully.\n");
	
	//read from mailslot

	bool fReturnResult = ReadFile(hSlot2, result, MAX_PATH, &x, NULL);
	if (!fReturnResult)
		printf("ReadFile2 failed with %d.\n", GetLastError());
	else printf("%s",result);
	
	CloseHandle(hSlot);
	CloseHandle(hSlot2);
	return 0;
}