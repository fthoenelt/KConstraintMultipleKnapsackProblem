\ This file was automatically generated by Zimpl
\ param file := "instances/knapsack100nr12.txt";
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
\Problem name: zimplfiles/knapsack100nr12.zpl
Maximize
 profit:  +905 x#1#1 +307 x#1#2 +894 x#1#3 +154 x#1#4 +340 x#1#5 +796 x#1#6
  +534 x#1#7 +357 x#1#8 +975 x#1#9 +485 x#1#10 +231 x#1#11 +725 x#1#12
  +528 x#1#13 +628 x#1#14 +760 x#1#15 +667 x#1#16 +579 x#1#17 +514 x#1#18
  +108 x#1#19 +218 x#1#20 +915 x#1#21 +661 x#1#22 +168 x#1#23 +747 x#1#24
  +376 x#1#25 +511 x#1#26 +944 x#1#27 +79 x#1#28 +507 x#1#29 +785 x#1#30
  +284 x#1#31 +568 x#1#32 +398 x#1#33 +181 x#1#34 +448 x#1#35 +36 x#1#36
  +799 x#1#37 +182 x#1#38 +536 x#1#39 +583 x#1#40 +908 x#1#41 +405 x#1#42
  +961 x#1#43 +364 x#1#44 +145 x#1#45 +41 x#1#46 +724 x#1#47 +80 x#1#48
  +916 x#1#49 +883 x#1#50 +966 x#1#51 +180 x#1#52 +530 x#1#53 +589 x#1#54
  +314 x#1#55 +45 x#1#56 +491 x#1#57 +534 x#1#58 +615 x#1#59 +961 x#1#60
  +305 x#1#61 +756 x#1#62 +774 x#1#63 +65 x#1#64 +887 x#1#65 +237 x#1#66
  +163 x#1#67 +117 x#1#68 +21 x#1#69 +858 x#1#70 +261 x#1#71 +912 x#1#72
  +184 x#1#73 +122 x#1#74 +648 x#1#75 +202 x#1#76 +158 x#1#77 +184 x#1#78
  +175 x#1#79 +404 x#1#80 +248 x#1#81 +345 x#1#82 +143 x#1#83 +59 x#1#84
  +897 x#1#85 +277 x#1#86 +685 x#1#87 +596 x#1#88 +251 x#1#89 +896 x#1#90
  +850 x#1#91 +241 x#1#92 +298 x#1#93 +13 x#1#94 +788 x#1#95 +662 x#1#96
  +888 x#1#97 +144 x#1#98 +77 x#1#99 +490 x#1#100 +905 x#2#1 +307 x#2#2
  +894 x#2#3 +154 x#2#4 +340 x#2#5 +796 x#2#6 +534 x#2#7 +357 x#2#8
  +975 x#2#9 +485 x#2#10 +231 x#2#11 +725 x#2#12 +528 x#2#13 +628 x#2#14
  +760 x#2#15 +667 x#2#16 +579 x#2#17 +514 x#2#18 +108 x#2#19 +218 x#2#20
  +915 x#2#21 +661 x#2#22 +168 x#2#23 +747 x#2#24 +376 x#2#25 +511 x#2#26
  +944 x#2#27 +79 x#2#28 +507 x#2#29 +785 x#2#30 +284 x#2#31 +568 x#2#32
  +398 x#2#33 +181 x#2#34 +448 x#2#35 +36 x#2#36 +799 x#2#37 +182 x#2#38
  +536 x#2#39 +583 x#2#40 +908 x#2#41 +405 x#2#42 +961 x#2#43 +364 x#2#44
  +145 x#2#45 +41 x#2#46 +724 x#2#47 +80 x#2#48 +916 x#2#49 +883 x#2#50
  +966 x#2#51 +180 x#2#52 +530 x#2#53 +589 x#2#54 +314 x#2#55 +45 x#2#56
  +491 x#2#57 +534 x#2#58 +615 x#2#59 +961 x#2#60 +305 x#2#61 +756 x#2#62
  +774 x#2#63 +65 x#2#64 +887 x#2#65 +237 x#2#66 +163 x#2#67 +117 x#2#68
  +21 x#2#69 +858 x#2#70 +261 x#2#71 +912 x#2#72 +184 x#2#73 +122 x#2#74
  +648 x#2#75 +202 x#2#76 +158 x#2#77 +184 x#2#78 +175 x#2#79 +404 x#2#80
  +248 x#2#81 +345 x#2#82 +143 x#2#83 +59 x#2#84 +897 x#2#85 +277 x#2#86
  +685 x#2#87 +596 x#2#88 +251 x#2#89 +896 x#2#90 +850 x#2#91 +241 x#2#92
  +298 x#2#93 +13 x#2#94 +788 x#2#95 +662 x#2#96 +888 x#2#97 +144 x#2#98
  +77 x#2#99 +490 x#2#100 +905 x#3#1 +307 x#3#2 +894 x#3#3 +154 x#3#4
  +340 x#3#5 +796 x#3#6 +534 x#3#7 +357 x#3#8 +975 x#3#9 +485 x#3#10
  +231 x#3#11 +725 x#3#12 +528 x#3#13 +628 x#3#14 +760 x#3#15 +667 x#3#16
  +579 x#3#17 +514 x#3#18 +108 x#3#19 +218 x#3#20 +915 x#3#21 +661 x#3#22
  +168 x#3#23 +747 x#3#24 +376 x#3#25 +511 x#3#26 +944 x#3#27 +79 x#3#28
  +507 x#3#29 +785 x#3#30 +284 x#3#31 +568 x#3#32 +398 x#3#33 +181 x#3#34
  +448 x#3#35 +36 x#3#36 +799 x#3#37 +182 x#3#38 +536 x#3#39 +583 x#3#40
  +908 x#3#41 +405 x#3#42 +961 x#3#43 +364 x#3#44 +145 x#3#45 +41 x#3#46
  +724 x#3#47 +80 x#3#48 +916 x#3#49 +883 x#3#50 +966 x#3#51 +180 x#3#52
  +530 x#3#53 +589 x#3#54 +314 x#3#55 +45 x#3#56 +491 x#3#57 +534 x#3#58
  +615 x#3#59 +961 x#3#60 +305 x#3#61 +756 x#3#62 +774 x#3#63 +65 x#3#64
  +887 x#3#65 +237 x#3#66 +163 x#3#67 +117 x#3#68 +21 x#3#69 +858 x#3#70
  +261 x#3#71 +912 x#3#72 +184 x#3#73 +122 x#3#74 +648 x#3#75 +202 x#3#76
  +158 x#3#77 +184 x#3#78 +175 x#3#79 +404 x#3#80 +248 x#3#81 +345 x#3#82
  +143 x#3#83 +59 x#3#84 +897 x#3#85 +277 x#3#86 +685 x#3#87 +596 x#3#88
  +251 x#3#89 +896 x#3#90 +850 x#3#91 +241 x#3#92 +298 x#3#93 +13 x#3#94
  +788 x#3#95 +662 x#3#96 +888 x#3#97 +144 x#3#98 +77 x#3#99 +490 x#3#100
  +905 x#4#1 +307 x#4#2 +894 x#4#3 +154 x#4#4 +340 x#4#5 +796 x#4#6
  +534 x#4#7 +357 x#4#8 +975 x#4#9 +485 x#4#10 +231 x#4#11 +725 x#4#12
  +528 x#4#13 +628 x#4#14 +760 x#4#15 +667 x#4#16 +579 x#4#17 +514 x#4#18
  +108 x#4#19 +218 x#4#20 +915 x#4#21 +661 x#4#22 +168 x#4#23 +747 x#4#24
  +376 x#4#25 +511 x#4#26 +944 x#4#27 +79 x#4#28 +507 x#4#29 +785 x#4#30
  +284 x#4#31 +568 x#4#32 +398 x#4#33 +181 x#4#34 +448 x#4#35 +36 x#4#36
  +799 x#4#37 +182 x#4#38 +536 x#4#39 +583 x#4#40 +908 x#4#41 +405 x#4#42
  +961 x#4#43 +364 x#4#44 +145 x#4#45 +41 x#4#46 +724 x#4#47 +80 x#4#48
  +916 x#4#49 +883 x#4#50 +966 x#4#51 +180 x#4#52 +530 x#4#53 +589 x#4#54
  +314 x#4#55 +45 x#4#56 +491 x#4#57 +534 x#4#58 +615 x#4#59 +961 x#4#60
  +305 x#4#61 +756 x#4#62 +774 x#4#63 +65 x#4#64 +887 x#4#65 +237 x#4#66
  +163 x#4#67 +117 x#4#68 +21 x#4#69 +858 x#4#70 +261 x#4#71 +912 x#4#72
  +184 x#4#73 +122 x#4#74 +648 x#4#75 +202 x#4#76 +158 x#4#77 +184 x#4#78
  +175 x#4#79 +404 x#4#80 +248 x#4#81 +345 x#4#82 +143 x#4#83 +59 x#4#84
  +897 x#4#85 +277 x#4#86 +685 x#4#87 +596 x#4#88 +251 x#4#89 +896 x#4#90
  +850 x#4#91 +241 x#4#92 +298 x#4#93 +13 x#4#94 +788 x#4#95 +662 x#4#96
  +888 x#4#97 +144 x#4#98 +77 x#4#99 +490 x#4#100 +905 x#5#1 +307 x#5#2
  +894 x#5#3 +154 x#5#4 +340 x#5#5 +796 x#5#6 +534 x#5#7 +357 x#5#8
  +975 x#5#9 +485 x#5#10 +231 x#5#11 +725 x#5#12 +528 x#5#13 +628 x#5#14
  +760 x#5#15 +667 x#5#16 +579 x#5#17 +514 x#5#18 +108 x#5#19 +218 x#5#20
  +915 x#5#21 +661 x#5#22 +168 x#5#23 +747 x#5#24 +376 x#5#25 +511 x#5#26
  +944 x#5#27 +79 x#5#28 +507 x#5#29 +785 x#5#30 +284 x#5#31 +568 x#5#32
  +398 x#5#33 +181 x#5#34 +448 x#5#35 +36 x#5#36 +799 x#5#37 +182 x#5#38
  +536 x#5#39 +583 x#5#40 +908 x#5#41 +405 x#5#42 +961 x#5#43 +364 x#5#44
  +145 x#5#45 +41 x#5#46 +724 x#5#47 +80 x#5#48 +916 x#5#49 +883 x#5#50
  +966 x#5#51 +180 x#5#52 +530 x#5#53 +589 x#5#54 +314 x#5#55 +45 x#5#56
  +491 x#5#57 +534 x#5#58 +615 x#5#59 +961 x#5#60 +305 x#5#61 +756 x#5#62
  +774 x#5#63 +65 x#5#64 +887 x#5#65 +237 x#5#66 +163 x#5#67 +117 x#5#68
  +21 x#5#69 +858 x#5#70 +261 x#5#71 +912 x#5#72 +184 x#5#73 +122 x#5#74
  +648 x#5#75 +202 x#5#76 +158 x#5#77 +184 x#5#78 +175 x#5#79 +404 x#5#80
  +248 x#5#81 +345 x#5#82 +143 x#5#83 +59 x#5#84 +897 x#5#85 +277 x#5#86
  +685 x#5#87 +596 x#5#88 +251 x#5#89 +896 x#5#90 +850 x#5#91 +241 x#5#92
  +298 x#5#93 +13 x#5#94 +788 x#5#95 +662 x#5#96 +888 x#5#97 +144 x#5#98
  +77 x#5#99 +490 x#5#100
