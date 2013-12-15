%====================== retract/1             ===============================
DOMAINS
  LIST = INTEGER*

DATABASE - dba1
  fact1(INTEGER,STRING,LIST)

DATABASE - dba2
  fact2(INTEGER,STRING)

CLAUSES
  fact1(1,"fact1",[1,2,3]).
  fact1(2,"fact2",[1,3]).
  fact1(3,"fact2",[3,2,1]).

  fact2(1,"one").
  fact2(1,"one once more").
  fact2(2,"two").

  -------- Dialog --------
Goal: fact1(X,Y,Z)
X=1, Y=fact1, Z=[1,2,3]
X=2, Y=fact2, Z=[1,3]
X=3, Y=fact2, Z=[3,2,1]
3 Solutions

Goal: retract(fact1(X,Y,[_,2|Z]))
X=1, Y=fact1, Z=[3]
X=3, Y=fact2, Z=[1]
2 Solutions

Goal: retract(fact1(X,Y,Z))
X=2, Y=fact2, Z=[1,3]
1 Solution

Goal: fact1(X,Y,Z)
No Solution

Goal: retract(fact2(1,X))
X=one
X=one once more
2 Solutions
