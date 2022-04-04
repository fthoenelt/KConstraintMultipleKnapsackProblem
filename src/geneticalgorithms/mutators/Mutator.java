package geneticalgorithms.mutators;

import knapsack.Solution;

public interface Mutator {

  Solution mutate(Solution chromosom);
  boolean getFeasibility();
}
