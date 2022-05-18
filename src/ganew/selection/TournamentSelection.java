package ganew.selection;

import ganew.Chromosom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * Klasse welche das Selector-Interface für den PermGA für das K-MKP implementiert. Hierbei wird die ordinale Selektion, bzw. die Tournament Selektion verwendet. Bei diesem
 * Selektionsverfahren werden eine bestimmte Anzahl an Individuen aus der Population gezogen und diese in einen simulierten Konkurrenzkampf gesetzt. Das fitteste dieser
 * Individuen wird dann dem Mating Pool hinzugefügt.
 */

public class TournamentSelection implements Selector{
  Random random;
  int s;

  /**
   * Konstruktor für die Tournament Selektion. Hier kann die Anzahl der Individuen bestimmt werden, welche in jeder Iteration in einen Konkurrenzkampf gesetzt werden
   *
   * @param s Anzahl der in Konkurrenz stehenden Individuen
   */
  public TournamentSelection(int s){
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
  public List<Chromosom> createMatingPool(List<Chromosom> population, int size) {
    ArrayList<Chromosom> matingPool = new ArrayList<>(size);
    //Führe so viele Iterationen aus wie Individuen in den Mating Pool hinzugefügt werden sollen
    for(int i = 0; i < size; i++){
      //Wähle in jeder Iteration s Individuen zufällig azs der Population
      Chromosom[] candidates = new Chromosom[s];
      for(int index = 0; index < s; index++){
        candidates[index] = population.get(random.nextInt(population.size()));
      }
      //Finde das Individuum mit der besten Fitness
      Optional<Chromosom> o = Arrays.stream(candidates).max(Comparator.comparingInt(Chromosom::getFitness));
      assert o.isPresent();
      //Und füge dieses dem MatingPool hinzu
      matingPool.add(o.get());
    }
    return matingPool;
  }
}
