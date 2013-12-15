%====================== break/1               ===============================
PREDICATES
  repeat

CLAUSES
  repeat.
  repeat:-repeat.

GOAL	break(OldBreakStatus),
	break(off),
	repeat,
	write("\nYou can only abort this program by typing a '#'."),
	readchar(CH),
	CH='#',!,
	break(OldBreakStatus).
