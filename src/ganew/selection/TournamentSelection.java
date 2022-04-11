package ganew.selection;

import ganew.Chromosom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import javax.swing.text.html.Option;

public class TournamentSelection implements Selector{
  Random random;
  int s;

  public TournamentSelection(int s){
    this.random = new Random();
    this.s = s;
  }
  @Override
  public List<Chromosom> createMatingPool(List<Chromosom> population, int size) {
    ArrayList<Chromosom> matingPool = new ArrayList<>(size);
    for(int i = 0; i < size; i++){
      Chromosom[] candidates = new Chromosom[s];
      for(int index = 0; index < s; index++){
        candidates[index] = population.get(random.nextInt(population.size()));
      }
      Optional<Chromosom> o = Arrays.stream(candidates).max(new Comparator<Chromosom>() {
        @Override
        public int compare(Chromosom o1, Chromosom o2) {
          return Integer.compare(o1.getFitness(), o2.getFitness());
        }
      });
      assert o.isPresent();
      matingPool.add(o.get());
    }
    return matingPool;
  }
}
