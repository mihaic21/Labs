%====================== cursor/2              ===============================
GOAL	makewindow(1,7,7,"cursor test",1,1,20,70),
	cursor(5,20),write("Hello"),
	cursor(ROW,COL),ROW1=ROW+1,cursor(ROW1,COL),
	write("you"),
	cursor(ROW2,COL2),ROW3=ROW1+1,cursor(ROW3,COL2),
	write("guy's"),
	cursor(0,0).
