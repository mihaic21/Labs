%======================  bt_statistics/7    ===============================
DOMAINS
  DB_SELECTOR = mydba

GOAL
/* You may need to add a path to REGISTER.BIN */
	db_open(mydba,"register.bin",in_file),
	bt_open(mydba,"person_name",BT_SELECTOR),
	bt_statistics(mydba,BT_selector,NoOfKeys,NoOfPages,Dept,KeyLen,Order,PageSize),
	writef("\nNoOfPages=%, NoOfKeys=% ,Dept=% ,Order=% ,KeyLen=% ,PageSize=%",
		NoOfPages,NoOfKeys,Dept,Order,KeyLen,PageSize).

% NoOfPages=3, NoOfKeys=10 ,Dept=2 ,Order=4 ,KeyLen=35 ,PageSize=358
