%======================== db_* and bt_*        ================================
/* Use for: db_create, db_delete
       and: bt_create, bt_delete
*/       
DOMAINS
  DB_SELECTOR = mydba

GOAL	db_create(mydba,"dd.bin",in_file),
	bt_create(mydba,"personnames",BT_SELECTOR,10,4),
	% ...... Update and access the database here
	bt_close(mydba,BT_SELECTOR),
	bt_delete(mydba,"personnames"),
	db_close(mydba),
	db_delete("dd.bin",in_file).
