package test;

import ganew.Chromosom;
import ganew.PermGA;
import ganew.crossover.CycleCrossover;
import ganew.crossover.PartiallyMatchedCrossover;
import ganew.population.InitializeFeasiblePopulation;
import ganew.replacement.SteadyStateReplacer;
import ganew.selection.TournamentSelection;
import geneticalgorithms.stopcriterias.TimeStopper;
import java.util.List;
import knapsack.KConstraintMultipleKnapsack;
import knapsack.Solution;
import library.KnapsackLibrary;
import library.KnapsackLibraryReader;
import org.junit.Test;
import vlsn.GreedySolution;

public class TestNewGA {

  @Test
  public void test(){
    int popSize = 1000;
    int iterations = 1000;
    int maxSize = 1500;
    KnapsackLibrary lib = KnapsackLibraryReader.readFile("knapsacks.ser");
    assert lib != null;
    int i = 0;
    int p = 0;
    long t = 0;
    for(KConstraintMultipleKnapsack knapsack : lib.getKnapsacks()){
      System.out.println("Test for GA  with population size "+popSize+", Nr of iterations "+ iterations + ", max pop size "+maxSize);
      System.out.println("-------------------");
      System.out.println("Instance Nr. "+i);
      long startGreedy = System.currentTimeMillis();
      Solution sGreedy = GreedySolution.getGreedy(knapsack);
      long endGreedy = System.currentTimeMillis();
      long startGA = System.currentTimeMillis();
      Chromosom s = new PermGA(knapsack, popSize, maxSize, new TimeStopper(10000), 1.0,1.0, new TournamentSelection(5), popSize,
          new PartiallyMatchedCrossover(), new SteadyStateReplacer(true, true, 0.5)).solve();
      long endGA = System.currentTimeMillis();
      p+=s.getFitness();
      t+= endGA-startGA;
      System.out.println("GreedyProfit: "+ sGreedy.getProfit()+ ", Greedy Time: "+ (endGreedy-startGreedy));
      System.out.println("GAProfit: "+ s.getFitness()+ ", GATime: "+ (endGA-startGA));
      i++;
    }
    System.out.println("Avg. Profit = "+ (double)p/lib.getKnapsacks().size());
    System.out.println("Avg. Time = "+(double)t/lib.getKnapsacks().size());
  }

  @Test
  public void testOrderBasedCrossover(){
    KConstraintMultipleKnapsack knapsack= GenerateTestInstances.generateTestInstance(100, 5, 10, 1, 0.3);
    List<Chromosom> population = InitializeFeasiblePopulation.initializePopulation(knapsack, 2);
    Chromosom p1 = population.get(0);
    Chromosom p2 = population.get(1);
    System.out.println("P1: "+p1.getSolution());
    System.out.println("P2: "+p2.getSolution());

    Chromosom c = new CycleCrossover().crossover(knapsack,p1, p2);
    System.out.println(c.getSolution());
  }


}
