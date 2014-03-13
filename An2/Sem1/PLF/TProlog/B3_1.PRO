domains
	elem = integer
	list = elem*

predicates
	remove(list, elem, list)
	remove(list, elem, elem, list)
		
clauses
	remove([],_,[]):-!.
	remove(L,N,R):-
		remove(L,N,1,R).

	remove([],_,_,[]):-!.
	remove([_|T],N,K,R):-
		K=N,
		!,
		N1=2*N,
		K1=K+1,
		remove(T,N1,K1,R).
	remove([H|T],N,K,[H|R]):-
		K1=K+1,
		remove(T,N,K1,R).