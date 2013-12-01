
/************************************************************************
*************************************************************************

		     Graphic constants

		Include file: "GrapDecl.PRO"


*************************************************************************
*************************************************************************/

Constants
/* graphresult error return codes */
  grOk		  	=   0
  grNoInitGraph	  	=  -1
  grNotDetected	  	=  -2
  grFileNotFound	=  -3
  grInvalidDriver	=  -4
  grNoLoadMem	 	=  -5
  grNoScanMem	 	=  -6
  grNoFloodMem	  	=  -7
  grFontNotFound	=  -8
  grNoFontMem	  	=  -9
  grInvalidMode	  	= -10
  grError		= -11   /* generic error */
  grIOerror	 	= -12
  grInvalidFont	  	= -13
  grInvalidFontNum  	= -14
  grInvalidDeviceNum 	= -15


/* define graphics drivers */
  DETECT		=   0	/* requests autodetection */		
  CGA			=   1
  MCGA			=   2
  EGA			=   3
  EGA64			=   4
  EGAMONO		=   5
  RESERVED		=   6
  HERCMONO		=   7
  ATT400		=   8
  VGA			=   9
  PC3270		=  10


/* graphics modes for each driver */
  CGAC0      = 0  /* 320x200 color palette 0: red, yellow, green, 1 page */
  CGAC1      = 1  /* 320x200 color palette 1: cyan, magenta, white, 1 pg */
  CGAC2      = 2  /* 320x200 color palette 2:			    */
  CGAC3      = 3  /* 320x200 color palette 3:			    */
  CGAHI      = 4  /* 640x200 1 page					    */
  MCGAC0     = 0  /* 320x200 color palette 0: red, yellow, green, 1 page */
  MCGAC1     = 1  /* 320x200 color palette 1: cyan, magenta, white, 1 pg */
  MCGAC2     = 2  /* 320x200 color palette 2:			    */
  MCGAC3     = 3  /* 320x200 color palette 3:			    */
  MCGAMED    = 4  /* 640x200 1 page					    */
  MCGAHI     = 5  /* 640x480 2 color 1 page				    */
  EGALO      = 0  /* 640x200 4 pages					    */
  EGAHI      = 1  /* 640x350 16 color 2 pages			    */
  EGA64LO    = 0  /* 640x200 16 color 1 page				    */
  EGA64HI    = 1  /* 640x350 4 color	 1 page				    */
  EGAMONOHI  = 3  /* 640x350 64K on card, 1 page - 256K on card, 4 pages */
  HERCMONOHI = 0  /* 720x348 2 pages					    */
  ATT400C0   = 0  /* 320x200 color palette 0: red, yellow, green, 1 page */
  ATT400C1   = 1  /* 320x200 color palette 1: cyan, magenta, white, 1 pg */
  ATT400C2   = 2  /* 320x200 color palette 2:			    */
  ATT400C3   = 3  /* 320x200 color palette 3:			    */
  ATT400MED  = 4  /* 640x200 1 page					    */
  ATT400HI   = 5  /* 640x400 1 page					    */
  VGALO      = 0  /* 640x200 16 color 4 pages			    */
  VGAMED     = 1  /* 640x350 16 color 2 pages			    */
  VGAHI      = 2  /* 640x480 16 color 1 page				    */
  VGAHI2     = 3  /* 640x480 2 color 1 page				    */
  PC3270HI   = 0  /* 720x350 1 page					    */


/* Colors for setpalette and setallpalette */

  BLACK		=	0	 /* dark colors */
  BLUE		= 	1
  GREEN		=	2
  CYAN		=	3
  RED		=	4
  MAGENTA	=	5
  BROWN		=	6
  LIGHTGRAY	=	7
  DARKGRAY	=	8	/* light colors */
  LIGHTBLUE	=	9
  LIGHTGREEN	=	10
  LIGHTCYAN	=	11
  LIGHTRED	=	12
  LIGHTMAGENTA	=	13
  YELLOW	=	14
  WHITE		=	15


/* Line styles for get/setlinestyle */
  SOLID_LINE   =	0
  DOTTED_LINE  = 	1
  CENTER_LINE  = 	2
  DASHED_LINE  = 	3
  USERBIT_LINE = 	4	/* User defined line style */


/* Line widths for get/setlinestyle */
  NORM_WIDTH	=	1
  THICK_WIDTH	=	3
  DEFAULT_FONT		= 0	/* 8x8 bit mapped font */
  TRIPLEX_FONT		= 1	/* "Stroked" fonts */
  SMALL_FONT		= 2
  SANS_SERIF_FONT	= 3
  GOTHIC_FONT		= 4
  HORIZ_DIR		= 0	/* left to right */
  VERT_DIR		= 1	/* bottom to top */
  USER_CHAR_SIZE	= 0	/* user-defined char size */

/* Fill patterns for get/setfillstyle */
  EMPTY_FILL		= 0	/* fills area in background color */
  SOLID_FILL		= 1	/* fills area in solid fill color */
  LINE_FILL		= 2	/* --- fill */
  LTSLASH_FILL		= 3	/* /// fill */
  SLASH_FILL		= 4	/* /// fill with thick lines */
  BKSLASH_FILL		= 5	/* \\\ fill with thick lines */
  LTBKSLASH_FILL	= 6	/* \\\ fill */
  HATCH_FILL		= 7	/* light hatch fill */
  XHATCH_FILL		= 8	/* heavy cross hatch fill */
  INTERLEAVE_FILL	= 9	/* interleaving line fill */
  WIDE_DOT_FILL		= 10	/* Widely spaced dot fill */
  CLOSE_DOT_FILL	= 11	/* Closely spaced dot fill */
  USER_FILL		= 12	/* user defined fill */


/* BitBlt operators for putimage */
  COPY_PUT		= 0	/* MOV */
  XOR_PUT		= 1	/* XOR */
  OR_PUT		= 2	/* OR  */
  AND_PUT		= 3	/* AND */
  NOT_PUT		= 4	/* NOT */


/* Horizontal and vertical justification for settextjustify */
  LEFT_TEXT	= 0
  CENTER_TEXT	= 1
  RIGHT_TEXT	= 2

  BOTTOM_TEXT	= 0
/*CENTER_TEXT	= 1  already defined above */
  TOP_TEXT	= 2


/* Define some handy constants */
  esc		= '\027'
  true		= 1
  false		= 0
  pi		= 3.14159
