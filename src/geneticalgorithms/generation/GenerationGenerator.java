package geneticalgorithms.generation;

import java.util.List;
import knapsack.Solution;

public interface GenerationGenerator {
  List<Solution> generate(List<Solution> old, List<Solution> next, int maxSize);
}
