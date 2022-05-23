package test;

import ganew.Chromosom;
import ganew.PermGA;
import ganew.PermGA.Mutator;
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
import vlsn.GreedySolution;
import vlsn.ImprovedVLSN;
import vlsn.VLSN;

public class EvaluateNewGA {

  public static void main(String[] args) {
    System.out.println("Starting V2....");
    KnapsackLibrary lib = KnapsackLibraryReader.readFile("knapsacks.ser");
    assert lib != null;
    StringBuilder str = new StringBuilder();
    Mutator[] mutators = new Mutator[]{Mutator.SWAP, Mutator.SHUFFLE, Mutator.ORDER};
    double[] mutationProbs = new double[]{0.01, 0.5, 1.0};
    for (Mutator mutator : mutators) {
      for (double mutationProb : mutationProbs) {
        double avgV1 = 0.0;
        double avgV2 = 0.0;
        double avgV3 = 0.0;
        double avgV4 = 0.0;
        System.out.println("---Next one---");
        System.out.println("Mutator: " + mutator.name());
        System.out.println("Mutation Probability: " + mutationProb);
        str.append("---------------\n");
        str.append("Mutator: ").append(mutator.name()).append("\n");
        str.append("Mutation Probability: ").append(mutationProb).append("\n");
        int i = 0;
        double avgGAProfit = 0.0;
        double avgGreedyProfit = 0.0;
        for (KConstraintMultipleKnapsack knapsack : lib.getKnapsacks()) {
          str.append("Knapsack Nr: ").append(i).append("\n");
          try {
            Chromosom c1 = new PermGA(knapsack, 1000, new TimeStopper(30000), 0.7, 0.01, new TournamentSelection(2), new UniformOrderCrossover(),
                new DeleteAllReplacer(), Mutator.SWAP).solve();
            avgV1 += c1.getFitness();
            str.append("V1: ").append(c1.getFitness()).append("\n");
            Chromosom c2 = new PermGA(knapsack, 5000, new TimeStopper(30000), 1.0, 0.5, new RandomPool(), new OrderBasedCrossover(),
                new SteadyStateReplacer(true, false, 0.5), Mutator.SWAP).solve();
            avgV2 += c2.getFitness();
            str.append("V2: ").append(c2.getFitness()).append("\n");
            Chromosom c3 = new PermGA(knapsack, 5000, new TimeStopper(30000), 1.0, 0.01, new TournamentSelection(2), new OrderBasedCrossover(),
                new DeleteAllReplacer(), Mutator.SWAP).solve();
            avgV3 += c3.getFitness();
            str.append("V3: ").append(c3.getFitness()).append("\n");
            Chromosom c4 = new PermGA(knapsack, 2000, new TimeStopper(30000), 1.0, 0.1, new TournamentSelection(5), new UniformOrderCrossover(),
                new DeleteAllReplacer(), Mutator.SWAP).solve();
            avgV4 += c4.getFitness();
            str.append("V4: ").append(c4.getFitness()).append("\n");

          } catch (Exception e) {
            System.out.println(e.getMessage());
          }


          i++;
        }
        str.append("-Resumee-").append("\n");
        str.append("AVG V1:").append(avgV1 / lib.getKnapsacks().size()).append("\n");
        str.append("AVG V2:").append(avgV2 / lib.getKnapsacks().size()).append("\n");
        str.append("AVG V3:").append(avgV3 / lib.getKnapsacks().size()).append("\n");
        str.append("AVG V4:").append(avgV4 / lib.getKnapsacks().size()).append("\n");
      }


    }
    String data = str.toString();
    try (FileOutputStream fos = new FileOutputStream(new File("outputV2.txt"));
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

  public void getGAValues() {
    System.out.println("Starting V2....");
    KnapsackLibrary lib = KnapsackLibraryReader.readFile("knapsacks.ser");
    assert lib != null;
    StringBuilder str = new StringBuilder();
    Selector[] selectors = new Selector[]{new RandomPool(), new FitnessProportional(), new TournamentSelection(2)};
    Crossover[] crossovers = new Crossover[]{new CycleCrossover(), new OrderBasedCrossover(), new PartiallyMatchedCrossover(), new UniformOrderCrossover()};
    Replacer[] replacers = new Replacer[]{new DeleteAllReplacer(), new SteadyStateReplacer(false, false, 0.5), new SteadyStateReplacer(false, false, 0.7),
        new SteadyStateReplacer(false, true, 0.5), new SteadyStateReplacer(false, true, 0.7),
        new SteadyStateReplacer(true, false, 0.5), new SteadyStateReplacer(true, false, 0.7),
        new SteadyStateReplacer(true, true, 0.5), new SteadyStateReplacer(true, true, 0.7)};
    int[] popSizes = new int[]{100, 1000, 5000};
    double[] crossoverProbs = new double[]{0.5, 0.7, 1.0};
    double[] mutationProbs = new double[]{0.01, 0.5};
    for (Selector selector : selectors) {
      for (Crossover crossover : crossovers) {
        for (Replacer replacer : replacers) {
          for (int popSize : popSizes) {
            for (double crossoverProb : crossoverProbs) {
              for (double mutationProb : mutationProbs) {
                System.out.println("---Next one---");
                System.out.println("Selector: " + selector.getClass());
                System.out.println("Crossover: " + crossover.getClass());
                System.out.println("Replacer: " + replacer.getClass());
                System.out.println("popSize: " + popSize);
                System.out.println("Crossover Probability: " + crossoverProb);
                System.out.println("Mutation Probability: " + mutationProb);
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
                for (KConstraintMultipleKnapsack knapsack : lib.getKnapsacks()) {
                  str.append("Knapsack Nr: ").append(i);
                  Chromosom chromosom;
                  try {
                    chromosom = new PermGA(knapsack, popSize, new TimeStopper(10000), crossoverProb, mutationProb, selector,
                        crossover, replacer, Mutator.SWAP).solve();
                    str.append("ERROR");
                  } catch (Exception e) {
                    System.out.println(e.getMessage());
                    chromosom = new Chromosom(IntStream.range(0, knapsack.getNrItems()).boxed().collect(Collectors.toList()), knapsack);
                  }

                  avgGAProfit += chromosom.getFitness();
                  str.append("GA Profit: ").append(chromosom.getFitness());
                  Solution s = GreedySolution.getGreedy(knapsack);
                  str.append("GreedyProfit: ").append(s.getProfit());
                  avgGreedyProfit += s.getProfit();
                  i++;
                }
                str.append("-Resumee-");
                str.append("Avg GA Profit: ").append(avgGAProfit / lib.getKnapsacks().size());
                str.append("Avg Greedy Profit: ").append(avgGreedyProfit / lib.getKnapsacks().size());
              }
            }


          }

        }
      }
    }
    String data = str.toString();
    try (FileOutputStream fos = new FileOutputStream(new File("outputV2.txt"));
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

  public void test(String[] args) {
    StringBuilder str = new StringBuilder();
    for (int i : new int[]{50, 100, 200, 500}) {
      for (int j = 0; j < 16; j++) {
        KnapsackLibrary lib = KnapsackLibraryReader.readFile("testinstances/knapsack" + i +
            "nr" + j + ".ser");
        assert lib != null;
        double avgV1 = 0.0;
        double avgV2 = 0.0;
        double avgV3 = 0.0;
        double avgV4 = 0.0;
        double avgVLSN = 0.0;
        double avgIVLSN = 0.0;
        str.append("Instance: knapsack").append(i).append("nr").append(j).append(".ser \n");
        System.out.println("Instance: knapsack" + i + "nr" + j + ".ser");
        System.out.println(lib.getKnapsacks().size());
        for (KConstraintMultipleKnapsack k : lib.getKnapsacks().subList(0, 2)) {

          try {
            Chromosom c1 = new PermGA(k, 1000, new TimeStopper(300000), 0.7, 0.01, new TournamentSelection(2), new UniformOrderCrossover(),
                new DeleteAllReplacer(), Mutator.SWAP).solve();
            avgV1 += c1.getFitness();

            Chromosom c2 = new PermGA(k, 5000, new TimeStopper(300000), 1.0, 0.5, new RandomPool(), new OrderBasedCrossover(),
                new SteadyStateReplacer(true, false, 0.5), Mutator.SWAP).solve();
            avgV2 += c2.getFitness();

            Chromosom c3 = new PermGA(k, 5000, new TimeStopper(300000), 1.0, 0.01, new TournamentSelection(2), new OrderBasedCrossover(),
                new DeleteAllReplacer(), Mutator.SWAP).solve();
            avgV3 += c3.getFitness();

            Chromosom c4 = new PermGA(k, 2000, new TimeStopper(300000), 1.0, 0.1, new TournamentSelection(5), new UniformOrderCrossover(),
                new DeleteAllReplacer(), Mutator.SWAP).solve();
            avgV4 += c4.getFitness();

            Solution s1 = new VLSN().solve(k);
            avgVLSN += s1.getProfit();

            Solution s2 = new ImprovedVLSN().solve(k);
            avgIVLSN += s2.getProfit();

          } catch (Exception e) {
            System.out.println("ERROR occured :(");
            System.out.println(e.getMessage());
          }
        }
        str.append("V1: ").append(avgV1 / lib.getKnapsacks().size()).append("\n");
        str.append("V2: ").append(avgV2 / lib.getKnapsacks().size()).append("\n");
        str.append("V3: ").append(avgV3 / lib.getKnapsacks().size()).append("\n");
        str.append("V4: ").append(avgV4 / lib.getKnapsacks().size()).append("\n");
        str.append("S1: ").append(avgVLSN / lib.getKnapsacks().size()).append("\n");
        str.append("S2: ").append(avgIVLSN / lib.getKnapsacks().size()).append("\n");

        if (avgV1 > avgIVLSN || avgV2 > avgIVLSN || avgV3 > avgIVLSN || avgV4 > avgIVLSN) {
          System.out.println("GA hat gewonnen");
        } else {
          System.out.println("VLSN hat gewonnen");
        }
      }
    }
    String data = str.toString();
    try (FileOutputStream fos = new FileOutputStream(new File("outputComp.txt"));
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

  public void dododododo(String[] args) {
    System.out.println("Alright lets goooooooooo!");
    for (int i : new int[]{50, 100, 200, 500}) {
      for (int j = 0; j < 16; j++) {
        KnapsackLibrary lib = KnapsackLibraryReader.readFile("testinstances/knapsack" + i +
            "nr" + j + ".ser");
        assert lib != null;
        KConstraintMultipleKnapsack knapsack = lib.getKnapsacks().get(0);
        Chromosom c = new PermGA(knapsack, 5000, new TimeStopper(100000), 0.7, 0.5, new TournamentSelection(4), new CycleCrossover(),
            new SteadyStateReplacer(true, false, 0.7), Mutator.SWAP).solve();
        Solution s = c.buildSolution();


      }
    }
  }

}





