package ganew.selection;

import ganew.Chromosom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Klasse die das Selector-Interface implementiert für den PermGA. Dabei wird die fitnessproportionale Selektion in Form der Rouletterad-Selektion nach J. Holland verwendet. Bei
 * dieser Selektionsstrategie wird jedem Individuum einer Population ein Platz auf einem Rouletterad zugewiesen. Die Größe dieses Platzes ist dabei proportional zu der Fitness des
 * Individuums, fittere Individuen haben also größere Felder auf dem Rad. Danach werden zufällig Felder gezogen und die jeweiligen Individuen dem Mating Pool hinzugefügt.
 */

public class FitnessProportional implements Selector{
  Random random;

  public FitnessProportional(){
    this.random = new Random();
  }

  /**
   * Methode welche die oben beschriebene Rouletterad-Selektion durchführt und einen Mating Pool in Form einer Liste zurückgibt
   *
   * @param population  Die Population von Individuen, aus welche Individuen in den Mating Pool selektiert werden sollen
   * @param size  Die gewünschte Größe des Mating Pools
   * @return  MatingPool von Individuen
   */
  @Override
  public List<Chromosom> createMatingPool(List<Chromosom> population, int size) {
    int[] fitnesses = new int[population.size()];
    int fitSum = 0;
    for(int i = 0; i < population.size(); i++){
      //Berechne die Fitness jedes Individuums sowie die Summe der Fitness' aller Individuen
      fitnesses[i] = population.get(i).getFitness();
      fitSum += fitnesses[i];
    }
    //Die Liste ratios repräsentiert das Rouletterad. Dafür wird in jedem Schritt die relative Fitness des Individuums als Quotient aus der eigenen Fitness und der Summe aller
    // Fitness' berechnet und diese dann iterativ aufsummiert wodurch kumulierte Wahrscheinlichkeiten berechnet werden.
    ArrayList<Double> ratios = new ArrayList<>(population.size());
    for(int i = 0; i < population.size(); i++){
      if(i==0){
        ratios.add((double) fitnesses[i]/(double) fitSum);
      }else{
        ratios.add(ratios.get(i-1)+ ((double) fitnesses[i]/(double) fitSum));
      }
    }
    //Zuletzt wird der Mating Pool erstellt und befüllt
    ArrayList<Chromosom> matingPool = new ArrayList<>(size);
    for(int i = 0; i < size; i++){
      //Dazu werden zufällige Felder auf dem Rouletterad gewählt und die entsprechenden Individuen dem Pool hinzugefügt
      matingPool.add(population.get((-Collections.binarySearch(ratios, random.nextDouble()) - 1)));
    }
    return matingPool;
  }
}
