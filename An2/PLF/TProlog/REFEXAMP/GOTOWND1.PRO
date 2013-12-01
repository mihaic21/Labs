%====================== gotowindow/1          ===============================
PREDICATES
  output(INTEGER)

CLAUSES
  output(N):-
	N<1000,!,
	WNO=1+N mod 2,
	gotowindow(WNO),write(N,' '),
	N1=N+1,output(N1).
  output(1000):-!,beep,output(1001).
  output(N):-
	WNO=1+N mod 2,
	shiftwindow(WNO),write(N,' '),
	N1=N+1,output(N1).

GOAL makewindow(1,7,7,"Window 1",0,0,25,40),
     makewindow(2,7,7,"Window 2",0,40,25,40),
     output(0).
