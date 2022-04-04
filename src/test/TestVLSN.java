package test;

import knapsack.KConstraintMultipleKnapsack;
import knapsack.Solution;
import org.junit.Test;
import vlsn.VLSN;

public class TestVLSN {

  @Test
  public void test(){
    KConstraintMultipleKnapsack knapsack =GenerateTestInstances.generateTestInstance(1000, 5, 500, 10, 0.3);

    Solution solution = new VLSN().solve(knapsack);
  }

}
