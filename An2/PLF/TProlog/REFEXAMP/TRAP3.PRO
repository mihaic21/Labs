%====================== trap/3                ===============================
domains
   file = dd

predicates
   nondeterm process(STRING)
   error(INTEGER)

clauses
   process("a").
   process("b"):- writedevice(dd).

   error(E):- writef("\n\n>> Error code=%", E),beep.

goal	write("\n\nTEST OF TRAPPING PROGRAMMING ERRORS"),
	trap( process(A), E, error(E) ),
	write("\nSucces process: A=", A),
	write("\nNow try second clause in process"),
	fail.
