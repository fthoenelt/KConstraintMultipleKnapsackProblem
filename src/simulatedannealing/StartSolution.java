package simulatedannealing;

import knapsack.KConstraintMultipleKnapsack;
import knapsack.Solution;

/**
 * Interface für die Berechnung einer Startlösung für Simulated Annealing für das K-MKP
 */

public interface StartSolution {

  /**
   * Hauptmethode welche eine zulässige Startlösung für das K-MKP zurückgeben soll
   *
   * @param knapsack  Die K-MKP-Instanz
   * @return  Eine zulässige Lösung für das K-MKP
   */
  Solution getStartSolution(KConstraintMultipleKnapsack knapsack);
}
