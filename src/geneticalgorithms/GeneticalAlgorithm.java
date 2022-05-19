package geneticalgorithms;

import geneticalgorithms.choosechildren.ChooseChildren;
import geneticalgorithms.choosechildren.FittestChild;
import geneticalgorithms.choosers.ChooseFitness;
import geneticalgorithms.choosers.Chooser;
import geneticalgorithms.crossover.Crossover;
import geneticalgorithms.crossover.OnePointCrossover;
import geneticalgorithms.generation.CutGeneration;
import geneticalgorithms.generation.GenerationGenerator;
import geneticalgorithms.mutators.BitflipMutator;
import geneticalgorithms.mutators.Mutator;
import geneticalgorithms.startpopulation.RandomSortedPopulation;
import geneticalgorithms.startpopulation.StartPopulationGenerator;
import geneticalgorithms.stopcriterias.ImprovementStopper;
import geneticalgorithms.stopcriterias.StopCriteria;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import knapsack.KConstraintMultipleKnapsack;
import knapsack.Solution;

/**
 * Genetischer Algorithmus für das K-MKP mit einer HashMap-Codierung welche auch in dem VLSN-Algorithhmus nach Ahuja und Cunha verwendet wird
 */
public class GeneticalAlgorithm {
  KConstraintMultipleKnapsack knapsack;
  Crossover crossover;
  Mutator mutator;
  StartPopulationGenerator generator;
  int popSize;
  StopCriteria crit;
  Chooser chooser;
  int maxSize;
  GenerationGenerator generationGenerator;
  boolean addBoth;
  double crossoverProb;
  double mutationProb;
  ChooseChildren chooseChildren;
  Random rand = new Random();
  int poolSize;

  /**
   * Konstruktor für den GA in welchem Parameter für die Konfiguration übergeben werden
   *
   * @param knapsack  Die zu lösende K-MKP Instanz
   * @param popSize Die Größe der Population des GAs
   * @param iterations  Die Anzahl an Iterationen
   * @param maxSize Die maximale Größe der Population
   */
  public GeneticalAlgorithm(KConstraintMultipleKnapsack knapsack, int popSize, int iterations, int maxSize){
    this.knapsack = knapsack;
    this.crossover = new OnePointCrossover(true, true);
    this.mutator = new BitflipMutator(true);
    this.generator = new RandomSortedPopulation(knapsack);
    this.popSize = popSize;
    this.crit = new ImprovementStopper(iterations);
    this.chooser = new ChooseFitness();
    this.maxSize = maxSize;
    this.generationGenerator = new CutGeneration();
    this.addBoth = false;
    this.crossoverProb = 0.9;
    this.mutationProb = 0.9;
    this.chooseChildren = new FittestChild();
    this.poolSize = popSize;
  }

  /**
   * Konstruktor für den GA
   *
   * @param knapsack  DIe zu lösende Instanz
   * @param c Der Crossover-Operator welcher von dem GA verwendet werden soll
   * @param m Der Mutator welcher verwendet wird
   * @param generator Startpopulationsgenerator
   * @param popSize Größe der Population
   * @param crit  Abbruchkriterium
   * @param chooser Der Auswahl-Operator
   * @param maxSize Maximale Größe der Population
   * @param generationGenerator Ersetzungsstrategie
   * @param addBoth Sollen beide Nachfahren oder nur einer der neuen Generation hinzugefügt werden
   * @param crossoverProb Wahrscheinlichkeit mit welcher ein Crossover stattfindet
   * @param mutationProb  Wahrscheinlichkeit mit welcher eine Mutation stattfindet
   * @param chooseChildren  Selektionsoperator
   * @param poolSize  Die Größe des Mating Pools
   */
  public GeneticalAlgorithm(KConstraintMultipleKnapsack knapsack, Crossover c, Mutator m, StartPopulationGenerator generator, int popSize, StopCriteria crit, Chooser chooser,
      int maxSize, GenerationGenerator generationGenerator, boolean addBoth, double crossoverProb, double mutationProb, ChooseChildren chooseChildren, int poolSize){
    assert c.getFeasibility()==m.getFeasibility();
    c.getFeasibility();
    this.knapsack = knapsack;
    this.crossover = c;
    this.mutator = m;
    this.generator = generator;
    this.popSize = popSize;
    this.crit = crit;
    this.chooser = chooser;
    this.maxSize = maxSize;
    this.generationGenerator = generationGenerator;
    this.addBoth = addBoth;
    this.crossoverProb = crossoverProb;
    this.mutationProb=mutationProb;
    this.chooseChildren = chooseChildren;
    this.poolSize = poolSize;
  }

  /**
   * Methode welche eine möglichst optimale Lösung für die gegebene K-MKP Instanz mit einem Genetischen Algorithmus berechnet
   *
   * @return  Eine Lösung mit einem möglichst guten Zielfunktionswert
   */
  public Solution solve(){
    //Initialisiere die Population
    List<Solution> oldGeneration = generator.generatePopulation(this.popSize);
    //Finde die beste Lösung aus der Startpopulation
    Optional<Solution> optBest = oldGeneration.stream().max(Comparator.comparingInt(Solution::getProfit));
    assert optBest.isPresent();
    Solution best = optBest.get();
    int bestFit = best.getProfit();

    boolean action = false;
    //Solange das Abbruchkriterium nicht erreicht ist
    while(!crit.stop(action)){
      //Erzeuge eine neue, leere Population
      List<Solution> newGeneration = new ArrayList<>(popSize);
      //Ermittel den Mating Pool für die alte Generation
      List<Solution> matingPool = chooser.createMatingPool(oldGeneration, poolSize);
      //Fülle die neue Generation mit Individuen
      while(newGeneration.size() <= popSize) {
        //Wähle zwei Elternchromosome aus dem Mating Pool
        Solution p1 = matingPool.get(rand.nextInt(poolSize));
        Solution p2 = matingPool.get(rand.nextInt(poolSize));
        //Erhalte das Kinderchromomsom indem mit der Crossover-Wahrscheinlichkeit Rekombination stattfindet
        Solution child;
        if(rand.nextDouble() < crossoverProb){
          child = crossover.crossover(p1, p2);
        }else{
          //Sonst ist das Kind eine zufällige Kopie eines der Elternchromosome
          child = (rand.nextBoolean())?p1:p2;
        }
        //Mutiere das Chromosom mit der Mutationswahrscheinlichkeit
        if(rand.nextDouble() < mutationProb) child = mutator.mutate(child);
        //Wenn das so erzeugte Kind eine bessere Fitness als die bisher beste Lösung hat speicher es
        if(child.getProfit() > bestFit && child.isFeasible()){
          best = child;
          bestFit = child.getProfit();
        }
        //Und füge es der neuen Generation hinzu
        newGeneration.add(child);
      }
      //Update die Population mit der neuen und der alten Generation
      oldGeneration = generationGenerator.generate(oldGeneration, newGeneration, this.maxSize);
    }

    assert best.isFeasible();
    return best;
  }

}

