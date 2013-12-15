%====================== db_btrees/2           ===============================

DOMAINS
  DB_SELECTOR = mydba

GOAL
/* You may need to add a path to REGISTER.BIN */
	db_open(mydba,"register.bin",in_file),
	db_btrees(mydba,BtreeName),
	write(BtreeName),nl,
	fail.