Subject to
 cap_1:
  +208 x#1#100 +586 x#1#99 +934 x#1#98 +786 x#1#97 +550 x#1#96 +760 x#1#95
  +51 x#1#94 +134 x#1#93 +11 x#1#92 +956 x#1#91 +244 x#1#90 +769 x#1#89
  +589 x#1#88 +617 x#1#87 +635 x#1#86 +932 x#1#85 +864 x#1#84 +188 x#1#83
  +849 x#1#82 +869 x#1#81 +245 x#1#80 +880 x#1#79 +565 x#1#78 +953 x#1#77
  +719 x#1#76 +210 x#1#75 +330 x#1#74 +609 x#1#73 +195 x#1#72 +923 x#1#71
  +757 x#1#70 +696 x#1#69 +636 x#1#68 +677 x#1#67 +680 x#1#66 +78 x#1#65
  +361 x#1#64 +886 x#1#63 +327 x#1#62 +726 x#1#61 +444 x#1#60 +10 x#1#59
  +852 x#1#58 +242 x#1#57 +911 x#1#56 +836 x#1#55 +153 x#1#54 +392 x#1#53
  +765 x#1#52 +579 x#1#51 +546 x#1#50 +350 x#1#49 +554 x#1#48 +466 x#1#47
  +180 x#1#46 +431 x#1#45 +249 x#1#44 +842 x#1#43 +102 x#1#42 +761 x#1#41
  +648 x#1#40 +698 x#1#39 +642 x#1#38 +678 x#1#37 +530 x#1#36 +621 x#1#35
  +233 x#1#34 +575 x#1#33 +866 x#1#32 +659 x#1#31 +489 x#1#30 +760 x#1#29
  +255 x#1#28 +945 x#1#27 +524 x#1#26 +670 x#1#25 +912 x#1#24 +619 x#1#23
  +999 x#1#22 +973 x#1#21 +957 x#1#20 +113 x#1#19 +579 x#1#18 +245 x#1#17
  +39 x#1#16 +935 x#1#15 +659 x#1#14 +659 x#1#13 +134 x#1#12 +933 x#1#11
  +339 x#1#10 +77 x#1#9 +422 x#1#8 +926 x#1#7 +546 x#1#6 +615 x#1#5
  +365 x#1#4 +430 x#1#3 +876 x#1#2 +777 x#1#1 <= 4943
 cap_2:
  +536 x#1#100 +845 x#1#99 +861 x#1#98 +614 x#1#97 +624 x#1#96 +603 x#1#95
  +907 x#1#94 +938 x#1#93 +315 x#1#92 +789 x#1#91 +509 x#1#90 +752 x#1#89
  +716 x#1#88 +370 x#1#87 +310 x#1#86 +999 x#1#85 +213 x#1#84 +814 x#1#83
  +145 x#1#82 +534 x#1#81 +618 x#1#80 +958 x#1#79 +868 x#1#78 +48 x#1#77
  +586 x#1#76 +565 x#1#75 +862 x#1#74 +649 x#1#73 +278 x#1#72 +674 x#1#71
  +222 x#1#70 +168 x#1#69 +105 x#1#68 +988 x#1#67 +556 x#1#66 +177 x#1#65
  +710 x#1#64 +780 x#1#63 +596 x#1#62 +413 x#1#61 +369 x#1#60 +853 x#1#59
  +806 x#1#58 +601 x#1#57 +853 x#1#56 +539 x#1#55 +898 x#1#54 +528 x#1#53
  +411 x#1#52 +389 x#1#51 +176 x#1#50 +398 x#1#49 +668 x#1#48 +696 x#1#47
  +125 x#1#46 +587 x#1#45 +645 x#1#44 +274 x#1#43 +73 x#1#42 +880 x#1#41
  +776 x#1#40 +767 x#1#39 +549 x#1#38 +345 x#1#37 +11 x#1#36 +788 x#1#35
  +600 x#1#34 +855 x#1#33 +601 x#1#32 +639 x#1#31 +802 x#1#30 +825 x#1#29
  +645 x#1#28 +815 x#1#27 +169 x#1#26 +527 x#1#25 +78 x#1#24 +125 x#1#23
  +232 x#1#22 +643 x#1#21 +486 x#1#20 +375 x#1#19 +791 x#1#18 +268 x#1#17
  +179 x#1#16 +909 x#1#15 +484 x#1#14 +593 x#1#13 +608 x#1#12 +866 x#1#11
  +552 x#1#10 +31 x#1#9 +573 x#1#8 +81 x#1#7 +54 x#1#6 +446 x#1#5
  +706 x#1#4 +706 x#1#3 +470 x#1#2 +62 x#1#1 <= 5718
 cap_3:
  +208 x#2#100 +586 x#2#99 +934 x#2#98 +786 x#2#97 +550 x#2#96 +760 x#2#95
  +51 x#2#94 +134 x#2#93 +11 x#2#92 +956 x#2#91 +244 x#2#90 +769 x#2#89
  +589 x#2#88 +617 x#2#87 +635 x#2#86 +932 x#2#85 +864 x#2#84 +188 x#2#83
  +849 x#2#82 +869 x#2#81 +245 x#2#80 +880 x#2#79 +565 x#2#78 +953 x#2#77
  +719 x#2#76 +210 x#2#75 +330 x#2#74 +609 x#2#73 +195 x#2#72 +923 x#2#71
  +757 x#2#70 +696 x#2#69 +636 x#2#68 +677 x#2#67 +680 x#2#66 +78 x#2#65
  +361 x#2#64 +886 x#2#63 +327 x#2#62 +726 x#2#61 +444 x#2#60 +10 x#2#59
  +852 x#2#58 +242 x#2#57 +911 x#2#56 +836 x#2#55 +153 x#2#54 +392 x#2#53
  +765 x#2#52 +579 x#2#51 +546 x#2#50 +350 x#2#49 +554 x#2#48 +466 x#2#47
  +180 x#2#46 +431 x#2#45 +249 x#2#44 +842 x#2#43 +102 x#2#42 +761 x#2#41
  +648 x#2#40 +698 x#2#39 +642 x#2#38 +678 x#2#37 +530 x#2#36 +621 x#2#35
  +233 x#2#34 +575 x#2#33 +866 x#2#32 +659 x#2#31 +489 x#2#30 +760 x#2#29
  +255 x#2#28 +945 x#2#27 +524 x#2#26 +670 x#2#25 +912 x#2#24 +619 x#2#23
  +999 x#2#22 +973 x#2#21 +957 x#2#20 +113 x#2#19 +579 x#2#18 +245 x#2#17
  +39 x#2#16 +935 x#2#15 +659 x#2#14 +659 x#2#13 +134 x#2#12 +933 x#2#11
  +339 x#2#10 +77 x#2#9 +422 x#2#8 +926 x#2#7 +546 x#2#6 +615 x#2#5
  +365 x#2#4 +430 x#2#3 +876 x#2#2 +777 x#2#1 <= 5717
 cap_4:
  +536 x#2#100 +845 x#2#99 +861 x#2#98 +614 x#2#97 +624 x#2#96 +603 x#2#95
  +907 x#2#94 +938 x#2#93 +315 x#2#92 +789 x#2#91 +509 x#2#90 +752 x#2#89
  +716 x#2#88 +370 x#2#87 +310 x#2#86 +999 x#2#85 +213 x#2#84 +814 x#2#83
  +145 x#2#82 +534 x#2#81 +618 x#2#80 +958 x#2#79 +868 x#2#78 +48 x#2#77
  +586 x#2#76 +565 x#2#75 +862 x#2#74 +649 x#2#73 +278 x#2#72 +674 x#2#71
  +222 x#2#70 +168 x#2#69 +105 x#2#68 +988 x#2#67 +556 x#2#66 +177 x#2#65
  +710 x#2#64 +780 x#2#63 +596 x#2#62 +413 x#2#61 +369 x#2#60 +853 x#2#59
  +806 x#2#58 +601 x#2#57 +853 x#2#56 +539 x#2#55 +898 x#2#54 +528 x#2#53
  +411 x#2#52 +389 x#2#51 +176 x#2#50 +398 x#2#49 +668 x#2#48 +696 x#2#47
  +125 x#2#46 +587 x#2#45 +645 x#2#44 +274 x#2#43 +73 x#2#42 +880 x#2#41
  +776 x#2#40 +767 x#2#39 +549 x#2#38 +345 x#2#37 +11 x#2#36 +788 x#2#35
  +600 x#2#34 +855 x#2#33 +601 x#2#32 +639 x#2#31 +802 x#2#30 +825 x#2#29
  +645 x#2#28 +815 x#2#27 +169 x#2#26 +527 x#2#25 +78 x#2#24 +125 x#2#23
  +232 x#2#22 +643 x#2#21 +486 x#2#20 +375 x#2#19 +791 x#2#18 +268 x#2#17
  +179 x#2#16 +909 x#2#15 +484 x#2#14 +593 x#2#13 +608 x#2#12 +866 x#2#11
  +552 x#2#10 +31 x#2#9 +573 x#2#8 +81 x#2#7 +54 x#2#6 +446 x#2#5
  +706 x#2#4 +706 x#2#3 +470 x#2#2 +62 x#2#1 <= 5278
 cap_5:
  +208 x#3#100 +586 x#3#99 +934 x#3#98 +786 x#3#97 +550 x#3#96 +760 x#3#95
  +51 x#3#94 +134 x#3#93 +11 x#3#92 +956 x#3#91 +244 x#3#90 +769 x#3#89
  +589 x#3#88 +617 x#3#87 +635 x#3#86 +932 x#3#85 +864 x#3#84 +188 x#3#83
  +849 x#3#82 +869 x#3#81 +245 x#3#80 +880 x#3#79 +565 x#3#78 +953 x#3#77
  +719 x#3#76 +210 x#3#75 +330 x#3#74 +609 x#3#73 +195 x#3#72 +923 x#3#71
  +757 x#3#70 +696 x#3#69 +636 x#3#68 +677 x#3#67 +680 x#3#66 +78 x#3#65
  +361 x#3#64 +886 x#3#63 +327 x#3#62 +726 x#3#61 +444 x#3#60 +10 x#3#59
  +852 x#3#58 +242 x#3#57 +911 x#3#56 +836 x#3#55 +153 x#3#54 +392 x#3#53
  +765 x#3#52 +579 x#3#51 +546 x#3#50 +350 x#3#49 +554 x#3#48 +466 x#3#47
  +180 x#3#46 +431 x#3#45 +249 x#3#44 +842 x#3#43 +102 x#3#42 +761 x#3#41
  +648 x#3#40 +698 x#3#39 +642 x#3#38 +678 x#3#37 +530 x#3#36 +621 x#3#35
  +233 x#3#34 +575 x#3#33 +866 x#3#32 +659 x#3#31 +489 x#3#30 +760 x#3#29
  +255 x#3#28 +945 x#3#27 +524 x#3#26 +670 x#3#25 +912 x#3#24 +619 x#3#23
  +999 x#3#22 +973 x#3#21 +957 x#3#20 +113 x#3#19 +579 x#3#18 +245 x#3#17
  +39 x#3#16 +935 x#3#15 +659 x#3#14 +659 x#3#13 +134 x#3#12 +933 x#3#11
  +339 x#3#10 +77 x#3#9 +422 x#3#8 +926 x#3#7 +546 x#3#6 +615 x#3#5
  +365 x#3#4 +430 x#3#3 +876 x#3#2 +777 x#3#1 <= 5246
 cap_6:
  +536 x#3#100 +845 x#3#99 +861 x#3#98 +614 x#3#97 +624 x#3#96 +603 x#3#95
  +907 x#3#94 +938 x#3#93 +315 x#3#92 +789 x#3#91 +509 x#3#90 +752 x#3#89
  +716 x#3#88 +370 x#3#87 +310 x#3#86 +999 x#3#85 +213 x#3#84 +814 x#3#83
  +145 x#3#82 +534 x#3#81 +618 x#3#80 +958 x#3#79 +868 x#3#78 +48 x#3#77
  +586 x#3#76 +565 x#3#75 +862 x#3#74 +649 x#3#73 +278 x#3#72 +674 x#3#71
  +222 x#3#70 +168 x#3#69 +105 x#3#68 +988 x#3#67 +556 x#3#66 +177 x#3#65
  +710 x#3#64 +780 x#3#63 +596 x#3#62 +413 x#3#61 +369 x#3#60 +853 x#3#59
  +806 x#3#58 +601 x#3#57 +853 x#3#56 +539 x#3#55 +898 x#3#54 +528 x#3#53
  +411 x#3#52 +389 x#3#51 +176 x#3#50 +398 x#3#49 +668 x#3#48 +696 x#3#47
  +125 x#3#46 +587 x#3#45 +645 x#3#44 +274 x#3#43 +73 x#3#42 +880 x#3#41
  +776 x#3#40 +767 x#3#39 +549 x#3#38 +345 x#3#37 +11 x#3#36 +788 x#3#35
  +600 x#3#34 +855 x#3#33 +601 x#3#32 +639 x#3#31 +802 x#3#30 +825 x#3#29
  +645 x#3#28 +815 x#3#27 +169 x#3#26 +527 x#3#25 +78 x#3#24 +125 x#3#23
  +232 x#3#22 +643 x#3#21 +486 x#3#20 +375 x#3#19 +791 x#3#18 +268 x#3#17
  +179 x#3#16 +909 x#3#15 +484 x#3#14 +593 x#3#13 +608 x#3#12 +866 x#3#11
  +552 x#3#10 +31 x#3#9 +573 x#3#8 +81 x#3#7 +54 x#3#6 +446 x#3#5
  +706 x#3#4 +706 x#3#3 +470 x#3#2 +62 x#3#1 <= 6253
 cap_7:
  +208 x#4#100 +586 x#4#99 +934 x#4#98 +786 x#4#97 +550 x#4#96 +760 x#4#95
  +51 x#4#94 +134 x#4#93 +11 x#4#92 +956 x#4#91 +244 x#4#90 +769 x#4#89
  +589 x#4#88 +617 x#4#87 +635 x#4#86 +932 x#4#85 +864 x#4#84 +188 x#4#83
  +849 x#4#82 +869 x#4#81 +245 x#4#80 +880 x#4#79 +565 x#4#78 +953 x#4#77
  +719 x#4#76 +210 x#4#75 +330 x#4#74 +609 x#4#73 +195 x#4#72 +923 x#4#71
  +757 x#4#70 +696 x#4#69 +636 x#4#68 +677 x#4#67 +680 x#4#66 +78 x#4#65
  +361 x#4#64 +886 x#4#63 +327 x#4#62 +726 x#4#61 +444 x#4#60 +10 x#4#59
  +852 x#4#58 +242 x#4#57 +911 x#4#56 +836 x#4#55 +153 x#4#54 +392 x#4#53
  +765 x#4#52 +579 x#4#51 +546 x#4#50 +350 x#4#49 +554 x#4#48 +466 x#4#47
  +180 x#4#46 +431 x#4#45 +249 x#4#44 +842 x#4#43 +102 x#4#42 +761 x#4#41
  +648 x#4#40 +698 x#4#39 +642 x#4#38 +678 x#4#37 +530 x#4#36 +621 x#4#35
  +233 x#4#34 +575 x#4#33 +866 x#4#32 +659 x#4#31 +489 x#4#30 +760 x#4#29
  +255 x#4#28 +945 x#4#27 +524 x#4#26 +670 x#4#25 +912 x#4#24 +619 x#4#23
  +999 x#4#22 +973 x#4#21 +957 x#4#20 +113 x#4#19 +579 x#4#18 +245 x#4#17
  +39 x#4#16 +935 x#4#15 +659 x#4#14 +659 x#4#13 +134 x#4#12 +933 x#4#11
  +339 x#4#10 +77 x#4#9 +422 x#4#8 +926 x#4#7 +546 x#4#6 +615 x#4#5
  +365 x#4#4 +430 x#4#3 +876 x#4#2 +777 x#4#1 <= 4885
 cap_8:
  +536 x#4#100 +845 x#4#99 +861 x#4#98 +614 x#4#97 +624 x#4#96 +603 x#4#95
  +907 x#4#94 +938 x#4#93 +315 x#4#92 +789 x#4#91 +509 x#4#90 +752 x#4#89
  +716 x#4#88 +370 x#4#87 +310 x#4#86 +999 x#4#85 +213 x#4#84 +814 x#4#83
  +145 x#4#82 +534 x#4#81 +618 x#4#80 +958 x#4#79 +868 x#4#78 +48 x#4#77
  +586 x#4#76 +565 x#4#75 +862 x#4#74 +649 x#4#73 +278 x#4#72 +674 x#4#71
  +222 x#4#70 +168 x#4#69 +105 x#4#68 +988 x#4#67 +556 x#4#66 +177 x#4#65
  +710 x#4#64 +780 x#4#63 +596 x#4#62 +413 x#4#61 +369 x#4#60 +853 x#4#59
  +806 x#4#58 +601 x#4#57 +853 x#4#56 +539 x#4#55 +898 x#4#54 +528 x#4#53
  +411 x#4#52 +389 x#4#51 +176 x#4#50 +398 x#4#49 +668 x#4#48 +696 x#4#47
  +125 x#4#46 +587 x#4#45 +645 x#4#44 +274 x#4#43 +73 x#4#42 +880 x#4#41
  +776 x#4#40 +767 x#4#39 +549 x#4#38 +345 x#4#37 +11 x#4#36 +788 x#4#35
  +600 x#4#34 +855 x#4#33 +601 x#4#32 +639 x#4#31 +802 x#4#30 +825 x#4#29
  +645 x#4#28 +815 x#4#27 +169 x#4#26 +527 x#4#25 +78 x#4#24 +125 x#4#23
  +232 x#4#22 +643 x#4#21 +486 x#4#20 +375 x#4#19 +791 x#4#18 +268 x#4#17
  +179 x#4#16 +909 x#4#15 +484 x#4#14 +593 x#4#13 +608 x#4#12 +866 x#4#11
  +552 x#4#10 +31 x#4#9 +573 x#4#8 +81 x#4#7 +54 x#4#6 +446 x#4#5
  +706 x#4#4 +706 x#4#3 +470 x#4#2 +62 x#4#1 <= 6286
 cap_9:
  +208 x#5#100 +586 x#5#99 +934 x#5#98 +786 x#5#97 +550 x#5#96 +760 x#5#95
  +51 x#5#94 +134 x#5#93 +11 x#5#92 +956 x#5#91 +244 x#5#90 +769 x#5#89
  +589 x#5#88 +617 x#5#87 +635 x#5#86 +932 x#5#85 +864 x#5#84 +188 x#5#83
  +849 x#5#82 +869 x#5#81 +245 x#5#80 +880 x#5#79 +565 x#5#78 +953 x#5#77
  +719 x#5#76 +210 x#5#75 +330 x#5#74 +609 x#5#73 +195 x#5#72 +923 x#5#71
  +757 x#5#70 +696 x#5#69 +636 x#5#68 +677 x#5#67 +680 x#5#66 +78 x#5#65
  +361 x#5#64 +886 x#5#63 +327 x#5#62 +726 x#5#61 +444 x#5#60 +10 x#5#59
  +852 x#5#58 +242 x#5#57 +911 x#5#56 +836 x#5#55 +153 x#5#54 +392 x#5#53
  +765 x#5#52 +579 x#5#51 +546 x#5#50 +350 x#5#49 +554 x#5#48 +466 x#5#47
  +180 x#5#46 +431 x#5#45 +249 x#5#44 +842 x#5#43 +102 x#5#42 +761 x#5#41
  +648 x#5#40 +698 x#5#39 +642 x#5#38 +678 x#5#37 +530 x#5#36 +621 x#5#35
  +233 x#5#34 +575 x#5#33 +866 x#5#32 +659 x#5#31 +489 x#5#30 +760 x#5#29
  +255 x#5#28 +945 x#5#27 +524 x#5#26 +670 x#5#25 +912 x#5#24 +619 x#5#23
  +999 x#5#22 +973 x#5#21 +957 x#5#20 +113 x#5#19 +579 x#5#18 +245 x#5#17
  +39 x#5#16 +935 x#5#15 +659 x#5#14 +659 x#5#13 +134 x#5#12 +933 x#5#11
  +339 x#5#10 +77 x#5#9 +422 x#5#8 +926 x#5#7 +546 x#5#6 +615 x#5#5
  +365 x#5#4 +430 x#5#3 +876 x#5#2 +777 x#5#1 <= 17891
 cap_10:
  +536 x#5#100 +845 x#5#99 +861 x#5#98 +614 x#5#97 +624 x#5#96 +603 x#5#95
  +907 x#5#94 +938 x#5#93 +315 x#5#92 +789 x#5#91 +509 x#5#90 +752 x#5#89
  +716 x#5#88 +370 x#5#87 +310 x#5#86 +999 x#5#85 +213 x#5#84 +814 x#5#83
  +145 x#5#82 +534 x#5#81 +618 x#5#80 +958 x#5#79 +868 x#5#78 +48 x#5#77
  +586 x#5#76 +565 x#5#75 +862 x#5#74 +649 x#5#73 +278 x#5#72 +674 x#5#71
  +222 x#5#70 +168 x#5#69 +105 x#5#68 +988 x#5#67 +556 x#5#66 +177 x#5#65
  +710 x#5#64 +780 x#5#63 +596 x#5#62 +413 x#5#61 +369 x#5#60 +853 x#5#59
  +806 x#5#58 +601 x#5#57 +853 x#5#56 +539 x#5#55 +898 x#5#54 +528 x#5#53
  +411 x#5#52 +389 x#5#51 +176 x#5#50 +398 x#5#49 +668 x#5#48 +696 x#5#47
  +125 x#5#46 +587 x#5#45 +645 x#5#44 +274 x#5#43 +73 x#5#42 +880 x#5#41
  +776 x#5#40 +767 x#5#39 +549 x#5#38 +345 x#5#37 +11 x#5#36 +788 x#5#35
  +600 x#5#34 +855 x#5#33 +601 x#5#32 +639 x#5#31 +802 x#5#30 +825 x#5#29
  +645 x#5#28 +815 x#5#27 +169 x#5#26 +527 x#5#25 +78 x#5#24 +125 x#5#23
  +232 x#5#22 +643 x#5#21 +486 x#5#20 +375 x#5#19 +791 x#5#18 +268 x#5#17
  +179 x#5#16 +909 x#5#15 +484 x#5#14 +593 x#5#13 +608 x#5#12 +866 x#5#11
  +552 x#5#10 +31 x#5#9 +573 x#5#8 +81 x#5#7 +54 x#5#6 +446 x#5#5
  +706 x#5#4 +706 x#5#3 +470 x#5#2 +62 x#5#1 <= 15241
 one_1:
  + x#5#1 + x#4#1 + x#3#1 + x#2#1 + x#1#1 <= 1
 one_2:
  + x#5#2 + x#4#2 + x#3#2 + x#2#2 + x#1#2 <= 1
 one_3:
  + x#5#3 + x#4#3 + x#3#3 + x#2#3 + x#1#3 <= 1
 one_4:
  + x#5#4 + x#4#4 + x#3#4 + x#2#4 + x#1#4 <= 1
 one_5:
  + x#5#5 + x#4#5 + x#3#5 + x#2#5 + x#1#5 <= 1
 one_6:
  + x#5#6 + x#4#6 + x#3#6 + x#2#6 + x#1#6 <= 1
 one_7:
  + x#5#7 + x#4#7 + x#3#7 + x#2#7 + x#1#7 <= 1
 one_8:
  + x#5#8 + x#4#8 + x#3#8 + x#2#8 + x#1#8 <= 1
 one_9:
  + x#5#9 + x#4#9 + x#3#9 + x#2#9 + x#1#9 <= 1
 one_10:
  + x#5#10 + x#4#10 + x#3#10 + x#2#10 + x#1#10 <= 1
 one_11:
  + x#5#11 + x#4#11 + x#3#11 + x#2#11 + x#1#11 <= 1
 one_12:
  + x#5#12 + x#4#12 + x#3#12 + x#2#12 + x#1#12 <= 1
 one_13:
  + x#5#13 + x#4#13 + x#3#13 + x#2#13 + x#1#13 <= 1
 one_14:
  + x#5#14 + x#4#14 + x#3#14 + x#2#14 + x#1#14 <= 1
 one_15:
  + x#5#15 + x#4#15 + x#3#15 + x#2#15 + x#1#15 <= 1
 one_16:
  + x#5#16 + x#4#16 + x#3#16 + x#2#16 + x#1#16 <= 1
 one_17:
  + x#5#17 + x#4#17 + x#3#17 + x#2#17 + x#1#17 <= 1
 one_18:
  + x#5#18 + x#4#18 + x#3#18 + x#2#18 + x#1#18 <= 1
 one_19:
  + x#5#19 + x#4#19 + x#3#19 + x#2#19 + x#1#19 <= 1
 one_20:
  + x#5#20 + x#4#20 + x#3#20 + x#2#20 + x#1#20 <= 1
 one_21:
  + x#5#21 + x#4#21 + x#3#21 + x#2#21 + x#1#21 <= 1
 one_22:
  + x#5#22 + x#4#22 + x#3#22 + x#2#22 + x#1#22 <= 1
 one_23:
  + x#5#23 + x#4#23 + x#3#23 + x#2#23 + x#1#23 <= 1
 one_24:
  + x#5#24 + x#4#24 + x#3#24 + x#2#24 + x#1#24 <= 1
 one_25:
  + x#5#25 + x#4#25 + x#3#25 + x#2#25 + x#1#25 <= 1
 one_26:
  + x#5#26 + x#4#26 + x#3#26 + x#2#26 + x#1#26 <= 1
 one_27:
  + x#5#27 + x#4#27 + x#3#27 + x#2#27 + x#1#27 <= 1
 one_28:
  + x#5#28 + x#4#28 + x#3#28 + x#2#28 + x#1#28 <= 1
 one_29:
  + x#5#29 + x#4#29 + x#3#29 + x#2#29 + x#1#29 <= 1
 one_30:
  + x#5#30 + x#4#30 + x#3#30 + x#2#30 + x#1#30 <= 1
 one_31:
  + x#5#31 + x#4#31 + x#3#31 + x#2#31 + x#1#31 <= 1
 one_32:
  + x#5#32 + x#4#32 + x#3#32 + x#2#32 + x#1#32 <= 1
 one_33:
  + x#5#33 + x#4#33 + x#3#33 + x#2#33 + x#1#33 <= 1
 one_34:
  + x#5#34 + x#4#34 + x#3#34 + x#2#34 + x#1#34 <= 1
 one_35:
  + x#5#35 + x#4#35 + x#3#35 + x#2#35 + x#1#35 <= 1
 one_36:
  + x#5#36 + x#4#36 + x#3#36 + x#2#36 + x#1#36 <= 1
 one_37:
  + x#5#37 + x#4#37 + x#3#37 + x#2#37 + x#1#37 <= 1
 one_38:
  + x#5#38 + x#4#38 + x#3#38 + x#2#38 + x#1#38 <= 1
 one_39:
  + x#5#39 + x#4#39 + x#3#39 + x#2#39 + x#1#39 <= 1
 one_40:
  + x#5#40 + x#4#40 + x#3#40 + x#2#40 + x#1#40 <= 1
 one_41:
  + x#5#41 + x#4#41 + x#3#41 + x#2#41 + x#1#41 <= 1
 one_42:
  + x#5#42 + x#4#42 + x#3#42 + x#2#42 + x#1#42 <= 1
 one_43:
  + x#5#43 + x#4#43 + x#3#43 + x#2#43 + x#1#43 <= 1
 one_44:
  + x#5#44 + x#4#44 + x#3#44 + x#2#44 + x#1#44 <= 1
 one_45:
  + x#5#45 + x#4#45 + x#3#45 + x#2#45 + x#1#45 <= 1
 one_46:
  + x#5#46 + x#4#46 + x#3#46 + x#2#46 + x#1#46 <= 1
 one_47:
  + x#5#47 + x#4#47 + x#3#47 + x#2#47 + x#1#47 <= 1
 one_48:
  + x#5#48 + x#4#48 + x#3#48 + x#2#48 + x#1#48 <= 1
 one_49:
  + x#5#49 + x#4#49 + x#3#49 + x#2#49 + x#1#49 <= 1
 one_50:
  + x#5#50 + x#4#50 + x#3#50 + x#2#50 + x#1#50 <= 1
 one_51:
  + x#5#51 + x#4#51 + x#3#51 + x#2#51 + x#1#51 <= 1
 one_52:
  + x#5#52 + x#4#52 + x#3#52 + x#2#52 + x#1#52 <= 1
 one_53:
  + x#5#53 + x#4#53 + x#3#53 + x#2#53 + x#1#53 <= 1
 one_54:
  + x#5#54 + x#4#54 + x#3#54 + x#2#54 + x#1#54 <= 1
 one_55:
  + x#5#55 + x#4#55 + x#3#55 + x#2#55 + x#1#55 <= 1
 one_56:
  + x#5#56 + x#4#56 + x#3#56 + x#2#56 + x#1#56 <= 1
 one_57:
  + x#5#57 + x#4#57 + x#3#57 + x#2#57 + x#1#57 <= 1
 one_58:
  + x#5#58 + x#4#58 + x#3#58 + x#2#58 + x#1#58 <= 1
 one_59:
  + x#5#59 + x#4#59 + x#3#59 + x#2#59 + x#1#59 <= 1
 one_60:
  + x#5#60 + x#4#60 + x#3#60 + x#2#60 + x#1#60 <= 1
 one_61:
  + x#5#61 + x#4#61 + x#3#61 + x#2#61 + x#1#61 <= 1
 one_62:
  + x#5#62 + x#4#62 + x#3#62 + x#2#62 + x#1#62 <= 1
 one_63:
  + x#5#63 + x#4#63 + x#3#63 + x#2#63 + x#1#63 <= 1
 one_64:
  + x#5#64 + x#4#64 + x#3#64 + x#2#64 + x#1#64 <= 1
 one_65:
  + x#5#65 + x#4#65 + x#3#65 + x#2#65 + x#1#65 <= 1
 one_66:
  + x#5#66 + x#4#66 + x#3#66 + x#2#66 + x#1#66 <= 1
 one_67:
  + x#5#67 + x#4#67 + x#3#67 + x#2#67 + x#1#67 <= 1
 one_68:
  + x#5#68 + x#4#68 + x#3#68 + x#2#68 + x#1#68 <= 1
 one_69:
  + x#5#69 + x#4#69 + x#3#69 + x#2#69 + x#1#69 <= 1
 one_70:
  + x#5#70 + x#4#70 + x#3#70 + x#2#70 + x#1#70 <= 1
 one_71:
  + x#5#71 + x#4#71 + x#3#71 + x#2#71 + x#1#71 <= 1
 one_72:
  + x#5#72 + x#4#72 + x#3#72 + x#2#72 + x#1#72 <= 1
 one_73:
  + x#5#73 + x#4#73 + x#3#73 + x#2#73 + x#1#73 <= 1
 one_74:
  + x#5#74 + x#4#74 + x#3#74 + x#2#74 + x#1#74 <= 1
 one_75:
  + x#5#75 + x#4#75 + x#3#75 + x#2#75 + x#1#75 <= 1
 one_76:
  + x#5#76 + x#4#76 + x#3#76 + x#2#76 + x#1#76 <= 1
 one_77:
  + x#5#77 + x#4#77 + x#3#77 + x#2#77 + x#1#77 <= 1
 one_78:
  + x#5#78 + x#4#78 + x#3#78 + x#2#78 + x#1#78 <= 1
 one_79:
  + x#5#79 + x#4#79 + x#3#79 + x#2#79 + x#1#79 <= 1
 one_80:
  + x#5#80 + x#4#80 + x#3#80 + x#2#80 + x#1#80 <= 1
 one_81:
  + x#5#81 + x#4#81 + x#3#81 + x#2#81 + x#1#81 <= 1
 one_82:
  + x#5#82 + x#4#82 + x#3#82 + x#2#82 + x#1#82 <= 1
 one_83:
  + x#5#83 + x#4#83 + x#3#83 + x#2#83 + x#1#83 <= 1
 one_84:
  + x#5#84 + x#4#84 + x#3#84 + x#2#84 + x#1#84 <= 1
 one_85:
  + x#5#85 + x#4#85 + x#3#85 + x#2#85 + x#1#85 <= 1
 one_86:
  + x#5#86 + x#4#86 + x#3#86 + x#2#86 + x#1#86 <= 1
 one_87:
  + x#5#87 + x#4#87 + x#3#87 + x#2#87 + x#1#87 <= 1
 one_88:
  + x#5#88 + x#4#88 + x#3#88 + x#2#88 + x#1#88 <= 1
 one_89:
  + x#5#89 + x#4#89 + x#3#89 + x#2#89 + x#1#89 <= 1
 one_90:
  + x#5#90 + x#4#90 + x#3#90 + x#2#90 + x#1#90 <= 1
 one_91:
  + x#5#91 + x#4#91 + x#3#91 + x#2#91 + x#1#91 <= 1
 one_92:
  + x#5#92 + x#4#92 + x#3#92 + x#2#92 + x#1#92 <= 1
 one_93:
  + x#5#93 + x#4#93 + x#3#93 + x#2#93 + x#1#93 <= 1
 one_94:
  + x#5#94 + x#4#94 + x#3#94 + x#2#94 + x#1#94 <= 1
 one_95:
  + x#5#95 + x#4#95 + x#3#95 + x#2#95 + x#1#95 <= 1
 one_96:
  + x#5#96 + x#4#96 + x#3#96 + x#2#96 + x#1#96 <= 1
 one_97:
  + x#5#97 + x#4#97 + x#3#97 + x#2#97 + x#1#97 <= 1
 one_98:
  + x#5#98 + x#4#98 + x#3#98 + x#2#98 + x#1#98 <= 1
 one_99:
  + x#5#99 + x#4#99 + x#3#99 + x#2#99 + x#1#99 <= 1
 one_100:
  + x#5#100 + x#4#100 + x#3#100 + x#2#100 + x#1#100 <= 1
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
 0 <= x#1#13 <= 1
 0 <= x#1#14 <= 1
 0 <= x#1#15 <= 1
 0 <= x#1#16 <= 1
 0 <= x#1#17 <= 1
 0 <= x#1#18 <= 1
 0 <= x#1#19 <= 1
 0 <= x#1#20 <= 1
 0 <= x#1#21 <= 1
 0 <= x#1#22 <= 1
 0 <= x#1#23 <= 1
 0 <= x#1#24 <= 1
 0 <= x#1#25 <= 1
 0 <= x#1#26 <= 1
 0 <= x#1#27 <= 1
 0 <= x#1#28 <= 1
 0 <= x#1#29 <= 1
 0 <= x#1#30 <= 1
 0 <= x#1#31 <= 1
 0 <= x#1#32 <= 1
 0 <= x#1#33 <= 1
 0 <= x#1#34 <= 1
 0 <= x#1#35 <= 1
 0 <= x#1#36 <= 1
 0 <= x#1#37 <= 1
 0 <= x#1#38 <= 1
 0 <= x#1#39 <= 1
 0 <= x#1#40 <= 1
 0 <= x#1#41 <= 1
 0 <= x#1#42 <= 1
 0 <= x#1#43 <= 1
 0 <= x#1#44 <= 1
 0 <= x#1#45 <= 1
 0 <= x#1#46 <= 1
 0 <= x#1#47 <= 1
 0 <= x#1#48 <= 1
 0 <= x#1#49 <= 1
 0 <= x#1#50 <= 1
 0 <= x#1#51 <= 1
 0 <= x#1#52 <= 1
 0 <= x#1#53 <= 1
 0 <= x#1#54 <= 1
 0 <= x#1#55 <= 1
 0 <= x#1#56 <= 1
 0 <= x#1#57 <= 1
 0 <= x#1#58 <= 1
 0 <= x#1#59 <= 1
 0 <= x#1#60 <= 1
 0 <= x#1#61 <= 1
 0 <= x#1#62 <= 1
 0 <= x#1#63 <= 1
 0 <= x#1#64 <= 1
 0 <= x#1#65 <= 1
 0 <= x#1#66 <= 1
 0 <= x#1#67 <= 1
 0 <= x#1#68 <= 1
 0 <= x#1#69 <= 1
 0 <= x#1#70 <= 1
 0 <= x#1#71 <= 1
 0 <= x#1#72 <= 1
 0 <= x#1#73 <= 1
 0 <= x#1#74 <= 1
 0 <= x#1#75 <= 1
 0 <= x#1#76 <= 1
 0 <= x#1#77 <= 1
 0 <= x#1#78 <= 1
 0 <= x#1#79 <= 1
 0 <= x#1#80 <= 1
 0 <= x#1#81 <= 1
 0 <= x#1#82 <= 1
 0 <= x#1#83 <= 1
 0 <= x#1#84 <= 1
 0 <= x#1#85 <= 1
 0 <= x#1#86 <= 1
 0 <= x#1#87 <= 1
 0 <= x#1#88 <= 1
 0 <= x#1#89 <= 1
 0 <= x#1#90 <= 1
 0 <= x#1#91 <= 1
 0 <= x#1#92 <= 1
 0 <= x#1#93 <= 1
 0 <= x#1#94 <= 1
 0 <= x#1#95 <= 1
 0 <= x#1#96 <= 1
 0 <= x#1#97 <= 1
 0 <= x#1#98 <= 1
 0 <= x#1#99 <= 1
 0 <= x#1#100 <= 1
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
 0 <= x#2#13 <= 1
 0 <= x#2#14 <= 1
 0 <= x#2#15 <= 1
 0 <= x#2#16 <= 1
 0 <= x#2#17 <= 1
 0 <= x#2#18 <= 1
 0 <= x#2#19 <= 1
 0 <= x#2#20 <= 1
 0 <= x#2#21 <= 1
 0 <= x#2#22 <= 1
 0 <= x#2#23 <= 1
 0 <= x#2#24 <= 1
 0 <= x#2#25 <= 1
 0 <= x#2#26 <= 1
 0 <= x#2#27 <= 1
 0 <= x#2#28 <= 1
 0 <= x#2#29 <= 1
 0 <= x#2#30 <= 1
 0 <= x#2#31 <= 1
 0 <= x#2#32 <= 1
 0 <= x#2#33 <= 1
 0 <= x#2#34 <= 1
 0 <= x#2#35 <= 1
 0 <= x#2#36 <= 1
 0 <= x#2#37 <= 1
 0 <= x#2#38 <= 1
 0 <= x#2#39 <= 1
 0 <= x#2#40 <= 1
 0 <= x#2#41 <= 1
 0 <= x#2#42 <= 1
 0 <= x#2#43 <= 1
 0 <= x#2#44 <= 1
 0 <= x#2#45 <= 1
 0 <= x#2#46 <= 1
 0 <= x#2#47 <= 1
 0 <= x#2#48 <= 1
 0 <= x#2#49 <= 1
 0 <= x#2#50 <= 1
 0 <= x#2#51 <= 1
 0 <= x#2#52 <= 1
 0 <= x#2#53 <= 1
 0 <= x#2#54 <= 1
 0 <= x#2#55 <= 1
 0 <= x#2#56 <= 1
 0 <= x#2#57 <= 1
 0 <= x#2#58 <= 1
 0 <= x#2#59 <= 1
 0 <= x#2#60 <= 1
 0 <= x#2#61 <= 1
 0 <= x#2#62 <= 1
 0 <= x#2#63 <= 1
 0 <= x#2#64 <= 1
 0 <= x#2#65 <= 1
 0 <= x#2#66 <= 1
 0 <= x#2#67 <= 1
 0 <= x#2#68 <= 1
 0 <= x#2#69 <= 1
 0 <= x#2#70 <= 1
 0 <= x#2#71 <= 1
 0 <= x#2#72 <= 1
 0 <= x#2#73 <= 1
 0 <= x#2#74 <= 1
 0 <= x#2#75 <= 1
 0 <= x#2#76 <= 1
 0 <= x#2#77 <= 1
 0 <= x#2#78 <= 1
 0 <= x#2#79 <= 1
 0 <= x#2#80 <= 1
 0 <= x#2#81 <= 1
 0 <= x#2#82 <= 1
 0 <= x#2#83 <= 1
 0 <= x#2#84 <= 1
 0 <= x#2#85 <= 1
 0 <= x#2#86 <= 1
 0 <= x#2#87 <= 1
 0 <= x#2#88 <= 1
 0 <= x#2#89 <= 1
 0 <= x#2#90 <= 1
 0 <= x#2#91 <= 1
 0 <= x#2#92 <= 1
 0 <= x#2#93 <= 1
 0 <= x#2#94 <= 1
 0 <= x#2#95 <= 1
 0 <= x#2#96 <= 1
 0 <= x#2#97 <= 1
 0 <= x#2#98 <= 1
 0 <= x#2#99 <= 1
 0 <= x#2#100 <= 1
 0 <= x#3#1 <= 1
 0 <= x#3#2 <= 1
 0 <= x#3#3 <= 1
 0 <= x#3#4 <= 1
 0 <= x#3#5 <= 1
 0 <= x#3#6 <= 1
 0 <= x#3#7 <= 1
 0 <= x#3#8 <= 1
 0 <= x#3#9 <= 1
 0 <= x#3#10 <= 1
 0 <= x#3#11 <= 1
 0 <= x#3#12 <= 1
 0 <= x#3#13 <= 1
 0 <= x#3#14 <= 1
 0 <= x#3#15 <= 1
 0 <= x#3#16 <= 1
 0 <= x#3#17 <= 1
 0 <= x#3#18 <= 1
 0 <= x#3#19 <= 1
 0 <= x#3#20 <= 1
 0 <= x#3#21 <= 1
 0 <= x#3#22 <= 1
 0 <= x#3#23 <= 1
 0 <= x#3#24 <= 1
 0 <= x#3#25 <= 1
 0 <= x#3#26 <= 1
 0 <= x#3#27 <= 1
 0 <= x#3#28 <= 1
 0 <= x#3#29 <= 1
 0 <= x#3#30 <= 1
 0 <= x#3#31 <= 1
 0 <= x#3#32 <= 1
 0 <= x#3#33 <= 1
 0 <= x#3#34 <= 1
 0 <= x#3#35 <= 1
 0 <= x#3#36 <= 1
 0 <= x#3#37 <= 1
 0 <= x#3#38 <= 1
 0 <= x#3#39 <= 1
 0 <= x#3#40 <= 1
 0 <= x#3#41 <= 1
 0 <= x#3#42 <= 1
 0 <= x#3#43 <= 1
 0 <= x#3#44 <= 1
 0 <= x#3#45 <= 1
 0 <= x#3#46 <= 1
 0 <= x#3#47 <= 1
 0 <= x#3#48 <= 1
 0 <= x#3#49 <= 1
 0 <= x#3#50 <= 1
 0 <= x#3#51 <= 1
 0 <= x#3#52 <= 1
 0 <= x#3#53 <= 1
 0 <= x#3#54 <= 1
 0 <= x#3#55 <= 1
 0 <= x#3#56 <= 1
 0 <= x#3#57 <= 1
 0 <= x#3#58 <= 1
 0 <= x#3#59 <= 1
 0 <= x#3#60 <= 1
 0 <= x#3#61 <= 1
 0 <= x#3#62 <= 1
 0 <= x#3#63 <= 1
 0 <= x#3#64 <= 1
 0 <= x#3#65 <= 1
 0 <= x#3#66 <= 1
 0 <= x#3#67 <= 1
 0 <= x#3#68 <= 1
 0 <= x#3#69 <= 1
 0 <= x#3#70 <= 1
 0 <= x#3#71 <= 1
 0 <= x#3#72 <= 1
 0 <= x#3#73 <= 1
 0 <= x#3#74 <= 1
 0 <= x#3#75 <= 1
 0 <= x#3#76 <= 1
 0 <= x#3#77 <= 1
 0 <= x#3#78 <= 1
 0 <= x#3#79 <= 1
 0 <= x#3#80 <= 1
 0 <= x#3#81 <= 1
 0 <= x#3#82 <= 1
 0 <= x#3#83 <= 1
 0 <= x#3#84 <= 1
 0 <= x#3#85 <= 1
 0 <= x#3#86 <= 1
 0 <= x#3#87 <= 1
 0 <= x#3#88 <= 1
 0 <= x#3#89 <= 1
 0 <= x#3#90 <= 1
 0 <= x#3#91 <= 1
 0 <= x#3#92 <= 1
 0 <= x#3#93 <= 1
 0 <= x#3#94 <= 1
 0 <= x#3#95 <= 1
 0 <= x#3#96 <= 1
 0 <= x#3#97 <= 1
 0 <= x#3#98 <= 1
 0 <= x#3#99 <= 1
 0 <= x#3#100 <= 1
 0 <= x#4#1 <= 1
 0 <= x#4#2 <= 1
 0 <= x#4#3 <= 1
 0 <= x#4#4 <= 1
 0 <= x#4#5 <= 1
 0 <= x#4#6 <= 1
 0 <= x#4#7 <= 1
 0 <= x#4#8 <= 1
 0 <= x#4#9 <= 1
 0 <= x#4#10 <= 1
 0 <= x#4#11 <= 1
 0 <= x#4#12 <= 1
 0 <= x#4#13 <= 1
 0 <= x#4#14 <= 1
 0 <= x#4#15 <= 1
 0 <= x#4#16 <= 1
 0 <= x#4#17 <= 1
 0 <= x#4#18 <= 1
 0 <= x#4#19 <= 1
 0 <= x#4#20 <= 1
 0 <= x#4#21 <= 1
 0 <= x#4#22 <= 1
 0 <= x#4#23 <= 1
 0 <= x#4#24 <= 1
 0 <= x#4#25 <= 1
 0 <= x#4#26 <= 1
 0 <= x#4#27 <= 1
 0 <= x#4#28 <= 1
 0 <= x#4#29 <= 1
 0 <= x#4#30 <= 1
 0 <= x#4#31 <= 1
 0 <= x#4#32 <= 1
 0 <= x#4#33 <= 1
 0 <= x#4#34 <= 1
 0 <= x#4#35 <= 1
 0 <= x#4#36 <= 1
 0 <= x#4#37 <= 1
 0 <= x#4#38 <= 1
 0 <= x#4#39 <= 1
 0 <= x#4#40 <= 1
 0 <= x#4#41 <= 1
 0 <= x#4#42 <= 1
 0 <= x#4#43 <= 1
 0 <= x#4#44 <= 1
 0 <= x#4#45 <= 1
 0 <= x#4#46 <= 1
 0 <= x#4#47 <= 1
 0 <= x#4#48 <= 1
 0 <= x#4#49 <= 1
 0 <= x#4#50 <= 1
 0 <= x#4#51 <= 1
 0 <= x#4#52 <= 1
 0 <= x#4#53 <= 1
 0 <= x#4#54 <= 1
 0 <= x#4#55 <= 1
 0 <= x#4#56 <= 1
 0 <= x#4#57 <= 1
 0 <= x#4#58 <= 1
 0 <= x#4#59 <= 1
 0 <= x#4#60 <= 1
 0 <= x#4#61 <= 1
 0 <= x#4#62 <= 1
 0 <= x#4#63 <= 1
 0 <= x#4#64 <= 1
 0 <= x#4#65 <= 1
 0 <= x#4#66 <= 1
 0 <= x#4#67 <= 1
 0 <= x#4#68 <= 1
 0 <= x#4#69 <= 1
 0 <= x#4#70 <= 1
 0 <= x#4#71 <= 1
 0 <= x#4#72 <= 1
 0 <= x#4#73 <= 1
 0 <= x#4#74 <= 1
 0 <= x#4#75 <= 1
 0 <= x#4#76 <= 1
 0 <= x#4#77 <= 1
 0 <= x#4#78 <= 1
 0 <= x#4#79 <= 1
 0 <= x#4#80 <= 1
 0 <= x#4#81 <= 1
 0 <= x#4#82 <= 1
 0 <= x#4#83 <= 1
 0 <= x#4#84 <= 1
 0 <= x#4#85 <= 1
 0 <= x#4#86 <= 1
 0 <= x#4#87 <= 1
 0 <= x#4#88 <= 1
 0 <= x#4#89 <= 1
 0 <= x#4#90 <= 1
 0 <= x#4#91 <= 1
 0 <= x#4#92 <= 1
 0 <= x#4#93 <= 1
 0 <= x#4#94 <= 1
 0 <= x#4#95 <= 1
 0 <= x#4#96 <= 1
 0 <= x#4#97 <= 1
 0 <= x#4#98 <= 1
 0 <= x#4#99 <= 1
 0 <= x#4#100 <= 1
 0 <= x#5#1 <= 1
 0 <= x#5#2 <= 1
 0 <= x#5#3 <= 1
 0 <= x#5#4 <= 1
 0 <= x#5#5 <= 1
 0 <= x#5#6 <= 1
 0 <= x#5#7 <= 1
 0 <= x#5#8 <= 1
 0 <= x#5#9 <= 1
 0 <= x#5#10 <= 1
 0 <= x#5#11 <= 1
 0 <= x#5#12 <= 1
 0 <= x#5#13 <= 1
 0 <= x#5#14 <= 1
 0 <= x#5#15 <= 1
 0 <= x#5#16 <= 1
 0 <= x#5#17 <= 1
 0 <= x#5#18 <= 1
 0 <= x#5#19 <= 1
 0 <= x#5#20 <= 1
 0 <= x#5#21 <= 1
 0 <= x#5#22 <= 1
 0 <= x#5#23 <= 1
 0 <= x#5#24 <= 1
 0 <= x#5#25 <= 1
 0 <= x#5#26 <= 1
 0 <= x#5#27 <= 1
 0 <= x#5#28 <= 1
 0 <= x#5#29 <= 1
 0 <= x#5#30 <= 1
 0 <= x#5#31 <= 1
 0 <= x#5#32 <= 1
 0 <= x#5#33 <= 1
 0 <= x#5#34 <= 1
 0 <= x#5#35 <= 1
 0 <= x#5#36 <= 1
 0 <= x#5#37 <= 1
 0 <= x#5#38 <= 1
 0 <= x#5#39 <= 1
 0 <= x#5#40 <= 1
 0 <= x#5#41 <= 1
 0 <= x#5#42 <= 1
 0 <= x#5#43 <= 1
 0 <= x#5#44 <= 1
 0 <= x#5#45 <= 1
 0 <= x#5#46 <= 1
 0 <= x#5#47 <= 1
 0 <= x#5#48 <= 1
 0 <= x#5#49 <= 1
 0 <= x#5#50 <= 1
 0 <= x#5#51 <= 1
 0 <= x#5#52 <= 1
 0 <= x#5#53 <= 1
 0 <= x#5#54 <= 1
 0 <= x#5#55 <= 1
 0 <= x#5#56 <= 1
 0 <= x#5#57 <= 1
 0 <= x#5#58 <= 1
 0 <= x#5#59 <= 1
 0 <= x#5#60 <= 1
 0 <= x#5#61 <= 1
 0 <= x#5#62 <= 1
 0 <= x#5#63 <= 1
 0 <= x#5#64 <= 1
 0 <= x#5#65 <= 1
 0 <= x#5#66 <= 1
 0 <= x#5#67 <= 1
 0 <= x#5#68 <= 1
 0 <= x#5#69 <= 1
 0 <= x#5#70 <= 1
 0 <= x#5#71 <= 1
 0 <= x#5#72 <= 1
 0 <= x#5#73 <= 1
 0 <= x#5#74 <= 1
 0 <= x#5#75 <= 1
 0 <= x#5#76 <= 1
 0 <= x#5#77 <= 1
 0 <= x#5#78 <= 1
 0 <= x#5#79 <= 1
 0 <= x#5#80 <= 1
 0 <= x#5#81 <= 1
 0 <= x#5#82 <= 1
 0 <= x#5#83 <= 1
 0 <= x#5#84 <= 1
 0 <= x#5#85 <= 1
 0 <= x#5#86 <= 1
 0 <= x#5#87 <= 1
 0 <= x#5#88 <= 1
 0 <= x#5#89 <= 1
 0 <= x#5#90 <= 1
 0 <= x#5#91 <= 1
 0 <= x#5#92 <= 1
 0 <= x#5#93 <= 1
 0 <= x#5#94 <= 1
 0 <= x#5#95 <= 1
 0 <= x#5#96 <= 1
 0 <= x#5#97 <= 1
 0 <= x#5#98 <= 1
 0 <= x#5#99 <= 1
 0 <= x#5#100 <= 1
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
 x#1#13
 x#1#14
 x#1#15
 x#1#16
 x#1#17
 x#1#18
 x#1#19
 x#1#20
 x#1#21
 x#1#22
 x#1#23
 x#1#24
 x#1#25
 x#1#26
 x#1#27
 x#1#28
 x#1#29
 x#1#30
 x#1#31
 x#1#32
 x#1#33
 x#1#34
 x#1#35
 x#1#36
 x#1#37
 x#1#38
 x#1#39
 x#1#40
 x#1#41
 x#1#42
 x#1#43
 x#1#44
 x#1#45
 x#1#46
 x#1#47
 x#1#48
 x#1#49
 x#1#50
 x#1#51
 x#1#52
 x#1#53
 x#1#54
 x#1#55
 x#1#56
 x#1#57
 x#1#58
 x#1#59
 x#1#60
 x#1#61
 x#1#62
 x#1#63
 x#1#64
 x#1#65
 x#1#66
 x#1#67
 x#1#68
 x#1#69
 x#1#70
 x#1#71
 x#1#72
 x#1#73
 x#1#74
 x#1#75
 x#1#76
 x#1#77
 x#1#78
 x#1#79
 x#1#80
 x#1#81
 x#1#82
 x#1#83
 x#1#84
 x#1#85
 x#1#86
 x#1#87
 x#1#88
 x#1#89
 x#1#90
 x#1#91
 x#1#92
 x#1#93
 x#1#94
 x#1#95
 x#1#96
 x#1#97
 x#1#98
 x#1#99
 x#1#100
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
 x#2#13
 x#2#14
 x#2#15
 x#2#16
 x#2#17
 x#2#18
 x#2#19
 x#2#20
 x#2#21
 x#2#22
 x#2#23
 x#2#24
 x#2#25
 x#2#26
 x#2#27
 x#2#28
 x#2#29
 x#2#30
 x#2#31
 x#2#32
 x#2#33
 x#2#34
 x#2#35
 x#2#36
 x#2#37
 x#2#38
 x#2#39
 x#2#40
 x#2#41
 x#2#42
 x#2#43
 x#2#44
 x#2#45
 x#2#46
 x#2#47
 x#2#48
 x#2#49
 x#2#50
 x#2#51
 x#2#52
 x#2#53
 x#2#54
 x#2#55
 x#2#56
 x#2#57
 x#2#58
 x#2#59
 x#2#60
 x#2#61
 x#2#62
 x#2#63
 x#2#64
 x#2#65
 x#2#66
 x#2#67
 x#2#68
 x#2#69
 x#2#70
 x#2#71
 x#2#72
 x#2#73
 x#2#74
 x#2#75
 x#2#76
 x#2#77
 x#2#78
 x#2#79
 x#2#80
 x#2#81
 x#2#82
 x#2#83
 x#2#84
 x#2#85
 x#2#86
 x#2#87
 x#2#88
 x#2#89
 x#2#90
 x#2#91
 x#2#92
 x#2#93
 x#2#94
 x#2#95
 x#2#96
 x#2#97
 x#2#98
 x#2#99
 x#2#100
 x#3#1
 x#3#2
 x#3#3
 x#3#4
 x#3#5
 x#3#6
 x#3#7
 x#3#8
 x#3#9
 x#3#10
 x#3#11
 x#3#12
 x#3#13
 x#3#14
 x#3#15
 x#3#16
 x#3#17
 x#3#18
 x#3#19
 x#3#20
 x#3#21
 x#3#22
 x#3#23
 x#3#24
 x#3#25
 x#3#26
 x#3#27
 x#3#28
 x#3#29
 x#3#30
 x#3#31
 x#3#32
 x#3#33
 x#3#34
 x#3#35
 x#3#36
 x#3#37
 x#3#38
 x#3#39
 x#3#40
 x#3#41
 x#3#42
 x#3#43
 x#3#44
 x#3#45
 x#3#46
 x#3#47
 x#3#48
 x#3#49
 x#3#50
 x#3#51
 x#3#52
 x#3#53
 x#3#54
 x#3#55
 x#3#56
 x#3#57
 x#3#58
 x#3#59
 x#3#60
 x#3#61
 x#3#62
 x#3#63
 x#3#64
 x#3#65
 x#3#66
 x#3#67
 x#3#68
 x#3#69
 x#3#70
 x#3#71
 x#3#72
 x#3#73
 x#3#74
 x#3#75
 x#3#76
 x#3#77
 x#3#78
 x#3#79
 x#3#80
 x#3#81
 x#3#82
 x#3#83
 x#3#84
 x#3#85
 x#3#86
 x#3#87
 x#3#88
 x#3#89
 x#3#90
 x#3#91
 x#3#92
 x#3#93
 x#3#94
 x#3#95
 x#3#96
 x#3#97
 x#3#98
 x#3#99
 x#3#100
 x#4#1
 x#4#2
 x#4#3
 x#4#4
 x#4#5
 x#4#6
 x#4#7
 x#4#8
 x#4#9
 x#4#10
 x#4#11
 x#4#12
 x#4#13
 x#4#14
 x#4#15
 x#4#16
 x#4#17
 x#4#18
 x#4#19
 x#4#20
 x#4#21
 x#4#22
 x#4#23
 x#4#24
 x#4#25
 x#4#26
 x#4#27
 x#4#28
 x#4#29
 x#4#30
 x#4#31
 x#4#32
 x#4#33
 x#4#34
 x#4#35
 x#4#36
 x#4#37
 x#4#38
 x#4#39
 x#4#40
 x#4#41
 x#4#42
 x#4#43
 x#4#44
 x#4#45
 x#4#46
 x#4#47
 x#4#48
 x#4#49
 x#4#50
 x#4#51
 x#4#52
 x#4#53
 x#4#54
 x#4#55
 x#4#56
 x#4#57
 x#4#58
 x#4#59
 x#4#60
 x#4#61
 x#4#62
 x#4#63
 x#4#64
 x#4#65
 x#4#66
 x#4#67
 x#4#68
 x#4#69
 x#4#70
 x#4#71
 x#4#72
 x#4#73
 x#4#74
 x#4#75
 x#4#76
 x#4#77
 x#4#78
 x#4#79
 x#4#80
 x#4#81
 x#4#82
 x#4#83
 x#4#84
 x#4#85
 x#4#86
 x#4#87
 x#4#88
 x#4#89
 x#4#90
 x#4#91
 x#4#92
 x#4#93
 x#4#94
 x#4#95
 x#4#96
 x#4#97
 x#4#98
 x#4#99
 x#4#100
 x#5#1
 x#5#2
 x#5#3
 x#5#4
 x#5#5
 x#5#6
 x#5#7
 x#5#8
 x#5#9
 x#5#10
 x#5#11
 x#5#12
 x#5#13
 x#5#14
 x#5#15
 x#5#16
 x#5#17
 x#5#18
 x#5#19
 x#5#20
 x#5#21
 x#5#22
 x#5#23
 x#5#24
 x#5#25
 x#5#26
 x#5#27
 x#5#28
 x#5#29
 x#5#30
 x#5#31
 x#5#32
 x#5#33
 x#5#34
 x#5#35
 x#5#36
 x#5#37
 x#5#38
 x#5#39
 x#5#40
 x#5#41
 x#5#42
 x#5#43
 x#5#44
 x#5#45
 x#5#46
 x#5#47
 x#5#48
 x#5#49
 x#5#50
 x#5#51
 x#5#52
 x#5#53
 x#5#54
 x#5#55
 x#5#56
 x#5#57
 x#5#58
 x#5#59
 x#5#60
 x#5#61
 x#5#62
 x#5#63
 x#5#64
 x#5#65
 x#5#66
 x#5#67
 x#5#68
 x#5#69
 x#5#70
 x#5#71
 x#5#72
 x#5#73
 x#5#74
 x#5#75
 x#5#76
 x#5#77
 x#5#78
 x#5#79
 x#5#80
 x#5#81
 x#5#82
 x#5#83
 x#5#84
 x#5#85
 x#5#86
 x#5#87
 x#5#88
 x#5#89
 x#5#90
 x#5#91
 x#5#92
 x#5#93
 x#5#94
 x#5#95
 x#5#96
 x#5#97
 x#5#98
 x#5#99
 x#5#100
End
