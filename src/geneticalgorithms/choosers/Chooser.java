package geneticalgorithms.choosers;

import java.util.List;
import java.util.Set;
import knapsack.Solution;

/**
 * Chooses two parent Solutions out of a population
 */
public interface Chooser {
  void update(List<Solution> pop);
  Solution[] choose();
}
