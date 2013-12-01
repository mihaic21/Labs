%====================== port_byte/2           ===============================
/* This program is written specifically for the Color Graphics Adapter */

predicates
   border(integer)

GOAL  border(0).

clauses
   border(Color) :-
      port_byte($3D9, Color),
      Color<15,
      readchar(_),
      NewColor=Color+1,
      border(NewColor).
