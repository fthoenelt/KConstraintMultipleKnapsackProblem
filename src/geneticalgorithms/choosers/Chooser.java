package geneticalgorithms.choosers;

import java.util.List;
import knapsack.Solution;

/**
 *  Interface welches den Selektor für den GA bereitstellt. Dabei wird ein Mating Pool von Individuen erzeugt, aus welchem in dem GA die Elternchromosome ausgewählt werden
 */

public interface Chooser {

  /**
   * Methode die den Mating Pool für eine gegebene Population erzeugt. Dabei kann die gewünschte Größe angegeben werden
   *
   * @param population  Die Population aus welcher die Individuen ausgewählt werden
   * @param size  Die gewünschte Größe des Mating Pools
   * @return  Den Mating Pool
   */
  List<Solution> createMatingPool(List<Solution> population, int size);
}
