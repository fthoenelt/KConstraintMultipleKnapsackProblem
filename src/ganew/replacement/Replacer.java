package ganew.replacement;

import ganew.Chromosom;
import java.util.List;

public interface Replacer {
  List<Chromosom> replace(List<Chromosom> oldPop, List<Chromosom> newPop, int size);
}
