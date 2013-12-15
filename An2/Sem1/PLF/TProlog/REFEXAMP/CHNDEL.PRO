%======================  chain_delete/2       ===============================
DOMAINS
  DB_SELECTOR = mydba

GOAL	db_open(mydba,"ddr.bin",in_file),
	chain_delete(mydba,names).
