%====================== keypressed/0          ===============================
PREDICATES
  count(INTEGER)
  loop

CLAUSES
  count(X):-keypressed,!,write("\nCounted to: ",X).
  count(X):-X1=X+1,count(X1).

  loop:-
	write("\n\nPress a key"),
	count(0),
	readchar(CH), % The char can be read later than it is detected
	write("\nYou pressed: ",CH),
	loop.

GOAL loop.
