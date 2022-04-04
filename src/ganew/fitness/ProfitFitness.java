package ganew.fitness;

import ganew.Chromosom;
import java.util.Arrays;
import knapsack.KConstraintMultipleKnapsack;

public class ProfitFitness implements FitnessFunction{
  KConstraintMultipleKnapsack knapsack;
  public ProfitFitness(KConstraintMultipleKnapsack knapsack){
    this.knapsack = knapsack;
  }

  @Override
  public int getFitness(Chromosom chromosom) {
    int fit=0;
    for(int i = 0; i < chromosom.getSolution().length; i++){
      if(Arrays.stream(chromosom.getSolution()[i]).sum()>0){
        fit += knapsack.getItem(i).getProfit();
      }
    }
    return fit;
  }
}
