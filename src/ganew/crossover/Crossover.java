package ganew.crossover;

import ganew.Chromosom;

public interface Crossover {
  Chromosom crossover(Chromosom p1, Chromosom p2);
}
