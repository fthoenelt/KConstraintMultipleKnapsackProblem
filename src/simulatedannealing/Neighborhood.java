package simulatedannealing;

import knapsack.Solution;

/**
 * Interface, welches verwendet werden kann, um eine Nachbarschaft einer gegebenen Lösung des K-MKPs zu berechnen. Hierdurch wird implizit eine Nachbarschaft definiert, ohne die
 * gesamte Nachbarschaft zu enumerieren. Wird in Simulated Annealing verwendet.
 */

public interface Neighborhood {

  /**
   * Berechnet einen Nachbarn einer gegebenen Lösung und gibt diesen zurück
   *
   * @param solution  Die Lösung deren Nachbar berechnet werden soll
   * @return  Die Nachbarlösung
   */
  Solution getNeighbor(Solution solution);
}
