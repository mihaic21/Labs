  /****************************************************************/
  /*			TOWERS OF HANOI				  */
  /*			===============				  */
  /*								  */
  /* Call the predicate hanoi with the number of discs, you want. */
  /****************************************************************/
DOMAINS
  TIME, ROW, COL, NUMBER = INTEGER

PREDICATES
  hanoi( NUMBER )
  move(  NUMBER, NUMBER, ROW, ROW, ROW, COL, COL, COL )
  inform(  NUMBER, NUMBER, ROW, ROW, COL, COL )
  makepole( NUMBER, NUMBER, COL)
  dd(TIME)
  move_vert(COL,NUMBER,ROW,ROW)
  move_horizon(ROW,NUMBER,COL,COL)
  keyact(CHAR)
  gendelay
  showdelay
  nondeterm for(INTEGER,INTEGER,INTEGER)
  fill

DATABASE
  determ delay(INTEGER)

CLAUSES
  for(X,X,_).
  for(I,A,B):-B>A,A1=A+1,for(I,A1,B).

  fill:- % fill current window.
	makewindow(_,_,_,_,_,_,ROWS,COLS),
	RR=ROWS-1, CC=COLS-1,
	for(COL,0,CC),for(ROW,0,RR),
	scr_char(ROW,COL,'°'),
	fail.
  fill.

  gendelay :- inkey(CH), keyact(CH),showdelay,!.
  gendelay :- delay(DELAY),!, dd(DELAY).
  gendelay.

  dd(0):-!.
  dd(N):-N1=N-1,dd(N1).

  keyact('+'):-
	retract(delay(VAL)),VAL1=VAL+1+VAL div 10,
	VAL1<=25000,!,assert(delay(VAL1)).
  keyact('+'):-!,assert(delay(25000)).
  keyact('-'):-
	retract(delay(VAL)),VAL1=VAL-1-VAL div 10,
	VAL1>=0,!,assert(delay(VAL1)).
  keyact('-'):-!,assert(delay(0)).
  keyact(_):-retractall(delay(_)),assert(delay(0)).

  
  showdelay:-
	delay(DELAY),gotowindow(2),
	clearwindow,
	write("Delay= ",DELAY),gotowindow(1).

  hanoi(N) :-   
  	N<=13,!,
	textmode(ROWS,COLS),
	makewindow(4,7,0,"",0,0,ROWS,COLS),
	fill,
	ROWS1=ROWS-1,
	makewindow(3,40,0,"",ROWS1,0,1,COLS),
	write("   + Improve delay,      - Lower delay,       Other keys removes delay"),
	VB=2+6*N,VH=3+N,CV=N, CM=3*N, CH=5*N,
  	STCOL=(79-6*N) div 2, STROW=(25-VH) div 2 -1,
	STROW1=STROW+1+VH,
	makewindow(2,74,0,"DELAY",STROW1,34,1,13),
	makewindow(1,7,52,"Hanoi",STROW,STCOL,VH,VB),
	retractall(delay(_)),
	makepole(N,N,CV),
	assert(delay(100)),
	showdelay,
	move(N,N,0,0,0,CV,CM,CH),
	cursor(0,0), write("Press any key"),readchar(_).

  hanoi(_):- write("maximum 13 disc's\n").

  move(H,1,HA,_,HC,CA,_,CH):-!,inform(H,1,HA,HC,CA,CH).
  move(H,N,HA,HB,HC,CA,CB,CC):-
		N1=N-1,
		HA1=HA+1,
		move(H,N1,HA1,HC,HB,CA,CC,CB),
		inform(H,N,HA,HC,CA,CC),
		HC1=HC+1,
		move(H,N1,HB,HA,HC1,CB,CA,CC).

 inform( H, N, H1, H2, C1, C2 ) :-
	C11=C1-N, C22=C2-N, NN=2*N,
	H11=H-H1, H22=H-H2,
	move_vert(C11,NN,H11,1),
	move_horizon(1,NN,C11,C22),
	move_vert(C22,NN,1,H22).

  makepole(_,0,_):-!.
  makepole(H,N,C):-HH=H-N,inform(H,N,HH,HH,C,C), N1=N-1, makepole(H,N1,C).

  move_vert(_,_,H,H):-!.
  move_vert(COL,SIZE,H1,H2):-H1<H2,!, /* move up */
	H11=H1+1,
	field_attr(H11,COL,SIZE,112),
	field_attr(H1,COL,SIZE,7),gendelay,gendelay,
	move_vert(COL,SIZE,H11,H2).
  move_vert(COL,SIZE,H1,H2):-H1>H2,!, /* move down */
	H11=H1-1,
	field_attr(H11,COL,SIZE,112),
	field_attr(H1,COL,SIZE,7),gendelay,gendelay,
	move_vert(COL,SIZE,H11,H2).

  move_horizon(_,_,H,H):-!.
  move_horizon(ROW,SIZE,C1,C2):-C1<C2,!, /* move right */
	C11=C1+1, HH=C1+SIZE,
	field_attr(ROW,HH,1,112),
	field_attr(ROW,C1,1,7),gendelay,
	move_horizon(ROW,SIZE,C11,C2).
  move_horizon(ROW,SIZE,C1,C2):-C1>C2,!, /* move left */
	C11=C1-1, HH=C11+SIZE,
	field_attr(ROW,C11,1,112),
	field_attr(ROW,HH,1,7),gendelay,
	move_horizon(ROW,SIZE,C11,C2).

GOAL hanoi(12).
