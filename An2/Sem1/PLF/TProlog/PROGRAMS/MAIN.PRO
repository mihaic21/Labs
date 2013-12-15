/*
  Copyright (c) 1986, 88 by Borland International, Inc.
*/

project "register"
check_determ
code = 2000

include "globals.pro"
include "menu2.pro"
include "lineinp.pro"

/****************************************************************

		PRINT

****************************************************************/


/****************************************************************
	Make foothers and headers
****************************************************************/

DATABASE - prnflags
  labelsprint


CLAUSES
  make_header:-
	labelsprint,!.
  make_header:-
	date(Y,M,D),
  	format(HEADER,"Total listning  %41 Date: %.%.%\n","",D,M,Y),
	write_str(HEADER),
	write_str("--------------------------------------------------------------------------\n\n").

  make_footer:-
	labelsprint,!.
  make_footer:-
	write_str("--------------------------------------------------------------------------\n"),
	getpageno(PAGENO),
	format(LINE,"%64 Page: %\n","",PAGENO),
	write_str(LINE).


/****************************************************************
	Print of a single person
****************************************************************/

PREDICATES
  printterm(DBDOM)
  printlabel(DBDOM)
  getcityname(CITYNO,CITYNAME)

CLAUSES
  printterm(person(FIRSTNAME,LASTNAME,STREET,CITYNO,CODE)):-
	getcityname(CITYNO,CITYNAME),
	format(Line1,"% %",FIRSTNAME,LASTNAME),
	format(Line2,"%-30 % %",STREET,CITYNO,CITYNAME),
	Line3=CODE,
	format(STR,"%\n%\n%\n\n\n",Line1,Line2,Line3),
	check_page(STR,0),
	write_str(STR).

  printlabel(person(FIRSTNAME,LASTNAME,STREET,CITYNO,_)):-
	getcityname(CITYNO,CITYNAME),
	format(Line1,"% %",FIRSTNAME,LASTNAME),
	format(Line2,"%-30 % %",STREET,CITYNO,CITYNAME),
	format(STR,"%\n%\n\n\n",Line1,Line2),
	write(STR).


  getcityname(CNO,CNAME):-
	indexes(city_no,I),
	key_search(dba,I,CNO,REF),
	ref_term(dba,dbdom,REF,city(_,CNAME)),!.
  getcityname(_,"").


/**************************************************************
	MY INIT PRINTER
***************************************************************/

PREDICATES
  my_init_printer
  my_close_printer
  my_init_printer_selection(INTEGER)

CLAUSES
  my_init_printer_selection(1):-
	makewindow(13,7,23,"OUTPUT DISPLAY",0,0,25,80),
	init_printer(0,0,20,no,"",con).
  my_init_printer_selection(2):-
	margin(MARGIN),
	topmargin(TOPMARGIN),
	pagelength(PAGELENGTH),
	charconv(CHARCONV),
	printer_init(INITCODE),
	printdest(PRINTDEST),!,
	init_printer(MARGIN,TOPMARGIN,PAGELENGTH,CHARCONV,INITCODE,PRINTDEST).

ifdef invar
  my_init_printer_selection(_):-
	write("\nInvariant error in my_init_printer"),readchar(_).
enddef


  my_init_printer:-
	menu(10,30,7,23,
	    [" screen ",
	     " Printer "],
	     " Where to print",1,Ch),
	my_init_printer_selection(Ch).


  my_close_printer:-
	close_printer,
	existwindow(13),
	shiftwindow(13),!,
	write("\n>> Press any key"),
	readkey(_),
	removewindow.
  my_close_printer.


/**************************************************************
	Print all registered persons
***************************************************************/

PREDICATES
  print_all
  print_all_1
  print_all_loop(BT_SELECTOR,REF)


CLAUSES
  print_all:-
	my_init_printer,
	trap(print_all_1,_,my_close_printer),
	my_close_printer,!.
  print_all.

  print_all_1:-
	indexes(person_name,I),
	key_first(dba,I,REF),
	make_header,
	trap(print_all_loop(I,REF),_,true),!.
  print_all_1.


  print_all_loop(_,REF):-
	ref_term(dba,dbdom,REF,TERM),
	printterm(TERM),
	fail.
  print_all_loop(I,_):-
	key_next(dba,I,REF),!,
	print_all_loop(I,REF).
  print_all_loop(_,_).



/**************************************************************
	Print matching codes
***************************************************************/

PREDICATES
  print_selected(STRING)
  print_selected_1(STRING)
  print_selected_loop(BT_SELECTOR,STRING,REF)
  match(STRING,STRING)
  match_substring(INTEGER,INTEGER,STRING,STRING).

