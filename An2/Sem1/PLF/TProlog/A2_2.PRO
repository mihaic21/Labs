%trace
domains
	lista = integer*
	llista = lista*

predicates
	sum(lista,integer)
	given_sum(llista,llista,integer)
	aux(integer,lista,integer,lista)
	aranj(lista,integer,lista)
	aranjam(lista,lista,integer,lista)
	toate_aranj(lista,integer,integer,llista)

clauses
	sum([],0):-!.
	sum([H|T],R):-
		sum(T,R1),
		R=H+R1.
	
	given_sum([],[],_):-!.
	given_sum([H|L],[H|R],S):-
		sum(H,R1),
		R1=S,
		given_sum(L,R,S).
	given_sum([H|L],R,S):-
		sum(H,R1),
		R1<>S,
		given_sum(L,R,S).

	aux(N,_,1,[N]):-!.
	aux(N,[H|T],K,L):-
		N=H,!,
		aux(N,T,K,L).
	aux(N,[H|T],K,L):-
		K1=K-1,
		aux(N,T,K1,L1),
		L=[H|L1].
	aux(N,[_|T],K,L):-
		aux(N,T,K,L).
	
	aranj([],_,[]):-!.
	aranj(L1,K,L):-
		aranjam(L1,L1,K,L).
		
	aranjam(L1,[H|_],K,L):-
		aux(H,L1,K,L).
	aranjam(L1,[_|T],K,L):-
		aranjam(L1,T,K,L).
	
	toate_aranj(L,K,S,Lr):-
		findall(X,aranj(L,K,X),P),
		given_sum(P,Lr,S).