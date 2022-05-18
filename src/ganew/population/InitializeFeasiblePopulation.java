package ganew.population;

import ganew.Chromosom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import knapsack.Item;
import knapsack.KConstraintMultipleKnapsack;
import knapsack.Solution;
import vlsn.GreedySolution;

/**
 * Klasse welche eine zufällige und zulässige Startpopulation erzeugt
 */

public class InitializeFeasiblePopulation {

  /**
   * Methode die eine zufällige zulässige Startpopulation einer gegebenen Größe für eine Instanz des K-MKPs erzeugt. Hierbei besteht die Population aus Chromosomen der
   * Permutationscodierung. Die einzelne Chromosome sind zufällig gewählte Permutationen. Des Weiteren wird ein Kinderchromosom mithilfe des Greedy-Algorithmus erzeugt und der
   * Population hinzugefügt.
   *
   * @param knapsack Die K-MKP Instanz für welche die Startpopulation erzeugt werden soll
   * @param size  Die gewünschte Größe der Startpopulation
   * @return  Eine ArrayList der Größe size mit zufälligen und zulässigen Chromosomen
   */
  public static List<Chromosom> initializePopulation(KConstraintMultipleKnapsack knapsack, int size){
    List<Chromosom> pop = new ArrayList<>(size);
    //Füge size-1 zufällige Chromosome zu der Population hinzu
    for(int i = 0; i < size-1; i++){
      //Erzeuge dafür zunächst eine Liste mit den Werten von 0 bis n-1 in einer zufälligen Reihenfolge
      List<Integer> perm = IntStream.range(0, knapsack.getNrItems()).boxed().collect(Collectors.toList());
      Collections.shuffle(perm);
      //UNd füge diese der Population hinzu
      pop.add(new Chromosom(perm, knapsack));
    }
    //Und zusätzlich die Greedy-Lösung
    Solution s = GreedySolution.getGreedy(knapsack);
    ArrayList<Integer> items= new ArrayList<>();
    for(int i = 0; i < knapsack.getNrKnapsacks(); i++){
      for(Item item: s.itemsOf(knapsack.getKnapsack(i))){
        items.add(item.getItemID());
      }
    }
    for(Item i: knapsack.getItems()){
      if(!s.isUsed(i)) items.add(i.getItemID());
    }
    pop.add(new Chromosom(items, knapsack));
    //Gebe die Population zurück
    return pop;


  }
}
