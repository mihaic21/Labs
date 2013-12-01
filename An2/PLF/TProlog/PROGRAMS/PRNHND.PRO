
project "register"
check_determ

include "globals.pro"

/****************************************************************

		PRINTER TOOLS
		=============

  Controls printer output. Takes care of printer initialization,
  pagelength and pageshift, indentation of leftmargin, printing
  of foothers and headers and a possible character convertion.

  Uses the global domain prn_file as printer output.

  The predicates mk_foother and mk_header should be defined globally.

  Print should be initiated by init_printer and finished by
  close_printer.

  Output should be performed by write_ch and write_str.


****************************************************************/

/* NESSECARRY GLOBAL DECLARATIONS
GLOBAL DOMAINS
  file = prn_file

GLOBAL PREDICATES
/* init_printer(MARGIN,TOPMARGIN,PAGELENGTH,CHARCONV,INITCODE,PRINTDEST) */
  determ init_printer(INTEGER,INTEGER,INTEGER,STRING,STRING,STRING)-(i,i,i,i,i,i)
  determ close_printer
  determ write_ch(CHAR) - (i)
  determ write_str(STRING) - (i)
  determ make_footer
  determ make_header
  determ check_page(STRING,INTEGER)-(i,i)
  determ shift_page()
  determ getpageno(INTEGER)-(o)
*/


/****************************************************************
	Local database used as global information during output
****************************************************************/

DATABASE - prn_dba
  prn_lineno(INTEGER)		/* Current line number			*/
  prn_pageno(INTEGER)		/* Current page number			*/
  prn_margin(INTEGER)		/* Left margin				*/
  prn_topmargin(INTEGER)	/* Noof empty toplines			*/
  prn_pagelength(INTEGER)	/* Pagelength				*/
  prn_charconv			/* Character convertion			*/


/****************************************************************
	HELP PREDICATES
****************************************************************/

PREDICATES
  printercode(STRING)
  count_lines(STRING,INTEGER,INTEGER)
  inc_lineno
  inc_lineno1(INTEGER)
  inc_pageno
  conv_char(CHAR,CHAR)
  mk_margin(integer)
  mk_topmargin(INTEGER)
  setwritedevice(STRING,STRING)

CLAUSES
  /* Interpreet escape sequences as:  "\027I\001abc"
     A backslash can be followed by an integer number
  */
  printercode(""):-!.
  printercode(CODE):-
	frontchar(CODE,'\\',REST),
	fronttoken(REST,NUM,RESTCODE),
	str_int(NUM,CHI),
	char_int(CH,CHI),
	write(CH),!,
	printercode(RESTCODE).
  printercode(CODE):-
	frontchar(CODE,CH,REST),
	write(CH),
	printercode(REST).

  count_lines("",N,N):-!.
  count_lines(STR,N,N1):-
	frontchar(STR,'\n',STR1),!,
	N2=N+1,
	count_lines(STR1,N2,N1).
  count_lines(STR,N,N1):-
	frontchar(STR,_,STR1),
	count_lines(STR1,N,N1).

  inc_lineno:-
	retract(prn_lineno(N)),!,
	inc_lineno1(N).
  inc_lineno.

  inc_lineno1(N):-
	prn_pagelength(N),!,
	shift_page.
  inc_lineno1(N):-
	N1=N+1,
	assert(prn_lineno(N1)).

  inc_pageno:-
	retract(prn_pageno(N)),!,
	N1=N+1,
	assert(prn_pageno(N1)).

  mk_margin(0):-!.
  mk_margin(N):-
	write(' '),
	N1=N-1,
	mk_margin(N1).

  conv_char('{','ë'):-!.	/* Convert to national char font */
  conv_char('[','í'):-!.
  conv_char('}','Ü'):-!.
  conv_char(']','è'):-!.
  conv_char('|','õ'):-!.
  conv_char('\\','ù'):-!.
  conv_char('\197','Ç'):-!.
  conv_char(C7,C7).

  mk_topmargin(0):-!.
  mk_topmargin(N):-
  	nl,
  	N1 = N - 1,
  	mk_topmargin(N1).

  setwritedevice(PRINTDEST,_):-
	upper_lower(PRINTDEST,"con"),!,
	writedevice(screen).
  setwritedevice(PRINTDEST,INITCODE):-
	openwrite(prn_file,PRINTDEST),
	writedevice(prn_file),
	printercode(INITCODE).


/****************************************************************
	INITIALIZING THE PRINTER
****************************************************************/

  init_printer(_,_,_,_,_,_):-retract(prn_margin(_)),fail.
  init_printer(_,_,_,_,_,_):-retract(prn_topmargin(_)),fail.
  init_printer(_,_,_,_,_,_):-retract(prn_pagelength(_)),fail.
  init_printer(_,_,_,_,_,_):-retract(prn_lineno(_)),fail.
  init_printer(_,_,_,_,_,_):-retract(prn_pageno(_)),fail.
  init_printer(_,_,_,_,_,_):-retract(prn_charconv()),fail.
  init_printer(_,_,_,yes,_,_):-assert(prn_charconv),fail.
  init_printer(MARGIN,TOPMARGIN,PAGELENGTH,_,INITCODE,PRINTDEST):-
	assert(prn_margin(MARGIN)),
	assert(prn_topmargin(TOPMARGIN)),
	assert(prn_pagelength(PAGELENGTH)),
	assert(prn_lineno(0)),
	assert(prn_pageno(1)),
	setwritedevice(PRINTDEST,INITCODE).


/****************************************************************
	CLOSING THE PRINTER
****************************************************************/

  close_printer:-
	writedevice(DEV),DEV=screen,!,
	make_footer.
  close_printer:-
	make_footer,
	write('\012'),
	closefile(prn_file),
	writedevice(screen).


/****************************************************************
	Check if page shift is nessecarry before output of a
	multilined string. First parameter the string, second
	parameter some ektra lines.
****************************************************************/

  check_page(STRING,N1):-
	count_lines(STRING,0,N2),
	prn_pagelength(PAGELENGTH),
	prn_lineno(LINENO),
	N1+N2+LINENO<PAGELENGTH,!.
  check_page(_,_):-shift_page.


/****************************************************************
	Return current page number
****************************************************************/

  getpageno(PAGE):-prn_pageno(PAGE),!.


/****************************************************************
	SHIFT PAGE: The two predicates: make_header and
	make_footer must be globally defined.
****************************************************************/

  shift_page:-
	retract(prn_lineno(_)),fail.
  shift_page:-
	writedevice(DEV),DEV=screen,!,
	make_footer,
	write(">> Press any Key"),readkey(_),
	clearwindow,
	inc_pageno,
	assert(prn_lineno(0)),
	make_header,
	nl.
  shift_page:-
	make_footer,
	write('\012'),
	inc_pageno,
	prn_topmargin(Tm),!,
	mk_topmargin(Tm),
	assert(prn_lineno(0)),
	make_header,
	nl.


/****************************************************************
	WRITE STRING
****************************************************************/

  write_str(STR):-
	frontchar(STR,CH,STR1),!,
	write_ch(CH),
	write_str(STR1).
  write_str(_).


/****************************************************************
	WRITE A SINGLE CHAR
****************************************************************/

  write_ch('\n'):-!,
	nl,
	inc_lineno,
	prn_margin(MARGIN),!,
	mk_margin(MARGIN).
  write_ch(CH):-
  	prn_charconv,
  	writedevice(Dev),
  	Dev = prn_file,
	conv_char(Cch,CH),!,
  	write(CCH).
  write_ch(Ch):-write(Ch).	
