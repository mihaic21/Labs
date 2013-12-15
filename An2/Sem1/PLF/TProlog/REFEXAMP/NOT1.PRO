%====================== not/1                 ===============================
Goal: not(2>3)
True
Goal: not(2<3)
False
Goal: not(concat("aaa","bbb","aaa-bbb"))
True
Goal: not(not(concat("aaa","bbb","aaa-bbb")))
False
Goal: not(concat("aaa","bbb",X))
704  Free variable are not allowed in 'not' or 'retractall'.
