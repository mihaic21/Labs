%====================== openmodify/2          ===============================
DOMAINS
  file = myfile

GOAL  file_str("dd.txt", "Create file with\nonly two lines\n"),
      openmodify(myfile, "dd.txt"),
      readdevice(myfile),
      readln(L),
      filepos(myfile,FilePos,0),
      writedevice(myfile),      % Moves filepointer to the end
      filepos(myfile,FilePos,0),
      write("This will overwrite the second line\n"),
      closefile(myfile),
      file_str("dd.txt", STR1),
      display(Str1).
