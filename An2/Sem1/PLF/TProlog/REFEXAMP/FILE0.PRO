%====================== fail/0                ===============================
DOMAINS
  Name, Addr = STRING

DATABASE
  person(Name,Addr)

PREDICATES
  clear_database
  print_all

CLAUSES
  clear_database:-retract(person(_,_)),fail.
  clear_database.

  print_all:-
	person(Name,Addr),
	write("\nName=",Name,", Adr=",Addr),
	fail.
