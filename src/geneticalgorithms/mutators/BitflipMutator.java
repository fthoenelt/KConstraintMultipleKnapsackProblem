package geneticalgorithms.mutators;

import java.util.Random;
import knapsack.Item;
import knapsack.Knapsack;
import knapsack.Solution;

/**
 * Implementiert einen Mutator für den GA für das K-MKP. Dabei wird bei einem Individuum zufällig ein Objekt einem Rucksack zugeordnet
 */

public class BitflipMutator implements Mutator {
  boolean feasibleSolutions;
  Random rand;

  /**
   * Konstruktor in welchem eingestellt werden kann ob bei der Mutation nur zulässige oder auch unzulässige Lösungen erzeugt werden können
   *
   * @param feasibleSolutions  true wenn nur zulössige Kösungen erzeugt werden sollen
   */
  public BitflipMutator(boolean feasibleSolutions){
    this.feasibleSolutions = feasibleSolutions;
    rand = new Random();
  }

  /**
   * Mutiert eine übergebene Lösung der K-MKPs
   *
   * @param chromosom Das zu mutierende Individuum
   * @return  Die veränderte Lösung
   */
  @Override
  public Solution mutate(Solution chromosom) {
    if(feasibleSolutions){
      //Bestimme ein zufälliges Objekt und einen zufälligen Rucksack
      Knapsack knapsack = chromosom.getKnapsack().getKnapsack(rand.nextInt(chromosom.getKnapsack().getNrKnapsacks()));
      Item item = chromosom.getKnapsack().getItem(rand.nextInt(chromosom.getKnapsack().getNrItems()));
      //Versuche maximal 20 mal ein Objekt einem RUcksack zuzuordnen
      for(int i = 0; i < 20; i++){
        if(!chromosom.isUsed(item)&&chromosom.canBeAdded(item,knapsack)){
          //Wenn das gewählte Objekt dem gewählten Rucksack hinzugefügt werden kann füge es hinzu
          chromosom.addItem(item, knapsack);
          break;
        }
        //Sonst wähle neuen Rucksack und Irem
        knapsack = chromosom.getKnapsack().getKnapsack(rand.nextInt(chromosom.getKnapsack().getNrKnapsacks()));
        item = chromosom.getKnapsack().getItem(rand.nextInt(chromosom.getKnapsack().getNrItems()));
      }
    }else{
      //Wenn auch unzulässige Lösungen erzeugt werden dürfen füge ohne Prüfung hinzu
      chromosom.addItem(chromosom.getKnapsack().getItem(rand.nextInt(chromosom.getKnapsack().getNrItems())),
          chromosom.getKnapsack().getKnapsack(rand.nextInt(chromosom.getKnapsack().getNrKnapsacks()+1)));
    }
    return chromosom;
  }

  @Override
  public boolean getFeasibility() {
    return this.feasibleSolutions;
  }
}
