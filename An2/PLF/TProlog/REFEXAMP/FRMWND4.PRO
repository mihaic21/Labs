%====================== framewindow/4         ===============================
PREDICATES
  delay(INTEGER)
  changeframe(INTEGER)

CLAUSES
  delay(0):-!.
  delay(N):-N1=N-1,delay(N1).

  changeframe(-1):-!,changeframe(50).
  changeframe(N):-
	not(keypressed),
	framewindow(7,"PRESS THE SPACE BAR",N,"\176\176\176\176\176\179"),
	delay(2000),
	N1=N-1,
	changeframe(N1).
  changeframe(_).

GOAL	makewindow(1,23,7,"TEST",5,5,10,50),
	changeframe(0).
