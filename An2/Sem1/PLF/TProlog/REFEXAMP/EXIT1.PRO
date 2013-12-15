%====================== exit/1                ===============================
PREDICATES
  readfacts

CLAUSES
  readfacts:-consult("mydba.dba"),!.
  readfacts:-write("\nError reading mydba.dba"),exit(2).

GOAL	readfacts,
	write("Database consulted").
