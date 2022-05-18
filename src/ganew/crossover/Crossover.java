package ganew.crossover;

import ganew.Chromosom;
import knapsack.KConstraintMultipleKnapsack;

/**
 * Interface für ein beliebiges Crossover
 */

public interface Crossover {

  /**
   * Methode führt welche das jeweilige Crossover für zwei Elternchromosome und K-MKP Instanz durchführt und ein Kindchromosom returnt
   *
   * @param knapsack Die Instanz des K-MKPs
   * @param p1  Erstes Elternchromosom
   * @param p2  Zweites Elternchromosom
   * @return  Ein Kindchromosom welches abhängig von den jeweiligen Crossover erzeugt wurde
   */
  Chromosom crossover(KConstraintMultipleKnapsack knapsack, Chromosom p1, Chromosom p2);
}
