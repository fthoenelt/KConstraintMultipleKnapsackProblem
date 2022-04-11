package ganew.crossover;

import ganew.Chromosom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import knapsack.KConstraintMultipleKnapsack;

public class UniformOrderCrossover implements Crossover{

  Random random;

  public UniformOrderCrossover(){
    this.random = new Random();
  }
  @Override
  public Chromosom crossover(KConstraintMultipleKnapsack knapsack,Chromosom p1, Chromosom p2) {
    Integer[] child = new Integer[knapsack.getNrItems()];
    ArrayList<Integer> notUsed = new ArrayList<>(p2.getSolution());
    for(int i = 0; i < knapsack.getNrItems(); i++){
      if(random.nextBoolean()){
        child[i] = p1.getSolution().get(i);
        notUsed.remove(p1.getSolution().get(i));
      }
    }
    for(int i = 0; i < child.length; i++){
      if(child[i]==null){
        assert !notUsed.isEmpty();
        child[i] = notUsed.remove(0);
      }
    }
    return new Chromosom(Arrays.asList(child), knapsack);
  }
}
