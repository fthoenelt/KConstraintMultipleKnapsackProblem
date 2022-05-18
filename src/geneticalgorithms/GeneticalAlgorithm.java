package geneticalgorithms;

import geneticalgorithms.choosechildren.ChooseChildren;
import geneticalgorithms.choosechildren.FittestChild;
import geneticalgorithms.choosers.ChooseFitness;
import geneticalgorithms.choosers.Chooser;
import geneticalgorithms.crossover.Crossover;
import geneticalgorithms.crossover.OnePointCrossover;
import geneticalgorithms.generation.CutGeneration;
import geneticalgorithms.generation.GenerationGenerator;
import geneticalgorithms.mutators.BitflipMutator;
import geneticalgorithms.mutators.Mutator;
import geneticalgorithms.startpopulation.RandomSortedPopulation;
import geneticalgorithms.startpopulation.StartPopulationGenerator;
import geneticalgorithms.stopcriterias.ImprovementStopper;
import geneticalgorithms.stopcriterias.StopCriteria;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import knapsack.KConstraintMultipleKnapsack;
import knapsack.Solution;

public class GeneticalAlgorithm {
  KConstraintMultipleKnapsack knapsack;
  Crossover crossover;
  Mutator mutator;
  StartPopulationGenerator generator;
  int popSize;
  StopCriteria crit;
  Chooser chooser;
  int maxSize;
  GenerationGenerator generationGenerator;
  boolean addBoth;
  double crossoverProb;
  double mutationProb;
  boolean oneChild;
  ChooseChildren chooseChildren;
  Random rand = new Random();
  int poolSize;

  public GeneticalAlgorithm(KConstraintMultipleKnapsack knapsack, int popSize, int iterations, int maxSize){
    this.knapsack = knapsack;
    this.crossover = new OnePointCrossover(true, true);
    this.mutator = new BitflipMutator(true);
    this.generator = new RandomSortedPopulation(knapsack);
    this.popSize = popSize;
    this.crit = new ImprovementStopper(iterations);
    this.chooser = new ChooseFitness();
    this.maxSize = maxSize;
    this.generationGenerator = new CutGeneration();
    this.addBoth = false;
    this.crossoverProb = 0.9;
    this.mutationProb = 0.9;
    oneChild = true;
    this.chooseChildren = new FittestChild();
    this.poolSize = popSize;
  }

  public GeneticalAlgorithm(KConstraintMultipleKnapsack knapsack, Crossover c, Mutator m, StartPopulationGenerator generator, int popSize, StopCriteria crit, Chooser chooser,
      int maxSize, GenerationGenerator generationGenerator, boolean addBoth, double crossoverProb, double mutationProb, boolean oneChild, ChooseChildren chooseChildren, int poolSize){
    assert c.getFeasibility()==m.getFeasibility();
    c.getFeasibility();
    this.knapsack = knapsack;
    this.crossover = c;
    this.mutator = m;
    this.generator = generator;
    this.popSize = popSize;
    this.crit = crit;
    this.chooser = chooser;
    this.maxSize = maxSize;
    this.generationGenerator = generationGenerator;
    this.addBoth = addBoth;
    this.crossoverProb = crossoverProb;
    this.mutationProb=mutationProb;
    this.oneChild = oneChild;
    this.chooseChildren = chooseChildren;
    this.poolSize = poolSize;
  }

  public Solution solve(){
    //Initialize population
    List<Solution> oldGeneration = generator.generatePopulation(this.popSize);
    //Save best Solution
    Solution best = new Solution(knapsack);
    int bestFit = 0;
    for(Solution s : oldGeneration){
      if(s.getProfit() > bestFit){
        best = s;
        bestFit=s.getProfit();
      }
    }
    boolean action = false;
    //While exit criterion not reached
    while(!crit.stop(action)){
      //Create a new, empty generation
      List<Solution> newGeneration = new ArrayList<>(popSize);
      List<Solution> matingPool = chooser.createMatingPool(oldGeneration, poolSize);
      while(newGeneration.size() <= popSize) {
        //Choose the parents of the next offspring
        Solution p1 = matingPool.get(rand.nextInt(poolSize));
        Solution p2 = matingPool.get(rand.nextInt(poolSize));
        //Get the offspring determined by crossovering the two parents
        Solution child;
        if(rand.nextDouble() < crossoverProb){
          child = crossover.crossover(p1, p2);
        }else{
          child = (rand.nextBoolean())?p1:p2;
        }
        if(rand.nextDouble() < mutationProb) child = mutator.mutate(child);
        if(child.getProfit() > bestFit && child.isFeasible()){
          best = child;
          bestFit = child.getProfit();
        }
        newGeneration.add(child);
      }
      //Save this new generation in a certain way
      oldGeneration = generationGenerator.generate(oldGeneration, newGeneration, this.maxSize);
    }

    assert best.isFeasible();
    return best;
  }

}

