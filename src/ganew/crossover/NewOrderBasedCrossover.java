package ganew.crossover;

import ganew.Chromosom;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import knapsack.KConstraintMultipleKnapsack;

/**
 * OrderBasedCrossover bei welchem die Crossoverpunkte beliebig gewählt werden. Sollte cp2 < cp1 gelten, so werden die Allele mit den Indizes 0 bis cp2 sowie cp1 bis n-1 aus dem
 * ersten Elternteil übernommen
 */

public class NewOrderBasedCrossover implements Crossover{

  Random random = new Random();

  @Override
  public Chromosom crossover(KConstraintMultipleKnapsack knapsack, Chromosom p1, Chromosom p2) {
    //Initialisiere das zu füllende Array aus welchem das Kinderchromosom erzeugt wird
    Integer[] child = new Integer[knapsack.getNrItems()];
    //Wähle die Crossoverpunkte zufällig
    int cp1 = random.nextInt(knapsack.getNrItems());
    int cp2 = random.nextInt(knapsack.getNrItems());

    HashSet<Integer> used = new HashSet<>();

    //Fülle das Kinderchromosom mit den Allelen zwischen den Chrossoverpunkten aus dem ersten Elternteil
    while(cp1 != cp2){
      child[cp1] = p1.getSolution().get(cp1);
      used.add(child[cp1]);
      cp1++;
      if(cp1 == knapsack.getNrItems()){
        cp1 = 0;
      }
    }
    //Und übernehme die noch nicht vorkommenden Allele in der Reihenfolge wie sie in dem zweiten Elternteil vorkommen
    int index = cp2;
    for(Integer i : p2.getSolution()){
      if(!used.contains(i)){
        while(child[index]!= null){
          index++;
          if (index>= knapsack.getNrItems()){
            index=0;
          }
        }
        child[index] = i;
      }
    }
    //Erzeuge aus dem Array ein Chromosom und gebe dies aus
    return new Chromosom(Arrays.asList(child), knapsack);
  }
}
