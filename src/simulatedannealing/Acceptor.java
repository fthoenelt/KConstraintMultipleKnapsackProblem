package simulatedannealing;

import knapsack.Solution;

/**
 * Interface, welches einen Acceptor für Simulated Annealing implementiert. Hierbei wird auf einem bestimmten Weg eine Fließkommazahl berechnet, welche den Abstand einer
 * aktuellen Lösung zu einer Lösung aus der Nachbarschaft angibt. Mithilfe des Acceptors wird der Abkühlungsprozess simuliert und verschlechternde Lösungen angenommen.
 */

public interface Acceptor {

  /**
   * Berechnet den Wert von den beiden gegebenen Lösungen über Formeln, welche in den implementierenden Klassen definiert werden muss. Im Allgemeinen werden verschlechternde
   * Lösungen im Laufe des Algorithmus mit einer sinkenden Wahrscheinlichkeit akzeptiert
   *
   * @param cur Die momentane Lösung
   * @param neighbor  Der Nachbar der Lösung
   * @param iteration Die Iteration, in welcher sich der Algorithmus befindet
   * @return  Einen Double der die Wahrscheinlichkeit der Akzeptanz der neuen Lösung angibt
   */
  double getValue(Solution cur, Solution neighbor, int iteration);
}
