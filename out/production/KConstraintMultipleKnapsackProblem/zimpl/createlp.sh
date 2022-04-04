for n in 50 100 200 500
do
  for((i = 0; i < 16; i++));
  do
    zimpl zimplfiles/knapsack${n}nr"$i".zpl
  done
done