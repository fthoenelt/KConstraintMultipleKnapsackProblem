package geneticalgorithms.stopcriterias;

public class TimeStopper implements StopCriteria{

  long maxTime;
  long startTime = 0;
  public TimeStopper(long maxTime){
    this.maxTime = maxTime;
  }
  @Override
  public boolean stop(boolean action) {
    if(startTime==0){
      startTime = System.currentTimeMillis();
      return false;
    }else{
      return System.currentTimeMillis() - startTime >= maxTime;
    }
  }
}
