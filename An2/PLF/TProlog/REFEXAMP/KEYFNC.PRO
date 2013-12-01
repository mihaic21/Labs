%========== key_first/3, key_next/3, key_current/4 ===========================
DOMAINS
  DB_SELECTOR = mydba

PREDICATES
   % List all keys in an index
   list_keys(db_selector, Bt_selector)

CLAUSES
  list_keys(Db_selector, Bt_selector):-
	key_current(Db_selector, Bt_selector, Key, _),
	write(Key, '\n'),
	fail.
  list_keys(Db_selector, Bt_selector) :-
	key_next(Db_selector, Bt_selector, _), !,
	list_keys(Db_selector, Bt_selector).
  list_keys(_, _).

GOAL
/* You may need to add a path to REGISTER.BIN */
	db_open(mydba,"register.bin",in_file),
	bt_open(mydba,"person_name",BT_SELECTOR),
	key_first(mydba,BT_SELECTOR,_),
	list_keys(mydba,BT_SELECTOR).
