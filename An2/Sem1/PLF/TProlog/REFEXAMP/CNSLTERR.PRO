%====================== consulterror/3         ===============================
DOMAINS
  ILIST=INTEGER*

DATABASE
  p1(INTEGER,CHAR,REAL,STRING,SYMBOL,ILIST)
  p2(INTEGER)

PREDICATES
  error_routine(integer)
GOAL 
  trap(consult("dd.dba"), Err, error_routine(Err)),
  write("Operation completed successfully..").

CLAUSES
  error_routine(Error):-
     Error>1100, Error<1110, !,
     consulterror(Line,LinePos,FilePos),
     write("Line with error is : ",Line,"\nPosition on line : ",LinePos),nl,
     write("File position is : ",FilePos),
     write("\n\nOperation not successful"),
     readchar(_),
     exit.
     
/* -------- CONTENT OF dd.dba ------------------
p1(1,'a',44.44,"Turbo","Prolog",[1,2,3,4])
p1(2,'b',-4.444E-98,"---","++++",[])
p2(88)
p2(99)
------------------------------------------------*/
