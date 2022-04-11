package ganew.selection;

import ganew.Chromosom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomPool implements Selector{
  Random random;
  public RandomPool(){
    this.random = new Random();
  }
  @Override
  public List<Chromosom> createMatingPool(List<Chromosom> population, int size) {
    ArrayList<Chromosom> matingPool = new ArrayList<>(size);
    for(int i = 0; i < size; i++){
      matingPool.add(population.get(random.nextInt(population.size())));
    }
    return matingPool;
  }
}
