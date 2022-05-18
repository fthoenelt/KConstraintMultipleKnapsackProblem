package geneticalgorithms.choosers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import knapsack.Solution;

/**
 * Klasse die das Chooser-Interface implementiert. Dabei wird die fitnessproportionale Selektion in Form der Rouletterad-Selektion nach J. Holland verwendet. Bei dieser
 * Selektionsstrategie wird jedem Individuum einer Population ein Platz auf einem Rouletterad zugewiesen. Die Größe dieses Platzes ist dabei proportional zu der FItness des
 * Individuums, fittere Individuen haben also größere Felder auf dem Rad. Danach werden zufällig Felder gezogen und die jeweiligen Individuen dem Mating Pool hinzugefügt.
 */
public class ChooseFitness implements Chooser {

  Random random;

  public ChooseFitness(){
    this.random = new Random();
  }

  /**
   * Methode, welche die oben beschriebene Rouletterad-Selektion ausführt und somit einen Mating-Pool der gewünschten Größe erzeugt
   *
   * @param population  Die Population aus welcher die Individuen ausgewählt werden
   * @param size  Die gewünschte Größe des Mating Pools
   * @return  Eine Liste von Individuen welche den Mating Pool bilden
   */
  @Override
  public List<Solution> createMatingPool(List<Solution> population, int size) {
    double[] fitnessPerChrom = new double[population.size()];
    double sumFit = 0.0;

    //Berechne für jedes Individuum der Population die Fitness und summiere die Fitness aller Individuen auf
    for(int i = 0; i < population.size(); i++){
      fitnessPerChrom[i] = population.get(i).getProfit();
      sumFit+=fitnessPerChrom[i];
    }

    assert sumFit !=0.0;
    ArrayList<Double> ratios= new ArrayList<>(population.size());
    //Berechne die kumulierten Wahrscheinlichkeiten für die Individuen indem iterativ der Quotient aus der eigenen Fitness und der gesamten Fitness aufsummiert werden
    for(int i = 0; i < population.size(); i++){
      if(i==0){
        ratios.add(fitnessPerChrom[i] /sumFit);
      }else{
        ratios.add(ratios.get(i-1)+(fitnessPerChrom[i] /sumFit));
      }
    }
    //Erzeuge den Mating Pool mit dem simulierten Rouletterad und fülle diesen
    ArrayList<Solution> matingPool = new ArrayList<>(size);
    for(int i = 0; i < size; i++){
      matingPool.add(population.get((-Collections.binarySearch(ratios, random.nextDouble()) - 1)));
    }
    return matingPool;
  }
}
