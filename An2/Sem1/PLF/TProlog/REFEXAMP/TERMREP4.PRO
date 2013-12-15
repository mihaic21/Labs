%====================== term_replace/4        ===============================
DOMAINS
  DB_SELECTOR = mydba
  dbdom = f(integer,char,real,string)

GOAL	db_open(mydba,"dd.bin",in_file),
	chain_first(mydba,mychain,FIRSTREF),
	term_replace(mydba,dbdom,FIRSTREF,f(1,'x',88.99,"Turbo Prolog")),
	db_close(mydba).
