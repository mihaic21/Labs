/*
  Copyright (c) 1986, 88 by Borland International, Inc.
*/
/* SYMBOLIC DIFFERENTIATION EXAMPLE */
DOMAINS
/*
  The input string is converted to a list of
  tokens
*/
TOKL = STRING*

/*
  Expressions are modeled via EXP
*/
EXP=var(STRING);
    int(INTEGER);
    plus(EXP,EXP);
    minus(EXP,EXP);
    mult(EXP,EXP);
    div(EXP,EXP);
    ln(EXP);
    cos(EXP); 
    sin(EXP); 
    tan(EXP); 
    sec(EXP); 
    potens(EXP,EXP)

PREDICATES
    run
    diff
    d(EXP,STRING,EXP);
    readexp(EXP);
    readexp(EXP,string,integer)
    check(EXP,TOKL,EXP);

    writeexp(EXP);
    strexp(EXP,string);
    strPOTENS(EXP,string);
    strMULT(EXP,string);
    strMINUS(EXP,string);
    strDIV(EXP,string);
    strPAR(EXP,string);

    tokl(STRING,TOKL);      /* Scanner */
    front(STRING,TOKL,TOKL);

    s_exp(TOKL,TOKL,EXP);   /* Parser */
    potensexp(TOKL,TOKL,EXP);
    potensexp1(TOKL,TOKL,EXP,EXP);
    multexp(TOKL,TOKL,EXP);
    multexp1(TOKL,TOKL,EXP,EXP);
    plusexp(TOKL,TOKL,EXP);
    plusexp1(TOKL,TOKL,EXP,EXP);
    elmexp(TOKL,TOKL,EXP);

    reduce(EXP,EXP);        /* Reducer */
    plusr(EXP,EXP,EXP);
    minusr(EXP,EXP,EXP);
    multr(EXP,EXP,EXP);
    divr(EXP,EXP,EXP);
    lnr(EXP,EXP)

    /* String Editor */
    editstr(string,string,integer,integer,integer).
    editstr(char,string,string,integer,integer,integer).
    editstr(symbol,char,string,string,integer,integer,integer).

    lstcat(TOKL,string)
    
    repeat

GOAL
    run.

CLAUSES
  run:- 
      makewindow(1,71,7,"",0,0,25,80),
      makewindow(1,71,7,"",1,14,9,52),
      write("   S Y M B O L I C  D I F F E R E N T A T I O N"),nl,
      write("   ********************************************"),nl,
      field_attr(0,3,44,112),
      write("           An expression may include: "),nl,
      write(" addition, subtraction, multiplication, division \n"),
      write(" exponents, natual logarithms, cos, sin and tan.\n\n"),
      write(" e.g. x+x  or  x*(2+y)^2  or  ln(1+1/(1-x))"),
      makewindow(3,7,7,"",10,3,14,73),
      clearwindow,diff.

  diff :- 
      readexp(EXP),
      d(EXP,"x",EXP1),
      write("\n  Differentiated expression:\n  "),
      writeexp(EXP1),
      write("\n\n  Reduced expression:\n  "),
      reduce(EXP1,EXP2), writeexp(EXP2),
      write("\n\n  Hit any key to continue..."),readchar(_),
      fail. 

diff :- diff.  

  repeat.
  repeat:- repeat.

/*
  CLAUSES FOR DIFFERENTIATION
*/


  d(int(_),_,int(0)).
  d(var(X),X,int(1)):-!.
  d(var(_),_,int(0)).
  d(plus(U,V),X,plus(U1,V1)):-
      d(U,X,U1),
      d(V,X,V1).
  d(minus(U,V),X,minus(U1,V1)):-
      d(U,X,U1),
      d(V,X,V1).
  d(mult(U,V),X,plus(mult(U1,V),mult(U,V1))):-
      d(U,X,U1),
      d(V,X,V1).
  d(div(U,V),X,div(minus(mult(U1,V),mult(U,V1)),mult(V,V))):-
      d(U,X,U1),
      d(V,X,V1).
  d(ln(U),X,mult(div(int(1),U),U1)):-d(U,X,U1).
  d(potens(E1,int(I)),X,mult(mult(int(I),potens(E1,int(I1))),EXP)):- 
      I1=I-1, 
      d(E1,X,EXP). 
  d(sin(U),X,mult(cos(U),U1)) :- d(U,X,U1). 
  d(cos(U),X,minus(int(0),mult(sin(U),U1))) :- d(U,X,U1).
  d(tan(U),X,mult(potens(sec(U),int(2)),U1)) :- d(U,X,U1).
  
