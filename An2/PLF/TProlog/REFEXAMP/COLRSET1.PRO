%====================== colorsetup/1          ===============================
GOAL	makewindow(1,7,7,"test window",5,30,10,40),
	cursor(3,5),write("Change color for the window"),
	colorsetup(0),
	cursor(3,5),write("Change color for the border"),
	colorsetup(1),
	clearwindow,
	makewindow(_,WindAttr,FrameAttr,_,_,_,_,_),
	write("\n\nWindAttr=",WindAttr),
	write("\n\nFrameAttr=",FrameAttr),
	readchar(_).
