%====================== editmsg/8             ===============================
GOAL	file_str("dd.txt", STR),
	editmsg(STR, OUT, "Left Header", "Right Header",
		"Press Shift F1 for help with asserta", 399, "Prolog.hlp", CODE),
	Code=0,
	write("F10 was pressed");
	write("Escape was pressed,  - or file not found").
