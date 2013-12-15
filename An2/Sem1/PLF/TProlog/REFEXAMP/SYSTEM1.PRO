%====================== system/1              ===============================
/* This program demonstrates the use of the built-in predicate system. */

goal	makewindow(1, 7, 0, " System ", 0, 0, 25, 80),
	write("\nYou will not be sent out in the DOS system, and to return",
	      "\nback to Prolog you must write: exit.",
	      "\nGood luck\n\n"),
	unreadchar('w'),unreadchar('/'),unreadchar(' '),
	unreadchar('r'),unreadchar('i'),unreadchar('d'),
	system(""),
	write("\nPress any key to continue"),
	readchar(_), clearwindow,
	write("We will now use DOS to print the file: PROLOG.ERR.\n",
		"You may stop the scrolling with the toggle <CTRL-S>.\n",
		"Or abort with <CTRL-BREAK>."),
	readchar(_), clearwindow,
	system("type c:\\prolog\\prolog.err"),
	write("That's it!"),
	readchar(_).