/*
  CLAUSES FOR READING OF AN EXPRESSION
*/
  readexp(EXP) :-
      clearwindow,
      cursor(11,1),
      write("<?> for help     <ESC> to quit"),
      cursor(1,1),
      write("Write an expression: "),!,
      readexp(EXP,"",0).
  readexp(int(0)):-exit(0).

  readexp(EXP1,STR,Pos) :-
      cursor(Ypos,Xpos),
      editstr(STR,STR1,Xpos,Ypos,Pos),
      cursor(2,0), write("                                            "),
      cursor(2,22),
      tokl(STR1,TOKL),
      s_exp(TOKL,OL,EXP),
      !,
      check(EXP,OL,EXP1).

  check(EXP,[],EXP):-
      cursor(2,22),
      write("                                             \n"), 
      write("                                             \n"),
      write("                                             \n"),
      write("                                             \n"),
      write("                                             \n"),
      cursor(2,0),!.
  check(EXP,Rest,NewExp):- 
      strexp(EXP,Str1),
      lstcat(Rest,Str2),
      str_len(Str1,LenStr1),
      ErrPos = 22 + LenStr1,
      cursor(2,ErrPos),
      write("^ syntax error"),
      cursor(1,22),
      concat(Str1,Str2,Str3),
      !,
      readexp(NewExp,Str3,LenStr1).
      

  tokl(STR,[TOK|TOKL]):-
      fronttoken(STR,TOK,STR1),!,
      tokl(STR1,TOKL).
  tokl(_,[]).


/*
  CLAUSES FOR PARSING OF AN EXPRESSION
*/

  s_exp(IL,OL,EXP):-plusexp(IL,OL,EXP).

  plusexp(IL,OL,EXP2):-
      multexp(IL,OL1,EXP1),
      plusexp1(OL1,OL,EXP1,EXP2).

  plusexp1(["+"|IL],OL,EXP1,EXP3):-!,
      multexp(IL,OL1,EXP2),
      plusexp1(OL1,OL,plus(EXP1,EXP2),EXP3).
  plusexp1(["-"|IL],OL,EXP1,EXP3):-!,
      multexp(IL,OL1,EXP2),
      plusexp1(OL1,OL,minus(EXP1,EXP2),EXP3).
  plusexp1(IL,IL,EXP,EXP).

  multexp(IL,OL,EXP2):-
      potensexp(IL,OL1,EXP1),
      multexp1(OL1,OL,EXP1,EXP2).

  multexp1(["*"|IL],OL,EXP1,EXP3):-!,
      potensexp(IL,OL1,EXP2),
      multexp1(OL1,OL,mult(EXP1,EXP2),EXP3).
  multexp1(["/"|IL],OL,EXP1,EXP3):-!,
      potensexp(IL,OL1,EXP2),
      multexp1(OL1,OL,div(EXP1,EXP2),EXP3).
  multexp1(IL,IL,EXP,EXP).

  potensexp(IL,OL,EXP2):-
      elmexp(IL,OL1,EXP1),
      potensexp1(OL1,OL,EXP1,EXP2).
  potensexp1(["^"|IL],OL,EXP1,EXP3):-!,
      elmexp(IL,OL1,EXP2),
      potensexp1(OL1,OL,potens(EXP1,EXP2),EXP3).
  potensexp1(IL,IL,EXP,EXP).

  elmexp(["("|IL],OL,EXP):-
      s_exp(IL,OL1,EXP),
      front(")",OL1,OL),!.
  elmexp(["ln","("|IL],OL,ln(EXP)):-
      s_exp(IL,OL1,EXP),
      front(")",OL1,OL),!.
  elmexp(["sin","("|IL],OL,sin(EXP)):-
      s_exp(IL,OL1,EXP),
      front(")",OL1,OL),!.
  elmexp(["cos","("|IL],OL,cos(EXP)):-
      s_exp(IL,OL1,EXP),
      front(")",OL1,OL),!.
  elmexp(["tan","("|IL],OL,tan(EXP)):-
      s_exp(IL,OL1,EXP),
      front(")",OL1,OL),!.
  elmexp(["-",TALSTR|IL],IL,int(INT)):-
      str_int(TALSTR,INTp),
      INT = -INTp.
  elmexp([TALSTR|IL],IL,int(INT)):-str_int(TALSTR,INT),!.
  elmexp([NAME|IL],IL,var(NAME)).

  front(TOK,[TOK|L],L).

/*
  CLAUSE FOR WRITING OF AN EXPRESSION
*/
  writeexp(EXP) :-
    strexp(EXP,STR),
    write(STR).

