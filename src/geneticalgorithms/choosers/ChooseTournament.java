package geneticalgorithms.choosers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import knapsack.Solution;

public class ChooseTournament implements Chooser{

  Random random;
  int s;

  public ChooseTournament(int s){
    this.random = new Random();
    this.s = s;
  }

  @Override
  public List<Solution> createMatingPool(List<Solution> population, int size) {
    ArrayList<Solution> matingPool = new ArrayList<>(size);
    for(int i = 0; i < size; i++){
      Solution[] candidates = new Solution[s];
      for(int index = 0; index < s; index++){
        candidates[index] = population.get(random.nextInt(population.size()));
      }
      Optional<Solution> o = Arrays.stream(candidates).max(Comparator.comparingInt(Solution::getProfit));
      assert o.isPresent();
      matingPool.add(o.get());
    }
    return matingPool;
  }
}
