package geneticalgorithms.choosers;

import java.util.List;
import java.util.Random;
import knapsack.Solution;

public class ChooseRandom implements Chooser{

  Random rand;
  List<Solution> pop;
  /**
   * Chooses the parents random
   */
  public ChooseRandom(){
    this.rand = new Random();
  }

  @Override
  public void update(List<Solution> pop) {
    this.pop = pop;
  }

  @Override
  public Solution[] choose() {
    return new Solution[]{pop.get(rand.nextInt(pop.size())), pop.get(rand.nextInt(pop.size()))};
  }
}
