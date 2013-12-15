%====================== storage/3             ===============================
/* This program demonstrates the built-in predicate storage. */

DATABASE
   fact(string)

PREDICATES
  test
  wstor(STRING)

CLAUSES
  wstor(TXT):-
	storage(Stack, Heap, Trail),
	write(TXT,":\n"),
	write("Stack size=", Stack, ", Heap size=",Heap, ", Trail size=", Trail),
	readchar(_), nl.

  test:-wstor("After call of test"),fail.
  test:-
 /* You may need to add a path to PROLOG.ERR */ 
        file_str("prolog.err", FILE),
	wstor("After load of file to GSTACK"),
	assert(fact(FILE)),
	wstor("After assert of file"),
	fail.
  test:-wstor("After fail").

goal	makewindow(1, 7, 7, " STORAGE ", 0, 0, 20, 80),
	wstor("Before call of test"),
	test,
	wstor("After return from test").
