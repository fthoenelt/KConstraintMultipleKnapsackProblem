package library;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import knapsack.KConstraintMultipleKnapsack;

/**
 * Klasse um mehrere K-MKP-Instanzen in einem Objekt zu speichern
 */

public class KnapsackLibrary implements Serializable {

  @Serial
  private static final long serialVersionUID = -5962625380072433817L;
  List<KConstraintMultipleKnapsack> knapsacks;

  /**
   * Konstruktor der eine leere KnapsackLibrary erzeugt
   */
  public KnapsackLibrary(){
    this.knapsacks=new ArrayList<>();
  }

  /**
   * Konstruktor der aus einer Liste von K-MKP-Instanzen eine KnapsackLibrary erzeugt
   *
   * @param knapsacks Die Liste der Instanzen
   */
  public KnapsackLibrary(List<KConstraintMultipleKnapsack> knapsacks){
    this.knapsacks=knapsacks;
  }

  /**
   * Fügt eine K-MKP-Instanz der KnapsackLibrary hinzu
   *
   * @param knapsack  Die hinzuzufügende K-MKP-Instanz
   */
  public void add(KConstraintMultipleKnapsack knapsack){
    this.knapsacks.add(knapsack);
  }

  /**
   * Fügt eine Menge von K-MKP-Instanzen der Library hinzu
   *
   * @param knapsackList  Eine Collection von K-MKP-Instanzen
   */
  public void addAll(Collection<KConstraintMultipleKnapsack> knapsackList){
    this.knapsacks.addAll(knapsackList);
  }

  /**
   * Gibt die Liste der K-MKP-Instanzen aus
   *
   * @return  Liste von K-MKP-Instanzen
   */
  public List<KConstraintMultipleKnapsack> getKnapsacks(){
    return this.knapsacks;
  }

}
