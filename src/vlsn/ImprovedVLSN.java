package vlsn;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import knapsack.Item;
import knapsack.KConstraintMultipleKnapsack;
import knapsack.Knapsack;
import knapsack.Solution;
import vlsn.graph.ConstructImprovementGraph;
import vlsn.graph.ImprovementGraph;

/**
 * Solver for the k-Constraint Multiple Knapsack problem implementing the VLSN-heuristic with random pertubation described by Ahuja and CUnha
 */
public class ImprovedVLSN {

  /**
   * Main method solving the k-MKP for a given knapsack instance
   * @param knapsack the knapsack instance to be solved
   * @return Approximated solution
   */
  public Solution solve(KConstraintMultipleKnapsack knapsack){
    //Obtain a feasible solution S for the k-MKP problem
    Solution cur = GreedySolution.getGreedy(knapsack);

    Solution best = new Solution(cur);

    long startTime = System.currentTimeMillis();
    int i = 1;
    //Stopping criteria limit on iterations
    while(System.currentTimeMillis()-startTime < 300000){
      //Construct the improvement graph
      ImprovementGraph g = ConstructImprovementGraph.constructImprovementGraph(knapsack, cur);
      //Obtain a negative cost subset-disjoint cycle W in G(S)
      Optional<ValidCycle> w = Optional.ofNullable(VLSN.validCycleDetection(g, 8));
      //While such a cycle exists
      while(w.isPresent()){
        //perform the multi-exchange corresponding to W
        cur = cur.applyCycle(w.get());
        //Update S and G(S)
        g = ConstructImprovementGraph.updateImprovementGraph(g, w.get(), knapsack, cur);
        w = Optional.ofNullable(VLSN.validCycleDetection(g, 8));
      }
      //if z(S)>z(S*)
      if(cur.getProfit() > best.getProfit()){
        //then make S the current best solution S*<-S
        best = new Solution(cur);
      }
      //Generate new random pertubed feasible solution S' based on S and make S<-S'
      cur = randomPertubate(cur, knapsack, i, knapsack.getNrKnapsacks());
    }
    return best;
  }

  /**
   * Method preforming the random pertubation for a given knapsack instance, with different cases depending on the current iteration
   * @param knapsack the knapsack instance
   * @param iteration the iteration
   * @param m maximum limit of trials to fit an item to a new knapsack
   * @return the random pertubated knapsack
   */
  private Solution randomPertubate(Solution sol, KConstraintMultipleKnapsack knapsack, int iteration, int m){
    if(iteration%10==0){
      //Method 3
      Random rand = new Random();
      ArrayList<Item> removedItems = new ArrayList<>();
      Iterator<Item> it = sol.getUsedItems().iterator();
      while(it.hasNext()){
        Item i = it.next();
        if(rand.nextBoolean()){
          it.remove();
          removedItems.add(i);
        }
      }
      return fillKnapsack(sol, knapsack, removedItems, m);
    }else if(iteration%4==0){
      //Method 2
      List<Item> usedItems = new RatioSort(new ArrayList<Item>(sol.getUsedItems()), knapsack.getK()).sort();
      int r = new Random().nextInt((usedItems.size()/3)+1);
      usedItems= usedItems.subList(0, r);
      for(Item i: usedItems){
        sol.removeItem(i);
      }
      return fillKnapsack(sol, knapsack, usedItems, m);
    }else{
      //Method 1
      List<Item> usedItems = new RatioSort(new ArrayList<Item>(sol.getUsedItems()), knapsack.getK()).sort();
      int r = new Random().nextInt((usedItems.size()/2)+1);
      usedItems= usedItems.subList(r, usedItems.size());
      for(Item i: usedItems){
        sol.removeItem(i);
      }
      return fillKnapsack(sol, knapsack, usedItems, m);
    }
  }

  private Solution fillKnapsack(Solution sol, KConstraintMultipleKnapsack knapsack, List<Item> items, int m){
    Random rand = new Random();
    for(Item item:items){
      for(int i =0; i < m; i++){
        Knapsack k = knapsack.getKnapsack(rand.nextInt(knapsack.getNrKnapsacks()));
        if(sol.canBeAdded(item, k)){
          sol.addItem(item, k);
          break;
        }
      }
    }
    ArrayList<Item> unusedItems = new ArrayList<>();
    for(Item i: knapsack.getItems()){
      if(!sol.isUsed(i)) unusedItems.add(i);
    }
    sol = GreedySolution.fillGreedy(knapsack,sol,  unusedItems);
    return sol;
  }


}
