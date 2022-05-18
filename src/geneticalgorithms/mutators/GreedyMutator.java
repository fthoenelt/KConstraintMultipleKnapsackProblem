package geneticalgorithms.mutators;

import java.util.ArrayList;
import java.util.List;
import knapsack.Item;
import knapsack.Solution;
import vlsn.GreedySolution;

/**
 * Implementiert das Mutator-Interface des GAs für das K-MKP. Dabei wird ein Individuum mutiert, indem die zugehörige Lösung mit der Greedy-Heuristik gefüllt wird. Dabei werden
 * immer nur zulässige Lösungen erzeugt
 */

public class GreedyMutator implements Mutator {

  boolean feasible;


  public GreedyMutator(boolean feasible){
    this.feasible = feasible;
  }

  /**
   * Mutiert eine übergebene Lösung der K-MKPs
   *
   * @param chromosom Das zu mutierende Individuum
   * @return  Die veränderte Lösung
   */
  @Override
  public Solution mutate(Solution chromosom) {
    List<Item> notUsed = new ArrayList<>();
    //Bestimme alle nicht genutzten Items
    for(Item i: chromosom.getKnapsack().getItems()){
      if(!chromosom.isUsed(i)){
        notUsed.add(i);
      }
    }
    //Und fülle die Lösung mit der Greedy-Heuristik
    GreedySolution.fillGreedy(chromosom.getKnapsack(), chromosom, notUsed);
    return chromosom;
  }

  @Override
  public boolean getFeasibility() {
    return this.feasible;
  }
}
