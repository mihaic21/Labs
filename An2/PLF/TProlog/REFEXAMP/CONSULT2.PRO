%====================== consult/2             ===============================
DOMAINS
  ILIST=INTEGER*

DATABASE - facts1
  p1(INTEGER,CHAR,REAL,STRING,SYMBOL,ILIST)

DATABASE - facts2
  p2(INTEGER)

GOAL consult("facts1.dba",facts1).
     consult("facts2.dba",facts2).

/* -------- CONTENT OF facts1.dba ------------------
p1(1,'a',44.44,"Turbo","Prolog",[1,2,3,4])
p1(2,'b',-4.444E-98,"---","++++",[])
------------------------------------------------*/

/* -------- CONTENT OF facts2.dba ------------------
p2(88)
p2(99)
------------------------------------------------*/
