package ganew.crossover;

import ganew.Chromosom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import knapsack.KConstraintMultipleKnapsack;

public class OrderBasedCrossover implements Crossover{

  KConstraintMultipleKnapsack knapsack;
  Random random;

  public OrderBasedCrossover(KConstraintMultipleKnapsack knapsack){
    this.knapsack = knapsack;
    this.random = new Random();
  }

  @Override
  public Chromosom crossover(Chromosom p1, Chromosom p2) {
    Integer[] child = new Integer[knapsack.getNrItems()];
    int cp1 = random.nextInt(knapsack.getNrItems());
    while(cp1 == knapsack.getNrItems()-1){
      cp1 = random.nextInt(knapsack.getNrItems());
    }
    int cp2 = cp1+ random.nextInt(knapsack.getNrItems()-cp1-1);
    while(cp1 == cp2){
      cp2 = cp1+ random.nextInt(knapsack.getNrItems()-cp1-1);
    }
    System.out.println("CP1: "+cp1);
    System.out.println("CP2: "+cp2);
    HashSet<Integer> used = new HashSet<>();
    for(int i = cp1; i <= cp2; i++){
      child[i]= p1.getSolution().get(i);
      used.add(child[i]);
    }
    int index = 0;
    for(Integer i : p2.getSolution()){
      if(used.contains(i)){
        continue;
      }else if(index >=cp1 && index <= cp2){
        index = cp2+1;
      }
      child[index]= i;
      index ++;
    }
    return new Chromosom(Arrays.stream(child).toList(), knapsack);
  }
}
