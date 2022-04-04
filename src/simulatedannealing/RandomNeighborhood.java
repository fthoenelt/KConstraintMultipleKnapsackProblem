package simulatedannealing;

import java.util.Random;
import knapsack.Item;
import knapsack.KConstraintMultipleKnapsack;
import knapsack.Knapsack;
import knapsack.Solution;

public class RandomNeighborhood implements Neighborhood{
  Random rand;
  KConstraintMultipleKnapsack knapsack;
  int maxIt;

  public RandomNeighborhood(KConstraintMultipleKnapsack knapsack, int maxIt){
    this.rand = new Random();
    this.knapsack = knapsack;
    this.maxIt=maxIt;
  }

  @Override
  public Solution getNeighbor(Solution solution) {
    Solution neighbor = new Solution(solution);
    for(int i = 0; i< maxIt; i++){
      Item item  = knapsack.getItem(rand.nextInt(knapsack.getNrItems()));
      if(neighbor.isUsed(item)){
        neighbor.removeItem(item);
        return neighbor;
      }else{
        for(Knapsack k : knapsack.getKnapsacks()){
          if(neighbor.canBeAdded(item, k)){
            neighbor.addItem(item, k);
            return neighbor;
          }
        }
      }
    }
    return neighbor;
  }
}
