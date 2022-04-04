package vlsn;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import knapsack.Item;

public class RatioSort {
  HashMap<Item, Double> itemList;
  List<Item> items;
  public RatioSort(List<Item> items, int k){
    this.itemList = new HashMap<>(items.size());
    this.items=items;
    int[] weights = new int[k];
    for(Item item : items){
      for(int i = 0; i < item.getK(); i++){
        weights[i]+=item.getWeight(i);
      }
    }
    for(Item item:items){
      double weight = 0.0;
      for(int i = 0; i < item.getK(); i++){
        weight += (double) item.getWeight(i)/weights[i];
      }
      assert item.getProfit() >0;
      double ratio = (double) item.getProfit()/weight;
      itemList.put(item, ratio);
    }
  }

  public List<Item> sort(){
    this.items.sort(new Comparator<Item>() {
      @Override
      public int compare(Item o1, Item o2) {
        return Double.compare(itemList.get(o2), itemList.get(o1));
      }
    });
    return this.items;
  }



}
