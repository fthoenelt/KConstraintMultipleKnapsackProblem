package vlsn.graph;

import knapsack.Item;
import knapsack.KConstraintMultipleKnapsack;
import knapsack.Knapsack;
import knapsack.Solution;
import vlsn.ValidCycle;


public class ConstructImprovementGraph {

  /**
   * Constructs an Improvement Graph for a given MultipleKnapsack Instance
   *
   * @param knapsack The Multiple Knapsack Instance
   * @return The constructed Improvement Graph
   */
  public static ImprovementGraph constructImprovementGraph(KConstraintMultipleKnapsack knapsack, Solution solution) {

    ImprovementGraph graph = new ImprovementGraph();

    //Add all Vertices corresponding to an Item (ItemID >=0)
    for (Item i : knapsack.getItems()) {
      if (solution.isUsed(i)) {
        graph.addVertex(new ItemVertex(i, solution.getKnapsack(i).getKnapsackID()));
      } else {
        graph.addVertex(new ItemVertex(i, knapsack.getNrKnapsacks()));
      }
    }

    //Add all DummyVertices, ItemID < 0
    int i = -1;
    for (Knapsack k : knapsack.getKnapsacks()) {
      graph.addVertex(new DummyVertex(i, k.getKnapsackID()));
      i--;
    }

    for (Vertex v : graph.vertexSet()) {
      for (Vertex w : graph.vertexSet()) {
        if (!v.equals(w)) {
          addEdgeWeight(v, w, graph, knapsack, solution);
        }
      }
    }
    return graph;
  }

  /**
   * Method which adds an edge between two given vertices and adds its weight by checking which case of those described in the paper fits
   * @param v The outgoing vertex
   * @param w The incoming vertex
   * @param graph the ImprovementGraph
   * @param knapsack the MultipleKnapsack corresponding to the ImprovementGraph
   * @return the improvementgraph with the new edge
   */
  private static ImprovementGraph addEdgeWeight(Vertex v, Vertex w, ImprovementGraph graph, KConstraintMultipleKnapsack knapsack, Solution solution) {
    if (!(v instanceof DummyVertex && w instanceof DummyVertex)) {
      //Case A -> Both Vertices represent Items
      if (!(v instanceof DummyVertex || w instanceof DummyVertex)) {

        ItemVertex j = (ItemVertex) v;
        ItemVertex l = (ItemVertex) w;
        Edge e = graph.addEdge(j, l);

        //Case 1, Both items are assigned
        if (solution.isUsed(j.getItem()) && solution.isUsed(l.getItem()) && j.getKnapsackID() != l.getKnapsackID()) {
          if (solution.couldBeReplaced(j.getItem(), l.getItem())) {
            graph.setEdgeWeight(e, 0);
          } else {
            graph.setEdgeWeight(e, Double.MAX_VALUE);
          }
          //Case 2
        } else if (!solution.isUsed(j.getItem()) && solution.isUsed(l.getItem())) {
          if (solution.couldBeReplaced(j.getItem(), l.getItem())) {
            //assert v.getKnapsackID()==knapsack.getNrKnapsacks();
            graph.setEdgeWeight(e, -j.getItem().getProfit());
          } else {
            graph.setEdgeWeight(e, Double.MAX_VALUE);
          }
          //Case 3
        } else if (solution.isUsed(j.getItem()) && !solution.isUsed(l.getItem())) {
          graph.setEdgeWeight(e, j.getItem().getProfit());
          //Case 4
        } else {

          graph.setEdgeWeight(e, Double.MAX_VALUE);
        }
        //Case B -> Edges (j,f) with j representing an Item and f an Dummy
      } else if (w instanceof DummyVertex) {
        ItemVertex j = (ItemVertex) v;
        DummyVertex f = (DummyVertex) w;
        Edge e = graph.addEdge(j, f);
        //Case 1
        if (!solution.isUsed(j.getItem()) && solution.canBeAdded(j.getItem(), knapsack.getKnapsack(f.getKnapsackID()))) {
          //assert v.getKnapsackID()==knapsack.getNrKnapsacks();
          graph.setEdgeWeight(e, -j.getItem().getProfit());
          //Case 2
        } else if (solution.isUsed(j.getItem()) && solution.canBeAdded(j.getItem(), knapsack.getKnapsack(f.getKnapsackID()))) {
          graph.setEdgeWeight(e, 0);
          //Case 3
        } else {
          graph.setEdgeWeight(e, Double.MAX_VALUE);
        }
      } else {
        DummyVertex f = (DummyVertex) v;
        ItemVertex j = (ItemVertex) w;
        Edge e = graph.addEdge(f, j);
        if (f.getKnapsackID() != j.getKnapsackID()) {
          graph.setEdgeWeight(e, 0.0);
        } else {
          graph.setEdgeWeight(e, Double.MAX_VALUE);
        }
      }
    } else {
      DummyVertex f = (DummyVertex) v;
      DummyVertex g = (DummyVertex) w;
      Edge e = graph.addEdge(f, g);
      graph.setEdgeWeight(e, Double.MAX_VALUE);
    }
    return graph;
  }

  /**
   * Updates the ImprovementGraph after a cyclic exchange in the corresponding MultipleKnapsack occured
   * @param g the ImprovementGraph
   * @param c ValidCycle which was applied to the MultipleKnapsack
   * @param k The MultipleKnapsack after the cyclic exchange
   * @return the updated ImprovementGraph
   */
  public static ImprovementGraph updateImprovementGraph(ImprovementGraph g, ValidCycle c, KConstraintMultipleKnapsack k, Solution solution) {
    for (int i = 0; i < c.getLabel().size(); i++) {
      Vertex v = c.getLabel().get(i);
      if (!(v instanceof DummyVertex)) {
        ((ItemVertex) v).setKnapsackID(solution.isUsed(((ItemVertex) v).getItem()) ? solution.getKnapsack(((ItemVertex) v).getItem()).getKnapsackID() :
            k.getNrKnapsacks());

      }
    }
    for (Vertex v : g.vertexSet()) {
      if (c.getLabel().contains(v) || c.containsLabel(v.getKnapsackID())) {
        for (Vertex w : g.vertexSet()) {
          if (!v.equals(w)) {
            g.removeAllEdges(v, w);
            g.removeAllEdges(w, v);
            addEdgeWeight(v, w, g, k, solution);
            addEdgeWeight(w, v, g, k, solution);
          }
        }
      }
    }
    return g;
  }


}
