/*
  Copyright (c) 1986, 88 by Borland International, Inc.
*/
CONSTANTS
  monster_speed = 125                 % smaller number faster speed (3 min)
  max_num_shots = 6
   
DOMAINS
  integerlist = integer*
        final = won ; lost
    
DATABASE 
   dead_monster ( integer, integer )   % ( row, column )
   monster ( integer, integer )        % ( row, column )
   shot ( integer, integer )           % ( row, column )
   DETERM spaceship ( integer )        % ( column )
       
PREDICATES
   another_game 
   bindkey ( char, char )
   check_monster ( integer, integer )
   clear_dead_monsters ( integer )
   clearkeys
   create_em ( integer )
   display_aliens 
   end_game ( final )
   length ( integer, integerlist )

   make_aliens
   monster_direction ( integer, integer, integer )
   move_monsters ( integer )
   move_ship ( char, integer )
   move_shots
   my_inkey ( char )    
   my_retract ( dbasedom )

   number_shots 
   play
   randcol ( integer )
   NONDETERM repeat
   row_member ( integer, integerlist )
   run
   test1
   test_ans ( string )
   testresult 

GOAL
    makewindow(1,12,0,"",0,0,25,80),
    run.

CLAUSES
  run :-
      write("\n Use the following keys :" ,
            "\n                          Left Arrow = Move Left" ,
            "\n                         Right Arrow = Move Right" ,
            "\n                          Down Arrow = Transport" ,
            "\n                            Spacebar = Fire Shot\n\n") ,
      repeat ,
      retractall( spaceship(_) ), retractall( monster(_,_) ) ,
      retractall( shot(_,_) ) ,
      asserta( spaceship(40) ) ,
      make_aliens ,
      play.
    
  make_aliens :-
        write("How many monsters do you want to fight? "),
        readint(Clones) ,
        create_em(Clones) ,
        clearwindow ,
        display_aliens.

  create_em(0) :- !.
  create_em(Clones) :- More_clones = Clones - 1 ,
           random(75, Col_1), Col = Col_1 +2 ,
           random(4, Row_1), Row = Row_1 + 1 ,
           assertz( monster(Row,Col) ) , 
           create_em(More_clones).

  display_aliens :- monster(Row, Col) ,
                    scr_char(Row, Col, '\157') ,
                    fail ;
                    spaceship(Col), ! ,
                    scr_char(24,Col,'\234').

/* MAIN LOOP */
  play :- 
        testresult,                       % test to see if game is over
        my_inkey(Ch) ,                    % get direction to move 
        clearkeys ,
        move_shots ,
        spaceship(Col) ,
        move_ship(Ch, Col) ,
        move_monsters(5), ! ,
        play.

  testresult :- monster(_,_), ! ,                     % Any monster left?
                test1 ;
                end_game(won).

  test1 :- findall(Row, monster(Row,_), Rows) ,
           not( row_member(24, Rows) ), ! ;
        end_game(lost).
                                  
% Screen Wrap Ship
  move_ship('\75',1):- !, scr_char(24,1,' ') ,    
                       scr_char(24,79,'\234') ,
                       asserta( spaceship(79) ).
  move_ship('\77',79):- !, scr_char(24,79,' ') ,  
                        scr_char(24,1,'\234') ,
                        asserta( spaceship(1) ).
  move_ship('\80',Col):- !, randcol(Rand_col),  % tele-port to a random spot 
                         scr_char(24,Col,' ') ,
                         scr_char(24,Rand_col,'\234') ,
                         asserta( spaceship(Rand_col) ).

% Move the Ship left or right
  move_ship('\75',OldCol) :- !, NewCol=OldCol-1 ,
                             scr_char(24,OldCol,' ') ,
                             scr_char(24,NewCol,'\234') ,
                             asserta( spaceship(NewCol) ).
  move_ship('\77',OldCol) :- !, NewCol=OldCol+1 ,
                             scr_char(24,OldCol,' ') ,
                             scr_char(24,NewCol,'\234') ,
                             asserta( spaceship(NewCol) ).
