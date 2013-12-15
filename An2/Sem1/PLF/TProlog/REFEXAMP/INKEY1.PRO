%====================== inkey/1               ===============================
predicates
   wait(char)
   test(string)

GOAL
   makewindow(2, 7, 7, "Key to press now", 0, 5, 8, 70),
   makewindow(1, 7, 7, "Accepted letters", 8, 10, 10, 60),
   Word = "Peter Piper picked a peck of pickled peppers",
   write("Please type :\n\t", Word, "\n\t"),
   shiftwindow(2),
   time(0, 0, 0, 0), test(Word), time(_, _, S, H),
   shiftwindow(1),
   write("\nYou took ", S, " seconds and ", H, " hundredths").

clauses
   wait(X) :- inkey(Y), X=Y, !.
   wait(X) :- write(X), wait(X).

   test(W) :- frontchar(W, Ch, R),!,wait(Ch),
      write(Ch), test(R).
   test(_).
