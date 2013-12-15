%====================== shiftwindow/1         ===============================
/* This program demonstrates the use of the built-in predicate shiftwindow. */
goal
   makewindow(1, 7, 7, " Window 1 ", 0, 0, 25, 40),
   makewindow(2, 7, 7, " Window 2 ", 0, 40, 25, 40),
   makewindow(3, 7, 7, " Window 3 ", 0, 0, 12, 80),
   makewindow(4, 7, 7, " Window 4 ", 12, 0, 13, 80),
   shiftwindow(1),
   write("This is window #1!\n\nPress any key"),
   readchar(_),
   shiftwindow(2),
   write("Now we're in window #2.\nWhat next?\n\nPress any key."),
   readchar(_),
   shiftwindow(3),
   write("This is what is next; window #3.\nPress any key."),
   readchar(_),
   shiftwindow(4),
   write("Now we can write into window #4.\n\nPress any key to continue."),
   readchar(_),
   shiftwindow(1),
   write("\n\nThat's all!").
