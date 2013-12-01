%====================== key_search/4          ===============================
DOMAINS
  DB_SELECTOR = mydba

PREDICATES
  locate(DB_SELECTOR,BT_SELECTOR,STRING)

CLAUSES
  locate(DB_SELECTOR,BT_SELECTOR,STRING):-
	key_search(DB_SELECTOR,BT_SELECTOR,STRING,_),!,
	write("\nExact match:").
  locate(_,_,_):-write("\nNot exact match:").

GOAL
/* You may need to add a path to REGISTER.BIN */
	db_open(mydba,"register.bin",in_file),
	bt_open(mydba,"person_name",BT_SELECTOR),
	locate(mydba,BT_SELECTOR,"Hoffman"),
	key_current(mydba,BT_SELECTOR,KEY,REF),
	write("\nKEY=",KEY,", REF=",REF).
