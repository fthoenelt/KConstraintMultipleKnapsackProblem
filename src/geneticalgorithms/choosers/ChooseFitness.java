package geneticalgorithms.choosers;

import java.util.List;
import java.util.Random;
import knapsack.Solution;

public class ChooseFitness implements Chooser{

  @Override
  public Solution[] choose(List<Solution> pop) {
    double[] fitnessPerChrom = new double[pop.size()];
    double sumFit = 0.0;
    int index = 0;
    for(Solution chrom : pop){
      fitnessPerChrom[index] = chrom.getProfit();
      index++;
      sumFit+=chrom.getProfit();
    }
    assert sumFit !=0.0;
    Random rand = new Random();
    double first = rand.nextDouble();
    double second = rand.nextDouble();
    double sum = 0.0;
    int firstParent = -1;
    int secondParent = -1;
    for(index = 0; index < fitnessPerChrom.length; index++){
      sum += (fitnessPerChrom[index]/sumFit);
      if(first <= sum &&firstParent==-1){
        firstParent = index;
      }else if(second <= sum && secondParent ==-1){
        secondParent = index;
      }
      if(firstParent!=-1 && secondParent!= -1){
        break;
      }
    }
    assert firstParent!= secondParent;
    if(firstParent==-1){
      firstParent = pop.size()-1;
    }
    if(secondParent == -1){
      secondParent =pop.size()-1;
    }
    return new Solution[]{pop.get(firstParent), pop.get(secondParent)};
  }
}
