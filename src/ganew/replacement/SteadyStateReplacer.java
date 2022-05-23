package ganew.replacement;

import ganew.Chromosom;
import java.util.List;
import java.util.Random;

/**
 * Klasse die das Replacer-Interface für den PermGA implementiert. Dabei wird die Steady-State Ersetzungsstrategie verwendet, bei welcher eine vorgegebene Anzahl an alten
 * Individuen aus der alten Population durch die gleiche Zahl an Individuen der neuen Population ersetzt. Hierbei kann über Parameter entschieden werden, ob die schlechtesten
 * Individuen ersetzt werden oder diese zufällig ausgewählt werden. Des Weiteren kann auch entschieden werden ob Duplikate in der neu erzeugten Population zulässig sind oder
 * nicht.
 */

public class SteadyStateReplacer implements Replacer{

  boolean best;
  boolean duplicates;
  double ratio;
  Random random;

  /**
   * Konstruktor für den Steady-State Replacer. Hierbei werden die verschiedenen Parameter für die verschiedenen Versionen der Ersetzungsstrategie festgelegt. Die möglichen
   * Einstellungen sind dabei ob die besten oder zufällige alte Individuen in die neue Population mit aufgenommen werden. Ausserdem kann entschieden werden, ob Dublikate in der
   * neuen Population zulässig sind oder nicht. Zuletzt kann das Verhältnis von neuen zu alten Individuen gewählt werden.
   *
   * @param best Wenn true werden die besten alten Individuen übernommen, sonst zufällige
   * @param duplicates  Wenn true sind Duplikate in der neuen Population in Ordnung
   * @param ratio Verhältnis von neuer zu alter Generation
   */
  public SteadyStateReplacer(boolean best, boolean duplicates, double ratio){
    this.random = new Random();
    this.best = best;
    this.duplicates = duplicates;
    this.ratio = ratio;
  }

  /**
   * Methode welche die oben beschriebene Ersetzungsstrategie durchführt und eine neue Population der Größe size erzeugt
   *
   * @param oldPop  Die alte Population aus der letzten Iteration des GAs
   * @param newPop  Die neu erzeugte Nachkommenpopulation
   * @param size  Gewünschte
   * @return  Die neue Population
   */
  @Override
  public List<Chromosom> replace(List<Chromosom> oldPop, List<Chromosom> newPop, int size) {
    //Anzahl an Individuen, welche aus der alten Generation übernommen werden sollen
    int n = (int) (size*ratio);
    if(best){
      //Die n besten Individuen aus der alten Generation werden in die neue mit aufgenommen und die schlechtesten aus der neuen Generation gelöscht
      newPop.sort((o1, o2) -> Integer.compare(o2.getFitness(),o1.getFitness()));
      //Dafür wird die neue Population absteigend nach ihrer Fitness sortiert und die überschüssigen Individuen gelöscht
      newPop.removeAll(newPop.subList(n, newPop.size()));
      //Die alte Generation wird besteigend nach ihrer Fitness sortiert
      oldPop.sort((o1, o2) -> Integer.compare(o2.getFitness(),o1.getFitness()));
      if(duplicates){
        //Wenn Duplikate erlaubt sind kann die Lücke in der neuen Population mit Individuen der alten aufgefüllt werden, in sortierter Reihenfolge
        newPop.addAll(oldPop.subList(0, size - newPop.size()));
      }else{
        //Wenn Duplikate nicht erlaubt sind muss vor dem Hinzufügen der Individuen aus der alten Generation geprüft werden, ob diese schon in der neuen Generation vorkommen
        for(Chromosom c : oldPop){
          if(newPop.size() >= size) break;
          if(!newPop.contains(c)){
            newPop.add(c);
          }
        }
      }
      assert newPop.size()==size;
      //Gebe die neu erzeugte Population zurück
      return newPop;
    }else{
      //Wenn die neue Population aus zufälligen Individuen der alten und neuen Generation bestehen soll ersetze n mal ein zufälliges neues Individuum mit einem zufälligen altem
      for(int i = 0; i < n; i++){
        //Lösche zunächst ein zufällig gewähltes Individuum
        newPop.remove(newPop.get(random.nextInt(newPop.size())));
        //Dann wähle ein zufälliges der alten Population
        Chromosom chromosom = oldPop.get(random.nextInt(oldPop.size()));
        //Sollten keine Duplikate erlaubt sein prüfe ob das gewählte Individuum schon in der Population vorkommt und wenn ja wähle ein neues
        while(newPop.contains(chromosom)&&!duplicates) chromosom = oldPop.get(random.nextInt(oldPop.size()));
        //Und füge das gewählte Individuum der neuen Population hinzu
        newPop.add(chromosom);
      }
      return newPop;
    }
  }
}
