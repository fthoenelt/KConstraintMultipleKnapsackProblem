package geneticalgorithms.startpopulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import knapsack.KConstraintMultipleKnapsack;
import knapsack.Solution;
import vlsn.GreedySolution;

/**
 *  Implementiert den StartPopulationGenerator für den GA für das K-MKP. Erzeugt eine initiale Startpopulation über eine Abwandlung der Greedy-Heuristik nach Ahuja und Cunha.
 *  Hierbei werden die Items und die Rucksäcke zufällig sortiert und iterativ gefüllt wie in der Greedy-Heuristik. Dabei wird die sortierung für jedes Individuum neu gewählt,
 *  sodass die Population insgesamt möglichst viel Variation aufweist
 */

public class RandomSortedPopulation implements StartPopulationGenerator{

  KConstraintMultipleKnapsack knapsack;

  public RandomSortedPopulation(KConstraintMultipleKnapsack knapsack){
    this.knapsack = knapsack;
  }

  /**
   * Generiert auf den oben beschriebenen Weg eine initiale Startpopulation in Form einer Liste einer bestimmten Länge von Lösungen
   *
   * @param size  Die Größe welche die Startpopulation haben soll
   * @return  Liste von Lösungen
   */
  @Override
  public List<Solution> generatePopulation(int size) {
    List<Integer> knapsackIDs = IntStream.range(0, knapsack.getNrKnapsacks()).boxed().collect(Collectors.toList());
    List<Integer> itemIDs =  IntStream.range(0, knapsack.getNrItems()).boxed().collect(Collectors.toList());
    List<Solution> solutions = new ArrayList<>(size);
    for(int i = 0; i < size-1; i++){
      Solution s = new Solution(knapsack);
      Collections.shuffle(knapsackIDs);
      Collections.shuffle(itemIDs);
      for(Integer itemID: itemIDs){
        for(Integer knapsackID: knapsackIDs){
          if(s.canBeAdded( knapsack.getItem(itemID),knapsack.getKnapsack(knapsackID))){
            s.addItem(knapsack.getItem(itemID),knapsack.getKnapsack(knapsackID));
            break;
          }
        }
      }
      solutions.add(s);
    }
    solutions.add(GreedySolution.getGreedy(knapsack));

    return solutions;
  }



  @Override
  public boolean getFeasibility() {
    return false;
  }
}
