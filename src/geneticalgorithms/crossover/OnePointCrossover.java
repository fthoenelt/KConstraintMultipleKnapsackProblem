package geneticalgorithms.crossover;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import knapsack.Item;
import knapsack.Solution;
import vlsn.GreedySolution;

/**
 *
 */
public class OnePointCrossover implements Crossover {

  boolean feasible;
  Random rand;
  boolean fillGreedy;

  public OnePointCrossover(boolean feasible){
    this.feasible = feasible;
    this.rand = new Random();
    this.fillGreedy = false;
  }

  public OnePointCrossover(boolean feasible, boolean fillGreedy){
    this.feasible = feasible;
    this.rand = new Random();
    this.fillGreedy = fillGreedy;
  }

  @Override
  public Solution[] crossover(Solution chrom1, Solution chrom2, double crossoverProb) {
    if(rand.nextDouble() < crossoverProb){
      Solution child = new Solution(chrom1.getKnapsack());
      int cut = rand.nextInt(chrom1.getKnapsack().getNrKnapsacks());
      List<Item> notUsed = new ArrayList<>();
      for(Item i: chrom1.getKnapsack().getItems()){
        if(chrom1.isUsed(i)&&chrom1.getKnapsack(i).getKnapsackID()<= cut&&(!this.feasible|| child.canBeAdded(i, chrom1.getKnapsack(i)))){
          child.addItem(i, chrom1.getKnapsack(i));
        }else if(chrom2.isUsed(i)&&(!this.feasible || child.canBeAdded(i, chrom2.getKnapsack(i)))){
          child.addItem(i, chrom2.getKnapsack(i));
        }else if(this.fillGreedy){
          notUsed.add(i);
        }
      }
      if(this.fillGreedy){
        GreedySolution.fillGreedy(chrom1.getKnapsack(), child, notUsed);
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
