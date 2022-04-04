package simulatedannealing;

import knapsack.Solution;

public interface Acceptor {
  double getValue(Solution cur, Solution neighbor, int iteration);
}
