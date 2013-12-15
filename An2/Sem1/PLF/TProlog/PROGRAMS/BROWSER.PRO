/*
  Copyright (c) 1986, 88 by Borland International, Inc.
*/
project "register"
check_determ
code = 2000

include "globals.pro"

CONSTANTS
  invar = 1  /* Invariants check turned on */


/***************************************************************
        Global information for record browser
****************************************************************/

DATABASE - recordbrowser
  currentrel(RELATION)
  currentindex(BT_SELECTOR)
  searchfields(FNAMELIST,VALUELIST)


/****************************************************************
	Conversion between a list and a term for the database
****************************************************************/

PREDICATES
  dbconv(RELATION,dbdom,VALUELIST)
  call_cursor(RELATION)

CLAUSES
  dbconv(person,person(P1,P2,P3,P4,P5),[str(PP1),str(PP2),str(PP3),str(PP4),str(PP5)]):-
	P1=PP1,P2=PP2,P3=PP3,P4=PP4,P5=PP5.
  dbconv(city,city(P1,P2),[str(PP1),str(PP2)]):-
	P1=PP1,P2=PP2.


/****************************************************************
	Names of attributes in relations
****************************************************************/

PREDICATES
  describe(RELATION,FNAMELIST)

CLAUSES
  describe(person,["fname","lname","street","cityno","code"]).
  describe(city,["cityno","cityname"]).




/****************************************************************
	Logging of operations on the database
****************************************************************/

DOMAINS
  LOGDOM =
	insert(RELATION,DBDOM,REF);
	replace(RELATION,DBDOM,REF,DBDOM);
	erase(RELATION,REF,DBDOM)

PREDICATES
  logdbchange(LOGDOM)

CLAUSES
  logdbchange(LOGTERM):-
	chain_insertz(logdba,logs,logdom,LOGTERM,_),
	db_flush(logdba).



/****************************************************************
	Nameindex = Lastname[0..20] + Firstname[0..10]
****************************************************************/

PREDICATES
  xname(FIRSTNAME,LASTNAME,STRING)

CLAUSES
  xname(F,L,S):-
	str_len(L,LEN),LEN>20,!,
	frontstr(20,L,L1,_),
	format(S,"%-20%",L1,F).
  xname(F,L,S):-
	format(S,"%-20%",L,F).


/****************************************************************
	Database updating
****************************************************************/

PREDICATES
  dba_insert(RELATION,DBDOM)
  dba_replace(RELATION,DBDOM,REF)
  dba_erase(RELATION,REF)

