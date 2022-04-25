package test;

import ganew.Chromosom;
import ganew.GeneticalAlgorithm;
import ganew.crossover.Crossover;
import ganew.crossover.CycleCrossover;
import ganew.crossover.OrderBasedCrossover;
import ganew.crossover.PartiallyMatchedCrossover;
import ganew.crossover.UniformOrderCrossover;
import ganew.replacement.DeleteAllReplacer;
import ganew.replacement.Replacer;
import ganew.replacement.SteadyStateReplacer;
import ganew.selection.FitnessProportional;
import ganew.selection.RandomPool;
import ganew.selection.Selector;
import ganew.selection.TournamentSelection;
import geneticalgorithms.stopcriterias.TimeStopper;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import knapsack.KConstraintMultipleKnapsack;
import knapsack.Solution;
import library.KnapsackLibrary;
import library.KnapsackLibraryReader;
import org.junit.Test;
import vlsn.GreedySolution;

public class EvaluateNewGA {
  @Test
  public static void main(String[] args) {
    System.out.println("Starting....");
    KnapsackLibrary lib = KnapsackLibraryReader.readFile("knapsacks.ser");
    assert lib != null;
    StringBuilder str = new StringBuilder();
    Selector[] selectors = new Selector[]{new RandomPool(), new FitnessProportional(), new TournamentSelection(2)};
    Crossover[] crossovers = new Crossover[]{new CycleCrossover(), new OrderBasedCrossover(), new PartiallyMatchedCrossover(), new UniformOrderCrossover()};
    Replacer[] replacers = new Replacer[]{new DeleteAllReplacer(), new SteadyStateReplacer(false, false, 0.5),new SteadyStateReplacer(false, false, 0.7),
        new SteadyStateReplacer(false, true, 0.5), new SteadyStateReplacer(false, true, 0.7),
        new SteadyStateReplacer(true, false, 0.5), new SteadyStateReplacer(true, false, 0.7),
        new SteadyStateReplacer(true, true, 0.5),new SteadyStateReplacer(true, true, 0.7)};
    int[] popSizes = new int[]{100, 1000, 5000};
    double[] crossoverProbs = new double[]{0.5, 0.7, 1.0};
    double[] mutationProbs = new double[]{0.01, 0.5};
    for(Selector selector: selectors){
      for (Crossover crossover: crossovers){
        for (Replacer replacer: replacers){
          for(int popSize: popSizes){
              for(double crossoverProb: crossoverProbs){
                for(double mutationProb: mutationProbs){
                  System.out.println("---Next one---");
                  System.out.println("Selector: "+ selector.getClass());
                  System.out.println("Crossover: "+  crossover.getClass());
                  System.out.println("Replacer: "+ replacer.getClass());
                  System.out.println("popSize: "+ popSize);
                  System.out.println("Crossover Probability: "+ crossoverProb);
                  System.out.println("Mutation Probability: "+ mutationProb);
                  str.append("---------------");
                  str.append("Selector: ").append(selector.getClass());
                  str.append("Crossover: ").append(crossover.getClass());
                  str.append("Replacer: ").append(replacer.getClass());
                  str.append("popSize: ").append(popSize);
                  str.append("Crossover Probability: ").append(crossoverProb);
                  str.append("Mutation Probability: ").append(mutationProb);
                  int i = 0;
                  double avgGAProfit = 0.0;
                  double avgGreedyProfit = 0.0;
                  for(KConstraintMultipleKnapsack knapsack: lib.getKnapsacks()){
                    str.append("Knapsack Nr: ").append(i);
                    Chromosom chromosom;
                    try{
                      chromosom = new GeneticalAlgorithm(knapsack, popSize, popSize, new TimeStopper(10000), crossoverProb, mutationProb, selector,
                          popSize, crossover, replacer).solve();
                      str.append("ERROR");
                    }catch(Exception e){
                      System.out.println(e.getMessage());
                      chromosom = new Chromosom(IntStream.range(0, knapsack.getNrItems()).boxed().collect(Collectors.toList()),knapsack);
                    }

                    avgGAProfit += chromosom.getFitness();
                    str.append("GA Profit: ").append(chromosom.getFitness());
                    Solution s = GreedySolution.getGreedy(knapsack);
                    str.append("GreedyProfit: ").append(s.getProfit());
                    avgGreedyProfit += s.getProfit();
                    i++;
                  }
                  str.append("-Resumee-");
                  str.append("Avg GA Profit: ").append(avgGAProfit/lib.getKnapsacks().size());
                  str.append("Avg Greedy Profit: ").append(avgGreedyProfit/lib.getKnapsacks().size());
                }
              }



          }

        }
      }
    }
    String data = str.toString();
    try(FileOutputStream fos = new FileOutputStream(new File("output.txt"));
        BufferedOutputStream bos = new BufferedOutputStream(fos)) {
      //convert string to byte array
      byte[] bytes = data.getBytes();
      //write byte array to file
      bos.write(bytes);
      bos.close();
      fos.close();
      System.out.print("Data written to file successfully.");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
