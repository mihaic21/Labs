
%12. Substitute all occurences of an elemement of
% a list with all the elemements of another list.
domains
	elem=integer
	list=elem*

predicates
	subst(elem,list,list,list)
	concatl(list,list,list)

clauses
	concatl([],L,L).
	concatl([H|L1],L2,[H|L3]):-concatl(L1,L2,L3).
	
	subst(_,_,[],[]).
	subst(E,L,[E|T],R):-subst(E,L,T,R1),!,concatl(L,R1,R).
	subst(E,L,[H|T],[H|R]):-subst(E,L,T,R).