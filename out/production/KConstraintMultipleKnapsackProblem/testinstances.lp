\ This file was automatically generated by Zimpl
\ param file := "testinstances.txt";
\ param n := read file as "1n" use 1 comment "#";
\ do print n;
\ param m := read file as "1n" skip 1 use 1 comment "#";
\ do print m;
\ param k := read file as "1n" skip 2 use 1 comment "#";
\ do print k;
\ set Items := {1 to n};
\ set Knapsacks := {1 to m};
\ set Capacities := {1 to k};
\ param p[Items] := read file as "1n" skip 3 use n comment "#";
\ param w[Items*Capacities] := read file as "n+" skip 3+n use n comment "#";
\ param c[Knapsacks*Capacities] := read file as "n+" skip 3+2*n use m comment "#";
\ var x[Knapsacks * Items] binary;
\ maximize profit: sum <i> in Knapsacks: sum <j> in Items: p[j]* x[i,j];
\ subto cap: forall <i> in Knapsacks: forall <h> in Capacities: sum <j> in Items: w[j,h]*x[i,j] <= c[i,h];
\ subto one: forall <j> in Items: sum <i> in Knapsacks: x[i,j] <= 1;
\Problem name: testinstances.zpl
Maximize
 profit:  +80 x#1#1 + x#1#2 +93 x#1#3 +51 x#1#4 +13 x#1#5 +38 x#1#6
  +23 x#1#7 +79 x#1#8 +70 x#1#9 +40 x#1#10 +94 x#1#11 +18 x#1#12
  +80 x#2#1 + x#2#2 +93 x#2#3 +51 x#2#4 +13 x#2#5 +38 x#2#6
  +23 x#2#7 +79 x#2#8 +70 x#2#9 +40 x#2#10 +94 x#2#11 +18 x#2#12
 
Subject to
 cap_1:
  +67 x#1#12 +47 x#1#11 +63 x#1#10 +44 x#1#9 +77 x#1#8 +75 x#1#7
  +27 x#1#6 +96 x#1#5 +53 x#1#4 +76 x#1#3 +17 x#1#2 +57 x#1#1
  <= 198
 cap_2:
  +26 x#1#12 +45 x#1#11 +68 x#1#10 +9 x#1#9 +54 x#1#8 +26 x#1#7
  +17 x#1#6 +4 x#1#5 +89 x#1#4 +83 x#1#3 +98 x#1#2 +7 x#1#1
  <= 165
 cap_3:
  +67 x#2#12 +47 x#2#11 +63 x#2#10 +44 x#2#9 +77 x#2#8 +75 x#2#7
  +27 x#2#6 +96 x#2#5 +53 x#2#4 +76 x#2#3 +17 x#2#2 +57 x#2#1
  <= 301
 cap_4:
  +26 x#2#12 +45 x#2#11 +68 x#2#10 +9 x#2#9 +54 x#2#8 +26 x#2#7
  +17 x#2#6 +4 x#2#5 +89 x#2#4 +83 x#2#3 +98 x#2#2 +7 x#2#1
  <= 217
 one_1:
  + x#2#1 + x#1#1 <= 1
 one_2:
  + x#2#2 + x#1#2 <= 1
 one_3:
  + x#2#3 + x#1#3 <= 1
 one_4:
  + x#2#4 + x#1#4 <= 1
 one_5:
  + x#2#5 + x#1#5 <= 1
 one_6:
  + x#2#6 + x#1#6 <= 1
 one_7:
  + x#2#7 + x#1#7 <= 1
 one_8:
  + x#2#8 + x#1#8 <= 1
 one_9:
  + x#2#9 + x#1#9 <= 1
 one_10:
  + x#2#10 + x#1#10 <= 1
 one_11:
  + x#2#11 + x#1#11 <= 1
 one_12:
  + x#2#12 + x#1#12 <= 1
Bounds
 0 <= x#1#1 <= 1
 0 <= x#1#2 <= 1
 0 <= x#1#3 <= 1
 0 <= x#1#4 <= 1
 0 <= x#1#5 <= 1
 0 <= x#1#6 <= 1
 0 <= x#1#7 <= 1
 0 <= x#1#8 <= 1
 0 <= x#1#9 <= 1
 0 <= x#1#10 <= 1
 0 <= x#1#11 <= 1
 0 <= x#1#12 <= 1
 0 <= x#2#1 <= 1
 0 <= x#2#2 <= 1
 0 <= x#2#3 <= 1
 0 <= x#2#4 <= 1
 0 <= x#2#5 <= 1
 0 <= x#2#6 <= 1
 0 <= x#2#7 <= 1
 0 <= x#2#8 <= 1
 0 <= x#2#9 <= 1
 0 <= x#2#10 <= 1
 0 <= x#2#11 <= 1
 0 <= x#2#12 <= 1
General
 x#1#1
 x#1#2
 x#1#3
 x#1#4
 x#1#5
 x#1#6
 x#1#7
 x#1#8
 x#1#9
 x#1#10
 x#1#11
 x#1#12
 x#2#1
 x#2#2
 x#2#3
 x#2#4
 x#2#5
 x#2#6
 x#2#7
 x#2#8
 x#2#9
 x#2#10
 x#2#11
 x#2#12
End
