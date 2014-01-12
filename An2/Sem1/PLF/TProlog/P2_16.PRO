domains
	elem = integer
	list = elem*
	
predicates
	remove(list,list)

clauses
	remove([],[]).
	remove([H1|[H2|[H3|T]]],L):-
		H1<H2,
		H2<H3,
		!,
		remove([H2|[H3|T]],L).
	remove([H1|[H2|[H3|T]]],L):-
		H1<H2,
		H2>=H3,
		!,
		remove([H3|T],L).
	remove([H1|[H2|[]]],[]):-
		H1<H2,
		!.
	remove([H|T],[H|L]):-
		remove(T,L).