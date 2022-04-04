package geneticalgorithms.stopcriterias;

public class IterationStopper implements StopCriteria {
  int iterations;
  int curIt;
  public IterationStopper(int iterations){
    this.iterations = iterations;
    curIt = 0;
  }

  @Override
  public boolean stop(boolean action) {
    curIt++;
    return curIt > iterations;
  }
}
