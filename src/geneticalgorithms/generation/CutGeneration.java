package geneticalgorithms.generation;

import java.util.Comparator;
import java.util.List;
import knapsack.Solution;

/**
 * Klasse die das Interface GenerationGenerator implementiert und als Ersetzungsstrategie eine Abwandlung der Steady-State Strategie nutzt. Hierbei werden beide Generationen von
 * Individuen in eine Menge gefügt, diese nach der jeweiligen Fitness sortiert und dann auf die gewünschte Größe reduziert.
 */

public class CutGeneration implements  GenerationGenerator{

  /**
   * Generiert die nächste Generation nach dem oben beschriebenen Verfahren und gibt diese als Liste von Individuen zurück
   *
   * @param old Die alte Generation von Individuen
   * @param next  Die neue Generation von Individuen
   * @param maxSize Die maximale Größe der zu erzeugenden Population
   * @return  Liste von Individuen
   */
  @Override
  public List<Solution> generate(List<Solution> old, List<Solution> next, int maxSize) {
    next.addAll(old);
    next.sort((o1, o2) -> Integer.compare(o2.getProfit(), o1.getProfit()));
    if(next.size()>maxSize)next.removeAll(next.subList(maxSize, next.size()));
    return next;
  }
}
