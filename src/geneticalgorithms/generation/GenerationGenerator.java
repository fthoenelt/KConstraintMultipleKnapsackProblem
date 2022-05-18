package geneticalgorithms.generation;

import java.util.List;
import knapsack.Solution;

/**
 * Interface welches die Ersetzungsstrategie bestimmt. Diese entscheidet, inwiefern die neu erzeugte Population von Individuen die alte Generation ersetzt.
 */

public interface GenerationGenerator {

  /**
   * Hauptmethode der Ersetzungsstrategie
   *
   * @param old Die alte Generation von Individuen
   * @param next  Die neue Generation von Individuen
   * @param maxSize Die maximale Größe der zu erzeugenden Population
   * @return  Eine Liste von Solution-Objekten mit Objekten aus der alten und der neuen Generation
   */
  List<Solution> generate(List<Solution> old, List<Solution> next, int maxSize);
}