CLAUSES
  print_selected(MATCH):-
	my_init_printer,
	trap(print_selected_1(MATCH),_,my_close_printer),
	my_close_printer,!.
  print_selected(_).

  print_selected_1(MATCH):-
	indexes(person_name,I),!,
	key_first(dba,I,REF),
	make_header,
	print_selected_loop(I,MATCH,REF).

  print_selected_loop(_,MATCH,REF):-
	ref_term(dba,dbdom,REF,TERM),
	TERM=person(_,_,_,_,CODE),
	CODESTR=CODE,
	match(MATCH,CODESTR),
	printterm(TERM),
	fail.
  print_selected_loop(I,MATCH,_):-
	key_next(dba,I,REF),!,
	print_selected_loop(I,MATCH,REF).
  print_selected_loop(_,_,_).



CLAUSES
  match(S,S):-!.
  match(S1,S2):-
	str_len(S1,LEN1),str_len(S2,LEN2),
	match_substring(LEN1,LEN2,S1,S2).

  match_substring(LEN1,_,S1,S2):-
	frontstr(LEN1,S2,S22,_),S1=S22,!.
  match_substring(LEN1,LEN2,S1,S2):-
	LEN2>LEN1,LEN22=LEN2-1,
	frontchar(S2,_,S22),
	match_substring(LEN1,LEN22,S1,S22).


/**************************************************************
	Make labels
***************************************************************/

PREDICATES
  labels
  labels_1
  labels_loop(BT_SELECTOR,REF)

CLAUSES
  labels:-
	assert(labelsprint),
	labels_margin(MARGIN),
	labels_initcode(INITCODE),
	labels_printdest(PRINTDEST),
	charconv(CHARCONV),
	init_printer(MARGIN,0,30000,CHARCONV,INITCODE,PRINTDEST),
	trap(labels_1,_,true),
	fail.
  labels:-
	close_printer,
	retract(labelsprint),fail.
  labels.

  labels_1:-
	indexes(person_name,I),!,
	key_first(dba,I,REF),
	make_header,
	labels_loop(I,REF).

  labels_loop(_,REF):-
	ref_term(dba,dbdom,REF,TERM),
	printterm(TERM),
	fail.
  labels_loop(I,_):-
	key_next(dba,I,REF),!,
	labels_loop(I,REF).
  labels_loop(_,_).


/****************************************************************


		M E N U   S Y S T E M


****************************************************************/


/****************************************************************
	Print selection
****************************************************************/

PREDICATES
  print_selection(INTEGER)

CLAUSES
  print_selection(1):-
	print_all.
  print_selection(2):-
	lineinput(16,40,60,7,23," Matching Code: ","",Code),
	print_selected(Code).


/****************************************************************
	Browse selection
****************************************************************/

PREDICATES
  browse_selection(INTEGER)

CLAUSES
  browse_selection(1):-browse(person).
  browse_selection(2):-browse(city).


/****************************************************************
	PRINTER SETUP
****************************************************************/

PREDICATES
  printer_setup(INTEGER)         	

CLAUSES
  printer_setup(1):-
  	printer_init(Init),
	lineinput(16,40,60,7,23," Initialization sequence: ",Init,Newinit),
	retract(printer_init(_)),!,
	assert(printer_init(Newinit)).
  printer_setup(2):-
  	pagelength(Len),
  	str_int(Sl,Len),
	lineinput(17,40,40,7,23," Page Length: ",Sl,Nsl),
	str_int(Nsl,Nlen),
	retract(pagelength(_)),!,
	assert(pagelength(Nlen)).
  printer_setup(3):-
  	margin(Len),
  	str_int(Sl,Len),
	lineinput(18,40,40,7,23," Margin: ",Sl,Nsl),
	str_int(Nsl,Nlen),
	retract(margin(_)),!,
	assert(margin(Nlen)).
  printer_setup(4):-
  	topmargin(Len),
  	str_int(Sl,Len),
	lineinput(19,40,40,7,23," Top Margin: ",Sl,Nsl),
	str_int(Nsl,Nlen),
	retract(topmargin(_)),!,
	assert(topmargin(Nlen)).
  printer_setup(5):-
  	printdest(Dest),
  	lineinput(20,40,40,7,23," Print Destination: ",Dest,Ndest),
	retract(printdest(_)),!,
	assert(printdest(Ndest)).
  printer_setup(6):-
  	charconv(Conv),
  	lineinput(21,40,40,7,23," Character conversion (Y/N): ",Conv,Chconv),!,
  	upper_lower(Chconvu,Chconv),
        retract(charconv(_)),!,
        assert(charconv(Chconvu)).
  printer_setup(7):-
  	labels_initcode(Init),
	lineinput(22,40,60,7,23," Labels initialization sequence: ",Init,Newinit),
	retract(labels_initcode(_)),!,
	assert(labels_initcode(Newinit)).
  printer_setup(8):-
  	labels_margin(Len),
  	str_int(Sl,Len),
	lineinput(23,40,40,7,23," Labels margin: ",Sl,Nsl),
	str_int(Nsl,Nlen),
	retract(labels_margin(_)),!,
	assert(labels_margin(Nlen)).
  printer_setup(9):-
  	labels_printdest(Dest),
  	lineinput(24,40,40,7,23," Label Print Destination: ",Dest,Ndest),
	retract(labels_printdest(_)),!,
	assert(labels_printdest(Ndest)).
  printer_setup(10):-
	save("register.zup").


