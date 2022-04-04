package geneticalgorithms.choosechildren;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import knapsack.Solution;

/**
 * Chooser choosing the fittest child from the offsprings
 */
public class FittestChild implements ChooseChildren{

  //TODO: ggf direkt immer 2 Kinder vergleichen oder Optional returnen
  @Override
  public Solution chooseChild(Solution[] children) {
    Optional<Solution> child = Arrays.stream(children).max(new Comparator<Solution>() {
      @Override
      public int compare(Solution o1, Solution o2) {
        return Integer.compare(o1.getProfit(), o2.getProfit());
      }
    });
    assert child.isPresent();
    return child.get();
  }
}
