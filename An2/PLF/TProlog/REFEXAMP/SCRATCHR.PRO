%=====================  scr_attr/3 + scr_char/3  ============================
/* This program demonstrates the built-in predicates scr_attr and
   scr_char. Note: The colors used in this sample program may not
   be visible on a monochrome system. */

goal	makewindow(1, 12, 7, "", 0, 0, 10, 40),
	scr_attr(0, 0, 139),  scr_char(0, 0, 'a'),
	scr_attr(0, 1, 72),   scr_char(0, 1, 'B'),
	scr_attr(0, 2, 24),   scr_char(0, 2, 'c'),
	scr_attr(0, 3, 33),   scr_char(0, 3, 'D'),
	scr_attr(0, 4, 42),   scr_char(0, 4, 'e'),
	scr_attr(0, 5, 94),   scr_char(0, 5, 'F'),
	% Now read content of position (0,3)
	scr_attr(0, 3, Attr),   scr_char(0, 3, Char),
	cursor(2,0),
	write("The attribute at position (0,3) is ", Attr),
	write("\nand the character is ", Char, ".").
