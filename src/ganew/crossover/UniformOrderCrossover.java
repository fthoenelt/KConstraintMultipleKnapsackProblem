package ganew.crossover;

import ganew.Chromosom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import knapsack.KConstraintMultipleKnapsack;

/**
 * Klasse, welche das Crossover-Interface des PermGA als Uniform-Order Crossover implementiert. Dabei wird ein zufälliges binäres Template der gleichen Länge wie die ELtern
 * generiert. Das Kinderchromosom übernimmt dann die Allele des ersten Elternteils an den Stellen, wo in dem Template eine 1 steht. Die Gene an den Stellen, wo eine 0 steht
 * werden dann in der Reihenfolge sortiert, wie sie in dem zweiten Elternteil vorkommen und in die Lücken gefüllt.
 */

public class UniformOrderCrossover implements Crossover{

  Random random;

  public UniformOrderCrossover(){
    this.random = new Random();
  }

  /**
   * Methode die das Uniform-Order Crossover für zwei gegebene Elternteile und eine K-MKP-Instanz durchführt
   *
   * @param knapsack Die Instanz des K-MKPs
   * @param p1  Erstes Elternchromosom
   * @param p2  Zweites Elternchromosom
   * @return  Ein Kinderchromosom
   */
  @Override
  public Chromosom crossover(KConstraintMultipleKnapsack knapsack,Chromosom p1, Chromosom p2) {
    Integer[] child = new Integer[knapsack.getNrItems()];
    //Speichert die noch nicht verwendeten Allele
    ArrayList<Integer> notUsed = new ArrayList<>(p2.getSolution());
    for(int i = 0; i < knapsack.getNrItems(); i++){
      //Binäres Template wird nur implizit generiert
      if(random.nextBoolean()){
        //Und die Allele entsprechend übernommen
        child[i] = p1.getSolution().get(i);
        notUsed.remove(p1.getSolution().get(i));
      }
    }
    //Füge die noch nicht vorkommenden Allele in die Kinderlösung ein
    for(int i = 0; i < child.length; i++){
      if(child[i]==null){
        assert !notUsed.isEmpty();
        child[i] = notUsed.remove(0);
      }
    }
    //Gebe das somit erzeugt Kind zurück
    return new Chromosom(Arrays.asList(child), knapsack);
  }
}
