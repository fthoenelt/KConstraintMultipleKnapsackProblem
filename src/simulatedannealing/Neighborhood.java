package simulatedannealing;

import knapsack.Solution;

public interface Neighborhood {
  Solution getNeighbor(Solution solution);
}
