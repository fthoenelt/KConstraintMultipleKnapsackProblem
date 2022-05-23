package geneticalgorithms.choosechildren;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import knapsack.Solution;

/**
 * Klasse die das ChooseChildren-Interface für den Genetischen Algorithmus für das K-MKP implementiert. Hierbei wird aus dem Array von Nachfahren das Individuum gewählt, welches
 * die beste Fitness besitzt
 */
public class FittestChild implements ChooseChildren{

  /**
   * Methode die das fitteste Individuum aus einem Array von Individuen wählt und zurückgibt
   *
   * @param children  Array von Chromosome
   * @return  Die Solution mit dem besten Fitnesswert
   */
  @Override
  public Solution chooseChild(Solution[] children) {
    Optional<Solution> child = Arrays.stream(children).max(Comparator.comparingInt(Solution::getProfit));
    assert child.isPresent();
    return child.get();
  }
}
