package ganew;

import ganew.population.InitializeFeasiblePopulation;
import geneticalgorithms.stopcriterias.StopCriteria;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import knapsack.KConstraintMultipleKnapsack;
import knapsack.Solution;

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
    int[] fitnesses = new int[population.size()];
    int fitSum = 0;
    boolean action = false;
    while(!criteria.stop(action)){
      //Create new pop
      for(int i = 0; i < population.size(); i++){
        fitnesses[i] = population.get(i).getFitness();
        fitSum += fitnesses[i];
        if(fitnesses[i] > bestFit){
          bestFit=fitnesses[i];
          best = population.get(i);
        }
      }
      //Choose Parents (Completely random)
      List<Chromosom> newPop = new ArrayList<>(popSize);
      while(newPop.size()<popSize){
        ArrayList<Double> ratios = new ArrayList<>(population.size());
        for(int i = 0; i < population.size(); i++){
          if(i==0){
            ratios.add((double) fitnesses[i]/(double) fitSum);
          }else{
            ratios.add(ratios.get(i-1)+ ((double) fitnesses[i]/(double) fitSum));
          }
        }
        Chromosom p1 = population.get( (-Collections.binarySearch(ratios, random.nextDouble()) - 1));
        Chromosom p2 = population.get((-Collections.binarySearch(ratios, random.nextDouble()) - 1));
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
        int fitness = chromosom.getFitness();
        if(fitness > bestFit){
          bestFit=fitness;
          best = chromosom;
        }
        newPop.add(chromosom);
      }
      population = newPop;
      fitnesses = new int[population.size()];
      fitSum = 0;
    }
    return best;
  }



}
