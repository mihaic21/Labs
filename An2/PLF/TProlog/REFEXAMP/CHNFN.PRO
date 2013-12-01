%====================== chain_first/3, chain_next/3 ============================
DOMAINS
  DB_SELECTOR = mydba
  dbdom	= city(CITYNO,CITYNAME);
  	  person(FIRSTNAME,LASTNAME,STREET,CITYNO,CODE)
  CITYNO, CITYNAME, FIRSTNAME, LASTNAME, STREET, CODE = STRING

PREDICATES
  rd(REF)

CLAUSES
  rd(REF):-
	ref_term(mydba,dbdom,REF,TERM),
	write(TERM),nl,
	fail.
  rd(REF):-
	chain_next(mydba,REF,NEXT),!,
	rd(NEXT).
  rd(_).

GOAL	
/* You may need to add a path to REGISTER.BIN */        
        db_open(mydba,"register.bin",in_file),
	db_chains(mydba,CHAIN),
	chain_first(mydba,CHAIN,REF),
	rd(REF).
