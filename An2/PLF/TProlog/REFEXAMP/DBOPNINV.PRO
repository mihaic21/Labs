%======================  db_openinvalid/3             ===============================
DOMAINS
  DB_SELECTOR = mydba
  dbdom	= city(CITYNO,CITYNAME);
  	  person(FIRSTNAME,LASTNAME,STREET,CITYNO,CODE)
  CITYNO, CITYNAME, FIRSTNAME, LASTNAME, STREET, CODE = STRING

GOAL
/* You may need to add a path to REGISTER.BIN */
	db_openinvalid(mydba,"register.bin",in_file),
	% Now you should only dump the database to a textfile
	db_chains(mydba,CHAIN),
	chain_terms(mydba,CHAIN,dbdom,TERM,REF),
	write("\nTERM=",TERM,", REF=",REF),
	fail.
