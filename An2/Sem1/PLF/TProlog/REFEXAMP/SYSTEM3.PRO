%====================== system/3             ===============================
goal	makewindow(1, 7, 0, " System ", 0, 0, 25, 80),
	write("If you have an EGA card, and the file ega43.com exist",
	      "we will try to put you in 43 rows mode"),
	unreadchar('\13'),unreadchar('t'),unreadchar('i'),unreadchar('x'),unreadchar('e'),
	unreadchar('\13'),unreadchar('3'),unreadchar('4'),
	unreadchar('a'),unreadchar('g'),unreadchar('e'),
	system("",0,ErrorLevel),
	write("\nErorLevel=",ErrorLevel),
	readchar(_).
