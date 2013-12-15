%====================== db_chains/2           ===============================
/*
DOMAINS
  DB_SELECTOR = mydba

GOAL
/* You may need to add a path to REGISTER.BIN */
	db_open(mydba,"register.bin",in_file),
	db_chains(mydba,ChainName),
	write(ChainName),nl,
	fail.
