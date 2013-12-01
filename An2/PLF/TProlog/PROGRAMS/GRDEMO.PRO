/************************************************************************
*************************************************************************
*									*
*		Demo program using the BGI standard predicates		*
*                                                                       *
*             Copyright (c) 1986, 88 by Borland International, Inc.     *
*                                                                       *
*									*
*************************************************************************
*************************************************************************/


/* 
   If you want to link device drivers and character fonts into
   the .EXE file you should include the following directives.
   Depending upon your hardware choose one of the 5 bgidrivers by 
   removing the % at the begining of the line. The Drivers are in
   BGI.LIB. In the Options/Link Options/Libraries add +bgi.

%bgidriver "_EGAVGA_driver_far"
%bgidriver "_CGA_driver_far"
%bgidriver "_HERC_driver_far"
%bgidriver "_PC3270_driver_far"
%bgidriver "_ATT_driver_far"
bgifont   "_gothic_font_far"
bgifont   "_small_font_far"
bgifont   "_sansserif_font_far"
bgifont   "_triplex_font_far"
*/


code = 2000
include	"GrapDecl.PRO"

CONSTANTS	% define a better name for BGI_ilist
  intlist = BGI_ilist
  bgi_Path = ""  % Hard disk users might need to set BGI_PATH="..\\bgi"
                 % 2 Floppy users, set BGI_PATH = "a:"


Domains
  PointList = intlist*

Constants
  PaletteList	= intlist

/************************************************************************
		Local data base
*************************************************************************/

Database - graphics
  Determ driver(Integer,Integer,String)
  Determ maxcolors(Integer)
  Determ maxX(Integer)
  Determ maxY(Integer)
  Determ aspectCorr(Real)
  Determ graphCoord(Integer,Integer)
  Determ lineStyleDBA(Integer,Integer,Integer)
  Determ textHeightDBA(Integer)

/************************************************************************
		Return name of driver
*************************************************************************/

PREDICATES
  GetDriverName2(Integer,String)

CLAUSES
  GetDriverName2(0,"Detect").	GetDriverName2(1,"CGA").
  GetDriverName2(2,"MCGA").	GetDriverName2(3,"EGA").
  GetDriverName2(4,"EGA64").	GetDriverName2(5,"EGAMono").
  GetDriverName2(6,"Reserved").	GetDriverName2(7,"HercMono").
  GetDriverName2(8,"ATT400").	GetDriverName2(9,"VGA").
  GetDriverName2(10,"PC3270").

/************************************************************************
	Convert the current device mode into a string
*************************************************************************/

PREDICATES
  GetMode(Integer,Integer,String)

CLAUSES
  GetMode(cga,cgaHi,"CGAHi"):-!.
  GetMode(cga,GraphMode,S):- !,format(S,"CGA%",GraphMode).
  GetMode(mcga,mcgaMed,"MCGAMed"):- !.
  GetMode(mcga,mcgahi,"MCGAHi"):- !.
  GetMode(mcga,GraphMode,S):- !,format(S,"MCGA%",GraphMode).
  GetMode(ega,egaLo,"EGALo"):- !.
  GetMode(ega,egaHi,"EGAHi"):- !.
  GetMode(ega64,ega64Lo,"EGA64Lo"):- !.
  GetMode(ega64,ega64Hi,"EGA64Hi"):- !.
  GetMode(hercMono,_,"HercMonoHi"):- !.
  GetMode(egaMono,_,"EGAMonoHi"):- !.
  GetMode(pc3270,_,"PC3270Hi"):- !.
  GetMode(att400,att400Med,"ATT400Med"):- !.
  GetMode(att400,att400Hi,"ATT400Hi"):- !.
  GetMode(att400,GraphMode,S):- !,format(S,"ATT400%",GraphMode).
  GetMode(vga,vgaLo,"VGALo"):- !.
  GetMode(vga,vgaMed,"VGAMedo"):- !.
  GetMode(vga,vgaHi,"VGAHi"):- !.
  GetMode(_,_,"UnKnown"):- !.

/************************************************************************
		Return name of font
*************************************************************************/

PREDICATES
  GetFontName(Integer,String)

CLAUSES
  GetFontName(0,"Default").	GetFontName(1,"TrixplexFont").
  GetFontName(2,"SmallFont").	GetFontName(3,"SansSerifFont").
  GetFontName(4,"GothicFont").

/************************************************************************
		BlackToWhite
*************************************************************************/

PREDICATES
  BlackToWhite(Integer,Integer)

CLAUSES
  BlackToWhite(black,white):-GetMaxColor(X),X>4,!.
  BlackToWhite(Color,Color).
	

/************************************************************************
		Repeat forever
*************************************************************************/

PREDICATES
  nondeterm repeat

CLAUSES
  repeat.
  repeat:- repeat.

/************************************************************************
	Implementation of the C loop:  for(I=Cur, i<Max, I++)
*************************************************************************/

PREDICATES
  nondeterm for(Integer,Integer,Integer)

CLAUSES
  for(Cur,_,Cur).
  for(Cur,Max,I):- Cur2=Cur+1, Cur2<Max, for(Cur2,Max,I).

/************************************************************************
	Graphics write a string
*************************************************************************/

PREDICATES
  gwrite(String)

CLAUSES
  gwrite(S):-
	retract(graphCoord(X,Y)),
	OutTextXY(X,Y,S), TextHeight("H",DY), Y2=Y+DY+2,
	assert(graphCoord(X,Y2)),!.

/************************************************************************
		Mode switching
*************************************************************************/

PREDICATES
  ToGraphic
  ToText
  KeepColor(integer,integer,integer)

CLAUSES
  ToGrapHic:-
	/* Detect graphic equipment */
	DetectGraph(G_Driver, G_Mode1),
	KeepColor(G_Driver,G_Mode1,G_Mode),
	GetDriverName2(G_Driver,G_Name),
	assert(driver(G_Driver,G_Mode,G_Name)),
	InitGraph(G_Driver,G_Mode, _, _, bgi_Path),!.

  ToText:-
	closegraph().
	
KeepColor(1,_,0).
KeepColor(_,Mode,Mode).
	

/************************************************************************
	Display a status line at the bottom of the screen
*************************************************************************/

PREDICATES
  StatusLine(String)

