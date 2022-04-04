package geneticalgorithms.crossover;

import java.util.Random;
import knapsack.Item;
import knapsack.Knapsack;
import knapsack.Solution;

public class RandomCrossover implements Crossover {

  boolean feasible;
  Random rand;

  public RandomCrossover(boolean feasible){
    this.feasible = feasible;
    this.rand = new Random();
  }

  @Override
  public Solution[] crossover(Solution chrom1, Solution chrom2, double crossoverProb) {
    if(rand.nextDouble()<crossoverProb){
      Solution child = new Solution(chrom1.getKnapsack());
      for(Item i : child.getKnapsack().getItems()){
        boolean choose = rand.nextBoolean();
        Knapsack k = (choose)? chrom1.getKnapsack(i):chrom2.getKnapsack(i);
        if(k!=null&& (child.canBeAdded(i, k)||!feasible)){
          child.addItem(i, k);
        }else {
          k = (choose)? chrom2.getKnapsack(i):chrom1.getKnapsack(i);
          if(k!=null&& (child.canBeAdded(i, k)||!feasible)){
            child.addItem(i, k);
          }
        }
      }
      return new Solution[]{child};
    }else{
      return new Solution[]{chrom1, chrom2};
    }

  }

  @Override
  public boolean getFeasibility() {
    return this.feasible;
  }
}
