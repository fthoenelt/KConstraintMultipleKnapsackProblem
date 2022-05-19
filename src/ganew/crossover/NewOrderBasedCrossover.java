package ganew.crossover;

import ganew.Chromosom;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import knapsack.KConstraintMultipleKnapsack;

public class NewOrderBasedCrossover implements Crossover{

  Random random = new Random();

  @Override
  public Chromosom crossover(KConstraintMultipleKnapsack knapsack, Chromosom p1, Chromosom p2) {
    Integer[] child = new Integer[knapsack.getNrItems()];
    int cp1 = random.nextInt(knapsack.getNrItems());
    int cp2 = random.nextInt(knapsack.getNrItems());

    System.out.println("cp1:"+cp1);
    System.out.println("cp2:"+cp2);

    HashSet<Integer> used = new HashSet<>();

    while(cp1 != cp2){
      child[cp1] = p1.getSolution().get(cp1);
      used.add(child[cp1]);
      cp1++;
      if(cp1 == knapsack.getNrItems()){
        cp1 = 0;
      }
    }
    int index = cp2;
    for(Integer i : p2.getSolution()){
      if(!used.contains(i)){
        while(child[index]!= null){
          index++;
          if (index>= knapsack.getNrItems()){
            index=0;
          }
        }
        child[index] = i;
      }
    }
    return new Chromosom(Arrays.asList(child), knapsack);
  }
}
