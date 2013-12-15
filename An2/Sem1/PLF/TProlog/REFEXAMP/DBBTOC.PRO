%===============    db_open,close  and bt_open,close   =======================
/* Use for: db_open, db_close
       and: bt_open, bt_close
*/     
DOMAINS
  DB_SELECTOR = mydba

GOAL	db_open(mydba,"dd.bin",in_file),
	bt_open(mydba,"personnames",BT_SELECTOR),
	% ...... Update and acces the database here
	bt_close(mydba,BT_SELECTOR),
	db_close(mydba).
