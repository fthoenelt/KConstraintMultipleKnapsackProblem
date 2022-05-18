package ganew.replacement;

import ganew.Chromosom;
import java.util.List;

/**
 * Klasse welche das Replacer-Interface für den PermGA implementiert. Dabei wird die Delete-All Strategie verwendet, bei welcher die alte Population komplett gelöscht und
 * durch die neue ersetzt wird. Für den Fall dass die neue Population größer als gewünscht ist werden nur die ersten Einträge genommen, sollte sie kleiner als gewünscht sein
 * werden Individuen aus der alten Population übernommen.
 */

public class DeleteAllReplacer implements Replacer{

  /**
   * Methode, welche die oben beschriebene Ersetzungsstrategie für gegebene neue und alte Population ausführt und eine neue Generation der Größe size zurückgibt.
   *
   * @param oldPop  Die alte Population aus der letzten Iteration des GAs
   * @param newPop  Die neu erzeugte Nachkommenpopulation
   * @param size  Die Größe, welche die neue Population haben soll
   * @return die neue Generation
   */
  @Override
  public List<Chromosom> replace(List<Chromosom> oldPop, List<Chromosom> newPop, int size) {
    //Für den Fall dass die neue Population größer als gewünscht ist wird die Liste newPop auf die gewünschte Größe gekürzt
    if(newPop.size()>size){
      newPop.subList(0, size);
    }else if (newPop.size()< size){
      //In dem Fall dass die neue Population kleiner als gewünscht ist wird die Liste newPop mit Individuen der alten Generation gefüllt
      newPop.addAll(oldPop.subList(0, size - newPop.size()));
    }
    return newPop;
  }
}