CLAUSES
  StatusLine(Msg):-
	maxX(MaxX), maxY(MaxY), SetViewPort(0,0,MaxX,MaxY, 1),
	maxColors(MaxColors), MaxCol2=MaxColors,
	SetColor(MaxCol2),
	SetBkColor(0),
	SetTextStyle(default_Font, horiz_Dir, 1),
	SetTextJustify(center_Text, top_Text),
	SetLineStyle(solid_Line,0,norm_Width),
	SetFillStyle(empty_Fill,0),
	TextHeight("H",Height), MaxYH = MaxY-(Height+4),
	Bar(0, MaxYH,MaxX,MaxY),
	Rectangle(0,MaxYH,MaxX,MaxY),
	MaxX2 = MaxX div 2, MaxY2 = MaxY-(Height+2),
	OutTextXY(MaxX2,MaxY2, Msg),
	Height5 = Height+5, MaxX1=MaxX-1, MaxY5 = MaxY-(Height+5),
	SetViewPort(1,Height5,MaxX1,MaxY5,1).

/************************************************************************
	Pause until the user enter a keystroke
*************************************************************************/

PREDICATES
  Pause
  ChkEsc(Char)

CLAUSES
  Pause:-
	StatusLine("Esc aborts or press a key ..."),
	readChar(Ch), ChkEsc(Ch).

  ChkEsc('\027'):- !,CloseGraph, exit(1).
  ChkEsc(_).

/************************************************************************
	Draw a solid line around the current viewport
*************************************************************************/

PREDICATES
  DrawBorder

CLAUSES
  DrawBorder:-
	maxColors(MaxColors), MaxCol2 = MaxColors, SetColor(MaxCol2),
	SetBkColor(0),
	SetLineStyle(solid_Line,0,norm_Width),
	GetViewSettings(Left,Top,Right,Bottom,_),
	RL=Right-Left, BT=Bottom-Top,
	Rectangle(0,0,RL,BT).

/************************************************************************
	Establish the main window and set a viewport
*************************************************************************/

PREDICATES
  MainWindow(String)

CLAUSES
  MainWindow(Header):-
	ClearDevice,
	maxColors(MaxColors),
	MaxCol1=MaxColors,
	SetColor(MaxCol1),		   % Set current color to white
	SetBkColor(0),			   % Set background to black
	TextHeight("H",Height),		   % Get basic text height
	Height5=Height+5, Height4=Height+4,
	maxX(MaxX), MaxX1=MaxX-1,MaxX2=MaxX div 2,
	maxY(MaxY), MaxY4=MaxY-(Height+4),MaxY5=MaxY-(Height+5),
	SetViewPort(0,0,MaxX,MaxY,1),	   % Open port to full screen
	SettextStyle(default_font,horiz_dir,1),
	SetTextJustify(center_text, top_text),
	OutTextXY(MaxX2,2,Header),
	SetViewPort( 0, Height4, MaxX, MaxY4, 1),
	DrawBorder,
	SetViewPort(1, Height5, MaxX1,MaxY5, 1).

/************************************************************************
		Initialize video and Global flags
*************************************************************************/

PREDICATES
  Initialize

CLAUSES
  Initialize:-
  	retractall(_, graphics),
  	ToGraphic,
  	GetMaxColor(MaxColors),   assert(maxcolors(MaxColors)),
  	GetMaxX(MaxX),	  assert(maxX(MaxX)),
  	GetMaxY(MaxY),	  assert(maxY(MaxY)),
  	GetAspectRatio(Xasp,Yasp),
  	AspectRatio = Xasp/Yasp,  assert(aspectCorr(AspectRatio)).

/************************************************************************
		Report the current configuration
*************************************************************************/

PREDICATES
  ReportStatus

CLAUSES
  ReportStatus:-
	GetViewSettings(Left,Top,Right,Bottom,Clip),
	GetLineSettings(LineStyle,_,ThickNess),
	GetFillSettings(FillPattern,FillColor),
	GetTextSettings(Font,Direction,CharSize,Horiz,Vert),
	X = 10, Y = 4, assert(graphCoord(X,Y)),
	MainWindow("Status report after InitGraph "),
	SetTextJustify(left_Text,top_Text),
	maxColors(MaxC), MaxC2 = MaxC, SetColor(MaxC2),
	driver(DriverNo,DriverMode,Driver),
	GetMode(DriverNo,DriverMode,Mode),
	format(Buf1,"Graphics device   : %-20 (%)",Driver,DriverNo), gwrite(Buf1),
	format(Buf2,"Graphics mode     : %-20 (%)",Mode,DriverMode), gwrite(Buf2),
	maxX(MaxX), maxY(MaxY),
	format(Buf3,"Screen resolution : ( 0, 0, %, % )",MaxX,MaxY), gwrite(Buf3),
	format(Buf4,"Current view port : ( %, %, %, % )",Left,Top, Right, Bottom), gwrite(Buf4),
	format(Buf5,"Clipping          : %",Clip), gwrite(Buf5),
	GetX(XX), GetY(YY),
	format(Buf6,"Current position  : ( %, % )",XX,YY), gwrite(Buf6),
	maxColors(MaxColors),
	format(Buf7,"Colors available  : %",MaxColors), gwrite(Buf7),
	GetColor(Color),
	format(Buf8,"Current color     : %",Color), gwrite(Buf8),
	format(Buf9 ,"Line style        : %",LineStyle), gwrite(Buf9),
	format(Buf10,"Line thickness    : %",Thickness), gwrite(Buf10),
        format(Buf11,"Current fill style: %",FillPattern), gwrite(Buf11),
        format(Buf12,"Current fill color: %",FillColor), gwrite(Buf12),
        format(Buf13,"Current font      : %",Font), gwrite(Buf13),
        format(Buf14,"Text direction    : %",Direction), gwrite(Buf14),
        format(Buf15,"Character size    : %",CharSize), gwrite(Buf15),
        format(Buf16,"Horizontal justify: %",Horiz), gwrite(Buf16),
        format(Buf17,"Vertical justify  : %",Vert), gwrite(Buf17),
        Pause.

/************************************************************************
	Display a 3-D bar chart on the screen
*************************************************************************/

PREDICATES
  Bar3DDemo
  Bar3DDemo2(Integer,Integer,Integer)
  Bar3DDemo3(Integer,Integer,Integer,Integer,Integer)
  BarHeight(Integer,Integer)

