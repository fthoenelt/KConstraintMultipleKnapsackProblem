package ganew.selection;

import ganew.Chromosom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Klasse die das Selector-Interface implementiert. Hierbei wird ein zufälliger Mating Pool erstellt, also ein Pool welcher mit zufällig gewählten Individuen der Population
 * gefüllt wird
 */

public class RandomPool implements Selector{
  Random random;
  public RandomPool(){
    this.random = new Random();
  }

  /**
   * Methode welche den Mating Pool in Form einer Liste erzeugt und diesen zufällig befüllt
   *
   * @param population  Die Population von Individuen, aus welche Individuen in den Mating Pool selektiert werden sollen
   * @param size  Die gewünschte Größe des Mating Pools
   * @return  Den Mating Pool
   */
  @Override
  public List<Chromosom> createMatingPool(List<Chromosom> population, int size) {
    ArrayList<Chromosom> matingPool = new ArrayList<>(size);
    for(int i = 0; i < size; i++){
      //Wähle so oft zufällig Individuen bis der Mating Pool die gewünschte Größe hat
      matingPool.add(population.get(random.nextInt(population.size())));
    }
    //Und gebe diesen dann aus
    return matingPool;
  }
}
