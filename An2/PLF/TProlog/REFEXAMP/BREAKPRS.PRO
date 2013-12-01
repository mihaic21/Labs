%====================== breakpressed/0        ===============================
PREDICATES
  repeat

CLAUSES
  repeat.
  repeat:-repeat.

GOAL	break(OldBreakStatus),
	break(off),
	repeat,
	write("\nPress BREAK to end this loop"),
	breakpressed,
	break(OldBreakStatus).
