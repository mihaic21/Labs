%====================== clearwindow/0        ===============================
GOAL	makewindow(1,7,7,"test window",1,1,20,70),
	cursor(5,20), write("Hello there"),
	cursor(10,10), write("Press any key to clear the window"),
	readchar(_),
	clearwindow,
	write("Cursor moves to (0,0)"),
	readchar(_).
