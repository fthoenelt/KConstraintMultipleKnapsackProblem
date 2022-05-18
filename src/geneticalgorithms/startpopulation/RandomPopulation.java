package geneticalgorithms.startpopulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import knapsack.Item;
import knapsack.KConstraintMultipleKnapsack;
import knapsack.Knapsack;
import knapsack.Solution;
import vlsn.GreedySolution;

/**
 *  Implementiert das StartPopulationGeneratorInterface des GAs für das K-MKP. Erzeugt eine Startpopulation welche aus zufälligen Lösungen und der Greedy-Lösung des
 *  VLSN-Algorithmus nach Ahuja und Cunha besteht
 */

public class RandomPopulation implements StartPopulationGenerator{

  Random rand;
  boolean feasible = true;
  KConstraintMultipleKnapsack knapsack;

  /**
   * Konstruktor benötigt die K-MKP Instanz für welche die Startpopulation erzeugt werden soll
   *
   * @param knapsack  K-MKP Instanz
   */
  public RandomPopulation(KConstraintMultipleKnapsack knapsack){
    this.knapsack = knapsack;
    this.rand = new Random();
  }

  /**
   * Konstruktor in welchem zusätzlich entschieden werden kann ob nur zulässige oder auch unzulässige Individuen erzeugt werden sollen
   *
   * @param knapsack  Die K-MKP-Instanz
   * @param feasible  true gdw. nur zulässige Lösungen erzeugt werden sollen
   */
  public RandomPopulation(KConstraintMultipleKnapsack knapsack, boolean feasible){
    this.knapsack = knapsack;
    this.feasible = feasible;
    this.rand = new Random();
  }

  /**
   * Generiert eine zufällige Startpopulation einer bestimmten gegebenen Größe
   *
   * @param size  Die Größe welche die Startpopulation haben soll
   * @return  Eine Liste von Individuen
   */
  @Override
  public List<Solution> generatePopulation(int size) {
    List<Solution> pop = new ArrayList<>(size);
    //Erzeuge size-1 zufällige Lösungen
    for(int i = 0 ; i < size-1; i++){
      pop.add(generateRandom());
    }
    //Und zusätzlich die Greedy-Lösung
    pop.add(GreedySolution.getGreedy(knapsack));
    return pop;
  }

  /**
   * Generiert eine zufällige Lösung des K-MKPs bei welcher für jedes Objekt versucht wird dies einem zufälligen Rucksack zuzuordnen
   *
   * @return  Eine zufällige Lösung des K-MKPs
   */
  private Solution generateRandom(){
    Solution c = new Solution(knapsack);
    /*
    for(int i = 0; i < knapsack.getNrItems(); i++){
      Item item = knapsack.getItem(rand.nextInt(knapsack.getNrItems()));
      if(c.isUsed(item)){
        continue;
      }
      for(int cnt = 0; cnt < 20; cnt++){
        Knapsack k = knapsack.getKnapsack(rand.nextInt(knapsack.getNrKnapsacks()));
        if(feasible && c.canBeAdded(item, k)){
          c.addItem(item, k);
          break;
        }else if(!feasible){
          c.addItem(item, k);
          break;
        }
      }
    }

     */
   // /*

    for(Item i : knapsack.getItems()){
      //Für jedes Item gibt es max 20 versuche dies einem Rucksack zuzuordnen
      for(int cnt = 0; cnt < 20; cnt++){
        //Der Rucksack wird dabei zufällig gewählt
        Knapsack k = knapsack.getKnapsack(rand.nextInt(knapsack.getNrKnapsacks()));
        //Sollte das Item dem Rucksack zugeordnet werden können unter der Zulässigkeitsbedingung wird dies getan
        if(feasible && c.canBeAdded(i, k)){
          c.addItem(i, k);
          break;
        }else if(!feasible){
          //Wenn auch unzulässige Lösungen erzeugt werden können füge es direkt hinzu
          c.addItem(i, k);
          break;
        }
      }
    }

    //*/
    return c;
  }

  @Override
  public boolean getFeasibility() {
    return feasible;
  }
}
