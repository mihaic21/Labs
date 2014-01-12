%trace
domains
	elem = integer
	list = elem*

predicates
	aux(list,elem)
	mountain(list)

clauses
	aux([_|T],1):-
		T=[],
		!.
	aux([H|[H1|T]],N):-
		H<H1,
		N=0,
		aux([H1|T],0).
	aux([H|[H1|T]],N):-
		H>H1,
		N=0,
		aux([H1|T],1).
	aux([H|[H1|T]],N):-
		H>H1,
		N=1,
		aux([H1|T],1).
		
	mountain([]).
	mountain(L):-
		aux(L,0).