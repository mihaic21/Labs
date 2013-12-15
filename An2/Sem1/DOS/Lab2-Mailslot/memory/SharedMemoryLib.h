void CreateSharedMemoryArea(void);
void OpenSharedMemory(void);
void DestroySharedMemoryArea (void);
void WriteOnSharedMemory(int data, int position);
void ReadFromSharedMemory(int *data, int position);