%====================== openwrite/2           ===============================
DOMAINS
  file = myfile

GOAL  openwrite(myfile, "dd.txt"),
      writedevice(myfile),
      write("line 1\n"),
      write("line 2\n"),
      write("line 3\n"),
      closefile(myfile),
      file_str("dd.txt", STR1),
      display(Str1).
