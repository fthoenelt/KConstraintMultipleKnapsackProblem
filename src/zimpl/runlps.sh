for n in 50 100 200 500
do
  for((i = 0; i < 16; i++));
  do
    if [ $i -eq 500]
    then
      /opt/ibm/ILOG/CPLEX_Studio201/cplex/bin/x86-64_linux/cplex -c "set timelimit 600" "read lpfiles/knapsack"$n"nr"$i".lp" "opt" "write sol/cplex1/knapsack"$n"nr"$i".sol"
    else
      /opt/ibm/ILOG/CPLEX_Studio201/cplex/bin/x86-64_linux/cplex -c "set timelimit 300" "read lpfiles/knapsack"$n"nr"$i".lp" "opt" "write sol/cplex1/knapsack"$n"nr"$i".sol"
    fi
  done
done

for n in 50 100 200 500
do
  for((i = 0; i < 16; i++));
  do
    /opt/ibm/ILOG/CPLEX_Studio201/cplex/bin/x86-64_linux/cplex -c "set timelimit 3600" "read lpfiles/knapsack"$n"nr"$i".lp" "opt" "write sol/cplex2/knapsack"$n"nr"$i".sol"
  done
done


