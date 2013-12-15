#include "stdafx.h"
#include <Windows.h>
#include <stdio.h>
#include <conio.h>

#include "SharedMemoryLib.h"

HANDLE hFile;
LPINT hView, aux;     

void CreateSharedMemoryArea()
{
	hFile = CreateFileMapping(INVALID_HANDLE_VALUE, NULL, PAGE_READWRITE, 0, 1024 * 4, "SharedMemoryFN");
	if (hFile == NULL)
	{
		 printf("Unable to create the shared memory file.");
		 exit(1);
	}

	hView = (LPINT) MapViewOfFile(hFile, FILE_MAP_ALL_ACCESS, 0, 0, 0);
	if (hView == NULL)
	{
		 printf("Unable to map the memory segment file to process memory.");
		 exit(1);
	}
	printf("Shared memory has been successfully created....\n");

	aux = hView;
}    

void OpenSharedMemory()
{
	hFile = OpenFileMapping(FILE_MAP_ALL_ACCESS, FALSE, "SharedMemoryFN");            
	if (hFile == NULL)
	{
		 printf("Unable to open the shared memory area.\n");
		 exit(1);
	}

	hView = (LPINT) MapViewOfFile(hFile, FILE_MAP_ALL_ACCESS, 0, 0, 0);
	if (hView == NULL)
	{
		 printf("Unable to map the memory segment file to process memory.");
		 exit(1);
	}
	printf("Shared memory has been successfully opened....\n");

	aux = hView;
}

void DestroySharedMemoryArea ()
{
	if (!UnmapViewOfFile(hView)) 
	{ 
       printf("Could not unmap memory segment from process memory."); 
	} 
	
	CloseHandle(hFile); 
    
	printf("Shared memory has been successfully destroyed....\n");
}

void WriteOnSharedMemory(int data, int position)
{
	aux[position] = data;
}

void ReadFromSharedMemory(int *data, int position)
{
	*data = aux[position];
}