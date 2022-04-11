package ganew.selection;

import ganew.Chromosom;
import java.util.List;

public interface Selector {
  public List<Chromosom> createMatingPool(List<Chromosom> population, int size);
}