CLAUSES
  Bar3DDemo:-
  	MainWindow("Bar 3-D / Rectangle Demonstration"),
  	TextHeight("H",Height), H = 3*Height,
	GetViewSettings(Left1,Top1,Right1,Bottom1,_),
	SetTextJustify(center_Text,top_Text),
	SetTextStyle(triplex_Font,horiz_Dir,4),
	maxX(MaxX), MaxX2 = MaxX div 2,
	OutTextXY(MaxX2,6,"These are 3-D Bars"),
	SetTextStyle(default_Font, horiz_Dir, 1),
	Left50 = Left1+50, Top40 = Top1+40, Right50=Right1-50, Bottom10=Bottom1-10,
	SetViewPort(Left50,Top40,Right50,Bottom10,1),
	GetViewSettings(Left,Top,Right,Bottom,_),
	BTH = Bottom-Top-H,
	Line(H,H,H, BTH),
	RLH = Right-Left-H,
	Line(H, BTH, RLH, BTH),
	XStep = (Right-Left-2*H) div 10,
	YStep = (Bottom-Top-2*H) div 5,
	J     = Bottom-Top-H,
	SetTextJustify(center_Text,center_Text),
	Bar3DDemo2(H,J,Ystep),
	SetTextJustify(center_Text,top_Text),
	Bar3DDemo3(H,Bottom,Top,YStep,XStep),
	Pause.

  Bar3DDemo2(H,InitJ,YStep):-
  	for (0,6,I),
  	  H2 = H div 2, J = InitJ - YStep*I,
  	  Line(H2,J,H,J),
  	  str_int(Buf,I),
  	  OutTextXY(0,J,Buf),
  	fail.
  Bar3DDemo2(_,_,_).

  Bar3DDemo3(H,Bottom,Top,YStep,XStep):-
  	maxColors(MaxColors),
  	for (0,11,I),
  	  J  = H + XStep*I,
  	  random(MaxColors,Color),
  	  I1 = I + 1,
  	  SetFillStyle(I1,Color),
  	  BTH = Bottom-Top-H, BT3H2 = Bottom-Top-3-H div 2,
  	  Line(J,BTH,J,BT3H2),
  	  Str_Int(Buf,I),
  	  BTH2 = Bottom-Top-H div 2,
  	  OutTextXY(J,BTH2,Buf),
  	  I<>10,
  	  BHeight = Bottom-Top-H-1,
  	  BarHeight(I,BH),
  	  BTHBY	  = Bottom-Top-H-BH*YStep, JX = J+XStep,
  	  Bar3D(J,BTHBY, JX, BHeight,15,1),
  	fail.
  Bar3DDemo3(_,_,_,_,_).

  BarHeight(0,1).  BarHeight(1,3).  BarHeight(2,5).  BarHeight(3,4).
  BarHeight(4,3).  BarHeight(5,2).  BarHeight(6,1).  BarHeight(7,5).
  BarHeight(8,4).  BarHeight(9,2).  BarHeight(10,3).

/************************************************************************
	Display a 2-D bar chart using Bar and Rectangle
*************************************************************************/

PREDICATES
  BarDemo
  BarDemo_2(Integer,Integer,Integer)
  BarDemo_3(Integer,Integer,Integer,Integer)
  BarDemo_Style(Integer,Integer)
  BarDemo_BarHeight(Integer,Integer)

CLAUSES
  BarDemo:-
  	MainWindow("Bar / Rectangle demonstration"),
  	TextHeight("H",Height), H = 3*Height,
  	GetViewSettings(Left1,Top1,Right1,Bottom1,_),
	SetTextJustify(center_Text,top_Text),
	SetTextStyle(triplex_Font,horiz_Dir,4),
	maxX(MaxX), MaxX2 = MaxX div 2,
	OutTextXY(MaxX2,6,"These are 2-D Bars"),
	SetTextStyle(default_Font, horiz_Dir, 1),
	Left50 = Left1+50, Top30 = Top1+30, Right50=Right1-50, Bottom10=Bottom1-10,
	SetViewPort(Left50,Top30,Right50,Bottom10,1),
	GetViewSettings(Left,Top,Right,Bottom,_),
	SHeight	= Bottom-Top,
	SWidth	= Right-Left,
	SHeightH= SHeight-H,
	Line(H,H,H,SHeightH),
	Line(H,SHeightH,SHeightH,SHeightH),
	YStep	= (SHeight - 2*H) div 5,
	XStep	= (SWidth  - 2*H) div 5,
	SetTextJustify(center_Text,center_Text),
	BarDemo_2(H,SHeightH,YStep),
	SetTextJustify(center_Text,top_Text),
	BarDemo_3(H,SHeight,YStep,XStep),
	Pause.

  BarDemo_2(H,SHeightH,YStep):-
  	H2 = H div 2,
  	for (0,5,I),
  	  J = SHeightH - I*YStep,
  	  Line(H2,J,H,J),
  	  Str_Int(Buf,I),
  	  OutTextXY(0,J,Buf),
  	fail.
  BarDemo_2(_,_,_).

  BarDemo_3(H,SHeight,YStep,XStep):-
  	maxColors(MaxColors),
  	for (0,5,I),
  	  J       = H + I*XStep,
  	  BarDemo_Style(I,Style),
  	  BarDemo_BarHeight(I,BHeight),
  	  Random(MaxColors,RM),
  	  SetFillStyle(Style,RM),
  	  SHeightH = SHeight-H, SHeight3H = Sheight-3-H div 2,
  	  Line(J,SHeightH,J,Sheight3H),
  	  Str_Int(Buf,I),
  	  SHeightH2 = SHeight-H div 2,
  	  OutTextXY(J,SHeightH2,Buf),
  	  I<>5,
  	  SheightHBY = SHeight-H-BHeight*YStep, JX = J+XStep, SHeightH1=SHeight-H-1,
  	  Bar(J,SHeightHBY,JX,SHeightH1),
  	  Rectangle(J,SHeightHBY, JX,SHeightH),
  	  fail.
  BarDemo_3(_,_,_,_).

  BarDemo_Style(0,1).  BarDemo_Style(1,3).  BarDemo_Style(2,10).
  BarDemo_Style(3,5).  BarDemo_Style(4,9).  BarDemo_Style(5,1).

  BarDemo_BarHeight(0,1).  BarDemo_BarHeight(1,3).  BarDemo_BarHeight(2,5).
  BarDemo_BarHeight(3,2).  BarDemo_BarHeight(4,4).

