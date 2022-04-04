package geneticalgorithms.generation;

import java.util.Comparator;
import java.util.List;
import knapsack.Solution;

public class CutGeneration implements  GenerationGenerator{

  @Override
  public List<Solution> generate(List<Solution> old, List<Solution> next, int maxSize) {
    next.addAll(old);
    next.sort(new Comparator<Solution>() {
      @Override
      public int compare(Solution o1, Solution o2) {
        return Integer.compare(o2.getProfit(), o1.getProfit());
      }
    });
    if(next.size()>maxSize)next.removeAll(next.subList(maxSize, next.size()));
    return next;
  }
}
