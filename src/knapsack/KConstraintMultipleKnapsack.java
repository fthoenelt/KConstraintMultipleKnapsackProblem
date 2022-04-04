package knapsack;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class KConstraintMultipleKnapsack implements Serializable {

  @Serial
  private static final long serialVersionUID = -8427621746814709189L;
  /**
   * Individual ID for the Knapsack
   */
  int knapsackID;
  /**
   * List of Items
   */
  List<Item> items;
  /**
   * List of Knapsacks
   */
  List<Knapsack> knapsacks;

  int k;

  public KConstraintMultipleKnapsack(){
    this.knapsackID = 0;
    this.items = new ArrayList<>();
    this.knapsacks = new ArrayList<>();
  }

  public KConstraintMultipleKnapsack(int n, int m){
    this.knapsackID = 0;
    this.items = new ArrayList<>(n);
    this.knapsacks = new ArrayList<>(m);
  }

  public KConstraintMultipleKnapsack(List<Item> items, List<Knapsack> knapsacks, int k){
    this.knapsackID = 0;
    this.items = items;
    this.knapsacks = knapsacks;
    this.k = k;
  }

  public int getKnapsackID() {
    return knapsackID;
  }

  public List<Knapsack> getKnapsacks() {
    return knapsacks;
  }

  public List<Item> getItems() {
    return items;
  }

  public int getNrItems(){return this.items.size();}

  public Knapsack getKnapsack(int index){
    return this.knapsacks.get(index);
  }

  public Item getItem(int index){
    return this.items.get(index);
  }

  //Vielleicht sogar weg
  public int getK(){
    return this.k;
  }

  public int getNrKnapsacks(){
    return this.knapsacks.size();
  }

  @Override
  public boolean equals(Object obj){
    return obj instanceof KConstraintMultipleKnapsack && ((KConstraintMultipleKnapsack) obj).getKnapsackID() == this.knapsackID && ((KConstraintMultipleKnapsack)obj).toString().equals(this.toString());

  }

  @Override
  public String toString(){
    StringBuilder str = new StringBuilder("K-Constraint Multiple Knapsack(");
    str.append("\nItems: ");
    for(int i = 0; i < items.size(); i++){
      str.append("\n    Item Nr").append(i).append(": ").append(items.get(i).toString());
    }
    str.append("\nKnapsacks: ");
    for(int i = 0; i < knapsacks.size(); i++){
      str.append("\n    Knapsack Nr").append(i).append(": ").append(knapsacks.get(i).toString());
    }
    return str.append(")").toString();
  }
}
