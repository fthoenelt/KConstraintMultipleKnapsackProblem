package geneticalgorithms.startpopulation;

import java.util.List;
import knapsack.Solution;

/**
 * Interface welches für die erzeugung einer Startpopulation für den GA für das K-MKP verwendet wird.
 */

public interface StartPopulationGenerator {

  /**
   * Erzeugt eine Startpopulation einer bestimmten Größe in Form einer Liste von Individuen
   *
   * @param size  Die Größe welche die Startpopulation haben soll
   * @return  Eine Liste von Solution-Objekten als Individuen
   */
  List<Solution> generatePopulation(int size);
  boolean getFeasibility();
}
