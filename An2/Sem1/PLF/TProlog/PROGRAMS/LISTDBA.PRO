/*****************************************************************************

		LIST CONTENTS OF DATABASE

 The predicate listdba can be used to display the state of an external
 database. It is called with one parameter which is the selector of an
 already open database.

 It is required that all terms in the database is stored under the same
 domain !.
 
 The name of the domain is here called mydom, it should be substituted
 by the actual name.

*****************************************************************************/

DOMAINS
  DB_SELECTOR	= mydba
  mydom		= city(CITYNO,CITYNAME);
  		  person(FIRSTNAME,LASTNAME,STREET,CITYNO,CODE)
  CITYNO, CITYNAME, FIRSTNAME, LASTNAME, STREET, CODE = STRING


/*****************************************************************************

			BT_KEYS

Returns through backtracking all keys and their associated database
reference number for a given index and database.

*****************************************************************************/



PREDICATES
  nondeterm bt_keys(DB_SELECTOR,BT_SELECTOR,STRING,REF)
  nondeterm bt_keysloop(DB_SELECTOR,BT_SELECTOR,STRING,REF)

CLAUSES
  bt_keys(DB_SELECTOR,BT_SELECTOR,KEY,REF):-
	key_first(DB_SELECTOR,BT_SELECTOR,_),
	bt_keysloop(DB_SELECTOR,BT_SELECTOR,KEY,REF).

  bt_keysloop(DB_SELECTOR,BT_SELECTOR,KEY,REF):-
	key_current(DB_SELECTOR,BT_SELECTOR,KEY,REF).
  bt_keysloop(DB_SELECTOR,BT_SELECTOR,KEY,REF):-
	key_next(DB_SELECTOR,BT_SELECTOR,_),
	bt_keysloop(DB_SELECTOR,BT_SELECTOR,KEY,REF).

 	
PREDICATES
  listdba(DB_SELECTOR)

CLAUSES
  listdba(DB_SELECTOR):-
	write("\n***********************************************************"),
	write("\n                         DATABASE LISTING"),
	write("\n***********************************************************"),
	db_statistics(DB_SELECTOR,NoOfTerms,MemSize,DbaSize,FreeSize),
	write("\n\nTotal number of records in the database: ",NoOfTerms),
	write("\nNumber of bytes used in main memory: ",MemSize),
	write("\nNumber of bytes in used by the database: ",DbaSize),
	write("\nNumber of bytes free on disk: ",FreeSize),
	fail.

  listdba(DB_SELECTOR):-
	db_chains(DB_SELECTOR,CHAIN),
        write("\n\nPress key to continue"),
        readchar(_),
        clearwindow,	
        write("\n\n\n\n******************* CHAIN LISTING *******************"),
	write("\n\nName=",CHAIN),
	write("\n\nCONTENT OF: ",CHAIN),
	write("\n------------------------------\n"),
	chain_terms(DB_SELECTOR,CHAIN,mydom,TERM,REF),
	write("\n",REF,": ",TERM),
	fail.
  listdba(DB_SELECTOR):-
/*        write("\n\nPress key to continue"),
        readchar(_),
        clearwindow,*/
	db_btrees(DB_SELECTOR,BTREE),
	bt_open(DB_SELECTOR,BTREE,BT_SELECTOR),
	bt_statistics(DB_SELECTOR,BT_SELECTOR,
		NoOfKeys,NoOfPages,Dept,KeyLen,Order,PageSize),
        write("\n\nPress key to continue"),
        readchar(_),
        clearwindow,
	write("\n\n\n******************** INDEX LISTING *********************"),
	write("\nName=     ",BTREE),
	write("\nNoOfKeys= ",NoOfKeys),
	write("\nNoOfPages=",NoOfPages),
	write("\nDept=     ",Dept),
	write("\nOrder=    ",Order),
	write("\nKeyLen=   ",KeyLen),
	write("\nPageSize= ",PageSize),nl,
	write("\nCONTENT OF: ",BTREE),
	write("\n------------------------------"),
	bt_keys(DB_SELECTOR,BT_SELECTOR,KEY,REF),
	write("\n",KEY," - ",REF),
	fail.
  listdba(_).


GOAL	db_open(mydba,"register.bin",in_file),
        textmode(R,C),
        makewindow(1,7,7,"",0,0,R,C),
	listdba(mydba).
