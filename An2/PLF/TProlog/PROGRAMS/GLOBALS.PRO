
GLOBAL DOMAINS
  DB_SELECTOR	= dba; logdba
  file = prn_file

/* Declarations for recordbrowser and screen handler */
  TYPE		= int(); str(); real() ; cat()
  TYPELIST	= TYPE*
  VALUE		= int(INTEGER); str(STRING); real(REAL)
  VALUELIST	= VALUE*
  IDL		= STRING* 
  FNAME		= STRING
  FNAMELIST	= FNAME*


/* Declarations for records in the database */
  dbdom	= city(CITYNO,CITYNAME);
  	  person(FIRSTNAME,LASTNAME,STREET,CITYNO,CODE)
  CITYNO, CITYNAME, FIRSTNAME, LASTNAME, STREET, CODE = STRING

  INDEXNAME = person_name; person_adr; city_no
  RELATION  = city; person


/* Domains used by tools */
include "tdoms.pro"


/* Global database used in screen handler and record browser */
GLOBAL DATABASE - globscrhnd
  /* Database declarations used in scrhnd */
  value(FNAME,STRING)		/* value of a field */
  field(FNAME,TYPE,ROW,COL,LEN) /* Screen definition */
  txtfield(ROW,COL,LEN,STRING)
  windowsize(ROW,COL).

  /* Open indexes on relations */
  indexes(INDEXNAME,BT_SELECTOR)


/* Database to load and save via consult and save */
GLOBAL DATABASE
  /* Printer definitions */
  margin(INTEGER)
  topmargin(INTEGER)
  pagelength(INTEGER)
  printer_init(STRING)
  printdest(STRING)
  charconv(STRING)  
  labels_margin(INTEGER)
  labels_initcode(STRING)
  labels_printdest(STRING)
  screen(RELATION,GLOBSCRHND)	/* Saving different screens */


GLOBAL PREDICATES
  /* Global predicates for printer control */
  determ init_printer(INTEGER,INTEGER,INTEGER,STRING,STRING,STRING)-(i,i,i,i,i,i)
  determ write_ch(CHAR) - (i)
  determ write_str(STRING) - (i)
  determ make_footer
  determ make_header
  determ close_printer
  determ check_page(STRING,INTEGER)-(i,i)
  determ shift_page()
  determ getpageno(INTEGER)-(o)

  /* Global predicates defined in screen handler */
  determ createwindow(STRING) - (i)
  determ scrhnd(STRING,KEY) - (i,o)
  determ field_action(FNAME) - (i)
  determ field_value(FNAME,STRING) - (i,o)
  determ noinput(FNAME) - (i)
  determ writescr

  /* Record browser */
  determ browse(RELATION)-(i)

include "tpreds.pro"
