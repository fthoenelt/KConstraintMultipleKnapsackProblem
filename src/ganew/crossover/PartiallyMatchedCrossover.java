package ganew.crossover;

import ganew.Chromosom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import knapsack.KConstraintMultipleKnapsack;

/**
 * Klasse die das Crossover-Interface des PermGAs für das K-MKP mit dem Partially-Matched Crossover (PMX) implementiert. Dabei werden zwei zufällige Crossover-Punkte gewählt.
 * Die Allele innerhalb diese Punkte des einen Elternteils werden dann mit den Allelen, welche bei dem zweiten Elternteil an den selben Stellen vorkommen getauscht.
 */

public class PartiallyMatchedCrossover implements Crossover{
  Random random;

  public PartiallyMatchedCrossover(){
    this.random = new Random();
  }

  /**
   * Methode die das PMX-Crossover für zwei Elternchromosome und eine Rucksackinstanz ausführt
   *
   * @param knapsack Die Instanz des K-MKPs
   * @param p1  Erstes Elternchromosom
   * @param p2  Zweites Elternchromosom
   * @return Kinderchromosom
   */
  @Override
  public Chromosom crossover(KConstraintMultipleKnapsack knapsack, Chromosom p1, Chromosom p2) {
    List<Integer> child = new ArrayList<>(p1.getSolution());
    //Wähle zufällige Crossover-Punkte
    int cp1 = random.nextInt(knapsack.getNrItems()-2);
    int cp2 = cp1+ random.nextInt(knapsack.getNrItems()-cp1);
    //Stelle sicher dass die Crossover-Punkte nicht identisch sind
    while(cp1 == cp2){
      cp2 = cp1+ random.nextInt(knapsack.getNrItems()-cp1);
    }
    //Tausche die Allele zwischen den Crossover-Punkten mit denen, welche an den selben Stellen im anderen Elternteil vorkommen
    for(int i = cp1; i <= cp2; i++){
      Collections.swap(child, i, child.indexOf(p2.getSolution().get(i)));
    }
    //Gebe das erzeugte Kinderchromosom zurück
    return new Chromosom(child, knapsack);
  }
}
