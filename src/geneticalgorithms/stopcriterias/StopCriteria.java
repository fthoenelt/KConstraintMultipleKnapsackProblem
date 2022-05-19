package geneticalgorithms.stopcriterias;

/**
 * Inteerface welches von verschiedenen Abbruchkriterien des GAs für das K-MKP verwendet werden kann. Diese werden bei dem Durchlaufen der Iterationen von Generationen geprüft
 * und führen zu einem Anhalten der Heuristik abhängig von dem jeweiligen Kriterium
 */

public interface StopCriteria {

  /**
   * Methode prüft das jeweilige Kriterium
   *
   * @param action  Eine Aktion (optional und abhängig von dem Kriterium)
   * @return  true wenn Kriterium nicht mehr erfüllt
   */
  boolean stop(boolean action);
}
