%====================== resizewindow/4             ===============================
predicates
   exp_window(integer)

clauses
   exp_window(0):- !.
   exp_window(N):-
      makewindow(_,_,_,_,A,B,C,D),
      A1 = A-1, B1 = B - 2, C1 = C + 2, D1 = D + 4,
      resizewindow(A1,B1,C1,D1),
      N1 = N - 1, exp_window(N1).

goal
   makewindow(1,7,7,"Test 1",10,20,3,10),
   exp_window(9),
   makewindow(2,7,7,"Test 2",12,50,3,10),
   exp_window(9),
   makewindow(3,7,7,"Test 3",14,16,3,10),
   exp_window(8).