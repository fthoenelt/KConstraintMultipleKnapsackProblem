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
import geneticalgorithms.startpopulation.RandomPopulation;
import geneticalgorithms.startpopulation.StartPopulationGenerator;
import geneticalgorithms.stopcriterias.ImprovementStopper;
import geneticalgorithms.stopcriterias.StopCriteria;
import java.util.ArrayList;
import java.util.Comparator;
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

  public GeneticalAlgorithm(KConstraintMultipleKnapsack knapsack, int popSize, int iterations, int maxSize){
    this.knapsack = knapsack;
    this.crossover = new OnePointCrossover(true, true);
    this.mutator = new BitflipMutator(true);
    this.generator = new RandomPopulation(knapsack);
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
  }

  public GeneticalAlgorithm(KConstraintMultipleKnapsack knapsack, Crossover c, Mutator m, StartPopulationGenerator generator, int popSize, StopCriteria crit, Chooser chooser,
      int maxSize, GenerationGenerator generationGenerator, boolean addBoth, double crossoverProb, double mutationProb, boolean oneChild, ChooseChildren chooseChildren){
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
  }
  //TODO: newGeneration größer!
  public Solution solve(){
    //Initialize population
    List<Solution> oldGeneration = generator.generatePopulation(this.popSize);
    //Save best Solution
    Solution best = new Solution(knapsack);
    int bestFit = 0;
    boolean action = false;
    //While exit criterion not reached
    while(!crit.stop(action)){
      //Create a new, empty generation
      List<Solution> newGeneration = new ArrayList<>();
      action = false;
      for(int i = 0; i < popSize; i++) {
        //Choose the parents of the next offspring
        Solution[] parents = chooser.choose(oldGeneration);
        //Get the offspring determined by crossovering the two parents
        Solution[] children = crossover.crossover(parents[0], parents[1], crossoverProb);
        //If one offspring was calculated
        if (children.length == 1) {
          //Mutate it with a certain probability and add it to the new generation
          newGeneration.add((rand.nextDouble() < mutationProb) ? mutator.mutate(children[0]) : children[0]);
        } else {
          //Otherwise check if one or two offsprings are to be added
          if (oneChild) {
            newGeneration.add((rand.nextDouble() < mutationProb) ? mutator.mutate(chooseChildren.chooseChild(children)) : chooseChildren.chooseChild(children));
          } else {
            newGeneration.add((rand.nextDouble() < mutationProb) ? mutator.mutate(children[0]) : children[0]);
            newGeneration.add((rand.nextDouble() < mutationProb) ? mutator.mutate(children[1]) : children[1]);
          }
        }
      }
      //Save this new generation in a certain way
      oldGeneration = generationGenerator.generate(oldGeneration, newGeneration, this.maxSize);
      //Get the best offspring in the new generation
      Solution curBest = oldGeneration.get(0);
      int index = 1;
      while(!curBest.isFeasible()&&index < oldGeneration.size()){
        curBest = oldGeneration.get(index);
        index++;
      }
      //If it is the best one save it
      int curBestFit = curBest.getProfit();
      if(curBestFit > bestFit&&curBest.isFeasible()){
        bestFit = curBestFit;
        best = curBest;
        action = true;
      }
    }

    assert best.isFeasible();
    return best;
  }

}
