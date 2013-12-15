%====================== field_attr/4             ===============================
DOMAINS
  FNAME = STRING
  TYPE = int(); str(); real()
  ROW, COL, LEN = INTEGER

DATABASE
  field(FNAME,TYPE,ROW,COL,LEN)
  value(FNAME,STRING)

PREDICATES
  writescr

CLAUSES
  writescr:-
	field(FNAME,_,ROW,COL,LEN),
	field_attr(ROW,COL,LEN,112),
	value(FNAME,STR),
	field_str(ROW,COL,LEN,STR),
	fail.
  writescr.
