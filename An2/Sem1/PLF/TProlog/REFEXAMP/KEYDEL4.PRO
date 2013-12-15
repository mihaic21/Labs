%====================== key_delete/4          ===============================
DOMAINS
  DB_SELECTOR = mydba
  dbdom	= city(CITYNO,CITYNAME);
  	  person(FIRSTNAME,LASTNAME,STREET,CITYNO,CODE)
  CITYNO, CITYNAME, FIRSTNAME, LASTNAME, STREET, CODE = STRING

GOAL	db_open(mydba,"dd.bin",in_file),
	bt_open(mydba,"person_name",BT_SELECTOR),
	key_search(mydba,BT_SELECTOR,"bilse      per",REF),
	ref_term(mydba,dbdom,Ref,Term),
	write("\nTERM=",TERM),
	key_delete(mydba,BT_SELECTOR,"bilse      per",REF),
	db_close(mydba).
