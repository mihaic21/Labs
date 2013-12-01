%====================== removewindow/2             ===============================
goal
   makewindow(1,7,7,"Test Window",5,5,15,70),
   write("\nPress any key to remove the window"),
   readchar(_),
   removewindow(1,0).