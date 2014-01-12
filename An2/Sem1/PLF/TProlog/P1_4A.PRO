%Substitute all occurences of a certain element
% of a list with another element.

domains
	elem=integer
	list=elem*
	
predicates
	subst(list,elem,elem,list)
	
clauses
	subst([],_,_,[]).
	subst([H|T],E,EN,[EN|L]):-H=E,subst(T,E,EN,L).
	subst([H|T],E,EN,[H|L]):-H<>E,subst(T,E,EN,L).