package ganew;

import ganew.population.InitializeFeasiblePopulation;
import geneticalgorithms.stopcriterias.StopCriteria;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import knapsack.KConstraintMultipleKnapsack;

public class GeneticalAlgorithm {
  KConstraintMultipleKnapsack knapsack;
  int popSize;
  int iterations;
  int maxSize;
  StopCriteria criteria;
  Random random;
  double crossoverProb;
  double mutationProb;

  public GeneticalAlgorithm(KConstraintMultipleKnapsack knapsack, int popSize, int iterations, int maxSize, StopCriteria criteria, double crossoverProb, double mutationProb){
    this.knapsack = knapsack;
    this.popSize = popSize;
    this.iterations = iterations;
    this.maxSize = maxSize;
    this.criteria = criteria;
    this.random = new Random();
    this.crossoverProb = crossoverProb;
    this.mutationProb = mutationProb;
  }
  public Chromosom solve(){
    List<Chromosom> population = InitializeFeasiblePopulation.initializePopulation(knapsack, popSize);
    Chromosom best = null;
    int bestFit = 0;
    for(Chromosom c : population){
      int fitness = c.getFitness();
      if(fitness > bestFit){
        bestFit=fitness;
        best = c;
      }
    }
    boolean action = false;
    while(!criteria.stop(action)){
      //Create new pop
      //Choose Parents (Completely random)
      List<Chromosom> newPop = new ArrayList<>(popSize);
      while(newPop.size()<popSize){
        //Select individuals
        Chromosom p1 = population.get(random.nextInt(population.size()));
        Chromosom p2 = population.get(random.nextInt(population.size()));
        Chromosom chromosom = null;
        //Recombination
        if(random.nextDouble() < crossoverProb){
          Integer[] child = new Integer[knapsack.getNrItems()];
          ArrayList<Integer> notUsed = new ArrayList<>(p2.getSolution());
          for(int i = 0; i < knapsack.getNrItems(); i++){
            if(random.nextBoolean()){
              child[i] = p1.getSolution().get(i);
              notUsed.remove(p1.getSolution().get(i));
            }
          }
          for(int i = 0; i < child.length; i++){
            if(child[i]==null){
              assert !notUsed.isEmpty();
              child[i] = notUsed.remove(0);
            }
          }
          chromosom= new Chromosom(List.of(child), knapsack);
        }else{
          chromosom = (random.nextBoolean())?p1:p2;
        }
        if(random.nextDouble()<mutationProb){
          int index1 = random.nextInt(knapsack.getNrKnapsacks());
          int index2 = random.nextInt(knapsack.getNrKnapsacks());
          Integer value = chromosom.getSolution().get(index2);
          chromosom.setValue(index2, chromosom.getSolution().get(index1));
          chromosom.setValue(index1, value);
        }
        int fitness = chromosom.getFitness();
        if(fitness > bestFit){
          bestFit=fitness;
          best = chromosom;
        }
        newPop.add(chromosom);
      }

    }
    return best;
  }



}
