package geneticalgorithms.choosechildren;

import java.util.Optional;
import knapsack.Solution;

/**
 * If Crossover generates more then one offspring classes implementing this interface are used to choose one offspring
 */
public interface ChooseChildren {
  Solution chooseChild(Solution[] children);
}
