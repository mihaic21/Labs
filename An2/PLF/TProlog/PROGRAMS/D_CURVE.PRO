constants
  root2 = 1.4142135624
  % You must give the path to the BGI drivers here
  bgi_path = ""  % Hard disk users might need to set BGI_PATH="..\\bgi"
                 % 2 Floppy users, set BGI_PATH = "a:"

predicates
  dragon(real, integer,integer)
  t(integer,integer,integer)

goal
  initgraph(0,0,_,_,bgi_path), % Autoselect highest mode
  getmaxX(X), Middle= X div 2,
  moveto(Middle,40),
  getmaxy(MaxY), End= MaxY div 2,
  dragon(200, 270,-1),readchar(_).

clauses
  dragon(L, A, Sign) :-
	L > 2, !,
	L1 = L / root2,
	A1 = A + 45 * Sign, A2 = A - 45 * Sign,
	dragon(L1, A1, 1),
	dragon(L1, A2, -1).
  dragon(D, A, _) :-
	Dir = (A mod 360) div 90,
	t(Dir,Xdir,Ydir),
	Dx=D*Xdir, Dy=D*Ydir,
	setcolor(Dir),
	linerel(Dx,Dy).

  % Transfer a direction to Xdirection sign and YDirection sign
  t(0,1,0).   t(1,0,-1).   t(2,-1,0).  t(3,0,1).
  t(-1,0,1).  t(-2,-1,0).  t(-3,0,-1).
