package simulatedannealing;

import knapsack.KConstraintMultipleKnapsack;
import knapsack.Solution;

public interface StartSolution {
  Solution getStartSolution(KConstraintMultipleKnapsack knapsack);
}
