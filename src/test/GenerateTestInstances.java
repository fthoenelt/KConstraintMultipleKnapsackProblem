package test;

import java.util.ArrayList;
import java.util.Random;
import knapsack.Item;
import knapsack.KConstraintMultipleKnapsack;
import knapsack.Knapsack;

public class GenerateTestInstances {
  public static KConstraintMultipleKnapsack generateTestInstance(int r, int k, int n, int m, double alpha){
    Random rand = new Random();
    ArrayList<Item> items = new ArrayList<>(n);
    int[] totalWeights = new int[k];
    for(int i=0; i < n; i ++){
      int[] weights = new int[k];
      for(int w = 0; w < k; w++){
        weights[w] = rand.nextInt(r)+1;
        totalWeights[w] += weights[w];
      }
      items.add(new Item(i, rand.nextInt(r)+1,weights));
    }
    ArrayList<Knapsack> knapsacks = new ArrayList<>(m);
    int[] totalCapacities = new int[k];
    for(int i = 0; i < m-1; i++){
      int[] capacity = new int[k];
      for( int j = 0; j < k; j++){
        int lowerBound = (int) ((alpha-0.1)*totalWeights[j]/m);
        int upperBound = (int) ((alpha+0.1)*totalWeights[j]/m);
        capacity[j] = lowerBound + rand.nextInt(upperBound-lowerBound);
        totalCapacities[j] += capacity[j];
      }
      knapsacks.add(new Knapsack(i, capacity));
    }
    int[] capacity = new int[k];

    for(int j = 0; j < k; j++){
      capacity[j] = (int) Math.round( alpha * (totalWeights[j] - totalCapacities[j]));
    }
    knapsacks.add(new Knapsack(m-1, capacity));
    return new KConstraintMultipleKnapsack(items, knapsacks, k);
  }
}
