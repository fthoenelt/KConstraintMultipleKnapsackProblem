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

/**
 * Klasse stellt eine Lösung einer K-MKP-Instanz dar
 */

public class Solution {
  KConstraintMultipleKnapsack knapsack;
  Map<Item, Knapsack> itemToKnapsack;
  Map<Knapsack, KnapsackSolution> knapsackSolutions;
  int profit;

  /**
   * Konstruktor einer leeren neuen Lösung einer K-MKP Instanz
   *
   * @param knapsack  K-MKP Instanz
   */
  public Solution(KConstraintMultipleKnapsack knapsack){
    this.knapsack=knapsack;
    this.itemToKnapsack = new HashMap<>();
    this.knapsackSolutions = new HashMap<>();
    for(Knapsack k : knapsack.getKnapsacks()){
      this.knapsackSolutions.put(k, new KnapsackSolution(k));
    }
    this.profit = 0;
  }

  /**
   * Copy-Konstruktor welcher eine neue Lösung als Kopie einer existierenden erzeugt
   *
   * @param sol Die zu kopierende Lösung
   */
  public Solution(Solution sol){
    this.knapsack = sol.knapsack;
    this.itemToKnapsack = new HashMap<>(sol.itemToKnapsack);
    this.knapsackSolutions = new HashMap<>(sol.knapsackSolutions);
    this.profit = sol.profit;
  }

  /**
   * Ermittelt Profit bzw. Zielfunktionswert der Lösung
   *
   * @return  Profit der Lösung
   */
  public int getProfit(){
    return this.profit;
  }

  /**
   * Berechnet die K Gewichte eines der Rucksäcke der K-MKP Instanz
   *
   * @param k Rucksack dessen Gewichte bestimmt werden sollen
   * @return  Array von Gewichten
   */
  public int[] getWeight(Knapsack k){
    return this.knapsackSolutions.get(k).weights;
  }

  /**
   * Gibt ein Gewicht der K Dimensionen eines bestimmten Rucksacks zurück. Wirft AssertionError wenn i >= K gilt.
   *
   * @param k Rucksack
   * @param i Index des Gewichts
   * @return  Gewicht
   */
  public int getWeight(Knapsack k, int i){
    assert i <= k.getK();
    return this.knapsackSolutions.get(k).weights[i];
  }

  /**
   * Gibt die K-MKP Instanz zurück, für welche diese Lösung gilt
   *
   * @return  K-MKP Instanz
   */
  public KConstraintMultipleKnapsack getKnapsack(){
    return this.knapsack;
  }

  /**
   * Bestimmt den Rucksack, welchem ein bestimmtes Item zugeordnet ist
   *
   * @param i Item
   * @return  Rucksack, wenn i einem Rucksack zugeordnet ist
   */
  public Knapsack getKnapsack(Item i){
    return this.itemToKnapsack.get(i);
  }

  /**
   * Prüft ob die Lösung zulässig für die Instanz ist
   *
   * @return  true wenn die Lösung zulässig ist
   */
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

  /**
   * Prüft, ob ein Objekt einem Rucksack zugeordnet werden kann, ohne dessen Kapazitätsbeschränkungen zu überschreiten
   *
   * @param item  Das Objekt welches zugeornet werden soll
   * @param knapsack  Der Rucksack welchem das Objekt zugeordnet werden soll
   * @return  true wenn die Zuordnung keine Kapazitätsbeschränkung verletzt
   */
  public boolean canBeAdded(Item item, Knapsack knapsack){
    for(int i = 0; i < knapsack.getK(); i ++){
      if(knapsackSolutions.get(knapsack).weights[i]+ item.getWeight(i) > knapsack.getCapacities(i)){
        return false;
      }
    }
    return true;
  }

  /**
   * Ordnet ein Objekt einen Rucksack zu, wirft AssertionError, wenn das Objekt bereits zugeordnet ist
   *
   * @param item
   * @param knapsack
   */
  public void addItem(Item item, Knapsack knapsack){
    assert !this.isUsed(item);
    this.itemToKnapsack.put(item, knapsack);
    this.profit += item.getProfit();
    this.knapsackSolutions.get(knapsack).addItem(item);
  }

  /**
   * Entfernt ein Objekt aus seinem zugerdneten Rucksack
   *
   * @param item  Das zu entfernende Objekt
   */
  public void removeItem(Item item){
    if(!itemToKnapsack.containsKey(item)) return;
    this.knapsackSolutions.get(itemToKnapsack.get(item)).removeItem(item);
    this.profit-=item.getProfit();
    this.itemToKnapsack.remove(item);
  }

