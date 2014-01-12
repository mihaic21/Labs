domains
	elem = symbol
	list = elem*
	pairs = i(integer); s(symbol)
	list_pair = pairs*
	list_final = list_pair*

predicates
	create(list,list_final)
	count(elem, list, integer)
	remove(elem, list, list)
	create_aux(list, list_final, list)

clauses
	count(_,[],0).
	count(E,[H|T],N):-
		E=H,
		!,
		count(E,T,N1),
		N=N1+1.
	count(E,[_|T],N):-
		count(E,T,N).
	
		
	remove(_,[],[]).
	remove(E,[H|T],L):-
		H=E,
		remove(E,T,L).
	remove(E,[H|T],[H|L]):-
		H<>E,
		remove(E,T,L).
		
	
	create([],[]):-
		!.
	create(L,LR):-
		create_aux(L,LR,L).
	
	create_aux([],[],_):-
		!.
	create_aux([H|_],[[i(N),s(H)]|LR],L):-
		count(H,L,N),
		remove(H,L,L1),
		create_aux(L1,LR,L1).