package library;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import knapsack.KConstraintMultipleKnapsack;

public class KnapsackLibrary implements Serializable {

  @Serial
  private static final long serialVersionUID = -5962625380072433817L;
  List<KConstraintMultipleKnapsack> knapsacks;

  public KnapsackLibrary(){
    this.knapsacks=new ArrayList<>();
  }
  public KnapsackLibrary(List<KConstraintMultipleKnapsack> knapsacks){
    this.knapsacks=knapsacks;
  }
  public void add(KConstraintMultipleKnapsack knapsack){
    this.knapsacks.add(knapsack);
  }
  public void addAll(Collection<KConstraintMultipleKnapsack> knapsackList){
    this.knapsacks.addAll(knapsackList);
  }

  public List<KConstraintMultipleKnapsack> getKnapsacks(){
    return this.knapsacks;
  }

}
