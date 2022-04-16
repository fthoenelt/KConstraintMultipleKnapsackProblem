package ganew.crossover;

import ganew.Chromosom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import knapsack.KConstraintMultipleKnapsack;

public class PartiallyMatchedCrossover implements Crossover{
  Random random;

  public PartiallyMatchedCrossover(){
    this.random = new Random();
  }

  @Override
  public Chromosom crossover(KConstraintMultipleKnapsack knapsack, Chromosom p1, Chromosom p2) {
    List<Integer> child = new ArrayList<>(p1.getSolution());
    int cp1 = random.nextInt(knapsack.getNrItems());
    while(cp1 == knapsack.getNrItems()-1){
      cp1 = random.nextInt(knapsack.getNrItems());
    }
    int cp2 = cp1+ random.nextInt(knapsack.getNrItems()-cp1);
    while(cp1 == cp2){
      cp2 = cp1+ random.nextInt(knapsack.getNrItems()-cp1);
    }
    for(int i = cp1; i <= cp2; i++){
      Collections.swap(child, i, child.indexOf(p2.getSolution().get(i)));
    }
    return new Chromosom(child, knapsack);
  }
}
