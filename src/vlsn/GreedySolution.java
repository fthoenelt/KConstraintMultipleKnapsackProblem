package vlsn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import knapsack.Item;
import knapsack.KConstraintMultipleKnapsack;
import knapsack.Knapsack;
import knapsack.Solution;

public class GreedySolution {
  public static Solution getGreedy(KConstraintMultipleKnapsack knapsack){
    return fillGreedy(knapsack, new Solution(knapsack), new ArrayList<>(knapsack.getItems()));
  }

  public static Solution fillGreedy(KConstraintMultipleKnapsack knapsack, Solution solution, List<Item> items){
    items = new RatioSort(items, knapsack.getK()).sort();
    List<KnapsackRatio> knapsackList =new ArrayList<>(knapsack.getNrKnapsacks());
    int[] capacities = new int[knapsack.getK()];
    for(Knapsack k: knapsack.getKnapsacks()){
      for(int i = 0; i < k.getK(); i++){
        capacities[i] += k.getCapacities(i)-solution.getWeight(k, i);
      }
    }

    for(Knapsack k: knapsack.getKnapsacks()){
      double capacity = 0.0;
      for(int i = 0; i < k.getK(); i++){
        capacity += (double) (k.getCapacities(i)-solution.getWeight(k, i))/capacities[i];
      }
      knapsackList.add(new KnapsackRatio(k, capacity));
    }

    knapsackList.sort((o1, o2) -> Double.compare(o2.ratio, o1.ratio));

    for(KnapsackRatio k:knapsackList){
      for (Item i : items) {
        if (solution.canBeAdded(i, k.knapsack)&&!solution.isUsed(i)) {
          solution.addItem(i, k.knapsack);
          //it.remove();
        }
      }
    }

    return solution;
  }

  public static FractionalSolution getFractional(KConstraintMultipleKnapsack knapsack){
    List<Item> items = new RatioSort(knapsack.getItems(), knapsack.getK()).sort();
    List<KnapsackRatio> knapsackList =new ArrayList<>(knapsack.getNrKnapsacks());
    int[] capacities = new int[knapsack.getK()];
    for(Knapsack k: knapsack.getKnapsacks()){
      for(int i = 0; i < k.getK(); i++){
        capacities[i] += k.getCapacities(i);
      }
    }

    for(Knapsack k: knapsack.getKnapsacks()){
      double capacity = 0.0;
      for(int i = 0; i < k.getK(); i++){
        capacity += (double) k.getCapacities(i)/capacities[i];
      }
      knapsackList.add(new KnapsackRatio(k, capacity));
    }

    knapsackList.sort((o1, o2) -> Double.compare(o2.ratio, o1.ratio));

    FractionalSolution sol = new FractionalSolution(knapsack);

    double[] remainingItems = new double[knapsack.getNrItems()];
    for(int i = 0; i < knapsack.getNrItems(); i++){
      remainingItems[i] =1.0;
    }
    for(KnapsackRatio k:knapsackList){
      for (Item i : items) {
        if(remainingItems[i.getItemID()] != 0.0) {
          OptionalDouble fac = sol.canBeAdded(i, k.knapsack, remainingItems[i.getItemID()]);
          if (fac.isPresent()) {
            sol.setItemsInKnapsacks(i, k.knapsack, fac.getAsDouble());
            remainingItems[i.getItemID()] -= fac.getAsDouble();
          }
        }
      }
    }
    return sol;
  }

  public static class FractionalSolution{
    double profit = 0.0;
    double[][] itemsInKnapsacks;
    double[][] remainingCapacities;
    private FractionalSolution(KConstraintMultipleKnapsack knapsack){
      this.itemsInKnapsacks = new double[knapsack.getNrKnapsacks()][knapsack.getNrItems()];
      this.remainingCapacities = new double[knapsack.getNrKnapsacks()][knapsack.getK()];
      for(Knapsack k: knapsack.getKnapsacks()){
        for(int i = 0; i < knapsack.getK(); i++){
          this.remainingCapacities[k.getKnapsackID()][i] = k.getCapacities(i);
        }
      }
    }

    private void setItemsInKnapsacks(Item item, Knapsack knapsack, double quantity){
      this.itemsInKnapsacks[knapsack.getKnapsackID()][item.getItemID()]=quantity;
      for(int i = 0; i < knapsack.getK(); i++){
        this.remainingCapacities[knapsack.getKnapsackID()][i] -= quantity* item.getWeight(i);
      }
      profit += quantity*item.getProfit();
    }

    private OptionalDouble canBeAdded(Item item, Knapsack knapsack, double maxPart){
      double[] factors = new double[knapsack.getK()];
      for(int i = 0; i < knapsack.getK();i++){
        if(remainingCapacities[knapsack.getKnapsackID()][i]==0.0){
          return OptionalDouble.empty();
        }else{
          factors[i] = Math.min(this.remainingCapacities[knapsack.getKnapsackID()][i] / item.getWeight(i), maxPart);
        }
      }
      return Arrays.stream(factors).min();

    }

    public void print(){
      for(int i = 0; i < itemsInKnapsacks.length; i++){
        System.out.println("Knapsack Nr. "+i);
        for(int j = 0; j < itemsInKnapsacks[0].length; j++){
          System.out.println("Item Nr. "+j+ ", Quantitiy = " + itemsInKnapsacks[i][j]);
        }
        System.out.println("------");
      }
      System.out.println("Profit =" + profit);

    }
  }



  private static class KnapsackRatio{
    Knapsack knapsack;
    double ratio;

    private KnapsackRatio(Knapsack knapsack, double ratio){
      this.knapsack = knapsack;
      this.ratio = ratio;
    }
  }
}
