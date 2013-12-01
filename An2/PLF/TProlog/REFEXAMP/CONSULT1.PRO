%====================== consult/1             ===============================
DOMAINS
  ILIST=INTEGER*

DATABASE
  p1(INTEGER,CHAR,REAL,STRING,SYMBOL,ILIST)
  p2(INTEGER)

GOAL consult("dd.dba").
/* -------- CONTENT OF dd.dba ------------------
p1(1,'a',44.44,"Turbo","Prolog",[1,2,3,4])
p1(2,'b',-4.444E-98,"---","++++",[])
p2(88)
p2(99)
------------------------------------------------*/
