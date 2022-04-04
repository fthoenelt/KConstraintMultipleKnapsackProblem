package vlsn;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import knapsack.KConstraintMultipleKnapsack;;
import knapsack.Solution;
import vlsn.graph.ConstructImprovementGraph;
import vlsn.graph.Edge;
import vlsn.graph.ImprovementGraph;
import vlsn.graph.Vertex;

/**
 * Class performing the Very-Large-Scale Neighborhood Search described in the paper by Ahuja and Cunha
 */
public class VLSN {

  /**
   * Detects a valid cycle in a given improvement graph (Pseudocode in "A composite very large-scale neighborhood structure" by Ahuja)
   *
   * @param g the improvement graph
   * @param r the maximal length of the cycle
   * @return Valid cycle if found
   */
  public static ValidCycle validCycleDetection(ImprovementGraph g, int r) {
    //List p1, containing all valid paths of length 1 (which are all edges with weight < 0)
    ArrayList<ValidCycle> p = new ArrayList<>();
    //Add all edges with weight < 0
    for (Edge e : g.edgeSet()) {
      if (e.getEdgeWeight() < 0.0) {
        p.add(new ValidCycle(r, e));
      }
    }
    int k = 1;
    //Best found valid cycle (null at the beginning)
    Optional<ValidCycle> w_Star = Optional.empty();
    int best = 0;
    //Stop searching if either the maximal length is reached or a path with negative costs is found
    while (k < r && best >= 0) {
      //List of paths of length k+1
      ArrayList<ValidCycle> p_plus = new ArrayList<>();
      while (!p.isEmpty()) {
        //Get the next path out of the old list
        ValidCycle p_Cycle = p.remove(0);
        Vertex i = p_Cycle.getHead();
        Vertex h = p_Cycle.getTail();
        if (g.containsEdge(i, h) && (p_Cycle.getC() + g.getEdge(i, h).getEdgeWeight() < best) && p_Cycle.getLabel().size()>2) {
          //If the valid path can be closed to an valid cycle and has less costs then the best found one
          p_Cycle.addVertex(g.getEdge(i, h));
          assert p_Cycle.isCycle;
          //Save the cycle as the best found one
          w_Star = Optional.of(p_Cycle);
          best = p_Cycle.c;
        }

        for (Edge e : g.outgoingEdgesOf(i)) {
          //If the knapsack the new vertex is assigned to isn't already in the Label of the current path and the costs aufer adding the new vertex are still < 0
          if (!p_Cycle.containsLabel(e.getTargetVertex().getKnapsackID()) && p_Cycle.getC() + e.getEdgeWeight() < 0) {
            assert e.getSourceVertex().equals(i);
            //Add the path with the new vertex to the list P_k+1
            ValidCycle p_CycleTmp = new ValidCycle(p_Cycle);
            p_CycleTmp.addVertex(e);
            //Check P_k+1 for dominated or dominant paths
            Iterator<ValidCycle> it = p_plus.iterator();
            while (it.hasNext()) {
              ValidCycle pTmp = it.next();
              if (pTmp.dominates(p_CycleTmp)) {
                it.remove();
              } else {
                p_CycleTmp = null;
                break;
              }
            }
            if (p_CycleTmp != null)
              p_plus.add(p_CycleTmp);
          }
        }
      }
      k++;
      p = p_plus;
    }
    return w_Star.orElse(null);
  }

  /**
   * Main method for the neighborhood search (pseudocode in the paper, figure 2 page 470)
   *
   * @param knapsack The knapsack instance to be solved
   * @return
   */
  public Solution solve(KConstraintMultipleKnapsack knapsack) {
    //Obtain a feasible solution for the K-MKP problem
    Solution solution = GreedySolution.getGreedy(knapsack);
    int initialProfit = solution.getProfit();
    //Construct improvement graph G(S)
    ImprovementGraph g = ConstructImprovementGraph.constructImprovementGraph(knapsack, solution);
    long startT = System.currentTimeMillis();
    //While G(S) contains a valid cycle (and time limit is ok) do
    Optional<ValidCycle> w = Optional.ofNullable(validCycleDetection(g, 8));
    while (w.isPresent() && System.currentTimeMillis() - startT < 100000) {
      assert w.get().getC() < 0;
      //Perform multi-exchange corresponding to W
      solution = solution.applyCycle(w.get());
      //Update S and G(S)
      g = ConstructImprovementGraph.updateImprovementGraph(g, w.get(), knapsack, solution);
      w = Optional.ofNullable(validCycleDetection(g, 8));
    }
    if (w.isPresent()) {
      System.out.println("Time Limit reached");
    }
    return solution;
  }
}
