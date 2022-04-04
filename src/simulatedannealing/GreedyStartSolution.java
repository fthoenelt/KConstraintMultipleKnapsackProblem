package simulatedannealing;

import knapsack.KConstraintMultipleKnapsack;
import knapsack.Solution;
import vlsn.GreedySolution;

public class GreedyStartSolution implements StartSolution{

  @Override
  public Solution getStartSolution(KConstraintMultipleKnapsack knapsack) {
    return GreedySolution.getGreedy(knapsack);
  }
}
