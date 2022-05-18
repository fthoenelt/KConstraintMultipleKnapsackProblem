package geneticalgorithms.crossover;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import knapsack.Item;
import knapsack.Solution;
import vlsn.GreedySolution;

/**
 *  Klasse die das Crossover-Interface für den GA für das K-MKP implementiert. Hierbei wird das OPC-Verfahren verwendet, wobei ein zufälliger Crossover-Punkt innerhalb der
 *  codierten Chromosomen gewählt wird. In diesem Fall entspricht dies einem Punkt zwischen den Rucksäcken. Alle Rucksäcke vor diesem Crossover-Punkt werden mit den ihnen
 *  zugewiesenen Items so von dem ersten Elternteil in den Nachfahren übernommen. Alle Rucksäcke danach werden mit den zugewiesenen Items aus dem zweiten Elternteil übernommen.
 *  Damit nur zulässige Lösungen erzeugt werden, werden die Items, die doppelt zugewiesen wurden aus den Rucksäcken des zweiten Elternteils entfernt. Um die so erzeugten Lücken
 *  zu schließen kann danach die Greedy-Heuristik aus dem VLSN-Algorithmus nach Ahuja und Cunha verwerwendet werden.
 */
public class OnePointCrossover implements Crossover {

  boolean feasible;
  Random rand;
  boolean fillGreedy;

  /**
   * Konstruktor des OPCs mit der Möglichkeit einzustellen ob nur zulässige oder auch unzulässige Lösungen erzeugt werden können
   *
   * @param feasible  Wenn true erzeugt OPC nur zulässige Lösungen
   */
  public OnePointCrossover(boolean feasible){
    this.feasible = feasible;
    this.rand = new Random();
    this.fillGreedy = false;
  }

  /**
   * Knostruktor des OPCs mit der Wahl der Zulässigkeit als Parameter sowie die Einstellung ob die Greedy-Heuristik nach dem Crossover auf die Lösung angewendet werden soll
   *
   * @param feasible  Wenn true erzeugt OPC nur zulässige Lösungen
   * @param fillGreedy  Wenn true so wird Lösung mit Greedy-Heuristik gefüllt
   */
  public OnePointCrossover(boolean feasible, boolean fillGreedy){
    this.feasible = feasible;
    this.rand = new Random();
    this.fillGreedy = fillGreedy;
  }
//TODO: new crossover (Zufallszahl w < m, übernehme Rucksack 1-w von p1 und w-m von p2)

  /**
   * Methode, welche das oben beschriebene OPC ausführt. Gibt ein Solution-Objekt zurück, welches Eigenschaften beider Elternchromosome chrom1 und chrom2 teilt
   *
   * @param chrom1  Das erste Elternchromosom
   * @param chrom2  Das zweite Elternchromosom
   * @return  Solution als Nachkommenchromosom
   */
  @Override
  public Solution crossover(Solution chrom1, Solution chrom2) {
      Solution child = new Solution(chrom1.getKnapsack());
      //Crossoverpunkt
      int cut = rand.nextInt(chrom1.getKnapsack().getNrKnapsacks());
      List<Item> notUsed = new ArrayList<>();
      //Betrachte jedes Item der K-MKP Instanz
      for(Item i: chrom1.getKnapsack().getItems()){
        //Prüfe die Parameter und die Zuordnung in den Elternchromosomen
        if(chrom1.isUsed(i)&&chrom1.getKnapsack(i).getKnapsackID()<= cut&&(!this.feasible|| child.canBeAdded(i, chrom1.getKnapsack(i)))){
          //Und füge das Objekt dem entsprechenden Rucksack hinzu
          child.addItem(i, chrom1.getKnapsack(i));
        }else if(chrom2.isUsed(i)&&(!this.feasible || child.canBeAdded(i, chrom2.getKnapsack(i)))){
          child.addItem(i, chrom2.getKnapsack(i));
        }else if(this.fillGreedy){
          notUsed.add(i);
        }
      }
      if(this.fillGreedy){
        GreedySolution.fillGreedy(chrom1.getKnapsack(), child, notUsed);
      }
      return child;

  }

  @Override
  public boolean getFeasibility() {
    return this.feasible;
  }
}
