%====================== db_copy/3             ===============================
DOMAINS
  DB_SELECTOR = mydba

GOAL
/* You may need to add a path to REGISTER.BIN */
	db_open(mydba,"register.bin",in_file),
	db_copy(mydba,"register",in_memory),
	db_close(mydba),
	db_open(mydba,"register",in_memory),
	% Continue with database in ems
	db_copy(mydba,"dd.bin",in_file),
	db_close(mydba),
	db_delete("register",in_memory).
