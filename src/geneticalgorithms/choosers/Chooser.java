package geneticalgorithms.choosers;

import java.util.List;
import java.util.Set;
import knapsack.Solution;

public interface Chooser {
  Solution[] choose(List<Solution> pop);
}
