
DOMAINS
  dom = f(INTEGER)
  DB_selector = dba



/*----------- Put something in the index -------------------------------------*/

PREDICATES
  write_dba(bt_selector,REF,INTEGER)

CLAUSES
  write_dba(_,_,0):-!.
  write_dba(INDX,REF,N):-
	str_int(NN,N),
	key_insert(dba,INDX,NN,REF),
	fail.
  write_dba(INDX,REF,N):-
	N1=N-1,
	write_dba(INDX,REF,N1).

/*----------- Write the records sorted ---------------------------------------*/

PREDICATES
  wsort(bt_selector)
  wsort1(bt_selector)
  wsort2(bt_selector,REF)

CLAUSES
  wsort(INDEX):-
	key_first(dba,INDEX,REF),
	wsort2(INDEX,REF),
	fail.
  wsort(_).

  wsort1(INDEX):-
	key_search(dba,INDEX,"1",REF),
	wsort2(INDEX,REF),
	fail.
  wsort1(_).


/*
  wsort2(_,REF):- /* Write the records */
	db_instance(dba,REF,_ /*RECORD*/),
	nl,write(RECORD),
	fail.
*/
  wsort2(INDEX,_):- /* Write the keys */
	key_current(dba,INDEX,KEY,_),
	nl,write(KEY),
	fail.
  wsort2(INDEX,_):-
	key_next(dba,INDEX,REF),
	wsort2(INDEX,REF).

/*----------- Delete some keys -----------------------------------------------*/

PREDICATES
  del_index(bt_selector,REF,INTEGER,INTEGER)

CLAUSES
  del_index(_,_,N,N):-!.
  del_index(INDX,REF,N,_):-
	str_int(NN,N),
	key_delete(dba,INDX,NN,REF),
	fail.
  del_index(INDX,REF,N,N2):-
	N1=N+1,
	del_index(INDX,REF,N1,N2).


/*----------- Do some searches -----------------------------------------------*/

PREDICATES
  testacces(bt_selector,INTEGER)

CLAUSES
  testacces(_,0):-!.
  testacces(INDEX,_):-
	key_search(dba,INDEX,"11",_),
	key_search(dba,INDEX,"22",_),
	key_search(dba,INDEX,"33",_),
	key_search(dba,INDEX,"44",_),
	key_search(dba,INDEX,"55",_),
	key_search(dba,INDEX,"66",_),
	key_search(dba,INDEX,"77",_),
	key_search(dba,INDEX,"88",_),
	key_search(dba,INDEX,"99",_),
	key_search(dba,INDEX,"00",_),
	fail.
  testacces(INDEX,N):-
	N1=N-1,
	testacces(INDEX,N1).

/*----------- GOAL -----------------------------------------------------------*/

GOAL	
	db_create(dba,"dd.dat",in_file),
	chain_insertz(dba,names,dom,f(0),REF),

	bt_create(dba,ndx,INDEX,4,5),

	time(H1,M1,S1,D1),
	write_dba(INDEX,REF,100),
	testacces(INDEX,10),
	bt_statistics(dba,INDEX,NoOfKeys,NoOfPages,Dept,KeyLen,Order,PageSize),
	UsedSize=NoOfPages*PageSize,
	writef("\nNoOfKeys=%, NoOfPages=%, Dept=%, Order=%\nKeyLen=%, PageSize=%, UsedSize=%",
		NoOfKeys,NoOfPages,Dept,Order,KeyLen,PageSize,UsedSize),

	del_index(INDEX,REF,2,100),
	wsort(INDEX),
	time(H2,M2,S2,D2),

	bt_open(dba,ndx,I1),
	db_flush(dba),
	bt_close(dba,INDEX),
	bt_close(dba,I1),
	bt_delete(dba,ndx),
	db_close(dba),

	Time=(D2-D1)+100.0*( (S2-S1) + 60.0*( (M2-M1) +60.0*(H2-H1) ) ),
	write("\n\nTime = ",Time,"/100 Sec" ),nl.
