%====================== disk/1              ===============================

goal
   disk(Dosp),
   write("Enter directory: "),
   readln(PathChange),
   DosP<>PathChange,
   disk(PathChange),
   disk(DosPath),
   dir(DosPath, "*.*", File),
   file_str(File, Str),
   display(Str).