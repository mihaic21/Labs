%====================== key_insert/4          ===============================
DOMAINS
  DB_SELECTOR = mydba
  dbdom	= city(CITYNO,CITYNAME);
  	  person(FIRSTNAME,LASTNAME,STREET,CITYNO,CODE)
  CITYNO, CITYNAME, FIRSTNAME, LASTNAME, STREET, CODE = STRING

GOAL	db_open(mydba,"dd.bin",in_file),
	bt_open(mydba,"person_name",BT_SELECTOR),
	chain_inserta(mydba,namechain,dbdom,person("per","bilse","Aptos Beach","80386","code"),REF),
	key_insert(mydba,BT_SELECTOR,"bilse      per",REF),
	db_close(mydba).
