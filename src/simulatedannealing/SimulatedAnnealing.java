package simulatedannealing;

import geneticalgorithms.stopcriterias.IterationStopper;
import geneticalgorithms.stopcriterias.StopCriteria;
import java.util.Random;
import knapsack.KConstraintMultipleKnapsack;
import knapsack.Solution;

/**
 * Hauptklasse des Simulated Annealing Algorithmus für das K-MKP
 */

public class SimulatedAnnealing {
  KConstraintMultipleKnapsack knapsack;
  StartSolution startSolution;
  Neighborhood neighborhood;
  StopCriteria stopCriteria;
  Acceptor acceptor;
  Random rand;
  
  public SimulatedAnnealing(KConstraintMultipleKnapsack knapsack, StartSolution startSolution, Neighborhood neighborhood, StopCriteria stopCriteria, Acceptor acceptor){
    this.knapsack = knapsack;
    this.startSolution = startSolution;
    this.neighborhood = neighborhood;
    this.stopCriteria = stopCriteria;
    this.acceptor = acceptor;
    this.rand = new Random();
  }

  public SimulatedAnnealing(KConstraintMultipleKnapsack knapsack, int iterations, double alpha, double start){
    this.knapsack = knapsack;
    this.startSolution = new GreedyStartSolution();
    this.neighborhood = new RandomNeighborhood(knapsack, 20);
    this.stopCriteria = new IterationStopper(iterations);
    this.acceptor = new SequencialAcceptor(alpha, start);
    this.rand = new Random();
  }

  public Solution solve(){
    int i = 0;
    boolean changed = false;
    Solution cur = startSolution.getStartSolution(this.knapsack);
    Solution best = new Solution(cur);
    int bestProfit = cur.getProfit();
    while(!stopCriteria.stop(changed)){
      changed = false;
      Solution neighbor = this.neighborhood.getNeighbor(cur);
      if(neighbor.getProfit() >= cur.getProfit() || rand.nextDouble() < acceptor.getValue(cur, neighbor, i)){
        cur = neighbor;
        if(cur.getProfit() > bestProfit){
          best = new Solution(cur);
          bestProfit = cur.getProfit();
          changed = true;
        }
      }
      i++;
    }
    return best;
  }



}