/************************************************************************
	Display a random pattern of ARC's on the screen
*************************************************************************/

PREDICATES
  ArcDemo

CLAUSES
  ArcDemo:-
  	MainWindow("Arc Demonstration"),
  	StatusLine("Esc Aborts - Press a Key to stop"),
  	maxY(MaxY), Mradius = MaxY div 10, maxX(MaxX),
  	maxColors(MaxColors),
  	Repeat,
  	  Random(MaxColors,RM), SetColor(RM),
  	  Random(358,R2), Eangle = 1 + R2,
  	  Random(MaxX,RX),
  	  Random(MaxY,RY),
  	  Random(Eangle,RE),
  	  Arc(RX,RY,RE,Eangle,Mradius),
  	  GetArcCoords(X,Y,XStart,YStart,XEnd,YEnd),
  	  Line(X,Y,XStart,YStart),
  	  Line(X,Y,XEnd,YEnd),
  	KeyPressed,!,
  	Pause.

/************************************************************************
	Display a random pattern of Circles on the screen
*************************************************************************/

PREDICATES
  CircleDemo

CLAUSES
  CircleDemo:-
	MainWIndow("Circle Demonstration"),
	StatusLine("Esc Aborts - Press a key to stop"),
  	maxY(MaxY), Mradius = MaxY div 10, maxX(MaxX),
  	maxColors(MaxColors),
  	Repeat,
  	  Random(MaxColors,RM), SetColor(RM),
  	  Random(MaxX,RX),
  	  Random(MaxY,RY),
  	  Random(Mradius,RR),
  	  Circle(Rx,Ry,RR),
  	KeyPressed,!,
  	Pause.

/************************************************************************
		Display a pie chart on the screen
*************************************************************************/

PREDICATES
  PieDemo
  ToRad(Real,Real)
  AdjAsp(Integer,Real)
  colorPie(integer,integer,integer,integer)

CLAUSES
  PieDemo:-
  	MainWindow("Pie Chart Demonstration"),
  	GetViewSettings(Left,Top,Right,Bottom,_),
  	XCenter	= (Right-Left) div 2,
  	YCenter = (Bottom-Top) div 2+20,
  	Radius  = (Bottom-Top) div 3,
  	LRadius = Radius + Radius div 5,
  	SetTextStyle(triplex_Font,horiz_Dir,4),
  	SetTextJustify(center_Text,top_Text),
  	maxX(MaxX), MaxX2 = MaxX div 2,
  	OutTextXY(MaxX2,6,"This is a Pie Chart"),
  	SetTextStyle(triplex_Font,horiz_Dir,3),
  	SetTextJustify(center_Text,top_Text),
        ColorPie(ColorP1,ColorP2,ColorP3,ColorP4),  	
  	SetFillStyle(solid_Fill,ColorP1),
  	XCenter10 = XCenter+4, AdjAsp(5,Asp), YCenterAdj10 = YCenter-Asp,
  	PieSlice(XCenter10,YCenterAdj10,0,90,Radius),
	aspectCorr(AspectRatio),
	ToRad(45,Radians),
  	X = XCenter + cos(Radians)*LRadius,
  	Y = YCenter - sin(Radians)*LRadius*AspectRatio,
  	SetTextJustify(left_Text,bottom_Text),
  	OutTextXY(X,Y,"25 %"),
  	SetFillStyle(wide_Dot_Fill,ColorP2),
  	PieSlice(XCenter,YCenter,90,135,Radius),
  	ToRad(113,Rad2),
  	X2 = XCenter + cos(Rad2)*LRadius+20,
  	Y2 = YCenter - sin(Rad2)*LRadius*AspectRatio+2,
  	SetTextJustify(right_Text,bottom_Text),
  	OutTextXY(X2,Y2,"12.5 %"),
  	SetFillStyle(interleave_Fill,ColorP3),
  	SetTextJustify(right_Text,center_Text),
  	XCenterM10 = XCenter - 7,
  	PieSlice(XCenterM10,YCenter,135,225,Radius),
  	ToRad(180,Rad3),
  	X3 = XCenter + cos(Rad3)*LRadius,
  	Y3 = YCenter - sin(Rad3)*LRadius*AspectRatio,
  	SetTextJustify(right_Text,center_Text),
  	OutTextXY(X3,Y3,"25 %"),
  	SetFillStyle(hatch_Fill,ColorP4),
  	PieSlice(XCenter,YCenter,225,360,Radius),
  	ToRad(293,Rad4),
  	X4 = XCenter + cos(Rad4)*LRadius+20,
  	Y4 = YCenter - sin(Rad4)*LRadius*AspectRatio-20,
  	SetTextJustify(left_Text,top_Text),
  	OutTextXY(X4,Y4,"37.5 %"),
  	Pause.

  ToRad(D,Rad):- Rad = D*pi/180.0.

  AdjAsp(Y,Asp):- aspectCorr(AspectRatio), Asp = AspectRatio*Y.

colorPie(red, green, yellow, blue):- getmaxColor(C),C>3,!.
ColorPie(blue,green,cyan,green).


/************************************************************************
	Display pattern using MoveRel and LineRel commands
*************************************************************************/

PREDICATES
  LineRelDemo

