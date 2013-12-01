%====================== snowcheck/1           ===============================
PREDICATES
  checksnow(STRING)
  setsnow(CHAR)
  testwrite(INTEGER)

CLAUSES
  checksnow(on):-!,
	snowcheck(off),
	testwrite(1000),
	write("\nDid you saw any snow on your screen ?"),
	readchar(ANS),
	setsnow(ANS).

  checksnow(off):-!,
	testwrite(1000),
	write("\nDid you saw any snow on your screen ?"),
	readchar(ANS),
	setsnow(ANS).

  setsnow('y'):-snowcheck(on).
  setsnow('Y'):-snowcheck(on).
  setsnow(_).

  testwrite(0):-!.
  testwrite(N):-
	makewindow(_,_,_,_,_,_,ROWS,COLS),
	RR=ROWS-1, CC=COLS-1,
	random(RR,X),random(CC,Y),
	scr_char(X,Y,CH), scr_char(X,Y,CH),
	N1=N-1,
	testwrite(N1).

GOAL	snowcheck(OLDSTATUS),
	write("\nYour snowcheck status is set to :",OLDSTATUS),
	checksnow(OLDSTATUS).