/****************************************************************
	Main proces
****************************************************************/

PREDICATES
  main_proces(INTEGER)

CLAUSES
  main_proces(1):-
	menu(14,28,7,23,
	    [" Persons",
	     " cities"],
	     " What to update",1,Ch),
	browse_selection(Ch).
  main_proces(2):-
	menu(15,28,7,23,
	    [" Print all",
	     " Print selected"],
	     " What to print",1,Ch),
	  print_selection(Ch).
  main_proces(3):-labels.
  main_proces(4):-
  	menu_repeat(17,27,7,23,
  		[" Initialization",
  		 " PageLength",
  		 " Margin",
  		 " Top Margin",
  		 " Print Destination  ",
  		 " Character Conversion ",
  		 " Labels Initialization ",
  		 " Labels Margin ",
  		 " Labels Destination",
  		 " Save setup"],
  	         " Printer setup",1,Ch),
  	printer_setup(Ch),
  	fail.  
  	
  main_proces(5):-
  	makewindow(10,7,23,"",10,5,8,70),
  	write("\nInsert an empty disk in drive A:"),
  	write("\n\n>> Press any key when ready, ESC: abort"),
  	readkey(KEY),
  	removewindow,
  	not(KEY=esc),
  	system("copy register.* a:").

  	
  main_proces(6):-
  	makewindow(10,7,23,"",2,0,22,80),
  	file_str("register.hlp",Text),
  	display(Text),
  	removewindow.
    	
  main_proces(7).


/****************************************************************
			MAIN DRIVER
****************************************************************/

PREDICATES
  main

CLAUSES
  main:-
	makewindow(2,7,0,"",0,0,25,80),
	makewindow(3,7,23,"",3,23,3,23),
	window_str("  PERSON REGISTER"),
	repeat,
	menu_repeat(12,23,7,23,
	    [" Update ",
	     " Print ",
	     " Labels ",
  	     " Setup ",
	     " Backup ",
	     " Help ",
	     " End "],
	     " Make a selection",1,Ch),
	main_proces(Ch),
	Ch=7,!,
	removewindow,
	removewindow.


/******************************************************************
     Open dba, or give msg.
*******************************************************************/

PREDICATES
  open_dba
  open_indexes
  
CLAUSES
  open_indexes:-
	bt_open(dba,person_name,I1),
	bt_open(dba,person_adr,I2),
	bt_open(dba,city_no,I3),
	assert(indexes(person_name,I1)),
	assert(indexes(person_adr,I2)),
	assert(indexes(city_no,I3)).


  open_dba:-
	consult("register.zup"),
	db_open(dba,"register.bin",in_file),
	open_indexes,
	db_open(logdba,"register.log",in_file),
	system("copy register.bin register.old"),!.
  open_dba:-
	existfile("register.zup"),
	existfile("register.bin"),!,
  	makewindow(10,7,23," Database error ",10,5,8,70),
  	write("\n\n The database file is invalid"),
  	write("\n\n Use backup !!!!!!!"),
  	write("\n\n>> Press any key"),
  	beep,readchar(_),
  	removewindow,fail.
  open_dba:-
  	makewindow(10,7,23," Database error ",10,6,8,70),
  	write("The files \"register.zup\" and \"register.bin\" "),
  	write("\nshould be found on the current directory"),
  	write("\n\n>> Press any key"),
  	beep,readchar(_),
  	removewindow,fail.

	
GOAL	open_dba,
	main,
	db_close(dba).


/*
/****************************************************************
	Create a new empty database 	
****************************************************************/

PREDICATES 
  createdba

CLAUSES
  createdba:-
	db_create(dba,"register.bin",in_file),
	bt_create(dba,person_name,_,35,4),
	bt_create(dba,person_adr,_,25,4),
	bt_create(dba,city_no,_,6,4),
	db_close(dba).
*/

/*
/****************************************************************
	Load database from text file from dumpdba
****************************************************************/

DOMAINS
  CHAINTERM	= chain(STRING,dbdom)

PREDICATES
  wr(CHAINTERM)
  nondeterm repfile(FILE)
  copydba
  loaddba(STRING)

CLAUSES
  wr(X):-write(X),nl.

  repfile(_).
  repfile(FILE):-not(eof(FILE)),repfile(FILE).

  loaddba(OUTFILE):-
	openread(prn_file,OUTFILE),
	readdevice(prn_file),
	repfile(prn_file),
	readterm(chainterm,chain(CHAIN,TERM)),
	write(TERM),nl,
	dba_insert(CHAIN,TERM), /* Don't use db_flush for each */
	fail.
  loaddba(_):-
	closefile(prn_file).

  copydba:-
	createdba,
	db_open(dba,"register.bin",in_file),
	open_indexes,
	loaddba("register.txt"),
	write("\nclose"),
	db_close(dba).
*/
