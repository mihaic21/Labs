%====================== exit/2              ===============================
domains
   intlist = integer*
database
   p1(integer,char,real,string,string,intlist)
   p2(integer)
      
predicates
   readfacts
   
clauses
   readfacts :- consult("dd.dba"),!.
   readfacts :- write("\nError reading dd.dba"),exit.
   
goal
   readfacts,
   write("Database consulted").