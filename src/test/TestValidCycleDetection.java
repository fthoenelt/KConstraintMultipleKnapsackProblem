package test;

import java.util.ArrayList;
import knapsack.Item;
import knapsack.KConstraintMultipleKnapsack;
import knapsack.Knapsack;
import knapsack.Solution;
import library.KnapsackLibrary;
import library.KnapsackLibraryWriter;
import org.junit.Test;
import vlsn.GreedySolution;
import vlsn.VLSN;
import vlsn.ValidCycle;
import vlsn.graph.ConstructImprovementGraph;
import vlsn.graph.Edge;
import vlsn.graph.ImprovementGraph;
import vlsn.graph.ItemVertex;
import vlsn.graph.Vertex;

public class TestValidCycleDetection {

  @Test
  public void test(){
    KConstraintMultipleKnapsack knapsack =GenerateTestInstances.generateTestInstance(100, 20, 1000, 10, 0.3);
    //KConstraintMultipleKnapsack knapsack = TestImprovementGraph.buildTestKnapsack();

    Solution solution = new Solution(knapsack);
    for(Item i: knapsack.getItems()){
      for(Knapsack k: knapsack.getKnapsacks()){
        if(solution.canBeAdded(i, k)){
          solution.addItem(i, k);
          break;
        }
      }
    }

    ImprovementGraph g = ConstructImprovementGraph.constructImprovementGraph(knapsack, solution);

    ValidCycle c = VLSN.validCycleDetection(g, 5);
    assert c!= null;
    assert c.getC() <0;
    int oldC = c.getC();

    int old = solution.getProfit();
    solution.applyCycle(c);
    assert old < solution.getProfit();
    assert solution.isFeasible();
    assert solution.getProfit()+oldC == old;
  }

  @Test
  public void testValidCycleAplly(){
    KConstraintMultipleKnapsack knapsack =GenerateTestInstances.generateTestInstance(100, 5, 1000, 10, 0.5);
    //MultipleKnapsack knapsack = TestImprovementGraph.buildTestKnapsack();

    Solution solution = GreedySolution.getGreedy(knapsack);

    ImprovementGraph g = ConstructImprovementGraph.constructImprovementGraph(knapsack, solution);

    ValidCycle c = VLSN.validCycleDetection(g, 8);

    if(c==null){
      for(Edge e: g.edgeSet()) assert e.getEdgeWeight()>=0;
      System.out.println("No Cycle found");
      return;
    }

    assert c.getC() < 0;
    assert c.isCycle();

    int old = solution.getProfit();

    solution.applyCycle(c);

    //Tauscht schlechteres Item ein
    assert solution.isFeasible();

    for(Vertex v: c.getLabel()){
      if(v instanceof ItemVertex&&solution.isUsed(((ItemVertex) v).getItem())){
        assert solution.getKnapsack(((ItemVertex)v).getItem()).getKnapsackID()!= v.getKnapsackID();
      }else{
        assert v.getKnapsackID() != knapsack.getNrKnapsacks();
      }

    }

    assert solution.getProfit()>old;

    g = ConstructImprovementGraph.updateImprovementGraph(g, c, knapsack, solution);

    TestImprovementGraph.testImprovementGraphFeasible(g, knapsack, solution);
  }

  @Test
  public void testNoCycles(){
    //while(true){
    KConstraintMultipleKnapsack knapsack =GenerateTestInstances.generateTestInstance(100, 5, 20, 3, 0.5);

    Solution solution = GreedySolution.getGreedy(knapsack);

    ImprovementGraph g = ConstructImprovementGraph.constructImprovementGraph(knapsack, solution);
    for(Vertex v:g.vertexSet()){
      assert g.outDegreeOf(v)==((knapsack.getItems().size()+ knapsack.getNrKnapsacks())-1);
    }

    ValidCycle c = VLSN.validCycleDetection(g, 8);
    if(c != null){
      ArrayList<KConstraintMultipleKnapsack> k = new ArrayList<>();
      k.add(knapsack);
      KnapsackLibrary lib = new KnapsackLibrary(k);
      KnapsackLibraryWriter.writeLibrary(lib, "goodInstance.ser");
        //break;
      }else{
        for (Edge e: g.edgeSet()){
          if (e.getEdgeWeight() < 0.0) {
            System.out.println("Hi");
          }
        }
        for(Vertex v: g.vertexSet()){
          if(v instanceof ItemVertex && !solution.isUsed(((ItemVertex)v).getItem())){
            for(Knapsack k : knapsack.getKnapsacks()){
              for(Item iji: solution.itemsOf(k)){
                if(solution.couldBeReplaced(((ItemVertex)v).getItem(), iji)){
                  System.out.println(v.getVertexID()+", "+ iji.getItemID());
                }
              }
            }
          }
        }
      }
    //}
    /*
    MultipleKnapsack knapsack = TestImprovementGraph.buildTestKnapsack();

    new GreedySolution();
    knapsack = GreedySolution.getGreedy(knapsack);

    new ConstructImprovementGraph();
    ImprovementGraph g = ConstructImprovementGraph.constructImprovementGraph(knapsack);

    ValidCycle c = VLSN.validCycleDetection(g, 8, knapsack);

     */
  /*
    if(c==null){
      List<List<Vertex>> cycles = new SzwarcfiterLauerSimpleCycles<>(g).findSimpleCycles();
      for(List<Vertex> cycle:cycles){
        int costs = 0;
        for (int i = 0; i < cycle.size(); i++){
          if(i == cycle.size()-1){
            costs += g.getEdge(cycle.get(i), cycle.get(0)).getEdgeWeight();
          }else{
            costs += g.getEdge(cycle.get(i), cycle.get(i+1)).getEdgeWeight();
          }
        }
        assert costs > 0;
      }
    }

   */
  }



}