CLAUSES
  dba_insert(person,TERM):-!,
	break(OLDBREAK),
	break(off),
	indexes(person_name,I1),
	indexes(person_adr,I2),!,
	TERM=person(FNAME,LNAME,ADR,_,_),
	xname(FNAME,LNAME,XNAME),
	chain_insertz(dba,person,dbdom,TERM,REF),
	key_insert(dba,I1,XNAME,REF),
	key_insert(dba,I2,ADR,REF),
	db_flush(dba),
	logdbchange(insert(person,TERM,REF)),
	break(OLDBREAK).

  dba_insert(city,TERM):-
	break(OLDBREAK),
	break(off),
	indexes(city_no,I),!,
	TERM=city(CITYNO,_),
	chain_insertz(dba,city,dbdom,TERM,REF),
	key_insert(dba,I,CITYNO,REF),
	db_flush(dba),
	logdbchange(insert(city,TERM,REF)),
	break(OLDBREAK).

  dba_replace(person,NEWTERM,REF):-!,
	break(OLDBREAK),
	break(off),
	indexes(person_name,I1),
	indexes(person_adr,I2),!,
	ref_term(dba,dbdom,REF,OLDTERM),
	OLDTERM=person(OLDFNAME,OLDLNAME,OLDADR,_,_),
	xname(OLDFNAME,OLDLNAME,OLDXNAME),
	key_delete(dba,I1,OLDXNAME,REF),
	key_delete(dba,I2,OLDADR,REF),
	NEWTERM=person(NEWFNAME,NEWLNAME,NEWADR,_,_),
	xname(NEWFNAME,NEWLNAME,NEWXNAME),
	term_replace(dba,dbdom,REF,NEWTERM),
	key_insert(dba,I1,NEWXNAME,REF),
	key_insert(dba,I2,NEWADR,REF),
	db_flush(dba),
	logdbchange(replace(person,NEWTERM,REF,OLDTERM)),
	break(OLDBREAK).

  dba_replace(city,NEWTERM,REF):-!,
	break(OLDBREAK),
	break(off),
	indexes(city_no,I),!,
	ref_term(dba,dbdom,REF,OLDTERM),
	OLDTERM=city(OLDCITYNO,_),
	key_delete(dba,I,OLDCITYNO,REF),
	NEWTERM=city(CITYNO,_),
	term_replace(dba,dbdom,REF,NEWTERM),
	key_insert(dba,I,CITYNO,REF),
	db_flush(dba),
	logdbchange(replace(city,NEWTERM,REF,OLDTERM)),
	break(OLDBREAK).


  dba_erase(person,REF):-!,
	break(OLDBREAK),
	break(off),
	indexes(person_name,I1),
	indexes(person_adr,I2),!,
	ref_term(dba,dbdom,REF,OLDTERM),
	OLDTERM=person(OLDFNAME,OLDLNAME,OLDADR,_,_),
	xname(OLDFNAME,OLDLNAME,OLDXNAME),
	key_delete(dba,I1,OLDXNAME,REF),
	key_delete(dba,I2,OLDADR,REF),
	term_delete(dba,person,REF),
	db_flush(dba),
	logdbchange(erase(person,REF,OLDTERM)),
	break(OLDBREAK).

  dba_erase(city,REF):-
	break(OLDBREAK),
	break(off),
	indexes(city_no,I),!,
	ref_term(dba,dbdom,REF,OLDTERM),
	OLDTERM=city(OLDCITYNO,_),
	key_delete(dba,I,OLDCITYNO,REF),
	term_delete(dba,city,REF),
	db_flush(dba),
	logdbchange(erase(city,REF,OLDTERM)),
	break(OLDBREAK).


PREDICATES
  rel_descr(RELATION,STRING)

CLAUSES
  rel_descr(person,"\t\tPerson information").
  rel_descr(city,"\t\tCities").

/*>>>>>>>>>>>>>>>> END OF DATABASE DEPENDENT FUNCTIONS <<<<<<<<<<<<<<<<<<<<*/


/****************************************************************
	Index an item in a stringlist
****************************************************************/

PREDICATES
  indexlist(STRINGLIST,STRINGLIST,INTEGERLIST)
  index(INTEGER,STRINGLIST,STRING)

CLAUSES
  indexlist([],_,[]).
  indexlist([H|T],L,[I|Il]):-
	index(I,L,H),
	indexlist(T,L,Il).
	

  index(1,[H|_],H):-!.
  index(N,[_|T],X):-bound(N),!,N>1,N1=N-1,index(N1,T,X).
  index(N1,[_|T],X):-bound(X),index(N,T,X),N1=N+1.


/****************************************************************
	Membership of a list
****************************************************************/
	
PREDICATES
  member(STRING,STRINGLIST)

CLAUSES
  member(X,[X|_]):-!.
  member(X,[_|L]):-member(X,L).


/****************************************************************
	Shift between screens
****************************************************************/

PREDICATES
  determ shiftscreen(RELATION)

CLAUSES
  shiftscreen(_):-retract(field(_,_,_,_,_)),fail.
  shiftscreen(_):-retract(txtfield(_,_,_,_)),fail.
  shiftscreen(_):-retract(windowsize(_,_)),fail.
  shiftscreen(NAME):-screen(NAME,TERM),assert(TERM),fail.
  shiftscreen(_).


/*******************************************************************
			Field action
*******************************************************************/

  field_action(_):-fail.

/*******************************************************************
			 Field_value
*******************************************************************/

  field_value(cityname,VAL):-
	currentrel(person),
	value(cityno,CITYNO),
	indexes(city_no,I),
	key_search(dba,I,CITYNO,REF),
	ref_term(dba,dbdom,REF,city(_,CITYNAME)),
	VAL=CITYNAME,!.
  field_value(FNAME,VAL):-value(FNAME,VAL),!.
  field_value(_,"").

