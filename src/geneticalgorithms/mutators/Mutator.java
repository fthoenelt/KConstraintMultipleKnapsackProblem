package geneticalgorithms.mutators;

import knapsack.Solution;

/**
 * Interface welches die Mutation in Genetischen Algorithmen beschreibt. Dabei werden durch Rekombination erzeugte Nachfahrenchromosome zufällig und lokal verändert, um sowohl
 * Variation zu simulieren als auch den Suchraum möglichst vollständig zu betrachten
 */

public interface Mutator {

  /**
   * Hauptmethode die ein gegebenes Individuum in Form eines Solution-Objektes zufällig verändert und dieses dann wieder zurückgibt
   *
   * @param chromosom Das zu mutierende Individuum
   * @return  Individuum nach der Mutation
   */
  Solution mutate(Solution chromosom);

  /**
   * Gibt an ob die Mutation nur zulässige oder auch unzulässige Individuen erzeugt
   *
   * @return  true gdw. nur zulässige Lösungen erzeugt werden
   */
  boolean getFeasibility();
}
