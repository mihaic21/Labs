%====================== file_str/2            ===============================
/* You may need to add a path to OLD.SYS */
config "old.sys"

PREDICATES
  extend(STRING,STRING)
  getfilename(STRING,STRING)

CLAUSES
  extend(S,S):-concat(_,".pro",S),!.
  extend(S,S1):-concat(S,".pro",S1).

  getfilename("",FNAME):-dir("","*.pro",FNAME),!.
  getfilename(X,X1):-extend(X,X1).

GOAL	comline(X),
	getfilename(X,X1),
	file_str(X1,S),
	textmode(ROWS,COLS),
	makewindow(1,23,0,"EDITOR",0,0,ROWS,COLS),
	editmsg(S,S1,"","","",0,"",RET),
	removewindow,
	RET><1,!,
	clearwindow,
	write("\Enter file name to save: "),readln(NewName),
	file_str(NewName,S1).