/*******************************************************************
			 noinput
*******************************************************************/

  noinput(cityname):-currentrel(person),!.


/*******************************************************************
	Set header in status window
*******************************************************************/

PREDICATES
  set_hdr

CLAUSES
  set_hdr:-
	currentrel(REL),!,
	rel_descr(REL,DESCR),
	shiftwindow(OLD),
	shiftwindow(85),
	field_str(0,0,80,DESCR),
	shiftwindow(OLD).


/*******************************************************************
	Set message line
*******************************************************************/

DOMAINS
  MSG = search; searchloop; replace; ins; del; main

PREDICATES
  msgtxt(MSG,STRING)
  msg(MSG)

CLAUSES
  msgtxt(search," Input some search values and press F10").
  msgtxt(searchloop," Search Mode    Down:Search next    Up:Search prev    Esc:End").
  msgtxt(replace," Replace Mode   Input new values and press F10").
  msgtxt(ins," Insert new record     F10:Insert new   Esc:Exit").
  msgtxt(del," Delete current record").
  msgtxt(main," F10:End  Ins:New Rec  Del:Remove Rec  F3:Search  F4:Replace  Arrows:shift").

  msg(MSG):-
	msgtxt(MSG,TXT),
	shiftwindow(OLD),
	shiftwindow(83),
	field_str(0,0,80,TXT),
	shiftwindow(OLD).


/****************************************************************
	Predicates dependent of the types for the screen handler.
****************************************************************/

PREDICATES
  determ void(TYPE,VALUE)
  determ conv(TYPE,STRING,VALUE)

CLAUSES
  void(int,int(0)).
  void(real,real(0)).
  void(str,str("")).

  conv(str,STR,str(STR)).
  conv(int,STR,int(INT)):-str_int(STR,INT).
  conv(real,STR,real(REAL)):-str_real(STR,REAL).


/****************************************************************
	Delete the facts in the value database
****************************************************************/

PREDICATES
  determ clear_value

CLAUSES
  clear_value:-retract(value(_,_)),fail.
  clear_value.


/****************************************************************
	Current record number
****************************************************************/

PREDICATES
  current_recno(REF)

CLAUSES
  current_recno(REF):-
	currentindex(I),
	key_current(dba,I,_,REF),!.


/****************************************************************
	Assert a list of values in the value predicate
****************************************************************/

PREDICATES
  assert_in_value(FNAMELIST,VALUELIST)
  current_to_value

CLAUSES
  assert_in_value([],[]).
  assert_in_value([FNAME|R1],[VALUE|R2]):-
	field(FNAME,TYPE,_,_,_),!,
	conv(TYPE,STR,VALUE),
	assert(value(FNAME,STR)),
	assert_in_value(R1,R2).

  current_to_value:-
	clear_value,
	currentrel(RELNAME),
	current_recno(REF),
	describe(RELNAME,FNAMES),!,
	ref_term(dba,dbdom,REF,TERM),!,
	dbconv(RELNAME,TERM,VALUES),
	assert_in_value(FNAMES,VALUES).
  current_to_value.


/****************************************************************
	Collect a list of values from the value predicate
****************************************************************/

PREDICATES
  /* Collect all the fields witch have a value. Used under search */
  collect_known(FNAMELIST,FNAMELIST,VALUELIST)

  /* Collect all the fields. Give void values to unknown. */
  collect(FNAMELIST,VALUELIST)

CLAUSES
  collect_known([],[],[]).
  collect_known([FNAME|R1],[FNAME|R2],[VALUE|R3]):-
	field(FNAME,TYPE,_,_,_),
	value(FNAME,STR),
	conv(TYPE,STR,VALUE),!,
	collect_known(R1,R2,R3).
  collect_known([_|R1],R2,R3):-
	collect_known(R1,R2,R3).


  collect([],[]).
  collect([FNAME|R1],[VALUE|R2]):-
	field(FNAME,TYPE,_,_,_),
	value(FNAME,STR),
	conv(TYPE,STR,VALUE),!,
	collect(R1,R2).
  collect([FNAME|R1],[VALUE|R2]):-
	field(FNAME,TYPE,_,_,_),!,
	void(TYPE,VALUE),
	collect(R1,R2).


