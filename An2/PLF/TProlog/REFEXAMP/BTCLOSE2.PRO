%======================  bt_close/2        ===============================
DOMAINS
  DB_SELECTOR = mydba

GOAL	db_open(mydba,"dd.bin",in_file),
	bt_open(mydba,"personnames",BtreeSel),
% ......... Update and access the databse here........
        bt_close(mydba,BtreeSel),
        db_close(mydba).
