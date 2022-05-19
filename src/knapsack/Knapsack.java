package knapsack;

import java.io.Serial;
import java.io.Serializable;

/**
 * Klasse die einen Rucksack mit K Gewichtsbechränkungen des K-MKPs repräsentiert
 */

public class Knapsack implements Serializable {


  @Serial
  private static final long serialVersionUID = -2801702757545933019L;
  int knapsackID;
  int[] capacities;

  /**
   * Konstruktor für ein Rucksack-Objekt
   *
   * @param knapsackID  EIndeutige Rucksack ID
   * @param capacities  Array von K Kapazitäten des Rucksacks
   */
  public Knapsack(int knapsackID, int[] capacities){
    this.knapsackID = knapsackID;
    this.capacities = capacities;
  }

  /**
   * Gibt die Kapazitäten des Rucksackes zurück
   *
   * @return  Array an Kapazitäten
   */
  public int[] getCapacities() {
    return capacities;
  }

  /**
   * Gibt die Kapazität an der Stelle index zurück, wirft ein AssertionError wenn index >= K gilt
   *
   * @param index Index der gewünschten Kapazität
   * @return  Kapazitöt
   */
  public int getCapacities(int index) {
    assert index < capacities.length;
    return capacities[index];
  }

  /**
   * Gibt die Kapazitätsdimension zurück
   *
   * @return  Kapazitätsdimension
   */
  public int getK(){
    return capacities.length;
  }

  /**
   * Gibt die eindeutige RucksackID zurück
   *
   * @return ID des Rucksacks
   */
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
