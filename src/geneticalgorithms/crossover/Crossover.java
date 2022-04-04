package geneticalgorithms.crossover;

import knapsack.Solution;

/**
 * Interface for the Crossover where two parents are combined/altered with a specific crossover probability to create one or more offsprings
 */
public interface Crossover {
  Solution[] crossover(Solution chrom1, Solution chrom2, double crossoverProb);
  boolean getFeasibility();
}
