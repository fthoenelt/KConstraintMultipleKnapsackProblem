package ganew;

import java.util.Collections;
import java.util.List;
import knapsack.Item;
import knapsack.KConstraintMultipleKnapsack;
import knapsack.Knapsack;

public class Chromosom {
 private KConstraintMultipleKnapsack knapsack;
 private List<Integer> solution;

 public Chromosom(List<Integer> solution, KConstraintMultipleKnapsack knapsack){
  this.solution = solution;
  this.knapsack = knapsack;
 }

 public List<Integer> getSolution(){
  return this.solution;
 }

 public void swap(int index1, int index2) {
  Collections.swap(solution, index1, index2);
 }

 public int getFitness(){
  int fitness=0;
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

}
