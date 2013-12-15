%====================== makewindow/8          ===============================
predicates
   nondeterm repeat
   delay(integer)

GOAL
   makewindow(1, 1, 7, "one", 5, 0, 10, 20), write("ONE"),
   makewindow(2, 2, 7, "two", 1, 10, 10, 20), write("TWO"),
   makewindow(3, 3, 7, "three", 2, 20, 10, 20), write("THREE"),
   makewindow(4, 4, 7, "four", 8, 30, 10, 20), write("FOUR"),
   makewindow(5, 5, 7, "five", 4, 40, 10, 20), write("FIVE"),
   makewindow(6, 6, 7, "six", 5, 50, 10, 20), write("SIX"),
   makewindow(7, 7, 7, "seven", 9, 5, 10, 20), write("SEVEN"),
   makewindow(8, 8, 7, "eight", 1, 10, 10, 20), write("EIGHT"),
   makewindow(9, 9, 7, "nine", 15, 20, 10, 20), write("NINE"),
   repeat,
   random(9,X), N=X+1, shiftwindow(N),
   framewindow(112),  delay(1000),   framewindow(7),
   keypressed.

clauses
  repeat.
  repeat :- repeat.

  delay(0):-!.
  delay(N):-N1=N-1,delay(N1).
