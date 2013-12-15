%====================== db_statistics/5       ===============================
DOMAINS
  DB_SELECTOR = mydba

GOAL
/* You may need to add a path to REGISTER.BIN */
	db_open(mydba,"register.bin",in_file),
	db_statistics(mydba,NoOfTerms,MemSize,DbaSize,FreeSize),
	write("\n\nTotal number of records in the database: ",NoOfTerms),
	write("\nNumber of bytes used in main memory: ",MemSize),
	write("\nNumber of bytes in used by the database: ",DbaSize),
	write("\nNumber of bytes free on disk: ",FreeSize).
