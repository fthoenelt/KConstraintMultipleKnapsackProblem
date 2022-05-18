package geneticalgorithms.crossover;

import knapsack.Solution;

/**
 * Interface für die Reproduktion in dem GA für das K-MKP, wobei zwei Elternchromosome mithilfe einer spezifischen Crossover-Wahrscheinlichkeit rekombiniert oder verändert
 * werden um ein Nachkommen zu erzeugen
 */
public interface Crossover {

  /**
   * Methode die das Crossover durchführt und eine neues Individuum erzeugt und zurückgibt
   *
   * @param chrom1  Das erste Elternchromosom
   * @param chrom2  Das zweite Elternchromosom
   * @return  Neues Individuum als Nachfahre
   */
  Solution crossover(Solution chrom1, Solution chrom2);
  boolean getFeasibility();
}
