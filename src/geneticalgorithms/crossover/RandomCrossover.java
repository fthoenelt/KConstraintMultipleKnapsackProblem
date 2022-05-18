package geneticalgorithms.crossover;

import java.util.Random;
import knapsack.Item;
import knapsack.Knapsack;
import knapsack.Solution;

/**
 * Klasse welche das Crossover-Interface implementiert. Hierbei wird ein zufälliges Crossover verwendet. Für jedes Objekt wird dabei bei dem Crossover zufällig entschieden, ob
 * es dem Rucksack zugeordnet wird, welchem es in der ersten Elternlösung zugeteilt ist oder dem zweiten.
 */
public class RandomCrossover implements Crossover {

  boolean feasible;
  Random rand;

  /**
   * Konstruktor des RandomCrossovers mit der Wahl, ob nur zulässige oder auch unzulässige Lösungen bei dem Crossover erzeugt werden
   *
   * @param feasible  Wenn true werden nur zulässige Lösungen bei dem Crossover erzeugt
   */
  public RandomCrossover(boolean feasible){
    this.feasible = feasible;
    this.rand = new Random();
  }

  /**
   * Methode, welche das oben beschriebene Crossover durchführt und ein Solution-Objekt zurückgibt welches Eigenschaften von beidem Elternchromosomen teilt
   *
   * @param chrom1  Das erste Elternchromosom
   * @param chrom2  Das zweite Elternchromosom
   * @return  Ein Solution-Objekt welches als Kinderchromosom fungiert
   */
  @Override
  public Solution crossover(Solution chrom1, Solution chrom2) {
      Solution child = new Solution(chrom1.getKnapsack());
      //Betrachte jedes Item der K-MKP-Instanz
      for(Item i : child.getKnapsack().getItems()){
        //Wähle zufällig einen Rucksack aus den Zuordnungen der beiden Elternchromosome
        boolean choose = rand.nextBoolean();
        Knapsack k = (choose)? chrom1.getKnapsack(i):chrom2.getKnapsack(i);
        //Wenn das Objekt dem Rucksack zugeordnet werden kann wird dies gemacht
        if(k!=null&& (child.canBeAdded(i, k)||!feasible)){
          child.addItem(i, k);
        }else {
          //Sonst versuche den anderen Rucksack
          k = (choose)? chrom2.getKnapsack(i):chrom1.getKnapsack(i);
          if(k!=null&& (child.canBeAdded(i, k)||!feasible)){
            child.addItem(i, k);
          }
        }
      }
      return child;
  }

  @Override
  public boolean getFeasibility() {
    return this.feasible;
  }
}
