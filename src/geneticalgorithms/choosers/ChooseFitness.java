package geneticalgorithms.choosers;

import ganew.Chromosom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import knapsack.Solution;

/**
 * Chosses two parent solutions by RouletteWheelSelection where every individual in the population gets a space on an "RouletteWheel" proportional to its fitness.
 * Parents are then choosen by calculating a random point on the wheel
 */
public class ChooseFitness implements Chooser{

  ArrayList<Double> ratios;
  Random random;
  List<Solution> pop;
  public ChooseFitness(){
    this.random = new Random();
  }

  @Override
  public void update(List<Solution> pop) {
    this.pop = pop;
    double[] fitnessPerChrom = new double[pop.size()];
    double sumFit = 0.0;

    for(int i = 0; i < pop.size(); i++){
      fitnessPerChrom[i] = pop.get(i).getProfit();
      sumFit+=fitnessPerChrom[i];
    }

    assert sumFit !=0.0;

    this.ratios = new ArrayList<>(pop.size());

    for(int i = 0; i < pop.size(); i++){
      if(i==0){
        ratios.add(fitnessPerChrom[i] /sumFit);
      }else{
        ratios.add(ratios.get(i-1)+(fitnessPerChrom[i] /sumFit));
      }
    }

  }

  @Override
  public Solution[] choose() {
    assert pop != null && ratios != null;
    return new Solution[]{pop.get((-Collections.binarySearch(ratios, random.nextDouble()) - 1)), pop.get((-Collections.binarySearch(ratios, random.nextDouble()) - 1))};
  }
}
