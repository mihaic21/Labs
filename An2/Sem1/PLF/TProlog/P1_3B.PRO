domains
	elem = integer
	list = elem*
	
predicates
	gcd(elem,elem,elem)
	gcdList(list,elem)
	
clauses
	gcd(A,B,R):-
		A = B,
		R = B,
		!.
	gcd(A,B,R):-
		B > A,
		C = B-A,
		gcd(C,A,R).
	gcd(A,B,R):-
		A > B,
		C = A-B,
		gcd(C,B,R).
	
	gcdList([H],H):-!.
	gcdList([H1|[H2|T]],R):-
		gcd(H1,H2,R1),
		gcdList([R1|T],R).