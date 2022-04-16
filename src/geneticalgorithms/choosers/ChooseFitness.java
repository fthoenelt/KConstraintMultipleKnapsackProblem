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

  Random random;

  public ChooseFitness(){
    this.random = new Random();
  }

  @Override
  public List<Solution> createMatingPool(List<Solution> population, int size) {
    double[] fitnessPerChrom = new double[population.size()];
    double sumFit = 0.0;

    for(int i = 0; i < population.size(); i++){
      fitnessPerChrom[i] = population.get(i).getProfit();
      sumFit+=fitnessPerChrom[i];
    }

    assert sumFit !=0.0;

    ArrayList<Double> ratios= new ArrayList<>(population.size());

    for(int i = 0; i < population.size(); i++){
      if(i==0){
        ratios.add(fitnessPerChrom[i] /sumFit);
      }else{
        ratios.add(ratios.get(i-1)+(fitnessPerChrom[i] /sumFit));
      }
    }
    ArrayList<Solution> matingPool = new ArrayList<>(size);
    for(int i = 0; i < size; i++){
      matingPool.add(population.get((-Collections.binarySearch(ratios, random.nextDouble()) - 1)));
    }
    return matingPool;
  }
}
