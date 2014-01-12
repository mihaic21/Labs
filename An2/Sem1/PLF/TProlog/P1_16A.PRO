domains
	elem = integer
	list = elem*

predicates
	noAp(list,elem,elem)
	remove(list,list,list)
	removeall(list,list)

clauses
	noAp([],_,0).
	noAp([H|T],H,A):-
		noAp(T,H,A1),
		A=A1+1.
	noAp([H|T],E,A):-
		H<>E,
		!,
		noAp(T,E,A).
	
	remove([],[],_).
	remove([H|T],T1,L):-
		noAp(L,H,1),
		remove(T,T1,L).
	remove([H|T],[H|T1],L):-
		noAp(L,H,E),
		E>1,
		remove(T,T1,L).
	
	removeAll([],[]).
	removeAll(L,L1):-
		remove(L,L1,L).