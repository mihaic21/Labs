%Build the sublist (lm, ..., ln) of the list(l1,...,lk).

domains
	elem=integer
	list=elem*
	
predicates
	sublist(list,elem,elem,list)
	sub(list,elem,list)
	
clauses
	sublist([],_,_,[]).
	sublist(_,M,N,[]):-M>N,!.
	sublist([H|_],M,N,[H]):-M=1,N=1.
	sublist(T,M,N,L):-M=1,N>1,sub(T,N,L),!.
	sublist([_|T],M,N,L):-M>1,M1=M-1,N1=N-1,sublist(T,M1,N1,L).
	
	sub([],_,[]).
	sub([H|_],N,[H]):-N=1.
	sub([H|T],N,[H|L]):-N>1,N1=N-1,sub(T,N1,L).