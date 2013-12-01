%====================== framewindow/1          ===============================
GOAL	makewindow(1,23,7,"",5,5,10,50),
	write("hello"),nl,readchar(_),
	framewindow(0),
	makewindow(_,_,Fattr,_,A,B,C,D),
	write("Fattr=",Fattr,", ",A,", ",B,", ",C,", ",D),nl,
	write("hello again"),nl,readchar(_),
	framewindow(112),
	write("hello once more"),nl,readchar(_).

