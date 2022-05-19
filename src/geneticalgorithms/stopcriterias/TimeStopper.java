package geneticalgorithms.stopcriterias;

/**
 * Abbruchkriterium welches nach einer bestimmten Zeit abbrechen soll
 */

public class TimeStopper implements StopCriteria{

  long maxTime;
  long startTime = 0;

  /**
   * Konstruktor
   *
   * @param maxTime maximale Zeit in ms nach welcher abgebrochen werden soll
   */
  public TimeStopper(long maxTime){
    this.maxTime = maxTime;
  }

  /**
   * Hauptmethode
   *
   * @param action  Eine Aktion (optional und abhängig von dem Kriterium)
   * @return  true wenn abgebrochen werden soll
   */
  @Override
  public boolean stop(boolean action) {
    if(startTime==0){
      startTime = System.currentTimeMillis();
      return false;
    }else{
      return System.currentTimeMillis() - startTime >= maxTime;
    }
  }
}
