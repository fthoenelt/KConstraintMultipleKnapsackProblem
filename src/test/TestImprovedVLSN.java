package test;

import knapsack.KConstraintMultipleKnapsack;
import knapsack.Solution;
import library.KnapsackLibrary;
import library.KnapsackLibraryReader;
import org.junit.Test;
import vlsn.GreedySolution;
import vlsn.ImprovedVLSN;
import vlsn.VLSN;

public class TestImprovedVLSN {
  @Test
  public void test(){
    KnapsackLibrary lib = KnapsackLibraryReader.readFile("/home/felix/Dokumente/Studium/WS2122/BA/KConstraintMultipleKnapsackProblem/src/testinstances/knapsack500nr0.ser");
    assert lib != null;
    KConstraintMultipleKnapsack knapsack = lib.getKnapsacks().get(0);
/*
    long start = System.currentTimeMillis();
    Solution greedySolution = GreedySolution.getGreedy(knapsack);
    assert  greedySolution.isFeasible();
    System.out.println("Greedy Profit: "+ greedySolution.getProfit());
    System.out.println("Time for Greedy: "+ (System.currentTimeMillis()-start)+" ms");


 */
    knapsack = lib.getKnapsacks().get(0);
    long start = System.currentTimeMillis();
    Solution vlsnSolution = new VLSN().solve(knapsack);
    assert  vlsnSolution.isFeasible();
    System.out.println("VLSN Profit: "+ vlsnSolution.getProfit());
    System.out.println("Time for VLSN: "+ (System.currentTimeMillis()-start)+" ms");

    knapsack = lib.getKnapsacks().get(0);
    start = System.currentTimeMillis();
    Solution improvedVLSNSolution = new ImprovedVLSN().solve(knapsack);
    assert  improvedVLSNSolution.isFeasible();
    System.out.println("ImprovedVLSN Profit: "+ improvedVLSNSolution.getProfit());
    System.out.println("Time for ImprovedVLSN: "+ (System.currentTimeMillis()-start)+" ms");
  }

}
