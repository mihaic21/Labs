%====================== window_attr/1         ===============================
GOAL	makewindow(1,7,0,"",0,0,25,80),
	window_attr(7),
	write("\nThe screen is now white on black background"),
	readchar(_),
	window_attr(112),
	write("\nThe screen is now black on white background"),
	readchar(_).
