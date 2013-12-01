%====================== equals/2              ===============================
domains
   dom1 = reference funci(integer, dom2)
   dom2 = string

database
   dbPred(dom2, dom1)
   
goal
   asserta(dbpred("November 8 1988", funci(1,"November 8 1988"))),
   X = 1,
   X1 = X + 7,
   S = "November ",
   S1 = " 1988 ",
   str_int(S2,X1),
   concat(S, S2, S3),
   concat(S3, S, S4),
   DataStruct = funci(_,S4),
   Fact = dbPred(S4, DataStruct),
   dbpred(_, funci(N, _)),
   DataStruct = funci(N, _),
   write(Fact).