CLAUSES
  LineRelDemo:-
  	MainWindow("MoveRel / LineRel Demonstration"),
	StatusLine("Press any key to continue, Esc to Abort"),
  	GetViewSettings(Left,Top,Right,Bottom,_),
  	CX = (Right-Left) div 2, CY = (Bottom-Top) div 2,
  	H = (Bottom-Top) div 8, W = (Right-Left) div 9,
  	DX = 2*W, DY = 2*H,
  	colorpie(C1,C2,C3,_),
  	SetColor(black),
  	SetFillStyle(solid_Fill,C1),
  	RL = Right-Left, BT = Bottom-Top,
  	Bar(0,0,RL,BT),
	CXMDX = CX-DX, CYMDY = CY-DY,
	CXMDXPW = CX-DX+W, CYMDYMH = CY-DY-H,
	CXPDX = CX+DX, CYPDY = CY+DY,
	CXPDXMW = CX+DX-W, CYPDYPH = CY+DY+H,
	OutS	= [CXMDX,CYMDY,CXMDXPW,CYMDYMH,CXPDX,CYMDYMH,
		   CXPDX,CYPDY,CXPDXMW,CYPDYPH,CXMDX,CYPDYPH,CXMDX,CYMDY],
	SetFillStyle(solid_Fill,C2),
	FillPoly(Outs),
	CXMW2 = CX-W div 2, CYPH = CY+H,
	CXPW2 = CX+W div 2, CYMH = CY-H,
	OutS2 = [CXMW2,CYPH,CXPW2,CYPH,CXPW2,CYMH,CXMW2,CYMH,CXMW2,CYPH],
	SetFillStyle(solid_Fill,C3),
	FillPoly(OutS2),
	MH = -H, WT3 = 3*W, HT5 = 5*H, M3TW = -3*W, M5TH = -5*H, MW = -W,
	MoveTo(CXMDX,CYMDY),
	LineRel(W,MH), LineRel(WT3,0), LineRel(0,HT5), LineRel(MW,H),
	LineRel(M3TW,0), LineRel(0,M5TH),
	WPWD2 = W+W div 2, M3TH = -3*H, WD2 = W div 2,
	MoveRel(W,MH),
	LineRel(0,HT5), LineRel(WPWD2,0), LineRel(0,M3TH),
	LineRel(WD2,MH), LineRel(0,HT5),
	MWMWD2 = -W-W div 2, HT3 = 3*H, MWD2 = - W div 2,
	MoveRel(0,M5TH), LineRel(MWMWD2,0), LineRel(0,HT3), LineRel(MWD2,H),
	MoveRel(WD2,MH), LineRel(W,0),
	M2TH = -2*H,
	MoveRel(0,M2TH), LineRel(MW,0),
  	Pause.

/************************************************************************
	Display a pattern using MoveTo and LineTo commands
*************************************************************************/

CONSTANTS
  maxpts	= 15

PREDICATES
  LineToDemo
  LineToDemo2(PointList,Integer,Integer,Integer,Integer,Real,Real)
  LineToDemo3(PointList)
  LineToDemo4(Integer,Integer,PointList)

CLAUSES
  LineToDemo:-
  	MainWindow("MoveTo / LineTo Demonstration"),
  	GetViewSettings(Left,Top,Right,Bottom,_),
  	H	= Bottom - Top,
  	W	= Right - Left,
  	XCenter	= W div 2,
  	YCenter	= H div 2,
	aspectCorr(AspectRatio),
  	Radius	= (H-30)/(AspectRatio*2),
  	Step	= 360/maxpts,
  	LineToDemo2(Points,0,XCenter,YCenter,Radius,AspectRatio,Step),
  	Circle(XCenter,YCenter,Radius),
  	LineToDemo3(Points),
  	Pause.

  LineToDemo2([[X,Y]|Points],I,XCenter,YCenter,Radius,AspectRatio,Step):-
	I<maxpts,!,
	Angle	= I*Step,
	Rads	= Angle*pi/180.0,
	X	= XCenter + cos(Rads)*Radius,
	Y	= YCenter - sin(Rads)*Radius*AspectRatio,
	I2	= I+1,
	LineToDemo2(Points,I2,XCenter,YCenter,Radius,AspectRatio,Step).
  LineToDemo2([],_,_,_,_,_,_).

  LineToDemo3([]):- !.
  LineToDemo3([[X,Y]|Points]):-
  	X2=X, Y2=Y,
  	LineToDemo4(X2,Y2,[[X,Y]|Points]),
  	LineToDemo3(Points).

  LineToDemo4(X,Y,[[X2,Y2]|Points]):-!,
  	MoveTo(X,Y),
  	LineTo(X2,Y2),
  	LineToDemo4(X,Y,Points).
  LineToDemo4(_,_,_).

/************************************************************************
	Display a pattern using all of the standard line styles that
	are available
*************************************************************************/

PREDICATES
  LineStyleDemo
  LineStyleDemo2(Integer,Integer,Integer,Integer,Integer)

CLAUSES
  LineStyleDemo:-
	MainWIndow("Pre-defined line styles"),
  	GetViewSettings(Left,_,Right,Bottom,_),
  	W = Right - Left, Step = W div 11,
  	X = 35, Y = 10,
  	GetTextSettings(Font,Direction,CharSize,_,_),
  	SetTextStyle(sANS_SERIF_FONT,horiz_Dir,2),
  	SetTextJustify(left_Text,top_Text),
  	Y10 = Y - 10,
  	X1 = X -20,
  	OutTextXY(X1,Y10,"Normal Width"),
  	SetTextStyle(Font,Direction,CharSize),
  	LineStyleDemo2(X,Y,Bottom,Step,norm_Width),
  	X2 = X + 6*Step,
  	X3 = X1 + 6*Step+3,
  	SetTextStyle(sANS_SERIF_FONT,horiz_Dir,2),
  	SetTextJustify(left_Text,top_Text),
  	OutTextXY(X3,Y10,"Thick Width"),
  	SetTextStyle(Font,Direction,CharSize),
  	SetTextJustify(center_Text,top_Text),
  	LineStyleDemo2(X2,Y,Bottom,Step,thick_Width),
  	SetTextJustify(left_Text,top_Text),
  	Pause.

  LineStyleDemo2(InitX,Y,Bottom,Step,Width):-
  	Y20	 = Y + 20,
  	Bottom40 = Bottom - 40,
  	Bottom30 = Bottom-30,
  	for (0,4,Style),
  	  SetLineStyle(Style,0,Width),
  	  X	= InitX + Style*Step,
  	  Line(X,Y20,X,Bottom40),
  	  Str_Int(Buf,Style),
  	  OutTextXY(X,Bottom30,Buf),
  	fail.
  LineStyleDemo2(_,_,_,_,_).

/************************************************************************
		Display user definable line styles
*************************************************************************/

PREDICATES
  UserLineStyleDemo
  UserLineStyleDemo2(Integer,Integer,Integer,Integer)
  UserLineStyleDemo3(Integer,Integer,Integer,Integer,Integer)

