package test;

import geneticalgorithms.GeneticalAlgorithm;
import knapsack.KConstraintMultipleKnapsack;
import knapsack.Solution;
import library.KnapsackLibrary;
import library.KnapsackLibraryReader;
import org.junit.Test;

public class TestValues {
  @Test
  public void testValues(){
    for(int i = 0; i < 16; i++){
      KnapsackLibrary lib = KnapsackLibraryReader.readFile("src/testinstances/knapsack"+i+".ser");
      int profit=0;
      long time = 0;
      assert lib != null;
      for(KConstraintMultipleKnapsack knapsack: lib.getKnapsacks()){
        long start = System.currentTimeMillis();
        Solution s = new GeneticalAlgorithm(knapsack, 2000, 4000, 2500).solve();
        long end = System.currentTimeMillis();
        time += end-start;
        profit += s.getProfit();
      }
      System.out.println("Instances: "+i);
      System.out.println("Time: "+time/10);
      System.out.println("Profit: "+profit/10);
    }
  }
}
