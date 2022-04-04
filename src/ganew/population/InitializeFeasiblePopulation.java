package ganew.population;

import ganew.Chromosom;
import knapsack.KConstraintMultipleKnapsack;

public class InitializeFeasiblePopulation {
  int size;
  KConstraintMultipleKnapsack knapsack;

  public InitializeFeasiblePopulation(int size, KConstraintMultipleKnapsack knapsack){
    this.size = size;
    this.knapsack = knapsack;
  }

  public Chromosom[] initializePopulation(){
    return new Chromosom[0];
  }
}
