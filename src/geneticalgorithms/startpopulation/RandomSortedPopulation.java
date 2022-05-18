package geneticalgorithms.startpopulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import knapsack.KConstraintMultipleKnapsack;
import knapsack.Solution;
import vlsn.GreedySolution;

/**
 *
 */

public class RandomSortedPopulation implements StartPopulationGenerator{

  KConstraintMultipleKnapsack knapsack;

  public RandomSortedPopulation(KConstraintMultipleKnapsack knapsack){
    this.knapsack = knapsack;
  }

  @Override
  public List<Solution> generatePopulation(int size) {
    List<Integer> knapsackIDs = IntStream.range(0, knapsack.getNrKnapsacks()).boxed().collect(Collectors.toList());
    List<Integer> itemIDs =  IntStream.range(0, knapsack.getNrItems()).boxed().collect(Collectors.toList());
    List<Solution> solutions = new ArrayList<>(size);
    for(int i = 0; i < size-1; i++){
      Solution s = new Solution(knapsack);
      Collections.shuffle(knapsackIDs);
      Collections.shuffle(itemIDs);
      for(Integer itemID: itemIDs){
        for(Integer knapsackID: knapsackIDs){
          if(s.canBeAdded( knapsack.getItem(itemID),knapsack.getKnapsack(knapsackID))){
            s.addItem(knapsack.getItem(itemID),knapsack.getKnapsack(knapsackID));
            break;
          }
        }
      }
      solutions.add(s);
    }
    solutions.add(GreedySolution.getGreedy(knapsack));

    return solutions;
  }



  @Override
  public boolean getFeasibility() {
    return false;
  }
}
