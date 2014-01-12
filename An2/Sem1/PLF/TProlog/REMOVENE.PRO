%remove all negative elements from a list
domains
	elem = integer
	list = elem*

predicates
	remove(list,list)

clauses
	remove([],[]).
	remove([H|T],[H|L]):-
		H>=0,
		!,
		remove(T,L).
	remove([_|T],L):-
		remove(T,L).