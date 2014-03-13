domains
	elem = integer
	list = elem*

predicates
	rem(elem,list,list)
	isPrime(integer,integer)
	prime(integer)	
		
clauses
	isPrime(_,1):-!.
	isPrime(N,M):-
		M>=2,
		N mod M <> 0,
		!,
		M1 = M-1,
		isPrime(N,M1).
	
	prime(N):-
		N>=2,
		!,
		N1 = N-1,
		isPrime(N,N1).
	
	rem(0,L,L):-!.
	rem(_,[],[]):-!.
	rem(N,[H|T],L):-
		prime(H),
		!,
		N1 = N-1,
		rem(N1,T,L).
	rem(N,[H|T],[H|L]):-
		rem(N,T,L).