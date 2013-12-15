%====================== openread/2             ===============================
domains
  file = input
  
predicates
  repfile(file)

clauses
  repfile(_).
  repfile(F):-
     not(eof(F)),
     repfile(F).
     
goal 
  openread(input,"dd.txt"),
  readdevice(input),
  repfile(input),
  readln(L),write(L),nl,
  fail.