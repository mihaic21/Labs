%trace
domains
	elem = integer
	list = elem*

predicates
	delete(list, elem, list)

clauses
	delete([],_,[]).
	delete([E|L],E,X):-
		
		delete(L,E,X).
	delete([A|L],E,[A|X]):-
		delete(L,E,X).

goal
	delete([1,2,3,4,2,3,2,2,5], 3, L),
	write(L).