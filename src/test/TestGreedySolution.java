package test;

import java.util.ArrayList;
import knapsack.Item;
import knapsack.KConstraintMultipleKnapsack;
import knapsack.Knapsack;
import knapsack.Solution;
import org.jgrapht.alg.scoring.KatzCentrality;
import org.junit.Test;
import vlsn.GreedySolution;
import vlsn.GreedySolution.FractionalSolution;
import vlsn.VLSN;
import vlsn.ValidCycle;
import vlsn.graph.ConstructImprovementGraph;
import vlsn.graph.Edge;
import vlsn.graph.ImprovementGraph;

public class TestGreedySolution {

  @Test
  public void test(){
    KConstraintMultipleKnapsack knapsack =GenerateTestInstances.generateTestInstance(100, 3, 20, 2, 0.3);

    Solution solution = GreedySolution.getGreedy(knapsack);

    assert solution.isFeasible();
    for(Knapsack k: knapsack.getKnapsacks()){
      for(Item i: knapsack.getItems()){
        assert solution.isUsed(i) || !solution.canBeAdded(i, k);
      }
    }

  }

  @Test
  public void testTestInstance(){
    ArrayList<Item> items = new ArrayList<Item>();
    items.add(new Item(0, 80, new int[]{57, 7}));
    items.add(new Item(1, 1, new int[]{17, 98}));
    items.add(new Item(2, 93, new int[]{76, 83}));
    items.add(new Item(3, 51, new int[]{53, 89}));
    items.add(new Item(4, 13, new int[]{96, 4}));
    items.add(new Item(5, 38, new int[]{27, 17}));
    items.add(new Item(6, 23, new int[]{75, 26}));
    items.add(new Item(7, 79, new int[]{77, 54}));
    items.add(new Item(8, 70, new int[]{44, 9}));
    items.add(new Item(9, 40, new int[]{63, 68}));
    items.add(new Item(10, 94, new int[]{47, 45}));
    items.add(new Item(11, 18, new int[]{67, 26}));

    ArrayList<Knapsack> knapsacks = new ArrayList<>();
    knapsacks.add(new Knapsack(0, new int[]{198,165}));
    knapsacks.add(new Knapsack(1, new int[]{301,217}));

    KConstraintMultipleKnapsack knapsack = new KConstraintMultipleKnapsack(items, knapsacks, 2);
    Solution solution = new Solution(knapsack);
    for(Item i: knapsack.getItems()){
      for(Knapsack k: knapsack.getKnapsacks()){
        if(solution.canBeAdded(i, k)){
          solution.addItem(i, k);
          break;
        }
      }
    }
    System.out.println(solution);
    ImprovementGraph g = ConstructImprovementGraph.constructImprovementGraph(knapsack, solution);

    ValidCycle w = VLSN.validCycleDetection(g, 8);
    System.out.println(w+", "+w.getC());
    solution = solution.applyCycle(w);
    //Update S and G(S)
    ConstructImprovementGraph.updateImprovementGraph(g, w, knapsack, solution);
    w = VLSN.validCycleDetection(g, 8);
    System.out.println(w+", "+w.getC());

    solution = solution.applyCycle(w);
    //Update S and G(S)
    ConstructImprovementGraph.updateImprovementGraph(g, w, knapsack, solution);
    w = VLSN.validCycleDetection(g, 8);
    System.out.println(w+", "+w.getC());
    solution = solution.applyCycle(w);
    ConstructImprovementGraph.updateImprovementGraph(g, w, knapsack, solution);
    w = VLSN.validCycleDetection(g, 8);
    System.out.println(w+", "+w.getC());
    solution = solution.applyCycle(w);
    System.out.println(solution.getProfit());

  }

  @Test
  public void testStuff(){
    ArrayList<Item> items = new ArrayList<>();
    items.add(new Item(0, 73, new int[]{45, 88}));
    items.add(new Item(1, 65, new int[]{49, 13}));
    items.add(new Item(2, 100, new int[]{84, 12}));
    items.add(new Item(3, 16, new int[]{86, 97}));
    items.add(new Item(4, 40, new int[]{77, 69}));

    ArrayList<Knapsack> knapsacks = new ArrayList<>();
    knapsacks.add(new Knapsack(0, new int[]{89, 70}));
    knapsacks.add(new Knapsack(1, new int[]{151, 125}));

    KConstraintMultipleKnapsack knapsack = new KConstraintMultipleKnapsack(items, knapsacks, 2);

    Solution s = GreedySolution.getGreedy(knapsack);

    System.out.println(s);

    ImprovementGraph g = ConstructImprovementGraph.constructImprovementGraph(knapsack, s);

    for(Edge e: g.edgeSet()){
      System.out.println(e.getSourceVertex().getVertexID() + " -> " + e.getTargetVertex().getVertexID() + ", Profit: "+e.getEdgeWeight());
    }

    ValidCycle c = VLSN.validCycleDetection(g, 8);
    System.out.println(c);
  }
}
