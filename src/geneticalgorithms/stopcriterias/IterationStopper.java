package geneticalgorithms.stopcriterias;

/**
 * Abbruchkriterium welches nach einer bestimmten Zahl an Iterationen abbrechen soll
 */

public class IterationStopper implements StopCriteria {
  int iterations;
  int curIt;

  /**
   * Konstruktor
   *
   * @param iterations  Maximale Zahl an Iterationen
   */
  public IterationStopper(int iterations){
    this.iterations = iterations;
    curIt = 0;
  }

  /**
   * Hauptmethode die nach einer erreichten Zahl an iterationen abbricht (true zurückgibt)
   *
   * @param action  Eine Aktion (optional und abhängig von dem Kriterium)
   * @return  true wenn abgebrochen werden soll
   */
  @Override
  public boolean stop(boolean action) {
    curIt++;
    return curIt > iterations;
  }
}
