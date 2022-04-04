package knapsack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import vlsn.ValidCycle;
import vlsn.graph.DummyVertex;
import vlsn.graph.ItemVertex;
import vlsn.graph.Vertex;

public class Solution {
  KConstraintMultipleKnapsack knapsack;
  Map<Item, Knapsack> itemToKnapsack;
  Map<Knapsack, KnapsackSolution> knapsackSolutions;
  int profit;

  public Solution(KConstraintMultipleKnapsack knapsack){
    this.knapsack=knapsack;
    this.itemToKnapsack = new HashMap<>();
    this.knapsackSolutions = new HashMap<>();
    for(Knapsack k : knapsack.getKnapsacks()){
      this.knapsackSolutions.put(k, new KnapsackSolution(k));
    }
    this.profit = 0;
  }

  public Solution(Solution sol){
    this.knapsack = sol.knapsack;
    this.itemToKnapsack = new HashMap<>(sol.itemToKnapsack);
    this.knapsackSolutions = new HashMap<>(sol.knapsackSolutions);
    this.profit = sol.profit;
  }

  public int getProfit(){
    return this.profit;
  }

  public int[] getWeight(Knapsack k){
    return this.knapsackSolutions.get(k).weights;
  }

  public int getWeight(Knapsack k, int i){
    return this.knapsackSolutions.get(k).weights[i];
  }

  public KConstraintMultipleKnapsack getKnapsack(){
    return this.knapsack;
  }

  public Knapsack getKnapsack(Item i){
    return this.itemToKnapsack.get(i);
  }

  public boolean isFeasible(){
    for(Knapsack k: this.knapsack.getKnapsacks()){
      for(int j = 0; j < this.knapsack.getK(); j++){
        if(this.knapsackSolutions.get(k).getWeight(j) > k.getCapacities(j)){
          return false;
        }
      }
    }
    return true;
  }

  public boolean canBeAdded(Item item, Knapsack knapsack){
    for(int i = 0; i < knapsack.getK(); i ++){
      if(knapsackSolutions.get(knapsack).weights[i]+ item.getWeight(i) > knapsack.getCapacities(i)){
        return false;
      }
    }
    return true;
  }

  public void addItem(Item item, Knapsack knapsack){
    assert !this.isUsed(item);
    this.itemToKnapsack.put(item, knapsack);
    this.profit += item.getProfit();
    this.knapsackSolutions.get(knapsack).addItem(item);
  }

  public void removeItem(Item item){
    if(!itemToKnapsack.containsKey(item)) return;
    this.knapsackSolutions.get(itemToKnapsack.get(item)).removeItem(item);
    this.profit-=item.getProfit();
    this.itemToKnapsack.remove(item);
  }

  public boolean couldBeReplaced(Item newIt, Item oldIt){
    return knapsackSolutions.get(itemToKnapsack.get(oldIt)).couldBeReplaced(newIt, oldIt);
  }

  public Set<Item> getUsedItems(){
    return this.itemToKnapsack.keySet();
  }

  public boolean isUsed(Item i){
    return this.itemToKnapsack.containsKey(i);
  }

  public List<Item> itemsOf(Knapsack knapsack){
    return this.knapsackSolutions.get(knapsack).items;
  }

  public Solution applyCycle(ValidCycle c){
    for(int i = c.getLabel().size()-1; i>= 0; i--){
      Vertex v = c.getLabel().get(i);
      if(!(v instanceof DummyVertex)) {
        if (v.getKnapsackID() != this.knapsack.getNrKnapsacks()) {
          this.removeItem(((ItemVertex) v).getItem());
        }
        if (i != c.getLabel().size() - 1 && c.getLabel().get(i + 1).getKnapsackID() != this.knapsack.getNrKnapsacks()) {
          this.addItem(((ItemVertex) v).getItem(), this.knapsack.getKnapsack(c.getLabel().get(i + 1).getKnapsackID()));
        }
      }
    }
    return this;
  }

  @Override
  public String toString(){
    StringBuilder str = new StringBuilder("Solution(");
    str.append("Profit: ").append(profit);
    str.append("\n, Knapsacks and Items(");
    for(Knapsack s : this.knapsackSolutions.keySet()){
      str.append("\n Knapsack: ").append(s.toString());
      str.append("\n Items: ");
      for(Item i : knapsackSolutions.get(s).items){
        str.append("\n ").append(i.toString());
      }
    }
    return str.append(")").toString();
  }

  private class KnapsackSolution{
    List<Item> items;
    int[] weights;
    int profit;
    Knapsack knapsack;

    private KnapsackSolution(Knapsack knapsack){
      this.knapsack = knapsack;
      this.weights = new int[knapsack.getK()];
      this.items = new ArrayList<>();
      this.profit = 0;
    }

    private int getProfit() {
      return profit;
    }

    private int[] getWeights(){
      return this.weights;
    }

    private List<Item> getItems(){
      return this.items;
    }

    private int getWeight(int index){
      assert index < weights.length;
      return this.weights[index];
    }

    private void addItem(Item item){
      this.items.add(item);
      this.profit += item.getProfit();
      for(int i = 0; i < weights.length; i++){
        this.weights[i] += item.getWeight(i);
      }
    }

    private void removeItem(Item item){
      this.items.remove(item);
      this.profit -= profit;
      for(int i = 0; i < weights.length; i++){
        this.weights[i] -= item.getWeight(i);
      }
    }

    private boolean couldBeReplaced(Item newIt, Item oldIt){
      for(int i = 0; i < this.weights.length; i++){
        if(this.weights[i] - oldIt.getWeight(i)+ newIt.getWeight(i)>this.knapsack.getCapacities(i)){
          return false;
        }
      }
      return true;
    }
  }

}
