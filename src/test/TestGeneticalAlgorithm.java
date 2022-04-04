package test;

import geneticalgorithms.choosechildren.FittestChild;
import geneticalgorithms.generation.CutGeneration;
import geneticalgorithms.mutators.BitflipMutator;
import geneticalgorithms.choosers.ChooseFitness;
import geneticalgorithms.choosers.ChooseRandom;
import geneticalgorithms.choosers.Chooser;
import geneticalgorithms.crossover.Crossover;
import geneticalgorithms.GeneticalAlgorithm;
import geneticalgorithms.mutators.GreedyMutator;
import geneticalgorithms.stopcriterias.ImprovementStopper;
import geneticalgorithms.stopcriterias.IterationStopper;
import geneticalgorithms.mutators.Mutator;
import geneticalgorithms.crossover.OnePointCrossover;
import geneticalgorithms.crossover.RandomCrossover;
import geneticalgorithms.startpopulation.RandomPopulation;
import geneticalgorithms.stopcriterias.StopCriteria;
import geneticalgorithms.stopcriterias.TimeStopper;
import knapsack.KConstraintMultipleKnapsack;
import knapsack.Solution;
import library.KnapsackLibrary;
import library.KnapsackLibraryReader;
import org.junit.Test;
import vlsn.GreedySolution;

public class TestGeneticalAlgorithm {

  @Test
  public void test(){
    int popSize = 1000;
    int iterations = 1000;
    int maxSize = 1500;
    KnapsackLibrary lib = KnapsackLibraryReader.readFile("knapsacks.ser");
    assert lib != null;
    int i = 0;
    int p = 0;
    long t = 0;
    for(KConstraintMultipleKnapsack knapsack : lib.getKnapsacks()){
      System.out.println("Test for GA  with population size "+popSize+", Nr of iterations "+ iterations + ", max pop size "+maxSize);
      System.out.println("-------------------");
      System.out.println("Instance Nr. "+i);
      long startGreedy = System.currentTimeMillis();
      Solution sGreedy = GreedySolution.getGreedy(knapsack);
      long endGreedy = System.currentTimeMillis();
      long startGA = System.currentTimeMillis();
      Solution s = new GeneticalAlgorithm(knapsack, popSize, iterations, maxSize).solve();
      long endGA = System.currentTimeMillis();
      p+=s.getProfit();
      t+= endGA-startGA;
      assert s.isFeasible();
      System.out.println("GreedyProfit: "+ sGreedy.getProfit()+ ", Greedy Time: "+ (endGreedy-startGreedy));
      System.out.println("GAProfit: "+ s.getProfit()+ ", GATime: "+ (endGA-startGA));
      i++;
    }
    System.out.println("Avg. Profit = "+ (double)p/lib.getKnapsacks().size());
    System.out.println("Avg. Time = "+(double)t/lib.getKnapsacks().size());
  }


  @Test
  public void testAll(){
    KnapsackLibrary lib = KnapsackLibraryReader.readFile("knapsacks.ser");
    assert lib != null;
    int bestProfit = 0;
    long bestTime = Integer.MAX_VALUE;
    int[] bestProfitMod = new int[3];
    int[] bestTimeMod = new int[3];
    for(int popSize: new int[]{100, 500, 1000, 1500, 2000, 5000, 10000}){
      for(int iterations: new int[]{100, 500, 1000, 1500, 2000, 5000, 10000}){
        for(int maxSize : new int[]{100, 500, 1000, 1500, 2000, 5000, 10000}){
          int profit = 0;
          long time = 0;
          for(KConstraintMultipleKnapsack knapsack : lib.getKnapsacks()){
            long start = System.currentTimeMillis();
            Solution s = new GeneticalAlgorithm(knapsack, popSize, iterations, maxSize).solve();
            long end = System.currentTimeMillis();
            profit += s.getProfit();
            time += end-start;
          }
          if(profit > bestProfit){
            bestProfit = profit;
            bestProfitMod = new int[]{popSize, iterations, maxSize};
          }
          if(time < bestTime){
            bestTime = time;
            bestTimeMod =new int[]{popSize, iterations, maxSize};
          }
        }
      }
    }
    System.out.println("Best Profit:"+ (double)bestProfit/lib.getKnapsacks().size());
    System.out.println("Modification: popSize = "+ bestProfitMod[0]+ ", iterations: "+ bestProfitMod[1]+ ", maxSize = "+ bestProfitMod[2]);
    System.out.println("Best Time:"+ (double)bestTime/lib.getKnapsacks().size());
    System.out.println("Modification: popSize = "+ bestTimeMod[0]+ ", iterations: "+ bestTimeMod[1]+ ", maxSize = "+ bestTimeMod[2]);
  }

  @Test
  public void testDifferentMod(){
    KnapsackLibrary lib = KnapsackLibraryReader.readFile("knapsacks.ser");
    for(Mutator mutator: new Mutator[]{new BitflipMutator(true), new GreedyMutator(true)}){
      for(Crossover crossover: new Crossover[]{new RandomCrossover(true), new OnePointCrossover(true)}){
        for (Chooser chooser:new Chooser[]{new ChooseFitness(), new ChooseRandom()}){
          for(StopCriteria criteria: new StopCriteria[]{new IterationStopper(1000), new ImprovementStopper(1000)}){
            System.out.println("Mutator: "+ mutator.getClass()+", Crossover: "+ crossover.getClass()+ ", StopCriteria: "+ criteria.getClass()+ ", Chooser:"+chooser.getClass());
            int profit = 0;
            long time = 0;
            assert lib != null;
            for(KConstraintMultipleKnapsack knapsack : lib.getKnapsacks()){
              long start = System.currentTimeMillis();
              Solution s =
                  new GeneticalAlgorithm(knapsack, crossover, mutator, new RandomPopulation(knapsack), 1000, criteria, chooser, 1500, new CutGeneration(), false, 0.9, 0.1, true, new FittestChild()).solve();
              long end = System.currentTimeMillis();
              profit += s.getProfit();
              time += end-start;
            }
            System.out.println("Profit: "+ profit);
            System.out.println("Time: "+ time);
          }
        }
      }
    }
  }

  @Test
  public void testTestInstances(){
    final Crossover c = new OnePointCrossover(true);
    final Mutator m = new BitflipMutator(true);
    final int popSize = 1000;
    final Chooser chooser = new ChooseFitness();
    final int maxSize = 2000;
    for(int i: new int[]{/*50,100,200,*/500}){
      for(int j = 0; j < 16; j++){
        KnapsackLibrary lib = KnapsackLibraryReader.readFile("/home/felix/Dokumente/Studium/WS2122/BA/KConstraintMultipleKnapsackProblem/src/testinstances/knapsack"+i+
            "nr"+j+".ser");
        assert lib != null;
        KConstraintMultipleKnapsack k = lib.getKnapsacks().get(0);
        Solution sol =
            new GeneticalAlgorithm(k, c, m, new RandomPopulation(k), popSize, new TimeStopper(300000), chooser, maxSize, new CutGeneration(), false, 0.9, 0.01, true, new FittestChild()).solve();
        System.out.println("Size: "+ i + ", instance: "+j+", value: "+ sol.getProfit());
      }
    }
  }
}
