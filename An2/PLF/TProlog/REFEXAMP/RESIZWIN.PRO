%====================== resizewindow/0        ===============================
GOAL	makewindow(1,7,7,"test window",5,5,15,70),
	write("\nPlease resize the window\n"),
	resizewindow,
	makewindow(Wno,Wattr,Fattr,Text,Srow,Scol,Rows,Cols),
	write("\nNew placement of the window:"),
	writef("\nWno=%, Wattr=%, Fattr=%, Text=%, Srow=%, Scol=%, Rows=%, Cols=%",
		Wno,Wattr,Fattr,Text,Srow,Scol,Rows,Cols),
	readchar(_).
