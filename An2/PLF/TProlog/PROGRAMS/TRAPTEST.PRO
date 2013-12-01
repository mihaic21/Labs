/*****************************************************

	TRAP TEST

 Example: trapping of a file not open error

******************************************************/

DOMAINS
  FILE = dd

PREDICATES
  nondeterm process(STRING)
  error(INTEGER)

CLAUSES
  process("a").
  process("b"):-
	writedevice(dd).

  error(E):-
	writef("\nerror(%)",E).

GOAL 	trap( process(A), E, error(E) ),
	write("\nSucces process: A=",A),
	fail.

