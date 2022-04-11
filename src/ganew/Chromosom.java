package ganew;

import java.util.Collections;
import java.util.List;
import knapsack.KConstraintMultipleKnapsack;

public class Chromosom {
 private final KConstraintMultipleKnapsack knapsack;
 private final List<Integer> solution;
 private int fitness;

 public Chromosom(List<Integer> solution, KConstraintMultipleKnapsack knapsack){
  this.solution = solution;
  this.knapsack = knapsack;
  this.fitness = updateFitness();
 }

 public List<Integer> getSolution(){
  return this.solution;
 }

 public void swap(int index1, int index2) {
  Collections.swap(solution, index1, index2);
  this.fitness = updateFitness();
 }

 private int updateFitness(){
  this.fitness = 0;
  int curKnapsack = 0;
  int[] curWeight = new int[knapsack.getK()];
  for(int i: solution){
   boolean fits = false;
   while(!fits){
    fits = true;
    for(int k = 0; k < knapsack.getK(); k++){
     if(curWeight[k] + knapsack.getItem(i).getWeight(k) > knapsack.getKnapsack(curKnapsack).getCapacities(k)){
      fits = false;
      curKnapsack++;
      if(curKnapsack >= knapsack.getNrKnapsacks()){
       return fitness;
      }
      curWeight = new int[knapsack.getK()];
     }
    }
   }
   for (int k = 0; k < knapsack.getK(); k++){
    curWeight[k]+= knapsack.getItem(i).getWeight(k);
   }
   fitness += knapsack.getItem(i).getProfit();
  }
  return fitness;
 }

 public int getFitness(){
  return fitness;
  }

}
