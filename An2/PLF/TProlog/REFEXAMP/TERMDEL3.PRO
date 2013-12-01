%====================== term_delete/3         ===============================
DOMAINS
  DB_SELECTOR = mydba
  dbdom = f(integer,char,real,string)

GOAL	db_open(mydba,"dd.bin",in_file),
	chain_first(mydba,mychain,FIRSTREF),
	term_delete(mydba,mychain,FIRSTREF),
	db_close(mydba).
