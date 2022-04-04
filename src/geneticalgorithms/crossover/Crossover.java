package geneticalgorithms.crossover;

import knapsack.Solution;

public interface Crossover {
  Solution[] crossover(Solution chrom1, Solution chrom2, double crossoverProb);
  boolean getFeasibility();
}
