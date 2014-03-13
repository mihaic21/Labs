domains
	elem = integer

predicates
	power(real,elem,real)

clauses
	%power(0,_,0):-!.
	%power(1,_,1):-!.
	%power(_,0,1):-!.
	power(X,1,X):-!.
	power(X,N,R):-
		N < 0,
		!,
		N1 = -N,
		X1 = 1/X,
		power(X1,N1,R).
	power(X,N,R):-
		N > 0,
		!,
		N1 = N-1,
		power(X,N1,R1),
		R = R1 * X.