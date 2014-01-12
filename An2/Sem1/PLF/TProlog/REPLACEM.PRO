%replace all apparitions of max with a given list
domains
	elem = integer
	list = elem*

predicates
	findMax(list,elem)
	concatl(list,list,list)
	replace(list,elem,list,list)
	replaceFinal(list,list,list)

clauses
	concatl([],L,L).
	concatl([H|L1],L2,[H|L3]):-
		concatl(L1,L2,L3).
	
	findMax([],0).
	findMax([H|T],H):-
		findMax(T,M),
		H>M,
		!.
	findMax([_|T],M):-
		findMax(T,M).
	
	replace([],_,_,[]).
	replace([M|T],M,Lr,R):-
		replace(T,M,Lr,R1),
		!,
		concatl(Lr,R1,R).
	replace([H|T],M,Lr,[H|R]):-
		replace(T,M,Lr,R).
	
	replaceFinal([],_,[]).
	replaceFinal(L,Lr,R):-
		findMax(L,M),
		replace(L,M,Lr,R).