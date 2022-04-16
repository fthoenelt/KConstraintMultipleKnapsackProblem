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
//TODO: new crossover (Zufallszahl w < m, übernehme Rucksack 1-w von p1 und w-m von p2)
  @Override
  public Solution crossover(Solution chrom1, Solution chrom2) {
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
      return child;

  }

  @Override
  public boolean getFeasibility() {
    return this.feasible;
  }
}