/*
  CLAUSES FOR REDUCTION OF AN EXPRESSION
*/

  reduce(plus(X,Y),R):- !,
      reduce(X,X1),
      reduce(Y,Y1),
      plusr(X1,Y1,R).
  reduce(minus(X,Y),R):-!,
      reduce(X,X1),
      reduce(Y,Y1),
      minusr(X1,Y1,R).
  reduce(mult(X,Y),R):-!,
      reduce(X,X1),
      reduce(Y,Y1),
      multr(X1,Y1,R).
  reduce(div(X,Y),R):-!,
      reduce(X,X1),
      reduce(Y,Y1),
      divr(X1,Y1,R).
  reduce(ln(X),R):-!,
      reduce(X,X1),
      lnr(X1,R).
  reduce(potens(E,int(1)),E):-!.
  reduce(R,R).

/*
  CLAUSES FOR REDUCTION OF AN ADDITION EXPRESSION
*/

  plusr(int(0),X,X):-!.
  plusr(X,int(0),X):-!.
  plusr(int(X),int(Y),int(Z)):-!,
      X+Y=Z.
  plusr(X,X,mult(int(2),X)):-!.
  plusr(int(X),Y,Z) :- 
      X < 0,
      T = -X, !,
      minusr(int(T),Y,Z).
  plusr(Y,int(X),Z) :-  
      X < 0,
      T = -X, !,
      minusr(int(T),Y,Z).
  plusr(mult(int(I),X),X,mult(int(I1),X)):-!,
      I+1=I1.
  plusr(X,mult(int(I),X),mult(int(I1),X)):-!,
      I+1=I1.
  plusr(mult(int(I1),X),mult(int(I2),X),mult(int(I3),X)):-!,
      I1+I2=I3.
  plusr(int(I),X,plus(X,int(I))):-!.
  plusr(plus(X,int(I1)),int(I2),plus(X,int(I3))):-!,
      I1+I2=I3.
  plusr(plus(X,int(I1)),plus(Y,int(I2)),plus(R,int(I3))):-!,
      I1+I2=I3,
      plusr(X,Y,R).
  plusr(plus(X,int(I)),Y,plus(R,int(I))):-!,
      plusr(X,Y,R).
  plusr(X,Y,plus(X,Y)).

/*
  CLAUSES FOR REDUCTION OF A MINUS EXPRESSION
*/

  minusr(int(X),int(Y),int(Z)):-!,
      Z=X-Y.
  minusr(X,int(0),X):-!.
  minusr(X,X,int(0)):-!.
  minusr(X,int(I),plus(int(I1),X)):- !,
      I1=-I.
  minusr(X,Y,minus(X,Y)).

/*
  CLAUSES FOR REDUCTION OF A MULTIPLICATION EXPRESSION
*/

  multr(int(X),int(Y),int(Z)):-!,
      X*Y=Z.
  multr(int(0),_,int(0)):-!.
  multr(_,int(0),int(0)):-!.
  multr(int(1),X,X):-!.
  multr(X,int(1),X):-!.
  multr(M,plus(X,Y),plus(X1,Y1)):-!,   
      multr(M,X,X1),multr(M,Y,Y1). 
  multr(M,minus(X,Y),minus(X1,Y1)):-!, 
      multr(M,X,X1),multr(M,Y,Y1). 
  multr(plus(X,Y),M,plus(X1,Y1)):-!, 
      multr(M,X,X1),multr(M,Y,Y1). 
  multr(minus(X,Y),M,minus(X1,Y1)):-!,
      multr(M,X,X1),multr(M,Y,Y1). 
  multr(mult(int(I1),X),int(I2),M1):-!,
      I1*I2=I3,
      multr(int(I3),X,M1).
  multr(int(I1),mult(int(I2),X),M1):-!,
      I1*I2=I3,
      multr(int(I3),X,M1).
  multr(mult(int(I1),X),mult(int(I2),Y),mult(int(I3),R)):-!,
      I1*I2=I3, 
      multr(X,Y,R).
  multr(mult(int(I),X),Y,mult(int(I),R)):-!,
      multr(X,Y,R).
  multr(X,int(I),mult(int(I),X)):-!.
  multr(potens(X,int(I1)),potens(X,int(I2)),potens(X,int(I3))):-!,
      I3=I1+I2.
  multr(X,potens(X,int(I)),potens(X,int(I1))):-!,
      I1=I+1.
  multr(potens(X,int(I)),X,potens(X,int(I1))):-!,
      I1=I+1.
  multr(X,X,potens(X,int(2))):-!.
  multr(X,Y,mult(X,Y)).

