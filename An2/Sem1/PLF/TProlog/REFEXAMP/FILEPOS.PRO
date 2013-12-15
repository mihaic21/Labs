%======================  filepos/3             ===============================
DOMAINS
   FILE = my_file

PREDICATES
   repeat
   position

GOAL
   makewindow(2, 23, 23, " file position ", 0, 40, 20, 40),
   makewindow(1, 23, 23, " file output ", 0, 0, 20, 40),
   dir("", "*.pro", Filename),
   clearwindow,
   openread(my_file, Filename),
   repeat,
   readdevice(my_file),
   readchar(Str), position,
   write(Str),
   eof(my_file).

CLAUSES
   repeat.
   repeat:-repeat.

   position:-
      shiftwindow(2),
      filepos(my_file, P1, 0),
      writef("position form start: %", P1), nl,
      shiftwindow(1).
