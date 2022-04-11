package ganew.crossover;

import ganew.Chromosom;
import java.util.List;
import knapsack.KConstraintMultipleKnapsack;

public class CycleCrossover implements Crossover{

  @Override
  public Chromosom crossover(KConstraintMultipleKnapsack knapsack,Chromosom p1, Chromosom p2) {
    Integer[] child = new Integer[knapsack.getNrItems()];
    int index = 0;
    do {
      child[index] = p1.getSolution().get(index);
      index = p1.getSolution().indexOf(p2.getSolution().get(index));
    } while (child[index] == null);
    for(int i = 0; i < child.length; i++){
      if(child[i] == null) child[i] = p2.getSolution().get(i);
    }
    return new Chromosom(List.of(child), knapsack);
  }
}
