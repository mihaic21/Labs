%====================== retract/2             ===============================
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
Goal: retract(X,dba1)
X=fact1(1,"fact1",[1,2,3])
X=fact1(2,"fact2",[1,3])
X=fact1(3,"fact2",[3,2,1])
3 Solutions

Goal: retract(fact2(1,X),dba2)
X=one
X=one once more
2 Solutions
