 /***********************************************************************/
 /*		LINE INPUT DRIVER		   			*/
 /***********************************************************************/

DATABASE - lineinp
  lineinpstate(STRING,COL)
  lineinpflag
  insmode


PREDICATES
 lineinput(ROW,COL,LEN,ATTR,ATTR,STRING,STRING,STRING)
  lineinput1(ROW,COL,LEN,ATTR,ATTR,STRING,STRING,STRING,KEY)
  inpkeyact(KEY,COL,LEN,COL,COL,STRING,STRING)
  inpendkey(KEY)
  changemode
  inpstate(lineinp)
  setlineinpflag(KEY)
  disp_str(COL,COL,LEN,STRING)
  lineinpcursor(COL,LEN,COL,COL,STRING)
  lin(KEY,COL,STRING,STRING)
  myfrontstr(COL,STRING,STRING,STRING)
  dropchar(STRING,STRING)

CLAUSES
  lineinput(ROW,COL,LEN,WATTR,FATTR,CONSTTXT,OLDTXT,NEWTXT):-
	lineinput1(ROW,COL,LEN,WATTR,FATTR,CONSTTXT,OLDTXT,NEWTXT,KEY),
	removewindow,
	not(KEY=esc).

  lineinput1(_,_,_,_,_,_,_,_,_):-retract(lineinpflag),fail.
  lineinput1(ROW,COL,LEN,WATTR,FATTR,CONSTTXT,OLDTXT,NEWTXT,KEY):-
	MAXCOL=LEN,
	adjframe(FATTR,1,MAXCOL,DR,DC),
	adjustwindow(ROW,COL,DR,DC,ROW1,COL1),
	makewindow(81,WATTR,FATTR,"",ROW1,COL1,DR,DC),
	str_len(CONSTTXT,MINCOL),
	write(CONSTTXT),
	inpstate(lineinpstate(OLDTXT,0)),
	REST=LEN-MINCOL,
	repeat,
	lineinpstate(CURTEXT,OFFSET),
	disp_str(OFFSET,MINCOL,REST,CURTEXT),
	cursor(_,CC),
	readkey(KEY),
	setlineinpflag(KEY),
	inpkeyact(KEY,CC,MAXCOL,MINCOL,OFFSET,CURTEXT,OLDTXT),
	inpendkey(KEY),
	lineinpstate(NEWTXT,_),!.

  inpendkey(fkey(10)):-!.
  inpendkey(cr):-!.
  inpendkey(esc).

  setlineinpflag(char(_)):-!.
  setlineinpflag(_):-lineinpflag,!.
  setlineinpflag(_):-assert(lineinpflag).

  disp_str(_,_,_,_):-keypressed,!.
  disp_str(0,MINCOL,LEN,TEXT):-!,
	field_str(0,MINCOL,LEN,TEXT).
  disp_str(OFFSET,MINCOL,LEN,TEXT):-
	frontstr(OFFSET,TEXT,_,TXT2),!,
	field_str(0,MINCOL,LEN,TXT2).
  disp_str(_,MINCOL,LEN,_):-
	field_str(0,MINCOL,LEN,"").

  inpstate(_):-retract(lineinpstate(_,_)),fail.
  inpstate(T):-assert(T).

  lineinpcursor(COL,MAXCOL,MINCOL,_,_):-
	COL<MAXCOL,COL>=MINCOL,!,cursor(0,COL).
  lineinpcursor(COL,MAXCOL,_,OFFSET,TXT):-
	COL>=MAXCOL,!,
	OFFSET1=1+OFFSET+COL-MAXCOL,
	inpstate(lineinpstate(TXT,OFFSET1)),
	COL1=MAXCOL-1,
	cursor(0,COL1).
  lineinpcursor(COL,_,MINCOL,OFFSET,TXT):-
	COL<MINCOL,
	OFFSET1=OFFSET-(1+MINCOL-COL),
	max(0,OFFSET1,OFFSET2),
	inpstate(lineinpstate(TXT,OFFSET2)),
	cursor(0,MINCOL).

  myfrontstr(N,STR,S1,S2):-frontstr(N,STR,S1,S2),!.
  myfrontstr(N,STR,S1,""):-
	str_len(STR,NN),
	LEN=N-NN,
	str_len(SS,LEN),
	concat(STR,SS,S1).

  changemode:-retract(insmode),!.
  changemode:-assert(insmode).

  lin(char(T),POS,STR,STR1):-
	insmode,!,
	myfrontstr(POS,STR,S1,S2),
	frontchar(S22,T,S2),
	concat(S1,S22,STR1).

  lin(char(T),POS,STR,STR1):-
	myfrontstr(POS,STR,S1,S2),
	dropchar(S2,S21),
	frontchar(S22,T,S21),
	concat(S1,S22,STR1).

  lin(del,POS,STR,STR1):-
	frontstr(POS,STR,S1,S2),
	frontchar(S2,_,S22),!,
	concat(S1,S22,STR1).
  lin(del,_,S,S).

  dropchar(S,S1):-frontchar(S,_,S1),!.
  dropchar(S,S).


 /***********************************************************************/
 /*		inpkeyact		   					*/
 /***********************************************************************/

  inpkeyact(char(T),COL,MAXCOL,MINCOL,OFFSET,TXT,OLDTXT):-
	lineinpflag,!,
	POS=OFFSET+COL-MINCOL,
	lin(char(T),POS,TXT,TXT1),
	inpstate(lineinpstate(TXT1,OFFSET)),
	inpkeyact(right,COL,MAXCOL,MINCOL,OFFSET,TXT1,OLDTXT).

  inpkeyact(char(T),_,MAXCOL,MINCOL,_,_,OLDTXT):-
	assert(lineinpflag),
	str_char(TXT1,T),
	inpstate(lineinpstate(TXT1,0)),
	inpkeyact(right,MINCOL,MAXCOL,MINCOL,0,TXT1,OLDTXT).

  inpkeyact(del,COL,_,MINCOL,OFFSET,TXT,_):-
	POS=OFFSET+COL-MINCOL,
	lin(del,POS,TXT,TXT1),
	inpstate(lineinpstate(TXT1,OFFSET)).

  inpkeyact(bdel,COL,MAXCOL,MINCOL,OFFSET,TXT,OLDTXT):-
	COL>MINCOL,
	POS=OFFSET+COL-1-MINCOL,
	lin(del,POS,TXT,TXT1),
	inpstate(lineinpstate(TXT1,OFFSET)),
	inpkeyact(left,COL,MAXCOL,MINCOL,OFFSET,TXT1,OLDTXT).

  inpkeyact(cr,_,_,_,_,_,_).

  inpkeyact(fkey(10),_,_,_,_,_,_).

