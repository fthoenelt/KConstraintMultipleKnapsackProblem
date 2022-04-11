package test;

import ganew.Chromosom;
import ganew.GeneticalAlgorithm;
import ganew.selection.FitnessProportional;
import ganew.selection.RandomPool;
import geneticalgorithms.stopcriterias.IterationStopper;
import geneticalgorithms.stopcriterias.TimeStopper;
import knapsack.KConstraintMultipleKnapsack;
import knapsack.Solution;
import library.KnapsackLibrary;
import library.KnapsackLibraryReader;
import org.junit.Test;
import vlsn.GreedySolution;

public class TestNewGA {

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
      Chromosom s = new GeneticalAlgorithm(knapsack, popSize, iterations, maxSize, new TimeStopper(50000), 1.0,1.0, new FitnessProportional(), popSize).solve();
      long endGA = System.currentTimeMillis();
      p+=s.getFitness();
      t+= endGA-startGA;
      System.out.println("GreedyProfit: "+ sGreedy.getProfit()+ ", Greedy Time: "+ (endGreedy-startGreedy));
      System.out.println("GAProfit: "+ s.getFitness()+ ", GATime: "+ (endGA-startGA));
      i++;
    }
    System.out.println("Avg. Profit = "+ (double)p/lib.getKnapsacks().size());
    System.out.println("Avg. Time = "+(double)t/lib.getKnapsacks().size());
  }

}
