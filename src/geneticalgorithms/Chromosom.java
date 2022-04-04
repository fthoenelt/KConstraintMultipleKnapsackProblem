package geneticalgorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import knapsack.Item;
import knapsack.KConstraintMultipleKnapsack;
import knapsack.Knapsack;

public class Chromosom {
  int[] chromosom;
  KConstraintMultipleKnapsack knapsack;
  private boolean isFeasible;

  public Chromosom(KConstraintMultipleKnapsack knapsack){
    this.chromosom = new int[knapsack.getNrItems()];
    this.knapsack = knapsack;
    this.isFeasible = true;
  }

  public int getFitness(){
    int fitness = 0;
    for(int i = 0; i < chromosom.length; i++){
      if(chromosom[i]>0){
        fitness += knapsack.getItem(i).getProfit();
      }
    }
    return fitness;
  }

  public int[] getChromosom(){
    return this.chromosom;
  }

  public int getNrItems(){
    return this.chromosom.length;
  }

  public int getValue(int index){
    return this.chromosom[index];
  }

  public boolean isFeasible(){
    for(int i = 0; i < knapsack.getNrKnapsacks(); i++){
      int[] sum = new int[knapsack.getK()];
      for(int j = 0; j < knapsack.getNrItems(); j++){
        if(chromosom[j]==i+1){
          sum = add(sum, knapsack.getItem(j).getWeights());
        }
      }
      if(!isFeasible(sum, knapsack.getKnapsack(i).getCapacities())) return false;
    }
    return true;
  }

  private int[] add(int[] a, int[] b){
    assert a.length== b.length;
    int[] c = new int[a.length];
    for(int i = 0; i < a.length; i++){
      c[i] = a[i] + b[i];
    }
    return c;
  }

  private boolean isFeasible(int[] itemSum, int[] knapsackCap){
    assert itemSum.length == knapsackCap.length;
    for (int i = 0; i< itemSum.length; i++){
      if(itemSum[i]>knapsackCap[i])return false;
    }
    return true;
  }

  public void set(int index, int value){
    this.chromosom[index] = value;
  }

  public KConstraintMultipleKnapsack getKnapsack(){
    return this.knapsack;
  }


}
