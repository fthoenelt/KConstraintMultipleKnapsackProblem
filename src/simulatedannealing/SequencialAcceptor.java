package simulatedannealing;

import knapsack.Solution;

public class SequencialAcceptor implements Acceptor{
  double alpha;
  double cur;
  public SequencialAcceptor(double alpha, double initial){
    assert 0<alpha&&alpha<1;
    this.alpha = alpha;
    this.cur=initial;
  }
  @Override
  public double getValue(Solution current, Solution neighbor, int iteration) {
    double next =  Math.exp(-(neighbor.getProfit()-current.getProfit()/cur));
    cur *= alpha;
    return next;
  }
}
