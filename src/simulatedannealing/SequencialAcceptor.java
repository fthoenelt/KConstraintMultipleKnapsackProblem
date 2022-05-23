package simulatedannealing;

import knapsack.Solution;

/**
 * Klasse, welche das Acceptor-Interface implementiert. Die Wahrscheinlichkeit wird dabei über eine Exponentialfunktion berechnet.
 */

public class SequencialAcceptor implements Acceptor{
  double alpha;
  double cur;

  /**
   * Konstruktor für den Acceptor mit einer initialen Wahrscheinlichkeit, welche mit fortschreiten des Algorithmus sinkt und dem Wert, um welchen sie sinkt
   *
   * @param alpha Faktor mit welchem die Temperatur sinkt
   * @param initial Initialer Temperaturwert
   */
  public SequencialAcceptor(double alpha, double initial){
    assert 0<alpha&&alpha<1;
    this.alpha = alpha;
    this.cur=initial;
  }

  /**
   * Berechnet die Wahrscheinlichkeit mit welcher der Nachbar akzeptiert wird, abhängig von der Iteration
   *
   * @param current Momentane Lösung
   * @param neighbor  Der Nachbar der Lösung
   * @param iteration Die Iteration, in welcher sich der Algorithmus befindet
   * @return  Wahrscheinlichkeit mit welcher der Nachbar akzepiert wird
   */
  @Override
  public double getValue(Solution current, Solution neighbor, int iteration) {
    double next =  Math.exp(-(neighbor.getProfit()-current.getProfit()/cur));
    cur *= alpha;
    return next;
  }
}