CLAUSES
  UserLineStyleDemo:-
  	MainWindow("User defined line styles"),
  	GetViewSettings(_,Top,Right,Bottom,_),
  	H = Bottom-Top,
  	Y = 10,
  	SetTextJustify(center_Text,top_Text),
  	assert(lineStyleDBA(0,0,true)),	% Style, I, Flag
  	for (0,Right,J),
  	  X = 4 + J*5,
  	  X < (Right-2),
  	  retract(lineStyleDBA(Style,I,Flag)),
  	  UserLineStyleDemo2(Style,Style2,I,Flag),
  	  SetLineStyle(userbit_Line,Style2,norm_Width),
  	  HY = H - Y,
  	  Line(X,Y,X,HY),
  	  I2 = (I+1) mod 16,
  	  UserLineStyleDemo3(Style2,Flag,Flag2,I2,I3),
  	  assert(lineStyleDBA(Style2,I3,Flag2)),
  	fail.

  UserLineStyleDemo:-
  	SetTextJustify(left_Text,top_Text),
  	Pause.

  UserLineStyleDemo2(Style,Style2,I,true):-
  	bitleft(1,I,IL),
  	bitor(Style,IL,Style2).

  UserLineStyleDemo2(Style,Style2,I,false):-
  	bitright($8000,I,IR), bitxor(IR,$FFFF,IR2),
  	bitand(Style,IR2,Style2).

  UserLineStyleDemo3($FFFF,_,false,_,0):-!.
  UserLineStyleDemo3(0,_,true,I,I):-!.
  UserLineStyleDemo3(_,Flag,Flag,I,I).

/************************************************************************
	Display all the characters in each of the available fonts
*************************************************************************/

PREDICATES
  TextDump
  TextDump2(Integer,Integer,Integer)
  EvalSize(Integer,Integer)
  CGASizes(Integer,Integer)
  NormSizes(Integer,Integer)

CLAUSES
  TextDump:-
	for (0,5,Font),
	  GetFontName(Font,FontName),
	  format(Buf,"%s Character Set",FontName),
	  MainWindow(Buf),
	  GetViewSettings(Left,_,Right,_,_),
	  SetTextJustify(left_Text, top_Text),
	  MoveTo(2,3),
	  WWidth	= Right - Left,
	  TextHeight("H",LWidth),
	  TextDump2(Font,LWidth,WWidth),
	  Pause,
	fail.
  TextDump.

  TextDump2(default_Font,Lwidth,WWidth):-
  	SetTextStyle(default_Font,horiz_Dir,1),
  	for (0,256,Ch),
  	  Char_Int(Ch2,Ch), Str_Char(Buf,Ch2),
  	  OutText(Buf),
  	  GetX(X),
  	  X+LWidth > WWidth,
  	    GetY(Y), TextHeight("H",TH), YT=Y+TH+3,
  	    MoveTo(2, YT),
  	fail.
  TextDump2(default_Font,_,_):- !.
  TextDump2(Font,Lwidth,WWidth):-
  	EvalSize(Font,Size),
  	SetTextStyle(Font,horiz_Dir,Size),
  	Char_Int('!',Start),
  	for (Start,127,Ch),
  	  Char_Int(Ch2,Ch), Str_Char(Buf,Ch2),
  	  OutText(Buf),
  	  GetX(X),
  	  X+LWidth > WWidth,
  	    GetY(Y), TextHeight("H",TH), YT=Y+TH+3,
  	    MoveTo(2, YT),
  	fail.
  TextDump2(_,_,_).

  EvalSize(Font,Size):- GetMaxY(Y), Y<200,!, CGASizes(Font,Size).
  EvalSize(Font,Size):- NormSizes(Font,Size).

  CGASizes(0,1).  CGASizes(1,3).  CGASizes(2,7).
  CGASizes(3,3).  CGASizes(4,3).

  NormSizes(0,1).  NormSizes(1,4).  NormSizes(2,7).
  NormSizes(3,4).  NormSizes(4,6).

/************************************************************************
	Show each font in several sizes to the user
*************************************************************************/

PREDICATES
  TextDemo
  TextDemo2(Integer,Integer,Integer)
  TextDemo3(Integer,Integer,Integer,Integer,Integer,Integer,Integer)
  FontSize(Integer,Integer,Integer)
  CharSizes(Integer,Integer)

CLAUSES
  TextDemo:-
  	for(0,5,Font),
	  GetFontName(Font,FontName),
  	  format(Buf,"% Demonstration",FontName),
  	  Mainwindow(Buf),
	  GetViewSettings(Left,Top,Right,Bottom,_),
	  CharSizes(Font,CharSiz),
 	  SetTextStyle(Font, vert_Dir, CharSiz),
	  Y = (Bottom - Top)/1.1,
	  SetTextJustify(center_Text,bottom_Text),
	  TextWidth("M",Width), TxtWidth2	= 2*Width,
	  OutTextXY(TxtWidth2, Y, "Vertical -->"),
	  SetTextStyle(Font,horiz_Dir,CharSiz),
	  SetTextJustify(left_Text,top_Text),
	  TextWidth("M",Width2), TxtWidth22	= 2*Width2,
	  OutTextXY(TxtWidth22,2,"Horizontal -->"),
	  SetTextJustify(center_Text,center_Text),
	  X = (Right - Left) / 2,
	  TextHeight("H",Y2),
	  TextDemo2(Font,Y2,X),
	  TextHeight("H",H),
	  TextDemo3(Y2,H,Font,horiz_Dir,user_Char_Size, Right,Left),
	  Pause,
	fail.
  TextDemo.

  TextDemo2(Font,InitY,X):-
  	assert(textHeightDBA(InitY)),
   	for (0,5,I),
  	  FontSize(Font,I,Size),
  	  SetTextStyle(Font,horiz_Dir,Size),
  	  TextHeight("H",H),
  	  retract(textHeightDBA(Y1)),
  	  Y = Y1 + H,
  	  assert(textHeightDBA(Y)),
  	  format(Buf,"Size %",Size),
  	  OutTextXY(X,Y,Buf),
  	fail.
  TextDemo2(_,_,_):- retract(textHeightDBA(_)),!.

  TextDemo3(Y2,H,Font,horiz_Dir,user_Char_Size, Right,Left):-
  	  Font <> default_Font,!,
	  YH2	= Y2 + 4*H + H div 2,
	  SetTextJustify(center_Text,top_Text),
	  SetUserCharSize(5,6,3,2),
	  SetTextStyle(Font,horiz_Dir,user_Char_Size),
	  RL2	= (Right - Left)  div  2,
	  OutTextXY(RL2,YH2,"User Defined Size").
  TextDemo3(_,_,_,_,_,_,_).

  FontSize(small_Font,I,I4):- !,I4=I+4.
  FontSize(_,I,I1):- I1 = I + 1.

  CharSizes(0,1).  CharSizes(1,3).  CharSizes(2,7).
  CharSizes(3,3).  CharSizes(4,3).

