%====================== chain_insertafter/6    ===============================
DOMAINS
  DB_SELECTOR = mydba
  dbdom = f(integer,char,real,string)

GOAL	db_open(mydba,"dd.bin",in_file),
	chain_first(mydba,mychain,FIRSTREF),
	chain_insertafter(mydba,mychain,dbdom,FIRSTREF,f(1,'x',88.99,"Turbo Prolog"),_),
	db_close(mydba).
