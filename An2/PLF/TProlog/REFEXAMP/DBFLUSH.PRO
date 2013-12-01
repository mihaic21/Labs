%====================== db_flush              ===============================
DOMAINS
  DB_SELECTOR = mydba

GOAL
/* You may need to add a path to REGISTER.BIN */
	db_open(mydba,"register.bin",in_file),
	% do some updating
	db_flush(mydba).
