package ganew.crossover;

import ganew.Chromosom;
import knapsack.KConstraintMultipleKnapsack;

public interface Crossover {
  Chromosom crossover(KConstraintMultipleKnapsack knapsack, Chromosom p1, Chromosom p2);
}
