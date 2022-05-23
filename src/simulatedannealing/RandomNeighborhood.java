package simulatedannealing;

import java.util.Random;
import knapsack.Item;
import knapsack.KConstraintMultipleKnapsack;
import knapsack.Knapsack;
import knapsack.Solution;

/**
 * Implementiert eine Nachbarschaft für Simulated Annealing. Hierbei ist die Nachbarschaft einer Lösung des K-MKPs definiert als die Lösungen, welche durch hinzufügen oder
 * entfernen eines Objektes aus einem der Rucksäcke erzeugt werden können
 */

public class RandomNeighborhood implements Neighborhood{
  Random rand;
  KConstraintMultipleKnapsack knapsack;
  int maxIt;

  /**
   * Konstruktor, welcher die Nachbarschaft erzeugt
   *
   * @param knapsack  Die K-MKP-Instanz
   * @param maxIt Die maximale Anzahl an Iterationen, welche genutzt werden, um zufällige Items zu wählen
   */
  public RandomNeighborhood(KConstraintMultipleKnapsack knapsack, int maxIt){
    this.rand = new Random();
    this.knapsack = knapsack;
    this.maxIt=maxIt;
  }

  @Override
  public Solution getNeighbor(Solution solution) {
    //Erzeuge eine neue Lösung
    Solution neighbor = new Solution(solution);
    for(int i = 0; i< maxIt; i++){
      //Versuche maxima maxIt-mal ein Item zu wählen
      Item item  = knapsack.getItem(rand.nextInt(knapsack.getNrItems()));
      //Sollte das Item verwendet sein entferne es aus dem Rucksack
      if(neighbor.isUsed(item)){
        neighbor.removeItem(item);
        return neighbor;
      }else{
        //Sonst versuche es einem Rucksack zuzuordnen
        for(Knapsack k : knapsack.getKnapsacks()){
          if(neighbor.canBeAdded(item, k)){
            neighbor.addItem(item, k);
            return neighbor;
          }
        }
      }
    }
    return neighbor;
  }
}
