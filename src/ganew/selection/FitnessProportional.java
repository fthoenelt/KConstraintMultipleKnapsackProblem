package ganew.selection;

import ganew.Chromosom;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class FitnessProportional implements Selector{
  Random random;

  public FitnessProportional(){
    this.random = new Random();
  }

  @Override
  public List<Chromosom> createMatingPool(List<Chromosom> population, int size) {
    int[] fitnesses = new int[population.size()];
    int fitSum = 0;
    for(int i = 0; i < population.size(); i++){
      fitnesses[i] = population.get(i).getFitness();
      fitSum += fitnesses[i];
      //If the fitness is better then the best found save it
    }
    ArrayList<Double> ratios = new ArrayList<>(population.size());
    for(int i = 0; i < population.size(); i++){
      if(i==0){
        ratios.add((double) fitnesses[i]/(double) fitSum);
      }else{
        ratios.add(ratios.get(i-1)+ ((double) fitnesses[i]/(double) fitSum));
      }
    }
    ArrayList<Chromosom> matingPool = new ArrayList<>(size);
    for(int i = 0; i < size; i++){
      matingPool.add(population.get((-Collections.binarySearch(ratios, random.nextDouble()) - 1)));
    }
    return matingPool;
  }
}
