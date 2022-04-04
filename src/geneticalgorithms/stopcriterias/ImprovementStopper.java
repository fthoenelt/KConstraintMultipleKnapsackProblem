package geneticalgorithms.stopcriterias;

public class ImprovementStopper implements StopCriteria {
  int iterations;
  int curNoImprovement;
  public ImprovementStopper(int iterations){
    this.iterations =iterations;

  }
  @Override
  public boolean stop(boolean action) {
    curNoImprovement = (action)? 0:curNoImprovement+1;
    return curNoImprovement > iterations;
  }
}