/****************************************************************
	Matching fields
****************************************************************/

PREDICATES
  match(FNAMELIST,VALUELIST,FNAMELIST,VALUELIST)
  match_value(VALUE,VALUE)
  match_substring(INTEGER,INTEGER,STRING,STRING).

CLAUSES
  match([],[],_,_):-!.
  match([F|R1],[V1|R2],[F|R3],[V2|R4]):-
	match_value(V1,V2),!,
	match(R1,R2,R3,R4).
  match(FELTL,VALL,[_|R1],[_|R2]):-match(FELTL,VALL,R1,R2).

  match_value(V,V):-!.
  match_value(str(V1),str(V2)):-!,
	str_len(V1,LEN1),
	str_len(V2,LEN2),
	upper_lower(V1,V11),
	upper_lower(V2,V22),
	match_substring(LEN1,LEN2,V11,V22).
  match_value(int(V),int(V)):-!.
  match_value(real(V),real(V)):-!.

  match_substring(LEN1,_,V1,V2):-
	frontstr(LEN1,V2,V22,_),V1=V22,!.
  match_substring(LEN1,LEN2,V1,V2):-
	LEN2>LEN1,LEN22=LEN2-1,
	frontchar(V2,_,V22),
	match_substring(LEN1,LEN22,V1,V22).


/****************************************************************
	Change the current index
****************************************************************/

PREDICATES
  change_index(BT_SELECTOR)

CLAUSES
  change_index(_):-retract(currentindex(_)),fail.
  change_index(BT_SELECTOR):-assert(currentindex(BT_SELECTOR)).


/****************************************************************
	Change the current relation
****************************************************************/

PREDICATES
  change_currentrel(RELATION)

CLAUSES
  change_currentrel(_):-retract(currentrel(_)),fail.
  change_currentrel(person):-
	clear_value,
	assert(currentrel(person)),
	indexes(person_name,I),
	change_index(I),
	key_first(dba,I,_),
	current_to_value,!.
  change_currentrel(city):-
	clear_value,
	assert(currentrel(city)),
	indexes(city_no,I),
	change_index(I),
	key_first(dba,I,_),
	current_to_value,!.
  change_currentrel(_).



/****************************************************************
	Change the search fields
****************************************************************/

PREDICATES
  change_searchfields(FNAMELIST,VALUELIST)

CLAUSES
  change_searchfields(_,_):-retract(searchfields(_,_)),fail.
  change_searchfields(SEARCHFIELDS,SEARCHVALUES):-
	assert(searchfields(SEARCHFIELDS,SEARCHVALUES)).


/****************************************************************
	Search sequential interactive
****************************************************************/

PREDICATES
  searchnext(RELATION)
  searchprev(RELATION)
  searchforward(RELATION,BT_SELECTOR,REF,FNAMELIST,FNAMELIST,VALUELIST,REF)
  searchbackward(RELATION,BT_SELECTOR,REF,FNAMELIST,FNAMELIST,VALUELIST,REF)
  firstmatch
  search_proces(KEY)
  search_proces2(KEY)

