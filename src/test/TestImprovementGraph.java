package test;

import java.util.ArrayList;
import knapsack.Item;
import knapsack.KConstraintMultipleKnapsack;
import knapsack.Knapsack;
import knapsack.Solution;
import org.junit.Test;
import vlsn.graph.ConstructImprovementGraph;
import vlsn.graph.DummyVertex;
import vlsn.graph.Edge;
import vlsn.graph.ImprovementGraph;
import vlsn.graph.ItemVertex;
import vlsn.graph.Vertex;

public class TestImprovementGraph {

  public static KConstraintMultipleKnapsack buildTestKnapsack(){
    ArrayList<Item> items = new ArrayList<>(20);
    items.add(new Item(0,7, new int[]{86, 85}));
    items.add(new Item(1,20, new int[]{98, 100}));
    items.add(new Item(2,90, new int[]{14, 57}));
    items.add(new Item(3,79, new int[]{64, 94}));
    items.add(new Item(4,14, new int[]{40, 11}));
    items.add(new Item(5,48, new int[]{100, 68}));
    items.add(new Item(6,33, new int[]{55, 66}));
    items.add(new Item(7,73, new int[]{47, 67}));
    items.add(new Item(8,87, new int[]{29, 56}));
    items.add(new Item(9,100, new int[]{35, 62}));
    items.add(new Item(10,12, new int[]{99, 73}));
    items.add(new Item(11,75, new int[]{20, 82}));
    items.add(new Item(12,60, new int[]{17, 86}));
    items.add(new Item(13,92, new int[]{44, 30}));
    items.add(new Item(14,45, new int[]{17, 20}));
    items.add(new Item(15,15, new int[]{88, 50}));
    items.add(new Item(16,23, new int[]{32, 50}));
    items.add(new Item(17,68, new int[]{53, 82}));
    items.add(new Item(18,81, new int[]{27, 9}));
    items.add(new Item(19,12, new int[]{99, 81}));

    ArrayList<Knapsack> knapsacks = new ArrayList<>(3);
    knapsacks.add(new Knapsack(0, new int[]{120,150}));
    knapsacks.add(new Knapsack(1, new int[]{91,143}));
    knapsacks.add(new Knapsack(2, new int[]{256,281}));

    return new KConstraintMultipleKnapsack(items, knapsacks, 2);
  }

  @Test
  public void testVertices(){
    KConstraintMultipleKnapsack knapsack = buildTestKnapsack();
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

    for(Vertex v:g.vertexSet()){

      if((v instanceof DummyVertex)){
        assert v.getKnapsackID() < knapsack.getNrKnapsacks();
      }else{
        if(solution.isUsed(((ItemVertex)v).getItem())){
          assert v.getKnapsackID() < knapsack.getNrKnapsacks();
        }else{
          assert v.getKnapsackID() == knapsack.getNrKnapsacks();
        }
      }
    }
  }

  @Test
  public void testConstructImprovementGraph(){
    KConstraintMultipleKnapsack knapsack =GenerateTestInstances.generateTestInstance(100, 5, 1000, 100, 0.5);
    //KConstraintMultipleKnapsack knapsack = buildTestKnapsack();
    //System.out.println(knapsack.toString());
    Solution solution = new Solution(knapsack);
    for(Item i: knapsack.getItems()){
      for(Knapsack k: knapsack.getKnapsacks()){
        if(solution.canBeAdded(i, k)){
          solution.addItem(i, k);
          break;
        }
      }
    }
    //System.out.println(solution.toString());
    ImprovementGraph g = ConstructImprovementGraph.constructImprovementGraph(knapsack, solution);
    /*
    for(Edge e: g.edgeSet()){
      System.out.println(e.getSourceVertex()+" -> "+ e.getTargetVertex() + ", weight: "+ e.getEdgeWeight());
    }

     */
    testImprovementGraphFeasible(g, knapsack, solution);
  }

