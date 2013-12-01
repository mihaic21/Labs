%====================== edit/13               ===============================
GOAL	clearwindow,
	EditMode=0, Indent=0, Insert=1, TextMode=0,
	dir("", "*.*", File),
	file_str(File, Str),

	edit(Str, Out, "", "", "Prolog.hlp", 1, "Prolog.hlp",
		EditMode, Indent, Insert, TextMode, RetPos, RetStatus),

	write("return status: ", RetStatus), nl,
	write("return position: ", RetPos), readchar(_),
	edit(Out, _, "", "", "Prolog.hlp", 1, "Prolog.hlp",
		1, Indent, Insert, 1, _, _).
