package test;

import geneticalgorithms.GeneticalAlgorithm;
import knapsack.KConstraintMultipleKnapsack;
import knapsack.Solution;
import library.KnapsackLibrary;
import library.KnapsackLibraryReader;
import org.junit.Test;
import vlsn.ImprovedVLSN;

public class TestInstances {
  @Test
  public void test(){
    int popSize = 1000;
    int iterations = 1000;
    int maxSize = 1500;
    KnapsackLibrary lib = KnapsackLibraryReader.readFile("/home/felix/Dokumente/Studium/WS2122/BA/KConstraintMultipleKnapsackProblem/src/testinstances/knapsack500nr0.ser");
    assert lib != null;
    KConstraintMultipleKnapsack knapsack = lib.getKnapsacks().get(0);

    Solution sol1 = new ImprovedVLSN().solve(knapsack);

    Solution s = new GeneticalAlgorithm(knapsack, popSize, iterations, maxSize).solve();

    System.out.println(sol1.getProfit());
    System.out.println(s.getProfit());
  }
}
