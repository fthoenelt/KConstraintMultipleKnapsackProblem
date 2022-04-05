package ganew.population;

import ganew.Chromosom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import knapsack.Item;
import knapsack.KConstraintMultipleKnapsack;
import knapsack.Solution;
import vlsn.GreedySolution;

public class InitializeFeasiblePopulation {
  public static List<Chromosom> initializePopulation(KConstraintMultipleKnapsack knapsack, int size){
    List<Chromosom> pop = new ArrayList<>(size);
    for(int i = 0; i < size-1; i++){
      List<Integer> perm = IntStream.range(0, knapsack.getNrItems()).boxed().collect(Collectors.toList());
      Collections.shuffle(perm);
      pop.add(new Chromosom(perm, knapsack));
    }
    Solution s = GreedySolution.getGreedy(knapsack);
    ArrayList<Integer> items= new ArrayList<>();
    for(int i = 0; i < knapsack.getNrKnapsacks(); i++){
      for(Item item: s.itemsOf(knapsack.getKnapsack(i))){
        items.add(item.getItemID());
      }
    }
    for(Item i: knapsack.getItems()){
      if(!s.isUsed(i)) items.add(i.getItemID());
    }
    Chromosom c = new Chromosom(items, knapsack);
    pop.add(new Chromosom(items, knapsack));
    return pop;
  }
}
