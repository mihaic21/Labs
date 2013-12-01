project "register"
check_determ

DOMAINS
  dom = reference f(integer,char,real,string)
  DB_SELECTOR = dba


PREDICATES
  write_dba(INTEGER)
  read_dba
  rd(REF)
  count_dba(INTEGER)
  count(REF,INTEGER,INTEGER)
  replace_dba
  replace(REF)
  double_dba
  double(REF)
  half_dba
  half(REF)
  mixture


CLAUSES

/*----------------------------------------------------------------------------*/

  write_dba(0):-!.
  write_dba(N):-
	chain_inserta(dba,names,dom,f(1,'x',88.99,"Prolog Development system"),_),
	chain_insertz(dba,names,dom,f(1,'x',88.99,"Prolog Compiler"),_),
/*	writef("\nREF1=% REF2=%",REF1,REF2), */
	N1=N-1,
	write_dba(N1).

/*----------------------------------------------------------------------------*/

  read_dba:-
	db_chains(dba,CHAIN),
	chain_terms(dba,CHAIN,dom,_,_),
/*	write("\n",REF,"  -->>  ",TERM), */
	fail.
  read_dba:-
	db_chains(dba,CHAIN),
	chain_first(dba,CHAIN,REF),
	rd(REF),
	fail.
  read_dba.

  rd(REF):-
	ref_term(dba,dom,REF,_),fail.
  rd(REF):-
	chain_next(dba,REF,NEXT),!,
	rd(NEXT).
  rd(_).

/*----------------------------------------------------------------------------*/

  replace_dba:-
	chain_first(dba,names,REF),
	replace(REF).

  replace(REF):-
	term_replace(dba,dom,REF,f(1,'x',88.99,"Prolog Toolbox")),
	chain_next(dba,REF,NN),
	chain_next(dba,NN,NEXT),!,
	replace(NEXT).
  replace(_).

/*----------------------------------------------------------------------------*/

  half_dba:-
	chain_last(dba,names,REF),
	half(REF).

  half(REF):-
	chain_prev(dba,REF,PP),
	chain_prev(dba,PP,PREV),!,
	term_delete(dba,names,REF),
	half(PREV).
  half(_).

/*----------------------------------------------------------------------------*/

  double_dba:-
	chain_first(dba,names,REF),
	double(REF).

  double(REF):-
	chain_next(dba,REF,NEXT),!,
	chain_insertafter(dba,names,dom,REF,f(1,'x',88.99,"Users guide"),_),
	double(NEXT).
  double(_).

/*----------------------------------------------------------------------------*/

  count_dba(N):-
	chain_first(dba,names,REF),
	count(REF,1,N).

  count(REF,N,N2):-
	chain_next(dba,REF,NEXT),!,
	N1=N+1,
	count(NEXT,N1,N2).
  count(_,N,N).

/*----------------------------------------------------------------------------*/

  mixture:-
	write("\nReplace every second term:"),
	replace_dba,
	write("\nDouble the number of terms:"),
	double_dba,
	write("\nErase every second term:"),
	half_dba,
	write("\nUse db_instance for all terms:"),
	read_dba,
	count_dba(N),
	write("\nThere are now ",N," terms in the database"),
	db_flush(dba),
	db_statistics(dba,NoOfRecords,MemSize,DbaSize,FreeSize),
	writef("\nNoOfRecords=%, MemSize=%, DbaSize=%, Freesize=%",NoOfRecords,MemSize,DbaSize,FreeSize).

/*----------------------------------------------------------------------------*/

GOAL	write("\n\n\n\tTEST OF DATABASE SYSTEM\n\t***********************\n\n"),
	time(H1,M1,S1,D1),
	db_create(dba,"dd.dat",in_memory),
	write("\n\nWrite records in the database:"),
	write_dba(100),
	read_dba,
	mixture,

	write("\n\n\nCopy memory -> ems"),
	db_copy(dba,"dd.dat",in_ems),
	db_close(dba), db_delete("dd.dat",in_memory),
	db_open(dba,"dd.dat",in_ems),
	mixture,

	write("\n\n\nCopy ems -> file"),
	db_copy(dba,"dd.dat",in_file),
	db_close(dba), db_delete("dd.dat",in_ems),
	db_open(dba,"dd.dat",in_file),
	mixture,
	db_close(dba),

	write("\n\n\nOpen the database on file"),
	db_open(dba,"dd.dat",in_file),
	mixture,
	db_close(dba), /* db_delete("dd.dat",in_file), */

	time(H2,M2,S2,D2),
	Time=(D2-D1)+100.0*( (S2-S1) + 60.0*( (M2-M1) +60.0*(H2-H1) ) ),
	write("\n\nTime = ",Time,"/100 Sec" ),nl.
