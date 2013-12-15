%====================== dir/3              ===============================
config "old.sys"

predicates
   extend(string,string)
   getfilename(string,string)

clauses
   extend(S,S) :- concat(_,".pro",S),!.
   extend(S,S1):- concat(S,".pro",S1).
   
   getfilename("",Fname):- dir("","*.pro",Fname,1,1,1),!.
   getfilename(X,X1):- extend(X,X1).
   
goal
   comline(X),
   getfilename(X,X1),
   file_str(X1,S),
   textmode(Rows,Cols),
   makewindow(1,23,0,"EDITOR",0,0,Rows,Cols),
   editmsg(S,S1,"","","",0,"",Ret),
   removewindow,
   Ret<>1,!,
   clearwindow,
   write("\nEnter file name to save: "),readln(NewName),
   file_str(NewName,S1).