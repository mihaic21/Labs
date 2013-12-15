/*
  Copyright (c) 1986, 88 by Borland International, Inc.
*/
code = 2000
/*
  This is a small example of how to create a
  classification expert-system in TURBO-
  Prolog.

  Animals are classified in different
  categories which are then broken up into
  smaller categories. One can move from one
  category to another if a number of
  conditions are fulfilled.

  In this system the conditions are added
  together. The first thing that is needed is
  'or' and 'not'.

  Please understand this is a simple example
  not a finished expert-system development
  tool. */

DOMAINS
  CONDITIONS = BNO*
  HISTORY = RNO*
  RNO, BNO, FNO = INTEGER
  CATEGORY = STRING
  data_file = string
  file = save_file
  slist = string*

DATABASE
  rule(RNO,CATEGORY,CATEGORY,CONDITIONS)
  cond(BNO,STRING)
  data_file(data_file)
  yes(BNO)
  no(BNO)
  fact(FNO,CATEGORY,CATEGORY)
  topic(string)

include "tdoms.pro"
include "tpreds.pro"
include "menu2.pro"

PREDICATES

/*Commands*/
  title_go
  update
  edit_kb
  list
  llist(HISTORY,string)
  load_know
  save_know
  pick_dba(data_file)
  erase
  clear
  proces(integer)
  endd(integer)
  listopt
  evalans(char)
  info(CATEGORY)
  goes(CATEGORY)
  run
  reverse(CONDITIONS,CONDITIONS)
  reverse1(CONDITIONS,CONDITIONS,CONDITIONS)
  

/*Inferences mechanisms*/
  go(HISTORY,CATEGORY)
  check(RNO,HISTORY,CONDITIONS)
  notest(BNO)
  inpq(HISTORY,RNO,BNO,STRING)
  do_answer(HISTORY,RNO,STRING,BNO,INTEGER)

/*Explanations*/
  sub_cat(CATEGORY,CATEGORY,CATEGORY)
  show_conditions(CONDITIONS,string)
  show_rule(RNO,string)
  show_cond(BNO,string)
  report(HISTORY,string)
  quest(CATEGORY,integer,integer,CATEGORY)

/*Update the knowledge*/
  topict(string)
  getrnr(RNO,RNO)
  getbnr(BNO,BNO)
  readcondl( CONDITIONS )
  help
  getcond(BNO,STRING)
  save_y(char,string,data_file)

GOAL
  makewindow(1,49,72,"",4,0,20,80),
  makewindow(2,3,7,"",14,0,10,80),
  makewindow(5,7,0,"",0,0,4,80),
  makewindow(8,23,0,"",24,0,1,80),
  makewindow(9,7,0,"",0,0,25,80),
  run.
clauses
 run :-
  repeat,
  shiftwindow(8),
  clearwindow,
  write("  select option with arrow key  "),
  shiftwindow(1),
  menu(6,55,7,7,
    ["Consultation",
    "Load knowledge",
    "Save knowledge",
    "List knowledge",
    "Update knowledge",
    "Erase knowledge",
    "Edit Knowledge",
    "Help Information",
    "DOS Shell",
    "Exit Geni"],"menu",2,
    CHOICE),
    proces(CHOICE),
endd(CHOICE),!.

/*Process Choice*/

 proces(0):-exit.
 proces(1):-title_go.
 proces(2):-load_know.
 proces(3):-save_know.
 proces(4):-list.
 proces(5):-update.
 proces(6):-erase.
 proces(7):-edit_kb.
 proces(8):-help.
 proces(9):-write("Borland ",'\3','\2'," you"),system("").
 proces(10).

 endd(0).
 endd(10):- clearwindow,
    write("Are you sure? (y or n) "),
    readchar(C),write(C),
    C='y',exit.

