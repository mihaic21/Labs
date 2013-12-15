%====================== scroll/2              ===============================
predicates
  msg(STRING)

CLAUSES
  msg(X):-
	shiftwindow(OLD), shiftwindow(2),
	concat("Press any key to: ",X,X1),
	window_str(X1), readchar(_),
	shiftwindow(OLD).

goal	makewindow(2,112,0,"",21,5,1,70),
	makewindow(1, 12, 7, "Scroll window", 5, 5, 15, 70),
/* You may need to add a path to PROLOG.ERR */
	file_str("prolog.err",X),
	window_str(X),
	msg("Scrool text up"),scroll(3,0),
	msg("Scrool text down"),scroll(-6,0),
	msg("Scrool text up"),scroll(3,0),
	msg("Scrool text left"),scroll(0,15),
	msg("Scrool text right"),scroll(0,-30),
	msg("Scrool text left"),scroll(0,15).
