domains
	number = integer
	list = number*

predicates
	gcd(number,number,number)
	gcdOfAll(list,number)

clauses
	gcd(A,A,A):-!.
	gcd(_,1,1):-!.
	gcd(A,B,R):-
		A<B,
		!,
		B1=B-A,
		gcd(A,B1,R).
	gcd(A,B,R):-
		A1=A-B,
		gcd(A1,B,R).
	
	gcdOfAll([A],A):-!.
	gcdOfAll([H|T],R):-
		gcdOfAll(T,R1),
		gcd(H,R1,R).
		