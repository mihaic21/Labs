%======================       beep/0             ============================
PREDICATES
  input_integer(INTEGER)

CLAUSES
  input_integer(X):-write("Write an integer: "),readint(X),!.
  input_integer(X):-beep,input_integer(X).

GOAL input_integer(X),write(X).
