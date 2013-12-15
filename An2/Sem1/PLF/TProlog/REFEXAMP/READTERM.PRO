%====================== readterm/1            ===============================
domains
   file = dbase

database - mydbase
   ...

predicates
   my_consult(string)
   repeat(file)

clauses
   my_consult(FileName) :-
      openread(dbase, FileName),
      readdevice(dbase),
      repeat(dbase),
      readterm(mydbase, Term),
      assertz(Term),
      fail.
   my_consult(_).

   repeat(_).
   repeat(File) :- not(eof(File)), repeat(File).