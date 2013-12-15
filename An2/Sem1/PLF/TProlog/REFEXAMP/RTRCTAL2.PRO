%====================== retractall/2          ===============================
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
Goal: fact1(X,Y,A)
X=1, Y=fact1, A=[1,2,3]
X=2, Y=fact2, A=[1,3]
X=3, Y=fact2, A=[3,2,1]
3 Solutions

Goal: retractall(_,dba1)
True

Goal: fact1(X,Y,Z)
No Solution
Goal:
