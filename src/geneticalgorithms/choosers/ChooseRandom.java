package geneticalgorithms.choosers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import knapsack.Solution;

/**
 * Klasse die das Chooser-Interface implementiert. Hierbei wird ein zufälliger Mating Pool erstellt, also ein Pool welcher mit zufällig gewählten Individuen der Population
 * gefüllt wird
 */

public class ChooseRandom implements Chooser {

  Random rand;

  public ChooseRandom(){
    this.rand = new Random();
  }

  /**
   * Methode die einen Mating Pool inform einer Liste erzeugt und diesen mit zufällig gewählten Individuen einer gegeben Population befüllt
   *
   * @param population  Die Population aus welcher die Individuen ausgewählt werden
   * @param size  Die gewünschte Größe des Mating Pools
   * @return  Eine Liste von Individuen
   */
  @Override
  public List<Solution> createMatingPool(List<Solution> population, int size) {
    ArrayList<Solution> matingPool = new ArrayList<>(size);
    for(int i = 0; i < size; i++){
      matingPool.add(population.get(rand.nextInt(population.size())));
    }
    return matingPool;
  }
}