CLAUSES
  searchprev(PID):-
	currentindex(I),
	searchfields(SEARCHFIELDS,SEARCHVALUES),
	describe(PID,TOTFIELDS),
	key_prev(dba,I,PREV),
	searchbackward(PID,I,PREV,TOTFIELDS,SEARCHFIELDS,SEARCHVALUES,_),!.
  searchprev(_).

  searchbackward(PID,_,REF,TOTFIELDS,SEARCHFIELDS,SEARCHVALUES,REF):-
	ref_term(dba,dbdom,REF,TERM),
	dbconv(PID,TERM,VALUES),
	match(SEARCHFIELDS,SEARCHVALUES,TOTFIELDS,VALUES),!.
  searchbackward(PID,I,_,TOTFIELDS,SEARCHFIELDS,SEARCHVALUES,NEXTMATCH):-
	key_prev(dba,I,PREV),
	searchbackward(PID,I,PREV,TOTFIELDS,SEARCHFIELDS,SEARCHVALUES,NEXTMATCH).
	

  searchnext(PID):-
	currentindex(I),
	searchfields(SEARCHFIELDS,SEARCHVALUES),
	describe(PID,TOTFIELDS),
	key_next(dba,I,NEXT),
	searchforward(PID,I,NEXT,TOTFIELDS,SEARCHFIELDS,SEARCHVALUES,_),!.
  searchnext(_).

  searchforward(PID,_,REF,TOTFIELDS,SEARCHFIELDS,SEARCHVALUES,REF):-
	ref_term(dba,dbdom,REF,TERM),
	dbconv(PID,TERM,VALUES),
	match(SEARCHFIELDS,SEARCHVALUES,TOTFIELDS,VALUES),!.
  searchforward(PID,I,_,TOTFIELDS,SEARCHFIELDS,SEARCHVALUES,NEXTMATCH):-
	key_next(dba,I,NEXT),
	searchforward(PID,I,NEXT,TOTFIELDS,SEARCHFIELDS,SEARCHVALUES,NEXTMATCH).


  firstmatch:-
  	currentindex(I),
  	currentrel(REL),
	describe(REL,Totfields),!,
	collect_known(Totfields,Sfields,Svalues),
	change_searchfields(Sfields,Svalues),
	key_first(dba,I,Ref),
	searchforward(REL,I,Ref,Totfields,Sfields,Svalues,_).
  firstmatch:-
	currentindex(I),
	key_last(dba,I,_),
	key_next(dba,I,_),
	fail.
  firstmatch.

  search_proces(esc).
  search_proces(fkey(10)):-
	cursor(0,0),
	firstmatch,
	msg(searchloop),
	set_hdr,
	repeat,
	current_to_value,
	writescr,
	readkey(Key),
	search_proces2(Key),
	Key = esc,!.

  search_proces2(esc).
/*
  search_proces2(fkey(10)):-
	window_str(Printstr),
	write_str(Printstr),
	write_ch('\n'),
	write_ch('\n'),
	close_printer.
*/
  search_proces2(up):-
	currentrel(REL),!,
	searchprev(REL).
  search_proces2(down):-
	currentrel(REL),!,
	searchnext(REL).


/****************************************************************
		answer
****************************************************************/

PREDICATES
  answer

CLAUSES
  answer:-
	makewindow(7,7,23,"",14,27,3,30),
	cursor(0,0),
	write(" Are you sure (y/n) : "),
	readchar(A),
	removewindow,
	str_char(Ans,A),
        upper_lower(Sans,Ans),
        Sans = "Y",!.


/****************************************************************
	INDEX HELP PREDICATES
****************************************************************/

PREDICATES
  mykey_next(DB_SELECTOR,BT_SELECTOR,REF)
  mykey_prev(DB_SELECTOR,BT_SELECTOR,REF)
  mykey_search(DB_SELECTOR,BT_SELECTOR,STRING,REF)
  samekey_next(DB_SELECTOR,BT_SELECTOR,REF)
  try_next(DB_SELECTOR,BT_SELECTOR,REF,STRING)
  samekey_prev(DB_SELECTOR,BT_SELECTOR,REF)
  try_prev(DB_SELECTOR,BT_SELECTOR,REF,STRING)

