#include <windows.h>
#include <stdio.h>
#include <string.h>

int main() {
	HANDLE f1,f2;
	char s[MAX_PATH];
	int n;
	DWORD x;
	char fileName[MAX_PATH];
	char result[MAX_PATH];
	
	printf ("I am the server\n");
	
	while (1) {
		f1=CreateNamedPipe(TEXT("\\\\.\\PIPE\\fifo1"), PIPE_ACCESS_INBOUND,PIPE_TYPE_BYTE|PIPE_WAIT, 3, 0, 0, 0, NULL);
		f2=CreateNamedPipe(TEXT("\\\\.\\PIPE\\fifo2"), PIPE_ACCESS_OUTBOUND,PIPE_TYPE_BYTE|PIPE_WAIT, 3, 0, 0, 0, NULL);
		ConnectNamedPipe(f1, NULL);
		ConnectNamedPipe(f2, NULL);
		
		//reading from pipe
		x=0;
		if (ReadFile(f1, &n, sizeof(n), &x, NULL) == 0) {
			printf ("reading error %d\n",x);
		}
		printf ("reading from pipe %d\n", n);
		
		//find files
		WIN32_FIND_DATA FindFileData;
		BOOL hFind;
		HANDLE firstFind = FindFirstFile("D:\\School\\Sem 3\\SOD\\Labs\\Lab1-WindowsPipes\\*.txt", &FindFileData);
		hFind = FindNextFile(firstFind, &FindFileData);
		int ok = 1;
		while (FindNextFile (firstFind, &FindFileData)) {
			if (FindFileData.nFileSizeLow >= n) {
				strcpy (fileName, FindFileData.cFileName);
				ok = 0;
				break;
				//printf ("%d\n",FindFileData.nFileSizeLow);
			}
		}
		
		//write to pipe
		
		if (ok == 0){
			strcpy (s,fileName);
			FILE *foundFile = fopen(fileName,"r");
			char code[MAX_PATH];
			size_t n = 0;
			int c;
			while (n<=100){
				c = fgetc(foundFile);
				code[n++] = (char) c;
			}
			code[n] = '\0';
			strcat(s,"\n");
			strcat(s,code);
			
			//printf("%s\n",fileName);
			//printf("%s",code);

			if (WriteFile (f2, s, strlen(s)+1, &x, NULL) == 0) {
				printf ("writing error %d\n", x);
			}
			fclose(foundFile);
		} else {
			strcpy (s,"File not found!");
			if (WriteFile (f2, s, strlen(s)+1, &x, NULL) == 0) {
				printf ("writing error %d\n", x);
			}
		}
		
		DisconnectNamedPipe(f1);
		DisconnectNamedPipe(f2);
		CloseHandle(f1);
		CloseHandle(f2);
		return 0;
	}
}