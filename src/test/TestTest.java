package test;

import ganew.Chromosom;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import knapsack.Item;
import knapsack.KConstraintMultipleKnapsack;
import knapsack.Knapsack;
import library.KnapsackLibrary;
import library.KnapsackLibraryReader;
import org.junit.Test;

public class TestTest {
  @Test
  public void testttest() throws FileNotFoundException {
    for(int n: new int[]{50,100,200,500}){
      for(int i = 0; i < 16; i++){
        KnapsackLibrary lib = KnapsackLibraryReader.readFile("/home/felix/Dokumente/Studium/WS2122/BA/KConstraintMultipleKnapsackProblem/src/testinstances/knapsack"+n+"nr"+i+
            ".ser");
        assert lib != null;
        KConstraintMultipleKnapsack k = lib.getKnapsacks().get(0);
          try{
            File f = new File("/home/felix/Dokumente/Studium/WS2122/BA/KConstraintMultipleKnapsackProblem/src/zimpl/instances/knapsack"+n+"nr"+i+".txt");
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

      }
    }


  }

  @Test
  public void writeZimplFiles(){
    for(int n: new int[]{50,100,200,500}){
      for(int i = 0; i < 16; i++){
        try{
          File f = new File("/home/felix/Dokumente/Studium/WS2122/BA/KConstraintMultipleKnapsackProblem/src/zimpl/zimplfiles/knapsack"+n+"nr"+i+".zpl");
          if(f.createNewFile()){
            System.out.println("File Created");
          }else {
            System.out.println("File already exists");
          }
          FileWriter fw = new FileWriter(f);
          fw.write("param file := \"instances/knapsack"+n+"nr"+i+".txt\";\n");
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
  }


}
