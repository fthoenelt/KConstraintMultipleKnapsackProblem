package knapsack;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;

/**
 * Class representing an item of the k-Constraint multiple knapsack problem
 */
public class Item implements Serializable {

  @Serial
  private static final long serialVersionUID = -1974982429872027723L;
  int itemID;
  int profit;
  int[] weights;

  /**
   * Constructor for the item
   *
   * @param itemID unique itemID
   * @param profit the profit of the item
   * @param weights Array holding the k different weight values
   */
  public Item(int itemID, int profit, int[] weights) {
    this.itemID = itemID;
    this.profit = profit;
    this.weights = weights;
  }

  public int getProfit() {
    return this.profit;
  }

  public int getK() {
    return this.weights.length;
  }

  public int[] getWeights() {
    return weights;
  }

  public int getWeight(int i) {
    assert i < weights.length;
    return weights[i];
  }

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
