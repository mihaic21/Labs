domains
	elem = integer
	list = elem*

predicates
	insert(integer,list,integer,list)

clauses
	insert(E,[],_,[E]).
	insert(E,[H|T],0,[E|[H|T]]).
	insert(E,[H|T],P,[H|L]):-
		N = P-1,
		insert(E,T,N,L).
	