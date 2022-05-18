package ganew.crossover;

import ganew.Chromosom;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import knapsack.KConstraintMultipleKnapsack;

/**
 * Klasse die das Crossover-Interface implementiert. Hierbei als Cycle-Crossover (CX) für GAs mit Permutationsrepräsentation.
 * Implementiert das Cycle-Crossover. Dabei erhält das Kindchromosom das erste Allel von dem ersten Elternchromosom. Danach wird das Allel, welches an dieser Stelle in dem
 * zweiten Elternteil vorkommt an der Stelle eingefügt, wo es in dem ersten Elternteil vorkommt. Dies wird wiederholt, bis ein Allel eingefügt werden soll, welches schon in dem
 * Kinderchromosom vorkommt und ein Kreis geschlossen ist. Danach werden die Lücken mit den restlichen Allelen des zweiten Elternteils gefüllt
 */
public class CycleCrossover implements Crossover{

  /**
   * Methode die das Cycle-Crossover für zwei gegebene Elternchromosome durchführt und dabei ein Kinderchromosom erzeugt, welches anschließend returnt wird.
   *
   * @param knapsack Die Instanz des K-MKPs
   * @param p1  Erstes Elternchromosom
   * @param p2  Zweites Elternchromosom
   * @return  Kinderchromosom welches durch CX erzeugt wurde
   */
  @Override
  public Chromosom crossover(KConstraintMultipleKnapsack knapsack,Chromosom p1, Chromosom p2) {
    Integer[] child = new Integer[knapsack.getNrItems()];
    //Starte mit dem ersten Allel
    int index = 0;
    do {
      //Fülle das Chromosom child mit dem Allel an der Stelle index in dem Elternchromosom
      child[index] = p1.getSolution().get(index);
      //Der nächste Wert von index ist der Index von dem Elternteil p1 von dem Wert, der in der zweiten Lösung an der Stelle von dem alten index steht
      index = p1.getSolution().indexOf(p2.getSolution().get(index));
      //Wiederhole dieses Vorgehen bis an der Stelle von dem neuen index-Wert schon ein Allel eingetragen wurde
    } while (child[index] == null);
    //Dann fülle die Lücken mit den Allelen aus dem zweiten Elternteil
    for (int i = 0; i < child.length; i++) {
      if (child[i] == null)
        child[i] = p2.getSolution().get(i);
    }
    //Erzeuge aus dem Integer-Array ein Chromosom und gebe dies aus
    return new Chromosom(Arrays.stream(child).collect(Collectors.toList()), knapsack);

  }
}
