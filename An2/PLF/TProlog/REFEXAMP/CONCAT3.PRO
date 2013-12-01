%====================== concat/3              ===============================
Goal: concat("aaa","bbb",X)
X=aaabbb
1 Solution
Goal: concat("aaa","bbb","aaabbb")
True
Goal: concat("aaa","bbb","aaa----bbb")
False
Goal: concat(X,"bbb","aaa----bbb")
X=aaa----
1 Solution
Goal: concat("aaa",X,"aaa----bbb")
X=----bbb
1 Solution
