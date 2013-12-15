/*
PROBLEM:
A farmer with his goat, wolf and cabbage come to a river that they
wish to cross. There is a boat, but it only has room for two, and
the farmer is the only one that can row. If the goat and cabbage
get in the boat at the same time, the cabbage gets eaten.
Similarly, if the wolf and goat are together without the farmer,
the goat is eaten. Devise a series of crossings of the river so that all
concerned make it across safely.

The state of the system is indicated by stating where the farmer, the goat
the wolf and the cabbage are located.
   state( Farmer, Wolf, Goat, Cabbage )

The problem is that a state must only be visited once, and some states 
are illegal. This is checked by 'unsafe' and 'member'.

The Predicate "go" can be called with a start state and a final state

  go( state(east,east,east,east), state(west,west,west,west) ).

*/

DOMAINS
  LOC   = east ; west
  STATE = state(LOC,LOC,LOC,LOC)
  PATH  = STATE*
  

PREDICATES
  go(STATE,STATE)		/* Start of the algorithm                 */
  path(STATE,STATE,PATH,PATH)	/* Finds a path from one state to another */
  move(STATE,STATE)	/* Transfer a system from one side to another	*/
  opposite(LOC,LOC)	/* Gives a location on the opposite side	*/
  unsafe(STATE)		/* Gives the unsafe states			*/
  member(STATE,PATH)	/* Checks if the state is already visited	*/
  write_path(PATH)
  write_move(STATE,STATE)
  show_move(STATE,STATE)
  showside(LOC,LOC,string)
  s_river

GOAL    
        makewindow(4,7,0,"",0,0,24,80),        
        makewindow(3,7,7," The River   ",15,0,10,80),        
        makewindow(2,112,0,"",0,0,1,80),        
        write("press any key for each step of solution"),
        makewindow(1,7,7," Solutions   ",2,0,13,80),
  
	show_move(state(west,west,west,west),state(west,west,west,west)),
	go(state(east,east,east,east),state(west,west,west,west)),
	write("solved press any key to continue"),
	readchar(_),
	exit.

CLAUSES
  go(S,G):-
  	path(S,G,[S],L),
  	nl,write("A solution is:"),nl,
  	write_path(L),
  	fail.
 go(_,_).

  path(S,G,L,L1):-	move(S,S1),
			not( unsafe(S1) ),
			not( member(S1,L) ),
			path( S1,G,[S1|L],L1),!.
  path(G,G,T,T):-       !.	        /* The final state is reached  */
                       

  move(state(X,X,G,C),state(Y,Y,G,C)):-opposite(X,Y). /* FARMER + WOLF    */
  move(state(X,W,X,C),state(Y,W,Y,C)):-opposite(X,Y). /* FARMER + GOAT    */
  move(state(X,W,G,X),state(Y,W,G,Y)):-opposite(X,Y). /* FARMER + CABBAGE */
  move(state(X,W,G,C),state(Y,W,G,C)):-opposite(X,Y). /* ONLY FARMER	  */

  opposite(east,west).
  opposite(west,east):-!.

  unsafe( state(F,X,X,_) ):- opposite(F,X).  /* The wolf eats the goat    */
  unsafe( state(F,_,X,X) ):- opposite(F,X).  /* The goat eats the cabbage */
  
  member(X,[X|_]).
  member(X,[_|L]):-member(X,L).

  write_path( [H1,H2|T] ) :- !,
                readchar(_),
                write_move(H1,H2),
                show_move(H1,H2), 
                write_path([H2|T]).
  write_path( [] ).
  


  write_move( state(X,W,G,C), state(Y,W,G,C) ) :-!,
    write("The farmer crosses the river from ",X," to ",Y),nl.
  write_move( state(X,X,G,C), state(Y,Y,G,C) ) :-!,
    write("The farmer takes the Wolf from ",X," of the river to ",Y),nl.
  write_move( state(X,W,X,C), state(Y,W,Y,C) ) :-!,
    write("The farmer takes the Goat from ",X," of the river to ",Y),nl.
  write_move( state(X,W,G,X), state(Y,W,G,Y) ) :-!,
    write("The farmer takes the cabbage from ",X," of the river to ",Y),nl.

/* Show them move across the river.*/

 show_move( state(F,W,G,C), state(F1,W1,G1,C1) ) :-!,
    s_river,
    showside(F,F1,"Farmer "),
    showside(W,W1," Wolf  "),    
    showside(G,G1," Goat  "),    
    showside(C,C1,"Cabbage"),
    shiftwindow(1).
    
  showside(east,east,Item):-!,
       write("                                    ~~~~~                       ",Item),nl.
  showside(west,west,Item):-!,
       write("      ",Item,"                       ~~~~~           "),nl.
  showside(east,west,Item):-!,
       write("      ",Item,"          <=  <=  <=   ",Item),nl.
  showside(west,east,Item):-!,
       write("                                    ",Item,"  =>  =>  =>         ",Item),nl.

s_river:-
    shiftwindow(3),clearwindow,
    write("        WEST                        river                      EAST"),nl,
    write("  ----------------------------------------------------------------------"),
    nl.

