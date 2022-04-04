package test;

import knapsack.KConstraintMultipleKnapsack;
import knapsack.Solution;
import library.KnapsackLibrary;
import library.KnapsackLibraryReader;
import org.junit.Test;
import simulatedannealing.SimulatedAnnealing;
import vlsn.GreedySolution;

public class TestSimulatedAnnealing {
  @Test
  public void test(){
    KnapsackLibrary lib = KnapsackLibraryReader.readFile("knapsacks.ser");
    final int iterations = 10000;
    final double alpha = 0.9;
    final double start = 10.0;
    int i = 0;
    assert lib!= null;
    for(KConstraintMultipleKnapsack knapsack: lib.getKnapsacks()){
      System.out.println("-------------------");
      System.out.println("Instance Nr. "+i);
      long startGreedy = System.currentTimeMillis();
      Solution sGreedy = GreedySolution.getGreedy(knapsack);
      long endGreedy = System.currentTimeMillis();
      long startGA = System.currentTimeMillis();
      Solution s = new SimulatedAnnealing(knapsack, iterations, alpha, start).solve();
      long endGA = System.currentTimeMillis();
      assert s.isFeasible();
      System.out.println("GreedyProfit: "+ sGreedy.getProfit()+ ", Greedy Time: "+ (endGreedy-startGreedy));
      System.out.println("GAProfit: "+ s.getProfit()+ ", GATime: "+ (endGA-startGA));
      i++;
    }
  }


}
