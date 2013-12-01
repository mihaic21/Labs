%====================== chain_inserta/5       ===============================
DOMAINS
  DB_SELECTOR = mydba
  dbdom = f(integer,char,real,string)

GOAL	db_open(mydba,"dd.bin",in_file),
	chain_inserta(mydba,mychain,dbdom,f(1,'x',88.99,"Turbo Prolog"),_),
	db_close(mydba).
