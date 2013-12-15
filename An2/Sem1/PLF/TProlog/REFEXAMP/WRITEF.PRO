%======================  writef/*             ===============================
goal
   writef("\nDemo of writef predicate\n"),
   A = one,
   B = 330.12,
   C = 4.3333375,
   D = "one two three",
   writef("A = '%-7' \nB = '%8.1e'\n",A,B),
   writef("A = '%' \nB = '%8.4e'\n",A,B),
   writef("C = '%-7.7g' \nD = '%7.7'\n",C,D),
   writef("C = '%-7.0f' \nD = '%0'\n",C,D),
   writef("char: %c, decimal: %d, hex: %x, unsigned: %u",97,'a',33,-1).