/*Inference mechanism*/

  title_go:-
    goes(Mygoal),
    nl,nl,go([],Mygoal),!.
  title_go:- nl,
    write("Sorry that one I did not know"),nl,update.

  goes(Mygoal):-
    clear,clearwindow,
    topict(Topic),
    repeat,
    write("You may select a general category( e.g. ",Topic,") \nor '?' for other options in the ",Topic,
    " domain.\n Enter Goal "),
    readln(Mygoal),
    info(Mygoal),!.

  topict(Topic) :- topic(Topic).
  topict(Topic) :- write("Enter a name that represents \nthis knowledge domain\n  : "),
    readln(Topic),assert(topic(Topic)).

  go( _, Mygoal ):-                     /* My best guess  */
    not(rule(_,Mygoal,_,_)),!,nl,
    write("I think it is a(n): ",Mygoal),nl,nl,
    write("I was right, wasn't I? (enter y or n)"),
    readchar(Ans),
    evalans(Ans).

  go( HISTORY, Mygoal ):-
    rule(RNO,Mygoal,NY,COND),
    check(RNO,HISTORY, COND),
    go([RNO|HISTORY],NY).

  check( RNO, HISTORY, [BNO|REST] ):- yes(BNO), !,
    check(RNO, HISTORY, REST).
  check( _, _, [BNO|_] ):- no(BNO), !,fail.
  check( RNO, HISTORY, [BNO|REST] ):- cond(BNO,NCOND),
    fronttoken(NCOND,"not",_COND),
    frontchar(_COND,_,COND),
    cond(BNO1,COND),
    notest(BNO1), !,
    check(RNO, HISTORY, REST).
  check(_,_, [BNO|_] ):- cond(BNO,NCOND),
    fronttoken(NCOND,"not",_COND),
    frontchar(_COND,_,COND),
    cond(BNO1,COND),
    yes(BNO1), !,fail.
  check( RNO, HISTORY, [BNO|REST] ):-
    cond(BNO,TEXT),
    inpq(HISTORY,RNO,BNO,TEXT),
    check(RNO, HISTORY, REST).
    check( _, _, [] ).

  notest(BNO):-no(BNO),!.
  notest(BNO):-not(yes(BNO)),!.

  inpq(HISTORY,RNO,BNO,TEXT):-
    write("Is it true that ",TEXT,": "),
    ROW = 14,
    COL = 60,
    menu(ROW,COL,7,7,[yes,no,why],"",1,CHOICE),
    do_answer(HISTORY,RNO,TEXT,BNO,CHOICE).

  do_answer(_,_,_,_,0):-exit.
  do_answer(_,_,_,BNO,1):-assert(yes(BNO)),
    shiftwindow(1),write(yes),nl.
  do_answer(_,_,_,BNO,2):-assert(no(BNO)),
    shiftwindow(1),write(no),nl,fail.
  do_answer(HISTORY,RNO,TEXT,BNO,3):- !,
    shiftwindow(2),
    rule( RNO, Mygoal1, Mygoal2, _ ),
    sub_cat(Mygoal1,Mygoal2,Lstr),
    concat("I try to show that: ",Lstr,Lstr1),
    concat(Lstr1,"\nBy using rule number ",Ls1),
    str_int(Str_num,RNO),
    concat(Ls1,Str_num,Ans),
    show_rule(RNO,Lls1),
    concat(Ans,Lls1,Ans1),
    report(HISTORY,Sng),
    concat(Ans1,Sng,Answ),
    display(Answ),
    shiftwindow(8),
    clearwindow,
    write("   Use Arrow Keys To Select Option  "),
    shiftwindow(1),
    ROW = 14,COL = 60,
    menu(ROW,COL,7,7,[yes,no,why],"",1,CHOICE),
    do_answer(HISTORY,RNO,TEXT,BNO,CHOICE).

/* List Rules / Explanation Mechanism */

  list :- findall(RNO,rule(RNO,_,_,_),LIST),
    llist(List,Str),!,display(Str),!.

  llist([],"") :-!.
  llist([RNO|List],Str):-
    llist(List,Oldstr),
    show_rule(RNO,RNO_Str),
    concat(RNO_Str,Oldstr,Str).

  show_rule(RNO,Strg):-
    rule( RNO, Mygoal1, Mygoal2, CONDINGELSER),
    str_int(RNO_str,RNO),
    concat("\n Rule ",RNO_str,Ans),
    concat(Ans,": ",Ans1),
    sub_cat(Mygoal1,Mygoal2,Lstr),
    concat(Ans1,Lstr,Ans2),
    concat(Ans2,"\n     if ",Ans3),
    reverse(CONDINGELSER,CONILS),
    show_conditions(CONILS,Con),
    concat(Ans3,Con,Strg).

  show_conditions([],"").
  show_conditions([COND],Ans):-
    show_cond(COND,Ans),!.
  show_conditions([COND|REST],Ans):-
    show_cond(COND,Text),
    concat("\n    and ",Text,Nstr),
    show_conditions(REST,Next_ans),
    concat(Next_ans,Nstr,Ans).

  show_cond(COND,TEXT):-cond(COND,TEXT).

  sub_cat(Mygoal1,Mygoal2,Lstr):-
    concat(Mygoal1," is a ",Str),
    concat(Str,Mygoal2,Lstr).

  report([],"").
  report([RNO|REST],Strg) :-
    rule( RNO, Mygoal1, Mygoal2, _),
    sub_cat(Mygoal1,Mygoal2,Lstr),
    concat("\nI have shown that: ",Lstr,L1),
    concat(L1,"\nBy using rule number ",L2),
    str_int(Str_RNO,RNO),
    concat(L2,Str_RNO,L3),
    concat(L3,":\n ",L4),
    show_rule(RNO,Str),
    concat(L4,Str,L5),
    report(REST,Next_strg),
    concat(L5,Next_strg,Strg).

