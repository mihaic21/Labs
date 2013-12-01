%====================== eof/1                 ===============================
DOMAINS
  FILE = input

PREDICATES
  repfile(FILE)

CLAUSES
  repfile(_).
  repfile(F):-not(eof(F)),repfile(F).

GOAL	openread(input,"dd.txt"),
	readdevice(input),
	repfile(input),
	readln(L),write(L),nl,
	fail.
