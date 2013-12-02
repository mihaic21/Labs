#include <windows.h>
#include <stdio.h>
#include <time.h>
#include <cstdlib>

int main() {
	HANDLE f1,f2;
	char s[MAX_PATH];
	DWORD x;

	srand(time(NULL));
	
	int n = rand() % 1000 + 500;
	
	printf("I am the client!\n");
	f1=CreateFile(TEXT("\\\\.\\PIPE\\fifo1"), GENERIC_WRITE, FILE_SHARE_WRITE, NULL, OPEN_EXISTING, 0, NULL);
	f2=CreateFile(TEXT("\\\\.\\PIPE\\fifo2"), GENERIC_READ, FILE_SHARE_READ, NULL, OPEN_EXISTING, 0, NULL);
	
	if (WriteFile (f1, &n, sizeof(n), &x, NULL) == 0) {
		printf ("writing error %d\n", x);
	}
	
	if (ReadFile(f2, s, sizeof(s), &x, NULL)==0) {
		printf("reading error %d\n", x);
	}

	printf("read from pipe: %s\n", s);
	
	CloseHandle(f1);
	CloseHandle(f2);
	return 0;
}