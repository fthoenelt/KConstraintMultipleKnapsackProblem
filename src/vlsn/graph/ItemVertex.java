package vlsn.graph;

import knapsack.Item;

/**
 * CLass representing an Vertex holding an Item
 */

public class ItemVertex extends Vertex{

  Item item;


  /**
   * Constructor
   *
   * @param item
   * @param knapsackID between 0 and m, m is the knapsack S0
   */
  public ItemVertex(Item item, int knapsackID) {
    super(item.getItemID(), knapsackID);
    this.item = item;
  }

  public int getKnapsackID() {
    return knapsackID;
  }

  public void setKnapsackID(int knapsackID) {
    super.knapsackID = knapsackID;
  }


  public int getVertexID() {
    return vertexID;
  }

  public Item getItem() {
    return item;
  }

  @Override
  public boolean equals(Object obj) {
    return (obj instanceof ItemVertex) && (this.vertexID == ((ItemVertex) obj).getVertexID()) && (this.getKnapsackID()==((ItemVertex) obj).knapsackID);
  }

}
