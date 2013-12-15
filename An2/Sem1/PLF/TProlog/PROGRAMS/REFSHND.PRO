
project "register"
code=2000
check_determ

include "globals.pro"

/****************************************************************
			SCRHND
			======

 This module implements a screen handler called by:
               
                 scrhnd(TOPLINE,ENDKEY)

	TOPLINE = on/off  - determines if there should be a top line
	ENDKEY            - Esc or F10 used to return values


  It includes the file SCRHND.PRO from the toolbox (version 1.2).

  In order to use the module SCRHND.PRO from the toolbox  version 1.0
  you must modify it to comment the global predicates out :

    createwindow(SYMBOL) - (i)
    scrhnd(SYMBOL,KEY) - (i,o)
    field_action(FNAME) - (i)
    field_value(FNAME,STRING) - (i,o)
    noinput(FNAME) - (i)
    writescr

****************************************************************/

CONSTANTS
  globdecl = true

include "scrhnd.pro"

