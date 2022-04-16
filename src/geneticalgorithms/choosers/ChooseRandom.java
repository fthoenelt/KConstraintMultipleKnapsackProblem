package geneticalgorithms.choosers;

import java.util.ArrayList;
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
  public List<Solution> createMatingPool(List<Solution> population, int size) {
    ArrayList<Solution> matingPool = new ArrayList<>(size);
    for(int i = 0; i < size; i++){
      matingPool.add(population.get(rand.nextInt(population.size())));
    }
    return matingPool;
  }
}
