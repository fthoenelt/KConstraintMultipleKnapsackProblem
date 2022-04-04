package ganew;

import java.util.Arrays;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;
import knapsack.KConstraintMultipleKnapsack;
import knapsack.Knapsack;

public class Chromosom {
  private int[][] solution;
  private KConstraintMultipleKnapsack knapsack;

  public Chromosom(KConstraintMultipleKnapsack knapsack){
    this.solution = new int[knapsack.getNrItems()][knapsack.getNrKnapsacks()];
    this.knapsack = knapsack;
  }

  public int[][] getSolution(){return solution;}

  public boolean getFeasibility(){
    for(int i = 0; i < knapsack.getNrKnapsacks(); i++){
      int[] sum = new int[knapsack.getK()];
      for(int j = 0; j < knapsack.getNrItems();j++){
        for(int k = 0; k < knapsack.getK(); k ++){
          sum[k]+= solution[j][i]*knapsack.getItem(j).getWeight(k);
        }
      }
      for(int k = 0; k < knapsack.getK(); k++){
        if(sum[k]>knapsack.getKnapsack(i).getCapacities(k)){return false;}
      }
    }
    for(int[] row: solution){
      if(Arrays.stream(row).sum()>1) return false;
    }
    return true;
  }

}