/************************************************************************
	Demonstrate the effects of changing mode
*************************************************************************/

PREDICATES
  CRTModeDemo

CLAUSES
  CRTModeDemo:-
  	MainWIndow("SetGraphMode / RestoreCRTMode demo"),
  	GetViewSettings(Left,Top,Right,Bottom,_),
	SetTextJustify(center_Text,center_Text),
  	RL2	= (Right - Left) div 2,
  	BT2	= (Bottom- Top) div 2,
  	OutTextXY(RL2,BT2,"Now you are in graphics mode ..."),
  	StatusLine("Press any key for text mode ..."), readchar(_),
  	GetGraphMode(GraphMode),
  	RestoreCRTMode, /*txtMode(R,C), textmode(R,C),*/
  	write("\nNow you are in text mode.\n\n"),
  	write("\nPress any key to go back to graphics ..."), readchar(_),
  	SetGraphMode(GraphMode),
  	MainWindow("SetGraphMode / RestoreCRTMode demo"),
  	SetTextJustify(center_Text,center_Text),
  	OutTextXY(RL2,BT2,"Back in Graphics Mode ..."),
  	Pause.

/************************************************************************
	Display the standard fill patterns available
*************************************************************************/

PREDICATES
  FillStyleDemo

CLAUSES
  FillStyleDemo:-
	MainWindow("Pre-defined Fill Styles"),
	GetViewSettings(_,_,Right,Bottom,_),
	W = 2*( (Right+1) div 13 ), H = 2*( (Bottom-10) div 10 ),
	maxColors(MaxColors), /*MaxColors1 = MaxColors-1,*/
	for (0,3,J),
	  Y	= H div 2 + (H div 2)*3*J,
	  for (0,4,I),
	    Style	= J*4 + I,
	    X		= W div 2 + (W div 2)*3*I,
	    Color	= (J*4+I) mod (MaxColors+1), BlackToWhite(Color,Color2),
	    SetFillStyle(Style,Color2),
	    XW = X + W, YH = Y + H,
	    Bar(X,Y,XW,YH),
	    Str_Int(Buf,Style),
	    XW2 = X + W div 2, YH4 = Y + H + 4,
	    OutTextXY(XW2,YH4,Buf),
	  fail.
  FillStyleDemo:-
  	SetTextJustify(left_Text,top_Text),
  	Pause.

/************************************************************************
	Demonstrate how to use the user definable fill patterns
*************************************************************************/

PREDICATES
  FillPatternDemo
  UserPattern(Integer,intlist)

CLAUSES
  FillPatternDemo:-
	MainWindow("User Defined Fill Styles"),
	GetViewSettings(_,_,Right,Bottom,_),
	W = 2*( (Right+1) div 13 ), H = 2*( (Bottom-10) div 10 ),
	maxColors(MaxColors),
	for (0,3,J),
	  Y	= H div 2 + (H div 2)*3*J,
	  for (0,4,I),
	    Style = J*4 + I,
	    X	  = W div 2 + (W div 2)*3*I,
	    Color = (J*4+I) mod (MaxColors+1), BlackToWhite(Color,Color2),
	    SetFillStyle(Style,Color2),
	    UserPattern(Style,UPattern),
	    SetFillPattern(UPattern,Color2),
	    XW = X + W, YH = Y + H,
	    Bar(X,Y,XW,YH),
	    Rectangle(X,Y,XW,YH),
	    Str_Int(Buf,Style),
	    XW2	= X + W div 2,
	    YH4	= Y + H + 4,
	    OutTextXY(XW2,YH4,Buf),
	  fail.
  FillPatternDemo:-
  	SetTextJustify(left_Text,top_Text),
  	Pause.

  UserPattern(0, [$AA,	$55,	$AA,	$55,	$AA,	$55,	$AA,	$55]).
  UserPattern(1, [$33,	$33,	$CC,	$CC,	$33,	$33,	$CC,	$CC]).
  UserPattern(2, [$F0,	$F0,	$F0,	$F0,	$0F,	$0F,	$0F,	$0F]).
  UserPattern(3, [$00,	$10,	$28,	$44,	$28,	$10,	$00,	$00]).
  UserPattern(4, [$00,	$70,	$20,	$27,	$24,	$24,	$07,	$00]).
  UserPattern(5, [$00,	$00,	$00,	$18,	$18,	$00,	$00,	$00]).
  UserPattern(6, [$00,	$00,	$3C,	$3C,	$3C,	$3C,	$00,	$00]).
  UserPattern(7, [$00,	$7E,	$7E,	$7E,	$7E,	$7E,	$7E,	$00]).
  UserPattern(8, [$00,	$00,	$22,	$08,	$00,	$22,	$1C,	$00]).
  UserPattern(9, [$FF,	$7E,	$3C,	$18,	$18,	$3C,	$7E,	$FF]).
  UserPattern(10,[$00,	$10,	$10,	$7C,	$10,	$10,	$00,	$00]).
  UserPattern(11,[$00,	$42,	$24,	$18,	$18,	$24,	$42,	$00]).

/************************************************************************
    Display a random pattern of polygons until the user says enough
*************************************************************************/

Constants
  MaxPoints	= 6

PREDICATES
  PolyDemo
  BuildPts(Integer,Integer,Integer,Integer,Integer,intlist)

CLAUSES
  PolyDemo:-
	MainWindow("DrawPoly / FillPoly Demonstration"),
	StatusLine("Esc Aborts - Press a key to stop"),
	maxColors(MaxColor), /*MaxColor1 = MaxColor-1,*/
	maxX(MaxX), maxY(MaxY),
	Repeat,
	  Random(MaxColor,R), Color = 1 + R,
	  Random(10,R10),
	  SetFillStyle(R10,Color),
	  SetColor(Color),
	  BuildPts(maxPoints,0,0,MaxX,MaxY,Poly),
	  FillPoly(Poly),
	KeyPressed,!,
	Pause.

  BuildPts(0,InitX,InitY,_,_,[InitX,InitY]):-!.
  BuildPts(maxPoints,_,_,MaxX,MaxY,[X,Y|Points]):- !,
  	Random(MaxX,X2),X=X2,
  	Random(MaxY,Y2),Y=Y2,
  	I2	= maxPoints-1,
  	BuildPts(I2,X2,Y2,MaxX,MaxY,Points).
  BuildPts(I,Ix1,Iy1,MaxX,MaxY,[X,Y|Points]):- !,
        Ix=Ix1,  Iy=Iy1,
  	Random(MaxY,X),
  	Random(MaxY,Y),
  	I2	= I -1,
  	BuildPts(I2,Ix,Iy,MaxX,MaxY,Points).

