package geneticalgorithms.startpopulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import knapsack.Item;
import knapsack.KConstraintMultipleKnapsack;
import knapsack.Knapsack;
import knapsack.Solution;
import vlsn.GreedySolution;

public class RandomPopulation implements StartPopulationGenerator{

  Random rand;
  boolean feasible = true;
  KConstraintMultipleKnapsack knapsack;

  public RandomPopulation(KConstraintMultipleKnapsack knapsack){
    this.knapsack = knapsack;
    this.rand = new Random();
  }

  public RandomPopulation(KConstraintMultipleKnapsack knapsack, boolean feasible){
    this.knapsack = knapsack;
    this.feasible = feasible;
    this.rand = new Random();
  }
  @Override
  public List<Solution> generatePopulation(int size) {
    List<Solution> pop = new ArrayList<>(size);
    for(int i = 0 ; i < size-1; i++){
      pop.add(generateRandom());
    }
    pop.add(GreedySolution.getGreedy(knapsack));
    return pop;
  }

  private Solution generateRandom(){
    Solution c = new Solution(knapsack);
    /*
    for(int i = 0; i < knapsack.getNrItems(); i++){
      Item item = knapsack.getItem(rand.nextInt(knapsack.getNrItems()));
      if(c.isUsed(item)){
        continue;
      }
      for(int cnt = 0; cnt < 20; cnt++){
        Knapsack k = knapsack.getKnapsack(rand.nextInt(knapsack.getNrKnapsacks()));
        if(feasible && c.canBeAdded(item, k)){
          c.addItem(item, k);
          break;
        }else if(!feasible){
          c.addItem(item, k);
          break;
        }
      }
    }

     */
   // /*
    for(Item i : knapsack.getItems()){
      for(int cnt = 0; cnt < 20; cnt++){
        Knapsack k = knapsack.getKnapsack(rand.nextInt(knapsack.getNrKnapsacks()));
        if(feasible && c.canBeAdded(i, k)){
          c.addItem(i, k);
          break;
        }else if(!feasible){
          c.addItem(i, k);
          break;
        }
      }
    }

    //*/
    return c;
  }

  @Override
  public boolean getFeasibility() {
    return feasible;
  }
}