  /**
   * Prüft ob ein Objekt in einem Rucksack durch ein anderes ersetzt werden kann, ohne dessen Kapazitätsbeschränkungen zu überschreiten
   *
   * @param newIt Das neue Objekt
   * @param oldIt Das zu ersetztende Objekt
   * @return  true genau dann, wenn der tausch keine Kapazitätsbeschränkung überschreiten würde
   */
  public boolean couldBeReplaced(Item newIt, Item oldIt){
    return knapsackSolutions.get(itemToKnapsack.get(oldIt)).couldBeReplaced(newIt, oldIt);
  }

  /**
   * Bestimmt die Objekte, welche in der aktuellen Lösung Rucksäcken zugeordnet sind
   *
   * @return  Eine Menge von Objekten
   */
  public Set<Item> getUsedItems(){
    return this.itemToKnapsack.keySet();
  }

  /**
   * Prüft, ob ein bestimmtes Objekt in der momentanen Lösung einem Rucksack zugeordnet ist
   *
   * @param i Das Objekt
   * @return  true genau dann, wenn das Objekt einem Rucksack zugeordnet ist
   */
  public boolean isUsed(Item i){
    return this.itemToKnapsack.containsKey(i);
  }

  /**
   * Bestimmt alle Objekte, welchem einen bestimmten Rucksack zugeordnet sind
   *
   * @param knapsack  Der Rucksack
   * @return  Liste von Objekten
   */
  public List<Item> itemsOf(Knapsack knapsack){
    return this.knapsackSolutions.get(knapsack).items;
  }

  /**
   * Wendet einen Valid Cycle aus dem VLSN-Algorithmus nach Ahuja und Cunha auf eine Lösung an. Dabei werden Objekte in einem Kreis zwischen ihren Rucksäcken getauscht
   *
   * @param c Der Valid Cycle
   * @return  Die Lösung nach Anwendung des Valid Cycles
   */
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

  /**
   * Innere Klasse, welche verwendet wird um die Zuordnung von Objekten zu Rucksäcken vorzunehmen
   */
  private static class KnapsackSolution{
    List<Item> items;
    int[] weights;
    int profit;
    Knapsack knapsack;

    /**
     * Konstruktor
     *
     * @param knapsack Rucksack, welchem Objekte zugeordnet werden sollen
     */
    private KnapsackSolution(Knapsack knapsack){
      this.knapsack = knapsack;
      this.weights = new int[knapsack.getK()];
      this.items = new ArrayList<>();
      this.profit = 0;
    }

    /**
     * Ermittelt den Profit des zugehörigen Rucksacks
     *
     * @return  Profit
     */
    private int getProfit() {
      return profit;
    }

    /**
     * Gibt die momentanen Gewichte des Rucksacks als K-stelliges Array zurück
     *
     * @return  K-Stelliges Array mit der momentanen Gewichtsauslastung
     */
    private int[] getWeights(){
      return this.weights;
    }

    /**
     * Methode welche die momentan dem Rucksack zugeordneten Objekte in Form einer Liste zurückgibt
     *
     * @return  Liste mit den zugeordneten Objekten
     */
    private List<Item> getItems(){
      return this.items;
    }

    /**
     * Bestimmt das Gewicht einer bestimmten Dimension. Wirft einen AssertionError wenn index >= K gilt
     *
     * @param index Index der Dimension
     * @return  Gewicht der index-Dimension
     */
    private int getWeight(int index){
      assert index < weights.length;
      return this.weights[index];
    }

    /**
     * Fügt dem zugehörigen Rucksack ein Objekt hinzu
     *
     * @param item Das hinzuzufügende Objekt
     */
    private void addItem(Item item){
      this.items.add(item);
      this.profit += item.getProfit();
      for(int i = 0; i < weights.length; i++){
        this.weights[i] += item.getWeight(i);
      }
    }

    /**
     * Entfernt ein Objekt aus dem Rucksack
     *
     * @param item  Das zu entfernende Objekt
     */
    private void removeItem(Item item){
      this.items.remove(item);
      this.profit -= profit;
      for(int i = 0; i < weights.length; i++){
        this.weights[i] -= item.getWeight(i);
      }
    }

    /**
     * Prüft, ob ein Objekt aus dem zugehörigen Rucksack durch ein anderes ersetzt werden kann ohne dabei die Kapazitätsbeschränkungen des Rucksacks zu überschreiten
     *
     * @param newIt Das Objekt welches hinzugefügt werden soll
     * @param oldIt Das Objekt welches entfernt werden soll
     * @return  true wenn der Austausch der Objekte keine Kapazitätsbeschränkung verletzt
     */
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
