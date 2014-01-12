domains
	elem = integer
	list = elem*

predicates
	remove(list,list)

clauses
	remove([],[]).
	remove([H1|[H2|[H3|T]]],L):-
		H3=H2+1,
		H2=H1+1,
		!,
		remove([H2|[H3|T]],L).
	remove([H1|[H2|[H3|T]]],L):-
		H2=H1+1,
		H3<>H2+1,
		!,
		remove([H3|T],L).
	remove([H1|[H2|[]]],[]):-
		H2=H1+1,
		!.
	remove([H|T],[H|L]):-
		remove(T,L).