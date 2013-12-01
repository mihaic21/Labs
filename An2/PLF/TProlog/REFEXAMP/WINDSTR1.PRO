%====================== window_str/1          ===============================
GOAL	makewindow(1,7,0,"",0,0,25,80),
	makewindow(2,7,7,"",5,10,10,50),
/* You may need to add a path to PROLOG.ERR */
	file_str("prolog.err",STR1),
	window_str(STR1),
	readchar(_),
	window_str(STR2),
	removewindow,
	window_str(STR2).
