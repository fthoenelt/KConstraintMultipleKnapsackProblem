param file := "instances/knapsack50nr0.txt";
param n := read file as "1n" use 1 comment "#";
do print n;
param m := read file as "1n" skip 1 use 1 comment "#";
do print m;
param k := read file as "1n" skip 2 use 1 comment "#";
do print k;

set Items := {1 to n};
set Knapsacks := {1 to m};
set Capacities := {1 to k};

param p[Items] := read file as "1n" skip 3 use n comment "#";

param w[Items*Capacities] := read file as "n+" skip 3+n use n comment "#";

param c[Knapsacks*Capacities] := read file as "n+" skip 3+2*n use m comment "#";

var x[Knapsacks * Items] binary;

maximize profit: sum <i> in Knapsacks: sum <j> in Items: p[j]* x[i,j];
subto cap: forall <i> in Knapsacks: forall <h> in Capacities: sum <j> in Items: w[j,h]*x[i,j] <= c[i,h];
subto one: forall <j> in Items: sum <i> in Knapsacks: x[i,j] <= 1;
