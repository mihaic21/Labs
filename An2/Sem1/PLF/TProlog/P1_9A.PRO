domains
	elem = integer
	list = elem*

predicates
	appears(list,elem)
	transform(list,list)
	
clauses
	appears([E|_],E).
	appears([_|T],E):-
		appears(T,E).
	
	transform([],[]).
	transform([E|T],R):-
		appears(T,E),
		!,
		transform(T,R).
	transform([E|T],[E|R]):-
		not(appears(T,E)),
		!,
		transform(T,R).