/*inpkeyact(fkey(1),_,_,_,_,_,_):-help.  If a help system is used  */

  inpkeyact(esc,_,_,_,_,_,_).

  inpkeyact(ins,_,_,_,_,_,_):-changemode.

  inpkeyact(home,_,_,MINCOL,_,TXT,_):-
	inpstate(lineinpstate(TXT,0)),
	cursor(0,MINCOL).

  inpkeyact(end,_,MAXCOL,MINCOL,_,TXT,_):-
	str_len(TXT,STRLEN),
	COL=MINCOL+STRLEN,
	COL<MAXCOL,!,
	inpstate(lineinpstate(TXT,0)),
	cursor(0,COL).

  inpkeyact(end,_,MAXCOL,MINCOL,_,TXT,_):-
	str_len(TXT,STRLEN),
	OFFSET=(MINCOL+STRLEN+1)-MAXCOL,
	inpstate(lineinpstate(TXT,OFFSET)),
	COL=MAXCOL-1,
	cursor(0,COL).

  inpkeyact(right,COL,MAXCOL,MINCOL,OFFSET,TXT,_):-
	COL1=COL+1,
	lineinpcursor(COL1,MAXCOL,MINCOL,OFFSET,TXT).

  inpkeyact(ctrlright,COL,MAXCOL,MINCOL,OFFSET,TXT,_):-
	COL1=COL+5,
	lineinpcursor(COL1,MAXCOL,MINCOL,OFFSET,TXT).

  inpkeyact(left,COL,MAXCOL,MINCOL,OFFSET,TXT,_):-
	COL1=COL-1,
	lineinpcursor(COL1,MAXCOL,MINCOL,OFFSET,TXT).

  inpkeyact(ctrlleft,COL,MAXCOL,MINCOL,OFFSET,TXT,_):-
	COL1=COL-5,
	lineinpcursor(COL1,MAXCOL,MINCOL,OFFSET,TXT).

  inpkeyact(ctrlbdel,_,_,MINCOL,_,_,_):-
	inpstate(lineinpstate("",0)),
	cursor(0,MINCOL).

  inpkeyact(fkey(8),_,_,MINCOL,_,_,OLDTXT):-
	OLDTXT><"",
	inpstate(lineinpstate(OLDTXT,0)),
	cursor(0,MINCOL).
