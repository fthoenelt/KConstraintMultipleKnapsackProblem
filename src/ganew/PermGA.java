package ganew;

import ganew.crossover.Crossover;
import ganew.population.InitializeFeasiblePopulation;
import ganew.replacement.Replacer;
import ganew.selection.Selector;
import geneticalgorithms.stopcriterias.StopCriteria;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import knapsack.KConstraintMultipleKnapsack;

/**
 * Hauptklasse des Genetischen Algorithmus' mit Permutationscodierung für das K-MKP (auch PermGA), bei welcher eine Population von Individuen iterativ mit Selektion,
 * Rekombination, Mutation und Ersetzung einer simulierten Evolution ausgesetzt werden mit dem Ziel dadurch ein Individuum mit einer möglichst hohen Fitness zu erzeugen.
 */

public class PermGA {

  KConstraintMultipleKnapsack knapsack;
  int popSize;
  StopCriteria criteria;
  Random random;
  double crossoverProb;
  double mutationProb;
  Selector selector;
  Crossover crossover;
  Replacer replacer;
  Mutator mutator;

  /**
   * Konstruktor für den PermGA. Hierbei werden sowohl die zu optimierende K-MKP-Instanz als auch die Parameter des GAs gesetzt, um diesen zu konfigurieren
   *
   * @param knapsack  Die K-MKP-Instanz, für welche der PermGA eine möglichst gute Lösung berechnen soll
   * @param popSize Die Größe der Population von Individuen
   * @param criteria  Das Abbruchkriterium des PermGAs
   * @param crossoverProb Die Wahrscheinlichkeit mit welcher ein Crossover stattfindet
   * @param mutationProb  Die Wahrscheinlichkeit mit welcher ein durch Rekombination erzeugtes Individuum mutiert wird
   * @param selector  Der Selektor, welcher in dem PermGA verwendet wird
   * @param crossover Das verwendete Crossover um die Rekombination von Chromosomen zu ermöglichen
   * @param replacer  Die Ersetzungsstrategie, welche bestimmt, inwiefern die neue Generation die alte ersetzt
   * @param mutator Der Mutator, welcher angewendet werden soll
   */
  public PermGA(KConstraintMultipleKnapsack knapsack, int popSize, StopCriteria criteria, double crossoverProb, double mutationProb,
      Selector selector, Crossover crossover, Replacer replacer, Mutator mutator) {
    this.knapsack = knapsack;
    this.popSize = popSize;
    this.criteria = criteria;
    this.random = new Random();
    this.crossoverProb = crossoverProb;
    this.mutationProb = mutationProb;
    this.selector = selector;
    this.crossover = crossover;
    this.replacer = replacer;
    this.mutator = mutator;
  }

  /**
   * Methode, welche für die in dem Konstruktor übergebene K-MKP Instanz eine optimale Lösung annähert. Dies geschieht über einen Genetischen Algorithmus auf Basis von
   * Permutations-Chromosomen mit den in dem Konstruktor festgelegten Operatoren und Parametern
   *
   * @return Das Chromosom mit dem besten Fitnesswert welches in dem GA gefunden wurde
   */
  public Chromosom solve() {
    //Generiere eine initiale Population
    List<Chromosom> population = InitializeFeasiblePopulation.initializePopulation(knapsack, popSize);
    //best speichert das beste gefundene Chromosom
    Chromosom best = null;
    int bestFit = 0;
    //Gehe die initiale Population durch, berechne für jedes Individuum die Fitness und speicher die beste gefundene Fitness sowie das zugehörige Individuum
    for (Chromosom c : population) {
      if (c.getFitness() > bestFit) {
        best = c;
        bestFit = c.getFitness();
      }
    }
    //Solange das Abbruchkriterium nicht erfüllt ist, erzeuge neue Generationen
    while (!criteria.stop(false)) {
      //Neue, noch leere Generation
      List<Chromosom> newPop = new ArrayList<>(popSize);
      //Erzeuge den Mating Pool mit dem Selektor und der alten Generation
      List<Chromosom> matingPool = selector.createMatingPool(population, popSize);
      //Und erzeuge iterativ neue Individuen bis die neue Generation die gewünschte Größe hat
      while (newPop.size() <= popSize) {
        //Wähle dazu zufällig zwei Elternindividuen aus dem Mating Pool
        Chromosom p1 = matingPool.get(random.nextInt(popSize));
        Chromosom p2 = matingPool.get(random.nextInt(popSize));
        Chromosom chromosom;
        //Rekombiniere die beiden gewählten Elternindividuen mit der Crossover-Wahrscheinlichkeit aus dem Konstruktor
        if (random.nextDouble() < crossoverProb) {
          //Rekombination der Elternindividuen
          chromosom = crossover.crossover(knapsack, p1, p2);
        } else {
          //Ansonsten ist das Kinderchromosom eine Kopie eines der beiden Eltern, wobei zufällig gewählt wird von welchem
          chromosom = (random.nextBoolean()) ? p1 : p2;
        }
        //Mutiere das Kinderchromosom mit der Mutationswahrscheinlichkeit, indem zwei Allele getauscht werden
        if (random.nextDouble() < mutationProb) {
          int index1 = random.nextInt(knapsack.getNrKnapsacks());
          int index2 = random.nextInt(knapsack.getNrKnapsacks());
          //Wähle hierbei den entsprechenden Mutator
          switch(mutator){
            case SWAP -> chromosom.swap(index1, index2);
            case SHUFFLE -> chromosom.shuffle(index1, index2);
            case ORDER -> chromosom.order(index1, index2);
          }

        }
        //Füge das Kinderchromosom der neuen Population hinzu
        newPop.add(chromosom);
        //Und prüfe ob es die beste bis dato gefundene Fitness besitzt
        if (chromosom.getFitness() > bestFit) {
          best = chromosom;
          bestFit = chromosom.getFitness();
        }
      }
      //Wenn die neue Generation komplett gefüllt wurde ersetze die alte Population mit der neuen
      population = replacer.replace(population, newPop, popSize);
    }
    //Zuletzt gebe die beste gefundene Lösung aus
    return best;
  }

  public enum Mutator{
    SWAP, SHUFFLE, ORDER;
  }

}
