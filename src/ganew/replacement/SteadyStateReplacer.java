package ganew.replacement;

import ganew.Chromosom;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class SteadyStateReplacer implements Replacer{

  boolean best;
  boolean dublicates;
  double ratio;
  Random random;

  public SteadyStateReplacer(boolean best, boolean dublicates, double ratio){
    this.random = new Random();
    this.best = best;
    this.dublicates = dublicates;
    this.ratio = ratio;
  }

  @Override
  public List<Chromosom> replace(List<Chromosom> oldPop, List<Chromosom> newPop, int maxSize) {
    int n = (int) (maxSize*ratio);
    if(best){
      newPop.sort((o1, o2) -> Integer.compare(o2.getFitness(),o1.getFitness()));
      newPop.removeAll(newPop.subList(n, newPop.size()));
      oldPop.sort((o1, o2) -> Integer.compare(o2.getFitness(),o1.getFitness()));
      if(dublicates){
        newPop.addAll(oldPop.subList(0, maxSize- newPop.size()));
      }else{
        for(Chromosom c : oldPop){
          if(newPop.size() >= maxSize) break;
          if(!newPop.contains(c)){
            newPop.add(c);
          }
        }
      }
      assert newPop.size()==maxSize;
      return newPop;
    }else{
      for(int i = 0; i < n; i++){
        newPop.remove(newPop.get(random.nextInt(newPop.size())));
        Chromosom chromosom = oldPop.get(random.nextInt(oldPop.size()));
        while(newPop.contains(chromosom)&&!dublicates) chromosom = oldPop.get(random.nextInt(oldPop.size()));
        newPop.add(chromosom);
      }
      return newPop;
    }
  }
}
