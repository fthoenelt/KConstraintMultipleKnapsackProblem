package geneticalgorithms.startpopulation;

import java.util.List;
import knapsack.Solution;


public interface StartPopulationGenerator {
  List<Solution> generatePopulation(int size);
  boolean getFeasibility();
}
