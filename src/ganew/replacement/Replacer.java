package ganew.replacement;

import ganew.Chromosom;
import java.util.List;

/**
 * Interface für die Ersetzungsstrategie in dem PermGA. Diese ist verantwortlich dafür, inwiefern eine neue Population eine alte in dem GA ersetzt.
 */

public interface Replacer {

  /**
   * Methode die eine neue Population aus einer gegebenen alten, einer neuen und einer gewünschten Größe erzeugt.
   *
   * @param oldPop  Die alte Population aus der letzten Iteration des GAs
   * @param newPop  Die neu erzeugte Nachkommenpopulation
   * @param size  Die Größe, welche die neue Population haben soll
   * @return  Eine Population welche mithilfe der jeweiligen Ersetzungsstrategie erzeugt wurde
   */
  List<Chromosom> replace(List<Chromosom> oldPop, List<Chromosom> newPop, int size);
}