/*
  CLAUSES FOR REDUCTION OF A DIVISION EXPRESION
*/

  divr(int(0),_,int(0)):-!.
  divr(_,int(0),var("'endless'")):-!,
      write("division by zero"),nl.
  divr(X,int(1),X):-!.
  divr(X,Y,div(X,Y)).

/*
  CLAUSES FOR REDUCTION OF A LOGARITHM EXPRESSION
*/

  lnr(int(0),var("endless")):-!,
      write("logarithm error"),nl.
  lnr(int(1),int(0)):-!.
  lnr(X,ln(X)).


/*
  CLAUSES FOR CONVERTING AN EXPRESSION TO A STRING
*/
% Taken from the old writeexp clauses

  strexp(var(NAME),NAME).
  strexp(int(INT),INTSTR) :-
      str_int(INTSTR,INT).
  strexp(ln(EXP),STR)  :-
      strPAR(EXP,STRp),
      concat("ln",STRp,STR).
  strexp(sin(EXP),STR) :- 
      strPAR(EXP,STRp),
      concat("sin",STRp,STR).
  strexp(cos(EXP),STR) :- 
      strPAR(EXP,STRp),
      concat("cos",STRp,STR).
  strexp(tan(EXP),STR) :- 
      strPAR(EXP,STRp),
      concat("tan",STRp,STR).
  strexp(sec(EXP),STR) :- 
      strPAR(EXP,STRp),
      concat("sec",STRp,STR).
  strexp(plus(EXP1,EXP2),STR):-
      strexp(EXP1,STR1),
      concat(STR1,"+",STR3),
      strexp(EXP2,STR2),
      concat(STR3,STR2,STR).
  strexp(minus(EXP1,EXP2),STR):-
      strexp(EXP1,STR1),
      concat(STR1,"-",STR3),
      strMINUS(EXP2,STR2),
      concat(STR3,STR2,STR).
  strexp(mult(EXP1,EXP2),STR):-
      strMINUS(EXP1,STR1),
      concat(STR1,"*",STR3),
      strMULT(EXP2,STR2),
      concat(STR3,STR2,STR).
  strexp(div(EXP1,EXP2),STR):-
      strMULT(EXP1,STR1),
      concat(STR1,"/",STR3),
      strDIV(EXP2,STR2),
      concat(STR3,STR2,STR).
  strexp(potens(EXP1,EXP2),STR):-
      strDIV(EXP1,STR1), 
      concat(STR1,"^",STR3),
      strPOTENS(EXP2,STR2),
      concat(STR3,STR2,STR).

  strPOTENS(div(X,Y),STR):-!,strPAR(div(X,Y),STR).
  strPOTENS(X,STR):-strDIV(X,STR).

  strDIV(mult(X,Y),STR):-!,strPAR(mult(X,Y),STR).
  strDIV(X,STR):-strMULT(X,STR).

  strMULT(minus(X,Y),STR):- !,strPAR(minus(X,Y),STR).
  strMULT(X,STR):-strMINUS(X,STR).

  strMINUS(plus(X,Y),STR):-!,strPAR(plus(X,Y),STR).
  strMINUS(X,STR):-strexp(X,STR).

  strPAR(EXP,STR):-
      strexp(EXP,STR1),
      concat("(",STR1,STR2),
      concat(STR2,")",STR).

