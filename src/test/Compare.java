package test;

import geneticalgorithms.GeneticalAlgorithm;
import geneticalgorithms.choosechildren.FittestChild;
import geneticalgorithms.choosers.ChooseFitness;
import geneticalgorithms.choosers.Chooser;
import geneticalgorithms.crossover.Crossover;
import geneticalgorithms.crossover.OnePointCrossover;
import geneticalgorithms.generation.CutGeneration;
import geneticalgorithms.mutators.BitflipMutator;
import geneticalgorithms.mutators.Mutator;
import geneticalgorithms.startpopulation.RandomPopulation;
import geneticalgorithms.stopcriterias.TimeStopper;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import knapsack.KConstraintMultipleKnapsack;
import knapsack.Solution;
import library.KnapsackLibrary;
import library.KnapsackLibraryReader;
import org.junit.Test;
import vlsn.ImprovedVLSN;

public class Compare {

  @Test
  public void test(){
    final Crossover c = new OnePointCrossover(true);
    final Mutator m = new BitflipMutator(true);
    final int popSize = 1000;
    final Chooser chooser = new ChooseFitness();
    final int maxSize = 2000;
    StringBuilder str = new StringBuilder();
    for(int i: new int[]{50,100,200,500}){
      for(int j = 0; j < 16; j++){
        str.append("Instance: knapsack").append(i).append("nr").append(j).append(".ser \n");
        System.out.println("Instance: knapsack"+i+"nr"+j+".ser");
        KnapsackLibrary lib = KnapsackLibraryReader.readFile("/home/felix/Dokumente/Studium/WS2122/BA/KConstraintMultipleKnapsackProblem/src/testinstances/knapsack"+i+
            "nr"+j+".ser");
        assert lib != null;
        KConstraintMultipleKnapsack k = lib.getKnapsacks().get(0);
        Solution sol =
            new GeneticalAlgorithm(k, c, m, new RandomPopulation(k), popSize, new TimeStopper(300000), chooser, maxSize, new CutGeneration(), false, 0.9, 0.01, true, new FittestChild()).solve();
        str.append("GA profit").append(sol.getProfit()).append("\n");
        System.out.println("GA profit"+ sol.getProfit());
        Solution sol2 = new ImprovedVLSN().solve(k);
        str.append("ImprovedVLSN profit:").append(sol2.getProfit()).append("\n");
        System.out.println("ImprovedVLSN profit:" + sol2.getProfit());
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


