package ganew.selection;

import ganew.Chromosom;
import java.util.List;

/**
 * Interface für die Selektionsstrategie in dem PermGA für das K-MKP. Bei der Selektionsstrategie wird aus einer gegebenen Population ein Mating Pool einer gewünschten Größe
 * erzeugt. Dabei gibt es verschiedene Methoden, welche Individuen aus der Population in den Mating Pool mit aufgenommen werden
 */

public interface Selector {

  /**
   * Methode erzeugt einen Mating Pool in Form einer Liste aus einer gegebenen Population und einer gegebenen Größe
   *
   * @param population  Die Population von Individuen, aus welche Individuen in den Mating Pool selektiert werden sollen
   * @param size  Die gewünschte Größe des Mating Pools
   * @return  Mating Pool, welcher nach der jeweiligen Selektionsstrategie erzeugt wurde
   */
  List<Chromosom> createMatingPool(List<Chromosom> population, int size);
}