% FIRE SHOT!
  move_ship('\32',_) :- 
          number_shots ,
          spaceship(Col) ,
          assertz( shot(23,Col) ), 
          scr_char(23, Col, '\24') ,
          check_monster( 23, Col),!.
  move_ship(_,_).         % Anything else, stay put.

  move_monsters(0) :- !.
  move_monsters(Num)  :- monster(Row, Col) ,
                         random(monster_speed, Dir) ,
                         monster_direction(Dir, Row, Col) ,
                         Num_1 = Num - 1, ! ,
                         move_monsters(Num_1) ; true.
                    
  monster_direction(1,R,1) :- ! ,                  
                              scr_char(R,1,' ') ,
                              my_retract( monster(R,1) ) ,
                              assertz( monster(R,79) ) ,
                              scr_char(R,79,'\157').
  monster_direction(2,R,79) :- ! ,                  
                              scr_char(R,79,' ') ,
                              my_retract( monster(R,79) ) ,
                              assertz( monster(R,1) ) ,
                              scr_char(R,1,'\157').
  monster_direction(0,R,C) :- ! ,                  
                              scr_char(R,C,' ') ,
                              R1 = R + 1 ,
                              my_retract( monster(R,C) ) ,
                              assertz( monster(R1,C) ) ,
                              scr_char(R1,C,'\157') ,
                              clear_dead_monsters(3).
                              
  monster_direction(1,R,C) :- ! ,                  
                              scr_char(R,C,' ') ,
                              C1 = C - 1 ,
                              my_retract( monster(R,C) ) ,
                              assertz( monster(R,C1) ) ,
                              scr_char(R,C1,'\157').
  monster_direction(2,R,C) :- ! ,                  
                              scr_char(R,C,' ') ,
                              C1 = C + 1 ,
                              my_retract( monster(R,C) ) ,
                              assertz( monster(R,C1) ) ,
                              scr_char(R,C1,'\157').
  monster_direction(_,_,_).   % Anything else, stay put!

  move_shots :- shot(Row, Col) ,
                Row > 0 ,
                Row1 = Row - 1 ,
                my_retract( shot(Row, Col) ) ,
                asserta( shot(Row1, Col) ) ,
                check_monster(Row1, Col), 
                scr_char(Row,Col,' ') ,
                scr_char(Row1,Col,'\24'), fail ;
             shot(0,Col) ,
             scr_char(0,Col,' '), 
             my_retract( shot(0,COL) ), fail ; 
          true.
               
  number_shots :- findall(X, shot(X,_), Shots) ,
                  length(Num, Shots) ,
                  Num < max_num_shots + 1 , 
                  ! ; sound(3,440), fail.

  check_monster(Row, Col) :- monster(Row, Col), ! ,
                             Row1 = Row + 1 ,
                             scr_char(Row1, Col, ' ') ,
                             scr_char(Row, Col, '\15') ,
                             my_retract( monster(Row, Col) ) ,
                             assertz( dead_monster(Row, Col) ) ,
                             my_retract( shot(Row, Col) ) ,
                             fail ; true.

  clear_dead_monsters(0) :- !.
  clear_dead_monsters(X) :- retract( dead_monster(R,C) ) ,
                            scr_char(R,C,' ') ,
                            X1 = X - 1, ! ,
                            clear_dead_monsters(X1) ; true.

  end_game(won) :- !, makewindow(7,14,3," WINNER ",8,20,10,40) ,
                   write("\n Well done, Champion Zapper !\n") ,
                   another_game.

  end_game(lost) :- makewindow(13,14,10," Looser ",8,20,10,40) ,
                    write("\n Too late, YOU have been zapped !\n") ,
                    another_game.
  
  another_game :- write("\n Do you wish to play another game?" ,
                        "\n   (enter 'Y' to play another game" ,
                        "\n       or 'Q' to quit) : ") ,
                  repeat ,
                  readchar(Ans) ,
                  str_char(Ans1, Ans) ,
                  upper_lower(Ans1, Ans2) ,
                  test_ans(Ans2), !, fail.
               
   test_ans(Ans) :- Ans = "q" , ! , exit ;
                    Ans = "y", removewindow, clearwindow.

/* * * * * * * * * * * * *
   AUXILIARY PREDICATES
* * * * * * * * * * * * */       
  bindkey(C,C1) :- C = '\0',inkey(C1),! ; C1 = C.
    
  clearkeys :- inkey(_), !, clearkeys ; !.
  
  length(0,[]) :- !.
  length(Num, [_|T]) :- length(Num1, T) ,
                        Num = Num1 + 1.
                        
  my_inkey(C) :- inkey(C1),bindkey(C1,C).
  my_inkey('\1').
   
  my_retract(X) :- retract(X),!.
        
  randcol(Col) :- random(77,X), Col = X + 2.

  repeat.
  repeat:-repeat.
   
  row_member(X,[H|T]) :- X = H, ! ; row_member(X,T).
