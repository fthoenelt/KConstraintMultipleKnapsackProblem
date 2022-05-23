package simulatedannealing;

import knapsack.KConstraintMultipleKnapsack;
import knapsack.Solution;
import vlsn.GreedySolution;

/**
 * Klasse die das StartSolution-Interface implementiert. Dafür wird die Greedy-Heuristik für das K-MKP von Ahuja und Cunha verwendet
 */

public class GreedyStartSolution implements StartSolution{

  /**
   * Gibt die Greedy-Lösung für das K-MKP zurück
   *
   * @param knapsack  Die K-MKP-Instanz
   * @return  Zulässige Lösung für die gegebene K-MKP-Instanz
   */
  @Override
  public Solution getStartSolution(KConstraintMultipleKnapsack knapsack) {
    return GreedySolution.getGreedy(knapsack);
  }
}
