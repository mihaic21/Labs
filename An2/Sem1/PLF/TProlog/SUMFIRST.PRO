domains
	elem = integer

predicates
	sum(elem,elem)
	sum2(elem,elem)
	sum2aux(elem,elem,elem)
clauses
	sum(0,0).
	sum(N,R):-
		N1 = N - 1,
		sum(N1,R1),
		R = N + R1.
	
	sum2(N,R):-
		sum2aux(N,0,R).
	sum2aux(0,R,R).
	sum2aux(N,T,R):-
		N>0,
		T1 = T + N,
		N1 = N - 1,
		sum2aux(N1,T1,R).		