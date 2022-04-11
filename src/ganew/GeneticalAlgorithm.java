package ganew;

import ganew.population.InitializeFeasiblePopulation;
import ganew.selection.Selector;
import geneticalgorithms.stopcriterias.StopCriteria;
import java.util.ArrayList;
import java.util.Arrays;
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
  Selector selector;
  int poolSize;

  public GeneticalAlgorithm(KConstraintMultipleKnapsack knapsack, int popSize, int iterations, int maxSize, StopCriteria criteria, double crossoverProb, double mutationProb,
      Selector selector, int poolSize){
    this.knapsack = knapsack;
    this.popSize = popSize;
    this.iterations = iterations;
    this.maxSize = maxSize;
    this.criteria = criteria;
    this.random = new Random();
    this.crossoverProb = crossoverProb;
    this.mutationProb = mutationProb;
    this.selector = selector;
    this.poolSize = poolSize;
  }

  /**
   * Solves the K-MKP with an genetical Algorithm based on Permutations as Chromosomes
   *
   * @return The best found Chromosom
   */
  public Chromosom solve(){
    //Get a initial population
    List<Chromosom> population = InitializeFeasiblePopulation.initializePopulation(knapsack, popSize);
    //Stores the best found Chromosom as well as its fitness
    Chromosom best = null;
    int bestFit = 0;
    for(Chromosom c : population){
      if(c.getFitness() > bestFit){
        best = c;
        bestFit = c.getFitness();
      }
    }
    boolean action = false;
    while(!criteria.stop(action)){
      //Create new generation
      List<Chromosom> newPop = new ArrayList<>(popSize);
      //Calculate fitnesses for the generation
      List<Chromosom> matingPool = selector.createMatingPool(population, poolSize);
      //Create individuals for the next generation
      while(newPop.size()<popSize){
        Chromosom p1 = matingPool.get(random.nextInt(poolSize));
        Chromosom p2 = matingPool.get(random.nextInt(poolSize));;
        //Select individuals
        Chromosom chromosom;
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
          chromosom= new Chromosom(Arrays.asList(child), knapsack);
        }else{
          chromosom = (random.nextBoolean())?p1:p2;
        }
        if(random.nextDouble()<mutationProb){
          int index1 = random.nextInt(knapsack.getNrKnapsacks());
          int index2 = random.nextInt(knapsack.getNrKnapsacks());
          chromosom.swap(index1, index2);

        }
        newPop.add(chromosom);
        if(chromosom.getFitness() > bestFit){
          best = chromosom;
          bestFit = chromosom.getFitness();
        }
      }
      population = newPop;
    }
    return best;
  }



}
