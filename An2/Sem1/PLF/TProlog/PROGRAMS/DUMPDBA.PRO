/*****************************************************************************

		DUMP CONTENTS OF DATABASE TO TEXTFILE

 The predicate dumpdba can be used to dump the contents of an external
 database into a textfile, if the database follow certain conditions.

 It is assumed that every chain in the database models a relation. It is
 assumed that indexes can be generated from the relations, so the B+Trees
 is not dumped.

 It is required that all terms in the database is stored under the same
 domain !.
 
 The name of the domain is here called mydom, it should be substituted
 by the actual name, and a proper declaration.

 The contents of the database is writen in the textfile, which is opened
 by oufile. In each line a term and the name of the chain is written. The
 term and the chain name is combined into the domain CHAINTERM. The database
 can a later be loded again by using readterm with the CHAINTERM domain.


*****************************************************************************/

DOMAINS
  DB_SELECTOR	= mydba
  CHAINTERM	= chain(STRING,mydom)
  FILE		= outfile

  mydom		= city(CITYNO,CITYNAME);
  		  person(FIRSTNAME,LASTNAME,STREET,CITYNO,CODE)
  CITYNO, CITYNAME, FIRSTNAME, LASTNAME, STREET, CODE = STRING


PREDICATES
  wr(CHAINTERM)
  dumpdba(STRING,STRING)

CLAUSES
  wr(X):-write(X),nl.

  dumpdba(DBASE,OUTFILE):-
	db_open(mydba,DBASE,in_file),
	openwrite(outfile,OUTFILE),
	writedevice(outfile),
	db_chains(mydba,CHAIN),
	chain_terms(mydba,CHAIN,mydom,TERM,_),
	wr(chain(CHAIN,TERM)),
	fail.
  dumpdba(_,_):-
	closefile(outfile),
	db_close(mydba).

GOAL dumpdba("register.bin","register.txt").