/*
  CLAUSES TO EDIT A STRING
*/

  editstr(InString, OutString, Xpos, Ypos, Cpos) :-
    cursor(Ypos,Xpos),
    write(InString," "),
    NXPos = Xpos+Cpos,
    cursor(Ypos,NXpos),
    readchar(Ch),
    !,
    editstr(Ch, InString, OutString, Xpos, Ypos, Cpos).

  % Return -- Accept the current string.
  editstr('\13',InString,InString,Xpos,Ypos,_) :-
    cursor(Ypos,Xpos),
    write(InString), nl.
    
  % ESC -- Terminate the program.
  editstr('\27',_,"",_,_,_) :- exit.
  
  % HELP -- Display the help message
  % taken from checkhelp.
  editstr('?',InString,OutString,Xpos,Ypos,Cpos) :- 
    makewindow(4,23,7,"",10,3,14,73),
    file_str("diff.hlp",I),
    display(I),
    removewindow,
    !,
    editstr(InString,OutString,Xpos,Ypos,Cpos).
  
  % ^S -- Move Cursor left
  editstr('\19',InString,OutString,Xpos,Ypos,Cpos) :-
    Cpos > 0,
    NewCpos = Cpos - 1,
    !,
    editstr(InString,OutString,Xpos,Ypos,NewCpos).
  editstr('\19',InString,OutString,Xpos,Ypos,Cpos) :- !,
    editstr(InString,OutString,Xpos,Ypos,Cpos).
     
  % ^D -- Move Cursor left
  editstr('\4',InString,OutString,Xpos,Ypos,Cpos) :-
    str_len(InString,InStrLen),
    Cpos < InStrLen,
    NewCpos = Cpos + 1, !,
    editstr(InString,OutString,Xpos,Ypos,NewCpos).
  editstr('\4',InString,OutString,Xpos,Ypos,Cpos) :- !,
    editstr(InString,OutString,Xpos,Ypos,Cpos).
    
  % ^H -- Backspace, Delete the previous character.
  editstr('\8',InString,OutString,Xpos,Ypos,1) :-
    frontchar(InString,_,NewString),
    !,
    editstr(NewString,OutString,Xpos,Ypos,0).
  editstr('\8',InString,OutString,Xpos,Ypos,Cpos) :-
    Cpos > 1,
    NewCpos = Cpos - 1,
    !,
    editstr('\21',InString,OutString,Xpos,Ypos,NewCpos). % Delete Char.
  editstr('\8',InString,OutString,Xpos,Ypos,Cpos) :- !,
    editstr(InString,OutString,Xpos,Ypos,Cpos).
  
  % ^U -- Delete the current character.
  editstr('\21',InString,OutString,Xpos,Ypos,Cpos) :-
    frontstr(Cpos,InString,HeadString,TailStringP),
    frontchar(TailStringP,_,TailString),
    concat(HeadString,TailString,NewString),
    !,
    editstr(NewString,OutString,Xpos,Ypos,Cpos).
  editstr('\21',InString,OutString,Xpos,Ypos,Cpos) :- !,
    editstr(InString,OutString,Xpos,Ypos,Cpos).

  % Insert a Character.
  editstr(Ch,InString,OutString,Xpos,Ypos,Cpos) :-
    makewindow(_,_,_,_,_,_,_,Width),
    str_len(InString,InStrLen),
    InStrLen < Width - 3 - Xpos,
    Ch >= ' ', Ch <= '~',
    frontstr(Cpos,InString,HeadString,TailStringP),
    frontchar(TailString,Ch,TailStringP),
    concat(HeadString,TailString,NewString),
    NewCpos = Cpos + 1,
    !,
    editstr(NewString,OutString,Xpos,Ypos,NewCpos).
    
  % Extended Key Hit.
  editstr('\0',InString,OutString,Xpos,Ypos,Cpos) :-
    readchar(Key), !,
    editstr(extended,Key,InString,OutString,Xpos,Ypos,Cpos).
    
   % Ignore All other keys pressed.
  editstr(_,InString,OutString,Xpos,Ypos,Cpos) :-
    editstr(InString,OutString,Xpos,Ypos,Cpos).

  % Process extended keys.
  % Del Key, map to the ^U key.
  editstr(extended,'\83',InString,OutString,Xpos,Ypos,Cpos) :- !,
    editstr('\21',InString,OutString,Xpos,Ypos,Cpos).
  % Left Arrow, map to the ^S key.
  editstr(extended,'\75',InString,OutString,Xpos,Ypos,Cpos) :- !,
    editstr('\19',InString,OutString,Xpos,Ypos,Cpos).
  % Right Arrow, map to the ^D key.
  editstr(extended,'\77',InString,OutString,Xpos,Ypos,Cpos) :- !,
    editstr('\4',InString,OutString,Xpos,Ypos,Cpos).
  % End.
  editstr(extended,'\79',InString,OutString,Xpos,Ypos,_) :- 
    str_len(InString,InStrLen),
    !,
    editstr(InString,OutString,Xpos,Ypos,InStrLen).
  % Home.
  editstr(extended,'\71',InString,OutString,Xpos,Ypos,_) :- !,
    editstr(InString,OutString,Xpos,Ypos,0).
  % Ignore the rest.
  editstr(extended,_,InString,OutString,Xpos,Ypos,Cpos) :- !,
    editstr(InString,OutString,Xpos,Ypos,Cpos).

/* 
  UTILITY TO TRANSFORM A TOKANIZED LIST TO A LIST
*/
  lstcat([],"").
  lstcat([X|Xs],STR) :- 
    lstcat(Xs,STR1),
    concat(X,STR1,STR).
    