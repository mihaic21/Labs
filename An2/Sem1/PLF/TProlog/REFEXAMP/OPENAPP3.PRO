%====================== openappend/2          ===============================
DOMAINS
  file = myfile

GOAL  file_str("dd.txt", "Create file with only one line\n"),
      openappend(myfile, "dd.txt"),
      writedevice(myfile),
      write("this is the second line in the file\n"),
      closefile(myfile),
      file_str("dd.txt", STR1),
      display(Str1).