  public static void testImprovementGraphFeasible(ImprovementGraph g, KConstraintMultipleKnapsack knapsack, Solution solution){

    assert g.vertexSet().size()==(knapsack.getItems().size()+ knapsack.getNrKnapsacks());
    for(Vertex v:g.vertexSet()){
      assert g.outDegreeOf(v)==((knapsack.getItems().size()+ knapsack.getNrKnapsacks())-1);
    }
    for(Edge e : g.edgeSet()){
      if(e.getEdgeWeight() == 0.0){
        //Two DummyVertices can't be connected with an edge with weight 0
        if(e.getSourceVertex() instanceof DummyVertex && e.getTargetVertex() instanceof DummyVertex){
          assert false;
        }else if(e.getSourceVertex() instanceof DummyVertex){
          //Case A.iii -> Ok if the Knapsacks are different
          assert e.getSourceVertex().getKnapsackID()!= e.getTargetVertex().getKnapsackID();
        }else if(e.getTargetVertex() instanceof DummyVertex){
          //Case A.ii -> Edge (j,f) with f Dummy; Ok if j not in S0, Sj!=Sf and Capacity ok
          assert e.getSourceVertex().getKnapsackID()!= knapsack.getNrKnapsacks();
          assert solution.isUsed(((ItemVertex)e.getSourceVertex()).getItem());

          assert e.getSourceVertex().getKnapsackID()!= e.getTargetVertex().getKnapsackID();
          assert solution.canBeAdded(((ItemVertex)e.getSourceVertex()).getItem(), knapsack.getKnapsack(e.getTargetVertex().getKnapsackID()));
        }else{
          //Case A.i -> Both are items, both items are used in different knapsacks, replacement is ok
          ItemVertex j = (ItemVertex) e.getSourceVertex();
          ItemVertex l = (ItemVertex) e.getTargetVertex();
          //Both Used
          assert solution.isUsed(j.getItem());
          assert solution.isUsed(l.getItem());
          assert j.getKnapsackID() != knapsack.getNrKnapsacks();
          assert l.getKnapsackID() != knapsack.getNrKnapsacks();
          //Different Knapsacks
          assert j.getKnapsackID()!=l.getKnapsackID();
          assert !solution.getKnapsack(j.getItem()).equals(solution.getKnapsack(l.getItem()));
          //Replacement ok
          assert solution.couldBeReplaced(j.getItem(), l.getItem());
        }
      }else if(e.getEdgeWeight()>0){
        if(e.getEdgeWeight()==Double.MAX_VALUE){
          //Case B edge weight infinity
          if(e.getSourceVertex() instanceof DummyVertex && e.getTargetVertex() instanceof DummyVertex){
            //B.i) Both are Dummys, always ok
            assert true;
          }else if(e.getSourceVertex() instanceof DummyVertex){
            //B.ii) j is Dummy, only ok if S[j]==S[f]
            assert e.getTargetVertex() instanceof ItemVertex;
            assert e.getSourceVertex().getKnapsackID()==e.getTargetVertex().getKnapsackID();
          }else if(e.getTargetVertex() instanceof DummyVertex){
            //B.iii) Doesn't matter if TargetVertex is DUmmy or Item, either way either both j and l belong to the same knapsack or the capacity is violated by the replacement
            assert e.getSourceVertex() instanceof ItemVertex;
            assert e.getTargetVertex().getKnapsackID()==e.getSourceVertex().getKnapsackID()||e.getSourceVertex().getKnapsackID()!= knapsack.getNrKnapsacks()||!solution.canBeAdded(((ItemVertex)e.getSourceVertex()).getItem(), knapsack.getKnapsack(e.getTargetVertex().getKnapsackID()));
          }else{
            //B.iv)
            assert (e.getTargetVertex().getKnapsackID()==e.getSourceVertex().getKnapsackID())||!solution.couldBeReplaced(((ItemVertex)e.getSourceVertex()).getItem(),
                ((ItemVertex)e.getTargetVertex()).getItem());
          }
        }else{
          //Case C) Edge Weight = pl
          assert e.getSourceVertex() instanceof ItemVertex && e.getTargetVertex() instanceof ItemVertex;
          assert e.getSourceVertex().getKnapsackID()!= knapsack.getNrKnapsacks()&&e.getTargetVertex().getKnapsackID()==knapsack.getNrKnapsacks();
          assert solution.isUsed(((ItemVertex)e.getSourceVertex()).getItem());
          assert !solution.isUsed(((ItemVertex)e.getTargetVertex()).getItem());
        }
      }else{
        assert e.getSourceVertex() instanceof ItemVertex;
        ItemVertex j = (ItemVertex) e.getSourceVertex();
        assert j.getKnapsackID() == knapsack.getNrKnapsacks();
        assert !solution.isUsed(j.getItem());
        assert j.getKnapsackID()!=e.getTargetVertex().getKnapsackID();
        if(e.getTargetVertex() instanceof DummyVertex){
          assert solution.canBeAdded(j.getItem(), knapsack.getKnapsack(e.getTargetVertex().getKnapsackID()));
        }else{

          assert solution.couldBeReplaced(j.getItem(), ((ItemVertex)e.getTargetVertex()).getItem());
        }
      }
    }

  }

}
