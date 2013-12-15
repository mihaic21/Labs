%trace
%Se da o lista formata din numere intregi si
%liste de numere intregi. Se cere suma elementelor
%pare din primele N subliste ale listei date.

domains
	list = integer*
	elem = i(integer);l(list)
	llist = elem*

predicates
	sum(llist,integer,integer)
	even(list,integer)
	
clauses
	even([],0).
	even([A|L],S):-
		A mod 2 = 0,
		even(L,S1),
		S=A+S1.
	even([_|L],S):-even(L,S),!.
	
	sum([],_,0).
	sum([_|_],0,0).
	sum([l(L)|T],N,S):-
		even(L,S1),
		N1=N-1,
		sum(T,N1,S2),
		S=S1+S2,
		!.
	sum([i(_)|T],N,S):-sum(T,N,S).