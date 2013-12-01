%====================== random/2              ===============================
PREDICATES
  run
  get(INTEGER,CHAR)

GOAL makewindow(1,116,96,"Labyrinth generator",5,5,15,60), run.

CLAUSES
  run:-	not(keypressed),
	random(13,ROW),	random(58,COL),	random(11,SEL),
	get(SEL,CHAR), scr_char(ROW,COL,CHAR),
	run.

  get(0,'Å').  get(1,'´').  get(2,'Ã').  get(3,'Á').  get(4,'Â').
  get(5,'Ú').  get(6,'¿').  get(7,'Ù').  get(8,'À').  get(9,'Ä').
  get(10,'³').
