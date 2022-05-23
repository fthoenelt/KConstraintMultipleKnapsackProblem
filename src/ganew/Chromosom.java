package ganew;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import knapsack.KConstraintMultipleKnapsack;
import knapsack.Solution;

/**
 * Klasse die ein Chromosom für den PermGA für das K-MKP implementiert. Hierfür wird eine Permutationsrepräsentation verwendet, bei welcher die Lösung für das Problem als
 * Permutation der Indizes der Items genutzt wird. Die Zuordnung geschieht dann über eine Heuristik, welche der Greedy-Heuristik aus dem VLSN-Algorithmus von Ahuja und Cunha
 * ähnelt. Die Items werden in der Reihenfolge wie sie in der Permutation vorkommen den Rucksäcken des K-MKPs zugeordnet. Dabei werden dem ersten Rucksack nacheinander die Items
 * zugeordnet, bis das nächste Item eine Kapazitätsbeschränkung des Rucksacks verletzen würde. Dieses wird dann dem nächsten Rucksack hinzugefügt. Dieses Verfahren wird
 * wiederholt, bis alle Rucksäcke befüllt sind. Die Fitness eines Chromosoms entspricht dabei dem Zielfunktionswert der dadurch abgeleiteten Lösung.
 */

public class Chromosom {
 //Die K-MKP Instanz für welche das Chromosom genutzt wird
 private final KConstraintMultipleKnapsack knapsack;
 //Die Permutation der Indizes der Items
 private List<Integer> solution;
 //Die Fitness des Chromosoms, diese wird in einer Variable gespeichert damit sie nicht jedes mal geupdatet werden muss
 private int fitness;

 /**
  * Konstruktor des Chromosoms, bei welchem eine Permutation und die K-MKP-Instanz übergeben werden. Bei dem erzeugen wird des weiteren automatisch die FItness des
  * Chromosoms
  * berechnet
  *
  * @param solution Die Liste welche die Permutation darstellt
  * @param knapsack Die K-MKP-Instanz
  */
 public Chromosom(List<Integer> solution, KConstraintMultipleKnapsack knapsack){
  this.solution = solution;
  this.knapsack = knapsack;
  this.fitness = updateFitness();
 }

 /**
  * Copy-Konstruktor, bei welchem ein neues Chromosom als Kopie eines bestehenden erzeugt wird
  *
  * @param chromosom Das zu kopierende Chromosom
  */
 public Chromosom(Chromosom chromosom){
  this.solution = new ArrayList<>(chromosom.solution);
  this.knapsack = chromosom.knapsack;
  this.fitness = chromosom.fitness;
 }

 /**
  * Gibt die Permutation des Individuums zurück
  *
  * @return Liste mit den Indices der Items
  */
 public List<Integer> getSolution(){
  return this.solution;
 }

 /**
  * Vertauscht zwei Allele in dem Chromosom, wird insbesondere für die Mutation verwendet
  *
  * @param index1 Index des ersten Allels
  * @param index2 Index des zweiten Allels
  */
 public void swap(int index1, int index2) {
  //Tausche die beiden Allele
  Collections.swap(this.solution, index1, index2);
  //Und berechne die Fitness nach dem Tausch neu
  this.fitness = updateFitness();
 }

 public void shuffle(int index1, int index2){
  Collections.shuffle(this.solution.subList(index1, index2));
  this.fitness = updateFitness();
 }

 public void order(int index1, int index2){
  this.solution.sort((o1, o2) -> Integer.compare(knapsack.getItem(o2).getProfit(), knapsack.getItem(o1).getProfit()));
  this.fitness = updateFitness();
 }
 /**
  * Methode, welche die Fitness des Chromosoms berechnet. Hierfür wird die oben beschriebene Heuristik verwendet und aus der daraus entstehenden Lösung der Zielfunktionswert
  * berechnet
  *
  * @return Fitnesswert des Chromosoms als Integer
  */
 private int updateFitness(){
  //Fitness des Chromosoms
  this.fitness = 0;
  //Der Rucksack welcher momentan betrachtet wird
  int curKnapsack = 0;
  //Die K Gewichte des momentanen Rucksacks
  int[] curWeight = new int[knapsack.getK()];
  //Betrachte den Index jedes Items in der durch die Permutation vorgegebenen Reihenfolge
  for(int i: solution){
   boolean fits = false;
   //Sucht den nächsten Rucksack, in welchen das momentane Item passt, im Normalfall ist dies der aktuell betrachtete Rucksack oder der, welcher in der Reihenfolge danach kommt
   while(!fits){
    fits = true;
    //Prüfe für alle K Kapazitäten, ob das Hinzufügen des aktuellen Items diese überschreitet
    for(int k = 0; k < knapsack.getK(); k++){
     if(curWeight[k] + knapsack.getItem(i).getWeight(k) > knapsack.getKnapsack(curKnapsack).getCapacities(k)){
      //Sollte eine Kapazitätsbeschränkung überschritten werden wähle den nächsten Rucksack sollte dieser existieren
      fits = false;
      curKnapsack++;
      if(curKnapsack >= knapsack.getNrKnapsacks()){
       return fitness;
      }
      //Und setze die Gewichte des aktuellen Rucksacks auf null
      curWeight = new int[knapsack.getK()];
     }
    }
   }
   //Wenn das Objekt in den Rucksack passt so addiere die Gewichte des Objekts auf das Gewicht des aktuellen Rucksack
   for (int k = 0; k < knapsack.getK(); k++){
    curWeight[k]+= knapsack.getItem(i).getWeight(k);
   }
   //Und aktualisiere die Fitness indem der Profit des aktuellen Objektes addiert wird
   fitness += knapsack.getItem(i).getProfit();
  }
  return fitness;
 }

 /**
  * Gibt die Fitness des Chromosoms zurück
  *
  * @return Fitnesswert als Integer
  */
 public int getFitness(){
  return fitness;
  }

 /**
  * Konstruiert ein Solution-Objekt aus einem Chromosom mit der oben beschriebenen Heuristik
  *
  * @return Solution-Objekt
  */
 public Solution buildSolution(){
   Solution s = new Solution(this.knapsack);
   int curKnapsackIndex = 0;
   //Gehe alle Items der Permutation durch
   for(int i: solution){
    //Prüfe ob das momentane Items dem aktuell betrachteten Rucksack hinzugefügt werden kann
    while(!s.canBeAdded(knapsack.getItem(i), knapsack.getKnapsack(curKnapsackIndex))){
     //Wenn nicht betrachte den nächsten Rucksack sofern dieser existiert
     curKnapsackIndex++;
     if(curKnapsackIndex >= knapsack.getNrKnapsacks()){
      assert s.getProfit() == this.fitness;
      //Wenn alle Rucksäcke befüllt worden sind kann die Lösung zurückgegeben werden
      return s;
     }
    }
    //Sollte das Item noch in den aktuellen Rucksack passen so kann es diesem hinzugefügt werden
    s.addItem(knapsack.getItem(i), knapsack.getKnapsack(curKnapsackIndex));
   }
   assert s.getProfit() == this.fitness;
   return s;
  }
}
