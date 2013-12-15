%====================== sound/2               ===============================
/* This program demonstrates the use of the built-in predicate sound. */

PREDICATES
   tune(char)

CLAUSES
   tune('e') :- !, sound(100, 165).
   tune('a') :- !, sound(100, 220).
   tune('d') :- !, sound(100, 294).
   tune('g') :- !, sound(100, 392).
   tune('b') :- !, sound(100, 494).
   tune('h') :- !, sound(100, 660).
   tune(_) :-
      clearwindow,
      write("That's not a note I can tune to!\n\n","Press any key to continue."),
      readchar(_), clearwindow, fail.
GOAL
   makewindow(1, 7, 7, " GUITAR TUNER ", 0, 0, 25, 80),
   write("Here's a a program to help you tune your guitar.\n\n"),
   write("Please enter the note that you wish to tune"," (e, a, d, g, b, or h for high e): "),
   readchar(Tune),
   upper_lower(Tune, Tune1),
   tune(Tune1).
