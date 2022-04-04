package geneticalgorithms.mutators;

import java.util.Random;
import knapsack.Item;
import knapsack.Knapsack;
import knapsack.Solution;

public class BitflipMutator implements Mutator {
  boolean feasibleSolutions;
  Random rand;
  public BitflipMutator(boolean feasibleSolutions){
    this.feasibleSolutions = feasibleSolutions;
    rand = new Random();
  }
  @Override
  public Solution mutate(Solution chromosom) {
    if(feasibleSolutions){
      Knapsack knapsack = chromosom.getKnapsack().getKnapsack(rand.nextInt(chromosom.getKnapsack().getNrKnapsacks()));
      Item item = chromosom.getKnapsack().getItem(rand.nextInt(chromosom.getKnapsack().getNrItems()));
      for(int i = 0; i < 20; i++){
        if(!chromosom.isUsed(item)&&chromosom.canBeAdded(item,knapsack)){
          chromosom.addItem(item, knapsack);
          break;
        }
        knapsack = chromosom.getKnapsack().getKnapsack(rand.nextInt(chromosom.getKnapsack().getNrKnapsacks()));
        item = chromosom.getKnapsack().getItem(rand.nextInt(chromosom.getKnapsack().getNrItems()));
      }
    }else{
      chromosom.addItem(chromosom.getKnapsack().getItem(rand.nextInt(chromosom.getKnapsack().getNrItems())),
          chromosom.getKnapsack().getKnapsack(rand.nextInt(chromosom.getKnapsack().getNrKnapsacks()+1)));
    }
    return chromosom;
  }

  @Override
  public boolean getFeasibility() {
    return this.feasibleSolutions;
  }
}
