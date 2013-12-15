%====================== makewindow/11         ===============================
GOAL	makewindow(1,7,7,"Window 1",1,1,10,40,1,0,"\176\176\176\176\176\179"),
	makewindow(2,7,7,"Window 2",6,20,10,40,0,30,"******"),
	readchar(_),
	window_str(X), % Capture screen content
	removewindow,
	readchar(_),
	write(X),readchar(_).
