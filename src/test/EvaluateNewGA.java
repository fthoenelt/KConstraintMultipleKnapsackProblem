package test;

import ganew.crossover.Crossover;
import ganew.crossover.CycleCrossover;
import ganew.crossover.OrderBasedCrossover;
import ganew.crossover.PartiallyMatchedCrossover;
import ganew.crossover.UniformOrderCrossover;
import ganew.replacement.DeleteAllReplacer;
import ganew.replacement.Replacer;
import ganew.replacement.SteadyStateReplacer;
import ganew.selection.FitnessProportional;
import ganew.selection.RandomPool;
import ganew.selection.Selector;
import ganew.selection.TournamentSelection;
import library.KnapsackLibrary;
import library.KnapsackLibraryReader;
import org.junit.Test;

public class EvaluateNewGA {

  @Test
  public void evaluateNewGA(){
    KnapsackLibrary lib = KnapsackLibraryReader.readFile("knapsacks.ser");
    assert lib != null;

    Selector[] selectors = new Selector[]{new RandomPool(), new FitnessProportional(), new TournamentSelection(2), new TournamentSelection(5)};
    Crossover[] crossovers = new Crossover[]{new CycleCrossover(), new OrderBasedCrossover(), new PartiallyMatchedCrossover(), new UniformOrderCrossover()};
    Replacer[] replacers = new Replacer[]{new DeleteAllReplacer(), new SteadyStateReplacer(false, false, 0.5),new SteadyStateReplacer(false, false, 0.7),
        new SteadyStateReplacer(false, true, 0.5), new SteadyStateReplacer(false, true, 0.7),
        new SteadyStateReplacer(true, false, 0.5), new SteadyStateReplacer(true, false, 0.7),
        new SteadyStateReplacer(true, true, 0.5),new SteadyStateReplacer(true, true, 0.7)};

  }

}
