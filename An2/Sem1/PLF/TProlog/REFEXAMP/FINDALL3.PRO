%====================== findall/3             ===============================
/* Answer to first exercise on @@Pageref[42]
   Use Shift-F9 to select the Dialog window and then Ctrl-F10 to explode the
   window. Enter the goal show(Name, X, Y).
*/

domains
   lyrics = lyrics(symbol, symbol)
   name = string
   music =  music(symbol, symbol)
   mulist = music*
   namelist = name*

database
   musicdba(symbol, symbol)

predicates
   show(name, lyrics, music)

GOAL
   findall(M, show(X, Y, M), Musiclist),
   findall(NAME, musicdba(_, NAME), Namelist),
   write(Musiclist, "\n", Namelist).

clauses
   show("West Side Story", lyrics(stephen, sondheim), X) :-
      musicdba(leonard, bernstein),
      X=music(leonard, bernstein).

   show("Apocalypses Now", lyrics(the, doors), music(jim, morrison)).
   show("Repo Man", lyrics(iggy, pop), music(iggy, pop)).
   show("Live and Let Die", lyrics(paul, mc_cartney), music("", wings)).
   show("Two Hundred Motels", lyrics(frank, zappa), music(mothers_of,invention)).
   show("Chess", lyrics("Andersson Rice", " & Ulvaeus"), music(london,symphony)).
   show("The Wall", lyrics(pink, floyd), music(pink, floyd)).
   show("Stop Making Sense", lyrics(talking, heads), music(talking, heads)).

   /*  Database clauses are automatically asserted */
   musicdba(leonard, bernstein).
   musicdba(jim, morrison).
   musicdba(iggy, pop).
   musicdba("", wings).
   musicdba(mothers_of, inventions).
   musicdba(london, symphony).
   musicdba(pink, floyd).
   musicdba(talking, heads).
