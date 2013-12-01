%====================== comline/1               ===============================
/* You may need to add a path to OLD.SYS */
config "old.sys"   % keyboard config + helpline

PREDICATES
  extend(STRING,STRING)
  getfilename(STRING,STRING)

CLAUSES
  extend(S,S):-concat(_,".pro",S),!.
  extend(S,S1):-concat(S,".pro",S1).

  % Pick name from directory if no argument
  getfilename("",FNAME):-dir("","*.pro",FNAME),!.
  % append extension .PRO if no extension
  getfilename(X,X1):-extend(X,X1).

GOAL	comline(X),
	getfilename(X,X1),
	file_str(X1,S),
	textmode(ROWS,COLS),CC=COLS-1,
	makewindow(1,23,0,"EDITOR",0,0,ROWS,CC),
	editmsg(S,S1,"","","",0,"",RET),
	removewindow,
	RET><1,!,  % Save if not ESC pressed
	file_str(X1,S1).
