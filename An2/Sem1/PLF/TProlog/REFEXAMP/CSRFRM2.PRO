%====================== cursorform/2          ===============================
predicates
  for(INTEGER,INTEGER,INTEGER)
  testcursorform

clauses
  for(X,X,_).
  for(I,A,B):-B>A,A1=A+1,for(I,A1,B).

  testcursorform:-
	for(StartLine,0,10), for(EndLine,0,10),
	cursorform(StartLine, EndLine),
	write("StartLine=",StartLine, ", EndLine= ", EndLine, " Press any key "),
	readchar(_),nl,
	StartLine=10,EndLine=10,!.

GOAL cursorform(A,B), testcursorform, cursorform(A,B).
