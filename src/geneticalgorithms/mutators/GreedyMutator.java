package geneticalgorithms.mutators;

import java.util.ArrayList;
import java.util.List;
import knapsack.Item;
import knapsack.Solution;
import vlsn.GreedySolution;

public class GreedyMutator implements Mutator {

  boolean feasible;

  public GreedyMutator(boolean feasible){
    this.feasible = feasible;
  }

  @Override
  public Solution mutate(Solution chromosom) {
    List<Item> notUsed = new ArrayList<>();
    for(Item i: chromosom.getKnapsack().getItems()){
      if(!chromosom.isUsed(i)){
        notUsed.add(i);
      }
    }
    GreedySolution.fillGreedy(chromosom.getKnapsack(), chromosom, notUsed);
    return chromosom;
  }

  @Override
  public boolean getFeasibility() {
    return this.feasible;
  }
}
