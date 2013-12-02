#include <windows.h>
#include <stdio.h>
#include <string.h>
#include <cstdio>

int main() {
	DWORD x;
	int n;
	char fileName[MAX_PATH];
	char result[MAX_PATH];
	printf ("I am the server\n");
	
	//creating mailslot
	HANDLE hSlot;
	//char *slotName = TEXT("\\\\.\\mailslot\\lab2_mailslot");
	hSlot = CreateMailslot (TEXT("\\\\.\\mailslot\\lab2_mailslot"), 0, MAILSLOT_WAIT_FOREVER, NULL);
	if (hSlot == INVALID_HANDLE_VALUE)
		printf("CreateMailslot failed with %d\n", GetLastError());
	else printf ("Mailslot created successfully.\n");
	
	//HANDLE hSlot2;
	//hSlot2 = CreateFile(TEXT("\\\\.\\mailslot\\lab2_mailslot2"), GENERIC_WRITE, FILE_SHARE_WRITE, NULL, OPEN_EXISTING, 0, NULL);


	while (1){
		
		//reading from mailslot
		bool fResult = ReadFile(hSlot, &n, sizeof(int), &x, NULL);
		if (!fResult)
			printf("ReadFile failed with %d.\n", GetLastError());
		else printf("Successfully read from file: %d\n", n);
		
		//finding specific file
		WIN32_FIND_DATA FindFileData;
		BOOL hFind;
		HANDLE firstFind = FindFirstFile("D:\\School\\Sem 3\\SOD\\Labs\\Lab2-Mailslot\\*.txt", &FindFileData);
		hFind = FindNextFile(firstFind, &FindFileData);
		int ok = 1;
		while (FindNextFile (firstFind, &FindFileData)) {
			if (FindFileData.nFileSizeLow >= n) {
				strcpy (fileName, FindFileData.cFileName);
				ok = 0;
				break;
			}
		}
		
		if (ok == 0) {
			strcpy (result,fileName);
			FILE *foundFile = fopen(fileName,"r");
			char code[MAX_PATH];
			size_t n = 0;
			int c;
			while (n<=100){
				c = fgetc(foundFile);
				code[n++] = (char) c;
			}
			code[n] = '\0';
			strcat(result,"\n");
			strcat(result,code);
		} else strcpy (result,"File not found!\n");
		//printf("%s\n",result);
		//write to mailslot
		HANDLE hSlot2;
		hSlot2 = CreateFile(TEXT("\\\\.\\mailslot\\lab2_mailslot2"), GENERIC_WRITE, FILE_SHARE_WRITE, NULL, OPEN_EXISTING, 0, NULL);
		
		bool fReturnResult = WriteFile(hSlot2, result, strlen(result)+1, &x, NULL);
		if (!fReturnResult)
			printf("WriteFile2 failed with %d.\n", GetLastError());
		else printf("Slot2 written to successfully.\n");
		CloseHandle(hSlot2);
	}
	
	CloseHandle(hSlot);
	
	return 0;
}
