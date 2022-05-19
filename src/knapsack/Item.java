package knapsack;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;

/**
 * Klasse die ein Objekt des K-Constraint Multiple Knapsack Problems repräsentiert
 */
public class Item implements Serializable {

  @Serial
  private static final long serialVersionUID = -1974982429872027723L;
  int itemID;
  int profit;
  int[] weights;

  /**
   * Konstruktor für das Objekt
   *
   * @param itemID Eindeutige ObjektID
   * @param profit Profit des Objektes
   * @param weights Gewichte des Objektes
   */
  public Item(int itemID, int profit, int[] weights) {
    this.itemID = itemID;
    this.profit = profit;
    this.weights = weights;
  }

  /**
   * Gibt den Profit des Objektes aus
   *
   * @return Profit
   */
  public int getProfit() {
    return this.profit;
  }

  /**
   * Gibt die Gewichtsdimension zurück
   *
   * @return  Gewichtsdimension des Objektes
   */
  public int getK() {
    return this.weights.length;
  }

  /**
   * Gibt die K Gewichte des Objektes als Array zurück
   *
   * @return  Gewichte des Objektes
   */
  public int[] getWeights() {
    return weights;
  }

  /**
   * Gibt das i-te Gewicht der K Gewichte des Objektes zurück, erzeugt einen AssertionError wenn i >= k gilt
   *
   * @param i Index des Gewichtes
   * @return  i-tes Gewicht
   */
  public int getWeight(int i) {
    assert i < weights.length;
    return weights[i];
  }

  /**
   * Gibt die ObjektID zur bestimmung des Objektes zurück
   *
   * @return  ObjektID
   */
  public int getItemID() {
    return this.itemID;
  }

  @Override
  public String toString() {
    final StringBuilder str = new StringBuilder("ItemID: ").append(itemID).append("(");
    str.append("Profit: ");
    str.append(profit);
    for (int i = 0; i < weights.length; i++) {
      str.append(", weigth ").append(i).append(": ").append(weights[i]);
    }
    return str.append(")").toString();
  }

  @Override
  public boolean equals(Object obj){
    if(!(obj instanceof Item)) return false;
    return this.itemID==((Item)obj).itemID&& Arrays.equals(this.weights, ((Item) obj).weights) &&this.profit==((Item)obj).profit;
  }
}
