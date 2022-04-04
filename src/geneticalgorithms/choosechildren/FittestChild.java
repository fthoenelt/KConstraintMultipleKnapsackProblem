package geneticalgorithms.choosechildren;

import knapsack.Solution;

public class FittestChild implements ChooseChildren{

  @Override
  public Solution chooseChild(Solution[] children) {
    assert children.length==2;
    return (children[0].getProfit() >= children[1].getProfit())? children[0]:children[1];
  }
}