CLAUSES
  mykey_prev(DBA,BT_SELECTOR,REF):-
	key_prev(DBA,BT_SELECTOR,REF),!.
  mykey_prev(DBA,BT_SELECTOR,REF):-
	key_next(DBA,BT_SELECTOR,REF),
	fail.

  mykey_next(DBA,BT_SELECTOR,REF):-
	key_next(DBA,BT_SELECTOR,REF),!.
  mykey_next(DBA,BT_SELECTOR,REF):-
	key_prev(DBA,BT_SELECTOR,REF),
	fail.

  mykey_search(DBA,BT_SELECTOR,KEY,REF):-
	key_search(DBA,BT_SELECTOR,KEY,REF),!.
  mykey_search(DBA,BT_SELECTOR,_,REF):-
	key_current(DBA,BT_SELECTOR,_,REF),!.
  mykey_search(DBA,BT_SELECTOR,_,REF):-
	key_last(DBA,BT_SELECTOR,REF).


  samekey_next(DBA,BT_SELECTOR,REF):-
	key_current(DBA,BT_SELECTOR,OLDKEY,_),
	try_next(DBA,BT_SELECTOR,REF,OLDKEY).

  try_next(DBA,BT_SELECTOR,REF,OLDKEY):-
	key_next(DBA,BT_SELECTOR,REF),
	key_current(DBA,BT_SELECTOR,NEWKEY,_),
	NEWKEY=OLDKEY,!.
  try_next(DBA,BT_SELECTOR,_,_):-
	key_prev(DBA,BT_SELECTOR,_),
	fail.

  samekey_prev(DBA,BT_SELECTOR,REF):-
	key_current(DBA,BT_SELECTOR,OLDKEY,_),
	try_prev(DBA,BT_SELECTOR,REF,OLDKEY).

  try_prev(DBA,BT_SELECTOR,REF,OLDKEY):-
	key_prev(DBA,BT_SELECTOR,REF),
	key_current(DBA,BT_SELECTOR,NEWKEY,_),
	NEWKEY=OLDKEY,!.
  try_prev(DBA,BT_SELECTOR,_,_):-
	key_next(DBA,BT_SELECTOR,_),
	fail.


/****************************************************************
	MAIN BROWSER PREDICATES
****************************************************************/

PREDICATES
  determ proces(BT_SELECTOR,KEY)
  endkey(KEY)
  newrec(KEY)
  changerec(KEY)


CLAUSES
  endkey(fkey(10)).
  endkey(esc).

  newrec(esc):-!,clearwindow.
  newrec(_):-
	currentrel(REL),
	describe(REL,FIELDNAMES),!,
	collect(FIELDNAMES,VALL),
	dbconv(REL,TERM,VALL),
	dba_insert(REL,TERM).

  changerec(esc):-!.
  changerec(_):-
	currentrel(REL),
	describe(REL,FIELDNAMES),
	collect(FIELDNAMES,VALL),
	current_recno(REF),
	dbconv(REL,TERM,VALL),
	dba_replace(REL,TERM,REF),!.


  browse(RELATION):-
	shiftscreen(RELATION),
	makewindow(83,112,0,"",24,0,1,80),
	createwindow(on),
	change_currentrel(RELATION),
	currentindex(I),
	msg(main),
	repeat,
	set_hdr,
	current_to_value,
	writescr,
	call_cursor(RELATION),
	readkey(KEY),
	proces(I,KEY),
	endkey(KEY),!,
	removewindow,
	removewindow,
	removewindow.

call_cursor(person):-!,cursor(6,16).
call_cursor(_):-cursor(8,14).

  proces(_,esc).
  proces(_,fkey(3)):-			/* search */
	msg(search),
	clear_value,clearwindow,
	scrhnd(on,Endkey),
	search_proces(Endkey),
	msg(main).
  proces(I,fkey(4)):-			/* change values for current rec. */
	key_current(dba,I,OLDKEY,_),
	msg(replace),
	repeat,
	clearwindow,
	scrhnd(on,EndKey),
	changerec(EndKey),
	msg(main),
	mykey_search(dba,I,OLDKEY,_),!.
  proces(I,ins):-			/* Insert new records */
	msg(ins),
	clear_value,
	repeat,
	clearwindow,
	clear_value,
	scrhnd(on,EndKey),
	newrec(EndKey),
	EndKey=esc,!,
	msg(main),
	key_last(dba,I,_).
  proces(I,del):-			/* Rmove rercord, if ok */
	msg(del),
	answer,
	key_current(dba,I,OLDKEY,REF),
	currentrel(REL),!,
	dba_erase(REL,REF),
	msg(main),
	mykey_search(dba,I,OLDKEY,_).
  proces(I,home):-
	key_first(dba,I,_).
  proces(I,end):-
	key_last(dba,I,_).
  proces(I,up):-
	mykey_prev(dba,I,_).
  proces(I,down):-
	mykey_next(dba,I,_).