/*Update the knowledge base*/

  getrnr(N,N):-not(rule(N,_,_,_)),!.
  getrnr(N,N1):-H=N+1,getrnr(H,N1).

  getbnr(N,N):-not(cond(N,_)),!.
  getbnr(N,N1):-H=N+1,getbnr(H,N1).

  readcondl( [BNO|R] ):-
    write("condition: "),readln(COND),
    COND><"",!,
    getcond(BNO,COND),
    readcondl( R ).
  readcondl( [] ).

  getcond(BNO,COND):-cond(BNO,COND),!.
  getcond(BNO,COND):-getbnr(1,BNO), assert( cond(BNO,COND) ).

/*EDIT KNOWLEDGE*/

  edit_kb :-
    pick_dba(Filename),
    file_str(Filename,Data),
    edit(Data,NewData),clearwindow,
    write("Save Knowledge Base (enter y or n) "),
    readchar(Ans),save_y(Ans,NewData,Filename).

  save_y('y',D,Filename):-
    openwrite(save_file,Filename),
    writedevice(save_file),
    write(D),
    closefile(save_file).
  save_y('n',_,_).

/*HELP !!!*/

   help :- file_str("geni.hlp",Help),
    display(Help).


/*User commands*/

  load_know:-pick_dba(Data), consult(Data).

  save_know :- data_file(Data), bound(Data),!,
    save(Data),clearwindow,
    writef(" Your % Knowledge base has been saved",Data).
  save_know :- makewindow(11,10,9,"Name of the file",10,40,4,35),
    write("Enter Knowledge\nBase Name: "),
    readln(Data),
    assert(data_file(Data)),
    removewindow,
    save(Data),clearwindow,
    writef(" Your % Knowledge base has been saved",Data).

  pick_dba(Data) :- makewindow(10,7,7,"PICK A DATA FILE",10,10,10,60),
    dir("","*.gni",Data),removewindow.

  erase:-retract(_),fail.
  erase.

  clear:-retract(yes(_)),retract(no(_)),fail,!.
  clear.

  update:-
    shiftwindow(5),
    clearwindow,
    write("\n\tUpdate knowledge\n\t****************\n"),
    cursor(1,30),
    write("Name of category: "),
    cursor(3,30),
    write("Name of subcategory: "),
    cursor(1,50),
    readln(KAT1),KAT1><"",
    quest(KAT1,1,50,KAT),
    cursor(3,50),
    readln(SUB1),SUB1><"",
    quest(SUB1,3,50,SUB),
    readcondl(CONDL),
    getrnr(1,RNO),
    assert( rule(RNO,KAT,SUB,CONDL) ),update.

  quest(Q,X,Y,Q2):- Q = "?",
    shiftwindow(2),clearwindow,
    write("The categories and subcategories are objects. For example:\n"),nl,
    write("subcategory|-----| category|-----|[condition1  |------|  condition2]\n"),
    write("___________|_____|_______________|_____________|______|____________"),nl,
    write("mammal     |is an| animal  |if it| has hair    |and it|  gives milk\n"),
    write("bird       |is an| animal  |if it| has feathers|and it|  lays eggs\n"),
    shiftwindow(5),
    cursor(X,Y),
    readln(Q2).
  quest(Q,_,_,Q).

  info("?") :-
    shiftwindow(2), clearwindow,
    write("Enter the type of thing you are trying to classify."),
    listopt,nl,nl, write(" press any key "),
    readchar(_),
    shiftwindow(1),clearwindow,fail.

  info(X) :- X>< "?".

  listopt :-
    write(" The options are:\n\n"),
    rule(_,Ans,_,_),
    write(Ans,"  "),
    fail.
  listopt.

  evalans('y'):-
    write("\nOf course, I am always right!").
  evalans(_):-
    write(" you're the boss \n  Update my Knowledge Base!"),!,run.

 /*system commands*/

  reverse(X,Y):-
     reverse1([],X,Y).
  reverse1(Y,[],Y).
  reverse1(X1,[U|X2],Y):-reverse1([U|X1],X2,Y).
  
     