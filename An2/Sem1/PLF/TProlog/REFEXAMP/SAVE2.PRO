%====================== save/2                ===============================
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
/*
Goal: save("con",dba1)
fact1(1,"fact1",[1,2,3])
fact1(2,"fact2",[1,3])
fact1(3,"fact2",[3,2,1])
True
Goal: save("con",dba2)
fact2(1,"one")
fact2(1,"one once more")
fact2(2,"two")
True
