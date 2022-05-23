package ganew.crossover;

import ganew.Chromosom;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import knapsack.KConstraintMultipleKnapsack;

/**
 * Klasse die das Crossover-Interface mit dem Order-Based Crossover für den PermGA implementiert. Dabei werden zwei zufällige Crossover-Punkte generiert und die Allele zwischen
 * diesen Punkten aus dem ersten Elternteil übernommen. Die Allele, welche noch nicht in dem Kinderchromosom vorkommen werden dann in der Reihenfolge, wie sie in dem zweiten
 * Elternteil vorkommen in das Kind kopiert.
 */

public class OrderBasedCrossover implements Crossover{

  Random random;

  public OrderBasedCrossover(){
    this.random = new Random();
  }

  /**
   * Methode die für zwei gegebene Elternteile und eine K-MKP-Instanz das Order-Based Crossover ausführt
   *
   * @param knapsack Die Instanz des K-MKPs
   * @param p1  Erstes Elternchromosom
   * @param p2  Zweites Elternchromosom
   * @return  Kinderchromosom
   */
  @Override
  public Chromosom crossover(KConstraintMultipleKnapsack knapsack,Chromosom p1, Chromosom p2) {
    Integer[] child = new Integer[knapsack.getNrItems()];
    //Wähle die Crossover-Punkte
    int cp1 = random.nextInt(knapsack.getNrItems()-1);
    int cp2 = cp1 + random.nextInt(knapsack.getNrItems()-cp1);
    //Speichert, welche Allele schon in dem Kinderchromosom vorkommen
    HashSet<Integer> used = new HashSet<>();
    for(int i = cp1; i <= cp2; i++){
      //Übernehme die Allele zwischen den Crossover-Punkten aus dem ersten Elternchromosom
      child[i]= p1.getSolution().get(i);
      used.add(child[i]);
    }
    int index = 0;
    //Fülle das Kinderchromosom mit den Allelen, welche noch nicht vorkommen in der Reihenfolge wie sie in p2 vorkommen
    for(Integer i : p2.getSolution()){
      if(used.contains(i)){
        continue;
      }else if(index >=cp1 && index <= cp2){
        index = cp2+1;
      }
      child[index]= i;
      index ++;
    }
    //Und gebe das somit erzeugte Chromosom zurück
    return new Chromosom(Arrays.asList(child), knapsack);
  }
}
