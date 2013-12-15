%====================== fronttoken/3          ===============================
Goal: fronttoken("all boys do fine",TOK,REST)
TOK=all, REST= boys do fine
1 Solution
Goal: fronttoken("all+boys do fine",TOK,REST),fronttoken(REST,TOK1,_)
TOK=all, REST=+boys do fine, TOK1=+
1 Solution
Goal: fronttoken("22all boys do fine",TOK,REST)
TOK=22, REST=all boys do fine
1 Solution
Goal: fronttoken("22.66all boys do fine",TOK,REST)
TOK=22.66, REST=all boys do fine
1 Solution
Goal: fronttoken("-22.66all boys do fine",TOK,REST)
TOK=-, REST=22.66all boys do fine
1 Solution
Goal: fronttoken(".66all boys do fine",TOK,REST)
TOK=., REST=66all boys do fine
1 Solution
Goal:
