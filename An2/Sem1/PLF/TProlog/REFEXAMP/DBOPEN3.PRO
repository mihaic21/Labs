%====================== db_open/3         ===============================
DOMAINS
   db_selector = mydba
      
GOAL 
   db_open(mydba,"dd.bin",in_file),
   %....... Update and access the database here.....
   db_close(mydba).