package test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import knapsack.Item;
import knapsack.KConstraintMultipleKnapsack;
import knapsack.Knapsack;
import org.junit.Test;
public class TestGenerate {

  @Test
  public void testInstances(){
    final int r = 1000;
    final int k = 6;
    final int n = 100;
    final int m = 20;
    final double alpha = 0.9;

    KConstraintMultipleKnapsack knapsack = GenerateTestInstances.generateTestInstance(r, k, n, m, alpha);

    assert knapsack.getK()==k;
    assert knapsack.getNrKnapsacks() ==m;
    assert knapsack.getItems().size()==n;

    for (Item i : knapsack.getItems()){
      for(int w : i.getWeights()){
        assert w >=1 && w <= r;
      }
      assert i.getK()==k;
      assert 1 <=i.getProfit()&& i.getProfit()<=r;
    }
    for(Knapsack knapsack1: knapsack.getKnapsacks()){
      for(int c : knapsack1.getCapacities()){
        assert c>=1;
      }
      assert knapsack1.getK()==k;
    }

  }

  @Test
  public void test(){
    KConstraintMultipleKnapsack k = GenerateTestInstances.generateTestInstance(100, 2, 12, 2, 0.6) ;
    System.out.println(k);
    try{
      File f = new File("/home/felix/Dokumente/Studium/WS2122/BA/KConstraintMultipleKnapsackProblem/src/testinstances.txt");
      if(f.createNewFile()){
        System.out.println("File Created");
      }else {
        System.out.println("File already exists");
      }
      FileWriter fw = new FileWriter(f);
      fw.write("# Nr of Items \n");
      fw.write(""+k.getNrItems());
      fw.write("\n# Nr of Knapsacks \n");
      fw.write(""+k.getNrKnapsacks());
      fw.write("\n# Nr of Constraints \n");
      fw.write(""+k.getK());

      fw.write("\n# Items profits:\n");
      for(Item item: k.getItems()){
        fw.write(""+ item.getProfit()+" ");

        fw.write("\n");
      }
      fw.write("\n# Items weights:\n");
      for(Item item: k.getItems()){
        for(int weight : item.getWeights()){
          fw.write(""+weight+" ");
        }
        fw.write("\n");
      }

      fw.write("# Knapsacks:\n");
      for(Knapsack kk: k.getKnapsacks()){
        for(int cap: kk.getCapacities()){
          fw.write(""+ cap + " ");
        }
        fw.write("\n");
      }
      fw.close();
    }catch (IOException e){
      System.out.println("An Error Occured");
      e.printStackTrace();
    }
    try{
      File f = new File("/home/felix/Dokumente/Studium/WS2122/BA/KConstraintMultipleKnapsackProblem/src/testinstances.zpl");
      if(f.createNewFile()){
        System.out.println("File Created");
      }else {
        System.out.println("File already exists");
      }
      FileWriter fw = new FileWriter(f);
      fw.write("param file := \"testinstances.txt\";\n");
      fw.write("""
              param n := read file as "1n" use 1 comment "#";
              do print n;
              param m := read file as "1n" skip 1 use 1 comment "#";
              do print m;
              param k := read file as "1n" skip 2 use 1 comment "#";
              do print k;

              set Items := {1 to n};
              set Knapsacks := {1 to m};
              set Capacities := {1 to k};

              param p[Items] := read file as "1n" skip 3 use n comment "#";

              param w[Items*Capacities] := read file as "n+" skip 3+n use n comment "#";

              param c[Knapsacks*Capacities] := read file as "n+" skip 3+2*n use m comment "#";

              var x[Knapsacks * Items] binary;

              maximize profit: sum <i> in Knapsacks: sum <j> in Items: p[j]* x[i,j];
              subto cap: forall <i> in Knapsacks: forall <h> in Capacities: sum <j> in Items: w[j,h]*x[i,j] <= c[i,h];
              subto one: forall <j> in Items: sum <i> in Knapsacks: x[i,j] <= 1;
              """);
      fw.close();
    }catch (IOException e){
      System.out.println("An Error Occured");
      e.printStackTrace();
    }
  }



}
