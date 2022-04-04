package test;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;
import knapsack.KConstraintMultipleKnapsack;
import knapsack.Knapsack;
import library.KnapsackLibrary;
import library.KnapsackLibraryReader;
import library.KnapsackLibraryWriter;
import org.junit.Test;

public class TestWriterReader {

  @Test
  public void test(){
    ArrayList<KConstraintMultipleKnapsack> knapsacks = new ArrayList<>();
    for(int i = 0; i < 20; i++){
      knapsacks.add(GenerateTestInstances.generateTestInstance(100, 2, 200, 5, 0.3));
    }
    KnapsackLibrary lib = new KnapsackLibrary(knapsacks);
    KnapsackLibraryWriter.writeLibrary(lib);
    KnapsackLibrary lib2 = KnapsackLibraryReader.readFile("knapsacks.ser");

    assert lib.getKnapsacks().size()== Objects.requireNonNull(lib2).getKnapsacks().size();
    for(int i = 0; i< lib.getKnapsacks().size();i++){
      assert lib.getKnapsacks().get(i).getNrKnapsacks()==lib2.getKnapsacks().get(i).getNrKnapsacks();
      assert lib.getKnapsacks().get(i).getK()==lib2.getKnapsacks().get(i).getK();
      assert lib.getKnapsacks().get(i).equals(lib2.getKnapsacks().get(i));
    }
    //new File("knapsacks.ser").delete();
  }
  @Test
  public void writeTestInstances(){

    for(int n: new int[]{50,100,200,500}){
      int index = 0;
      for(int r:new int[]{100, 1000}){
        for(double alpha: new double[]{0.3,0.5}){
          for(int m: new int[]{5,10}){
            for(int k: new int[]{2,5}){
              System.out.println(m+", "+ k+", "+ alpha+", "+r+": "+index);
              ArrayList<KConstraintMultipleKnapsack> knapsacks = new ArrayList<>();
              for(int i = 0; i < 10; i++){
                knapsacks.add(GenerateTestInstances.generateTestInstance(r, k, n, m, alpha));
              }
              KnapsackLibrary lib = new KnapsackLibrary(knapsacks);
              KnapsackLibraryWriter.writeLibrary(lib, "src/testinstances/knapsack"+n+"nr"+index+".ser");
              index++;
            }
          }
        }
      }
    }

  }
}
