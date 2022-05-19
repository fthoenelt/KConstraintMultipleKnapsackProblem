package test;

import ganew.Chromosom;
import ganew.crossover.NewOrderBasedCrossover;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import knapsack.KConstraintMultipleKnapsack;
import library.KnapsackLibraryReader;
import org.junit.Test;

public class TestNewOrderBasedCrossover {

  @Test
  public void testFunctionality(){
    KConstraintMultipleKnapsack knapsack = GenerateTestInstances.generateTestInstance(100, 3, 10, 2, 0.7);

    List<Integer> p1Items = IntStream.range(0, knapsack.getNrItems()).boxed().collect(Collectors.toList());
    Collections.shuffle(p1Items);
    Chromosom p1 = new Chromosom(p1Items, knapsack);

    System.out.println("p1:"+p1.getSolution().toString());

    List<Integer> p2Items = IntStream.range(0, knapsack.getNrItems()).boxed().collect(Collectors.toList());
    Collections.shuffle(p2Items);
    Chromosom p2 = new Chromosom(p2Items, knapsack);

    System.out.println("p2:"+p2.getSolution().toString());

    Chromosom child = new NewOrderBasedCrossover().crossover(knapsack, p1, p2);

    System.out.println("Child:"+child.getSolution().toString());
  }

}
