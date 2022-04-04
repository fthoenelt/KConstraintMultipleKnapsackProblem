package geneticalgorithms.choosers;

import java.util.List;
import java.util.Random;
import knapsack.Solution;

public class ChooseRandom implements Chooser{

  Random rand;

  /**
   * Chooses the parents random
   */
  public ChooseRandom(){
    this.rand = new Random();
  }
  @Override
  public Solution[] choose(List<Solution> pop) {
    return new Solution[]{pop.get(rand.nextInt(pop.size())), pop.get(rand.nextInt(pop.size()))};
  }
}
