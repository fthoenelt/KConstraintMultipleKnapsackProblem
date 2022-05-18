package geneticalgorithms.choosers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import knapsack.Solution;

/**
 * Klasse welche das Chooser-Interface für den GA für das K-MKP implementiert. Hierbei wird die ordinale Selektion, bzw. die Tournament Selektion verwendet. Bei diesem
 * Selektionsverfahren werden eine bestimmte Anzahl an Individuen aus der Population gezogen und diese in einen simulierten Konkurrenzkampf gesetzt. Das fitteste dieser
 * Individuen wird dann dem Mating Pool hinzugefügt.
 */

public class ChooseTournament implements Chooser {

  Random random;
  int s;

  /**
   * Konstruktor für die Tournament Selektion. Hier kann die Anzahl der Individuen bestimmt werden, welche in jeder Iteration in einen Konkurrenzkampf gesetzt werden
   *
   * @param s Anzahl der in Konkurrenz stehenden Individuen
   */
  public ChooseTournament(int s){
    this.random = new Random();
    this.s = s;
  }

  /**
   * Methode, welche mit dem oben beschriebenen Verfahren einen MatingPool in Form einer Liste erzeugt.
   *
   * @param population  Die Population von Individuen, aus welche Individuen in den Mating Pool selektiert werden sollen
   * @param size  Die gewünschte Größe des Mating Pools
   * @return  Den Mating Pool
   */
  @Override
  public List<Solution> createMatingPool(List<Solution> population, int size) {
    ArrayList<Solution> matingPool = new ArrayList<>(size);
    //Führe so viele Iterationen aus wie Individuen in den Mating Pool hinzugefügt werden sollen
    for(int i = 0; i < size; i++){
      Solution[] candidates = new Solution[s];
      //Wähle s Individuen aus der Population zufällig
      for(int index = 0; index < s; index++){
        candidates[index] = population.get(random.nextInt(population.size()));
      }
      //Bestimme das Individuum mit der besten Fitness
      Optional<Solution> o = Arrays.stream(candidates).max(Comparator.comparingInt(Solution::getProfit));
      assert o.isPresent();
      //Und füge dieses dem Mating Pool hinzu
      matingPool.add(o.get());
    }
    return matingPool;
  }
}
