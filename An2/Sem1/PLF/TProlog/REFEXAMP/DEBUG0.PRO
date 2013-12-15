%====================== debug/0               ===============================
C:\PROLOG >debug prolog.exe
-g
+++++++++ Press Run +++++++++
Goal: debug,X=2+3
AX=022D  BX=0069  CX=0125  DX=0500  SP=3ADA  BP=3AF2  SI=3E84  DI=0105
DS=552D  ES=642F  SS=5DDA  CS=27E5  IP=0014   NV UP EI PL NZ NA PO NC
27E5:0014 CB            RETF
-t
AX=022D  BX=0069  CX=0125  DX=0500  SP=3ADE  BP=3AF2  SI=3E84  DI=0105
DS=552D  ES=642F  SS=5DDA  CS=96C0  IP=012F   NV UP EI PL NZ NA PO NC
96C0:012F B80200        MOV     AX,0002
-u
96C0:012F B80200        MOV     AX,0002
96C0:0132 B90300        MOV     CX,0003
96C0:0135 03C1          ADD     AX,CX
96C0:0137 8946FE        MOV     [BP-02],AX
-g
X=5
1 Solution
Goal:
