package ganew.population;

import ganew.Chromosom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import knapsack.KConstraintMultipleKnapsack;

public class InitializeFeasiblePopulation {
  public static List<Chromosom> initializePopulation(KConstraintMultipleKnapsack knapsack, int size){
    List<Chromosom> pop = new ArrayList<>(size);
    for(int i = 0; i < size; i++){
      List<Integer> perm = IntStream.range(0, knapsack.getNrItems()).boxed().toList();
      Collections.shuffle(perm);
      pop.add(new Chromosom(perm, knapsack));
    }
    return pop;
  }
}
