%trace
domains
	elem = integer
	list = elem*
	
predicates
	sort(list,list)
	insert(elem,list,list)
	
clauses
	insert(E,[],[E]).
	insert(E,[H|T],[E|[H|T]]):-
		E < H,
		!.
	insert(E,[H|T],[H|L]):-
		E > H,
		!,
		insert(E,T,L).
	insert(E,[H|T],[H|T]):-
		E = H,
		!.
		
	sort([],[]).
	sort([H|T],L):-
		sort(T,L1),
		insert(H,L1,L).