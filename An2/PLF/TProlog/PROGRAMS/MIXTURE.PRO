/************************************************************************/
/*                                                                      */
/*             Turbo Prolog 2.0 example program                         */
/*									*/
/*   Copyright (c) 1986, 88 by Borland International, Inc.              */
/*                                                                      */
/*		A couple of small usefull predicates			*/
/*                                                                      */
/************************************************************************/

DOMAINS
  LIST=INTEGER*

PREDICATES
  append(LIST,LIST,LIST)
  member(INTEGER,LIST)
  reverse(LIST,LIST)
  genl(LIST,INTEGER)
  
  repeat
  timer

  plus(INTEGER,INTEGER,INTEGER)
  numb(INTEGER)

  fib(INTEGER,INTEGER)
  factorial(INTEGER,REAL)

CLAUSES
  /* Concatenate two lists */
  append([],L,L).
  append([X|L1],L2,[X|L3]):-append(L1,L2,L3).  

  /* Membership of a list */
  member(X,[X|_]).
  member(X,[_|L]):-member(X,L).

  /* reverse a list */
  reverse([],[]).
  reverse([H|T],Lr):-reverse(T,Tr), append(Tr,[H],Lr).

  /* generate a reversed list */
  genl( [], 0 ).
  genl( [I|T], I )  :- I>0, I1=I-1, genl(T,I1).

  repeat. repeat:-repeat.

  timer:-
	time(H1,M1,S1,D1),
	/* Call program which should be timed */
	time(H2,M2,S2,D2),
	Time=(D2-D1)+100*( (S2-S1) + 60*( (M2-M1) +60*(H2-H1) ) ),
	write("Time = ",Time,"/100 Sec" ),nl.

  /* This predicate implements addition for all flow-patterns */
  plus(X,Y,Z):-bound(X),bound(Y),Z=X+Y.
  plus(X,Y,Z):-bound(Z),bound(Y),X=Z-Y.
  plus(X,Y,Z):-bound(Z),bound(X),Y=Z-X.
  plus(X,Y,Z):-free(X),free(Y),bound(Z),numb(X),Y=Z-X.
  plus(X,Y,Z):-free(X),free(Z),bound(Y),numb(X),Z=X+Y.
  plus(X,Y,Z):-free(Y),free(Z),bound(X),numb(Y),Z=X+Y.
  plus(X,Y,Z):-free(X),free(Y),free(Z),numb(X),numb(Y),Z=X+Y.

  numb(0).
  numb(X):-numb(A), X=A+1.

  fib(1,1):-!.
  fib(2,1):-!.
  fib(I,RES):-I>2, I1=I-1, I2=I-2, fib(I1,M), fib(I2,N), RES=N+M.

  factorial(0,1):-!.
  factorial(N,RES) :-
	N1=N-1, factorial(N1,N1fak), Res=N*N1fak.

  
PREDICATES
  nondeterm for(Integer,Integer,Integer)
  next(integer,integer)

CLAUSES
  for(Cur,_,Cur).
  for(Cur,Max,I):- Cur2=Cur+1, Cur2<=Max, for(Cur2,Max,I).
   
  next(Max,Max).
  
/* GOAL
    MaxI=3,
    MaxJ=5,
    for(0,MaxI,I),nl,
        writef("I= % : ",I),
        for(1,MaxJ,J),
            write("  J = ",J),
        next(MaxJ,J),
    next(MaxI,I).   
 */   

PREDICATES
    sort(LIST,LIST)
    sort(LIST,LIST,LIST)
    split(LIST,INTEGER,LIST,LIST)

CLAUSES
    sort(LIST,RESULT):-
          sort(LIST,[],RESULT).    
    sort([HEAD|TAIL],SORTEDBIGGER,RESULT):-
          split(TAIL,HEAD,LESSLIST,BIGGERLIST),
          sort(BIGGERLIST,SORTEDBIGGER,NEWSORTEDBIGGER),
          sort(LESSLIST,[HEAD|NEWSORTEDBIGGER],RESULT).
    sort([],SORTED,SORTED).

    split([],_,[],[]).
    split([Y|L],X,[Y|L1],L2):-Y<X,!,split(L,X,L1,L2).
    split([Y|L],X,L1,[Y|L2]):-split(L,X,L1,L2).
    
    
    