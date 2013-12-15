%Return the path from the root of a binary tree to a 
%certain given node.


domains
	element=integer
	treetype=tree(element, treetype, treetype);nil
	list=element*

predicates	
	consulta(element)
	traverseaza(treetype)
	create_tree(element,treetype)
	insert_left(treetype,treetype,treetype)
	insert_right(treetype,treetype,treetype)
	p(treetype,element,list)

goal
	create_tree(10,Ten),
	create_tree(11,Eleven),
	create_tree(3,Three),
	create_tree(5,Five),
	create_tree(8,Eight),
	create_tree(4,Four),
	create_tree(1,One),

	insert_left(Ten,Eleven,Eleven1),
	insert_right(Three,Eleven1,Eleven2),
	insert_left(Five,Eight,Eight1),
	insert_right(Four,Eight1,Eight2),
	insert_left(Eleven2,One,One1),
	insert_right(Eight2,One1,FinalTree),

	traverseaza(FinalTree),nl,

	p(FinalTree,4,R),
	write(R),nl.

clauses

	p(nil,_,[]):-fail.
	p(tree(E,_,_),E,[E]):-!.
	p(tree(I,S,_),E,[I|T]):-
	p(S,E,T).
	p(tree(I,_,D),E,[I|T]):-
	p(D,E,T).

	consulta(I):-
	write(I), write(",").

	traverseaza(nil).


	traverseaza(tree(I,S,D)):-
	consulta(I),
	traverseaza(S),
	traverseaza(D).

	create_tree(A,tree(A,nil,nil)).

	insert_left(X,tree(A,_,B),tree(A,X,B)).

	insert_right(X,tree(A,B,_),tree(A,B,X)).