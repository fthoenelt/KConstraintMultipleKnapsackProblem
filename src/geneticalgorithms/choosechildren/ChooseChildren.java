package geneticalgorithms.choosechildren;

import knapsack.Solution;

/**
 * Interface welches ein Nachfahre aus einem Array von Nachfahren auswählt. Hierbei ist nicht spezifiziert welcher Nachfahre gewählt wird. Wird zum Beispiel verwendet, wenn bei
 * dem Crossover mehr als ein Nachfahre erzeugt wird.
 */
public interface ChooseChildren {

  /**
   * Wählt ein Chromosom aus einem Array von Chromosomen
   *
   * @param children  Array von Chromosome
   * @return  Ein Chromosom
   */
  Solution chooseChild(Solution[] children);
}
