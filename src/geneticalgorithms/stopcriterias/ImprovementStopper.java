package geneticalgorithms.stopcriterias;

/**
 * Stopkriterium wordruch abgebrochen wird wenn eine bestimmte Anzahl an Iterationen erreicht wurde, in welcher keine Aktion eingetreten ist. Die Aktion ist z.B. das finden
 * einer verbessernden Lösung im GA
 */

public class ImprovementStopper implements StopCriteria {
  int iterations;
  int curNoImprovement;

  /**
   * Konstruktor
   *
   * @param iterations  Die maximale Anzahl an Iterationen
   */
  public ImprovementStopper(int iterations){
    this.iterations =iterations;

  }

  /**
   * Methode die das oben beschriebene Abbruchkriterium implementiert
   *
   * @param action  Eine Aktion (optional und abhängig von dem Kriterium)
   * @return  true wenn abgebrochen werden soll
   */
  @Override
  public boolean stop(boolean action) {
    curNoImprovement = (action)? 0:curNoImprovement+1;
    return curNoImprovement > iterations;
  }
}
