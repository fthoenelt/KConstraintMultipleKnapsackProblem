package knapsack;

import java.io.Serial;
import java.io.Serializable;

public class Knapsack implements Serializable {


  @Serial
  private static final long serialVersionUID = -2801702757545933019L;
  int knapsackID;
  int[] capacities;

  public Knapsack(int knapsackID, int[] capacities){
    this.knapsackID = knapsackID;
    this.capacities = capacities;
  }

  public int[] getCapacities() {
    return capacities;
  }

  public int getCapacities(int index) {
    return capacities[index];
  }


  public int getK(){
    return capacities.length;
  }

  public int getKnapsackID() {
    return knapsackID;
  }

  @Override
  public boolean equals(Object obj){
    if(!(obj instanceof Knapsack) || ((Knapsack)obj).capacities.length!= this.capacities.length ||((Knapsack)obj).knapsackID!= this.knapsackID ) return false;
    for(int i = 0; i < capacities.length; i++){
      if(capacities[i]!= ((Knapsack)obj).capacities[i]) return false;
    }
    return true;
  }

  @Override
  public String toString(){
    StringBuilder str = new StringBuilder("Knapsack( ID: ").append(this.knapsackID);
    for(int i = 0; i < capacities.length; i++){
      str.append(", Capacity ").append(i).append(": ").append(capacities[i]);
    }
    return str.append(")").toString();
  }
}
