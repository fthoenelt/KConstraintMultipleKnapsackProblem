package knapsack;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementiert eine Instanz des K-Constraint Multiple Knapsack Problems mit n Items und m Rucksöcken
 */

public class KConstraintMultipleKnapsack implements Serializable {

  @Serial
  private static final long serialVersionUID = -8427621746814709189L;

  int knapsackID;
  List<Item> items;
  List<Knapsack> knapsacks;
  int k;

  /**
   * Konstruktor erstellt eine leere Instanz
   */
  public KConstraintMultipleKnapsack(){
    this.knapsackID = 0;
    this.items = new ArrayList<>();
    this.knapsacks = new ArrayList<>();
  }

  /**
   * Konstruktor erstellt eine leere Instanz
   *
   * @param n Anzahl Items
   * @param m Anzahl Rucksäcke
   */
  public KConstraintMultipleKnapsack(int n, int m){
    this.knapsackID = 0;
    this.items = new ArrayList<>(n);
    this.knapsacks = new ArrayList<>(m);
  }

  /**
   * Erstellt eine Instanz mit gegebenen Items und Rucksäcken
   *
   * @param items Liste von Items der Instanz
   * @param knapsacks Liste von Rucksäcken der Instanz
   * @param k Gewichts- und Kapazitätsdimension
   */
  public KConstraintMultipleKnapsack(List<Item> items, List<Knapsack> knapsacks, int k){
    this.knapsackID = 0;
    this.items = items;
    this.knapsacks = knapsacks;
    this.k = k;
  }

  /**
   * Gibt eine eindeutige Instanz ID zurück
   *
   * @return  ID der K-MKP Instant
   */
  public int getKnapsackID() {
    return knapsackID;
  }

  /**
   * Gibt Liste der Rucksäcke der Instanz zurück
   *
   * @return  Liste von RUcksack-Objekten
   */
  public List<Knapsack> getKnapsacks() {
    return knapsacks;
  }

  /**
   * Gibt Liste von Items der Instanz zurück
   *
   * @return  Liste der Items
   */
  public List<Item> getItems() {
    return items;
  }

  /**
   * Ermittelt n (Anzahl an Objekten)
   *
   * @return  Anzahl der Objekte der Instanz
   */
  public int getNrItems(){return this.items.size();}

  /**
   * Gibt den Rucksack an der Stelle index in der Liste zurück
   *
   * @param index Index des Rucksacks
   * @return  Rucksack an der Stelle index
   */
  public Knapsack getKnapsack(int index){
    return this.knapsacks.get(index);
  }

  /**
   * Gibt das Objekt an einer gewünschten Stelle zurück
   *
   * @param index Index des zu ermittelnden Objektes
   * @return  Objekt
   */
  public Item getItem(int index){
    return this.items.get(index);
  }

  /**
   * Gibt die Gewichts- und Kapazitätsdimension der K-MKP Instanz zurück
   *
   * @return  Gewichts- und Kapazitätsdimension
   */
  public int getK(){
    return this.k;
  }

  /**
   * Gibt die Anzahl an Rucksäcken in der K-MKP Instanz zurück
   *
   * @return  Anzahl an Rucksäcken
   */
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