/************************************************************************
	Give a closing screen to the user before leaving
*************************************************************************/

PREDICATES
  SayGoodBye

CLAUSES
  SayGoodBye:-
  	MainWindow("== Finale =="),
	GetViewSettings(Left,Top,Right,Bottom,_),
	SetTextStyle(gOTHIC_FONT,horiz_Dir,5),
	SetTextJustify(center_Text,center_Text),
	maxColors(MaxColor), Color = 1+round(MaxColor / 2.5),
	SetColor(Color),
	H = Bottom - Top, W = Right  - Left,
	W2 = W div 2, H2 = H div 2,
	OutTextXY(W2,H2,"That's all folks!"),
	StatusLine("Press any key to Exit"),
	readchar(_),
	ClearDevice.

/************************************************************************
	Display the current color palette on the screen
*************************************************************************/

PREDICATES
  ColorDemo

CLAUSES
  ColorDemo:-
	MainWindow("Color Demonstration"),
	GetViewSettings(_,_,Right,Bottom,_),
	Width  = 2*( (Right+1) div 16 ),
	Height = 2*( (Bottom-10) div 10 ),
	maxColors(MaxColors),
	for (0,3,J),
	  for (0,5,I),
  	    Color = (5*J + I+1) mod (MaxColors+1),
	    X	= Width div 2  + (Width div 2) * 3*I,
	    Y	= Height div 2 + (Height div 2)* 3*J,
	    SetFillStyle(solid_Fill,Color),
	    SetColor(Color),
	    XW = X+Width, YH = Y + Height,
	    Bar(X,Y,XW,YH),
	    BlackToWhite(Color,Color2), SetColor(Color2),
	    Rectangle(X,Y,XW,YH),
	    str_int(Cnum,Color),
	    XW2 = X+(Width div 2),  YH4 = Y + Height +4,
	    OutTextXY(XW2,YH4, Cnum),
	  fail.
  ColorDemo:- Pause.

/************************************************************************
	Display a pattern of random dots on the screen
	and pick them back up again
*************************************************************************/

DOMAINS
  Pixel     = p(Integer,Integer,Integer)
  PixelList = Pixel*

PREDICATES
  PutPixelDemo
  PutPixels(Pixellist,Integer,Integer,Integer,Integer)
  OutPixels(Pixellist)
  DelPIxels(Pixellist)

CLAUSES
  PutPixelDemo:-
	MainWindow("PutPixel Demonstration"),
	GetViewSettings(Left,Top,Right,Bottom,_),
	H = Bottom - Top,
	W = Right - Left,
	maxColors(MaxColors),
 	PutPixels(Points,5000,H,W,Maxcolors),
	DelPixels(Points),
	for (0,2,I),
	  OutPixels(Points),
	  I<1,
	  DelPixels(Points),
	fail.
  PutPixelDemo:-
	Pause.

  PutPixels([],0,_,_,_):- !.
  PutPixels([p(X,Y,Color)|Points],I,H,W,Maxcolors):-
	  random(W,X),
	  random(H,Y),
	  random(MaxColors,Color),
	  PutPixel(X,Y,Color),
	  I2 = I - 1,!,
	  PutPixels(Points,I2,H,W,Maxcolors).

  OutPixels([p(X,Y,Color)|Points]):-!,
  	PutPixel(X,Y,Color),
  	OutPixels(Points).
  OutPixels(_).

  DelPixels([p(X,Y,_)|Points]):- !,PutPixel(X,Y,black),DelPixels(Points).
  DelPixels(_).

/************************************************************************
     Displays a random pattern of polygons until the user says enough
*************************************************************************/

PREDICATES
  PaletteDemo
  PaletteDemo2(Integer)
  PaletteDemo3

CLAUSES
  PaletteDemo:-
	GetPalette(PaletteList),
	driver(GraphDriver,_,_),
	PaletteDemo2(GraphDriver),
	setallpalette(PaletteList).

  Palettedemo2(ega):- Palettedemo3,!.
  Palettedemo2(vga):- Palettedemo3,!.
  Palettedemo2(_).

  PaletteDemo3:-
	MainWindow("Palette Demonstration"),
	StatusLine("Press any key to continue, Esc to Abort"),
	GetViewSettings(Left,Top,Right,Bottom,_),
	Width	= (Right-Left) div 15,
	Height	= (Bottom-Top) div 10,
	maxColors(MaxColors),
	for (0,10,J),
	  Y	  = (Height+1)*J,
	  for (0,15,I),
	    Color = 1 + ((J*15 + I)*2) mod (MaxColors-2),
	    X	  = (Width+1)*I,
	    SetFillStyle(solid_Fill,Color),
	    XW	  = X + Width,
	    YH	  = Y + Height,
	    Bar(X,Y,XW,YH),
	  fail.
  PaletteDemo3:-
  	maxColors(MaxColors),MAX1=MaxColors-2,
  	Repeat,
  	  random(MAX1,R), RMax = 1 + R,
  	  random(65,R65),
  	  SetPalette(RMax,R65),
  	KeyPressed,!,Pause.

/************************************************************************
	Main GOAL
************************************************************************/

Goal	Initialize,	% Set system into graphic mode
        ReportStatus,
        ColorDemo,
        PaletteDemo,
        GraphDefaults,
        PutPixelDemo,
        ArcDemo,
        CircleDemo,
        Bar3DDemo,
        BarDemo,
        PieDemo,
        LineRelDemo,
        LineToDemo,
        LineStyleDemo,
        UserLineStyleDemo,
        TextDump,
        TextDemo,
        CRTModeDemo,
        FillStyleDemo,
        FillPatternDemo,
        PolyDemo,
        SayGoodBye,
	ToText.

