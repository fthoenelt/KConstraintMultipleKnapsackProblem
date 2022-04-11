package ganew.replacement;

import ganew.Chromosom;
import java.util.List;

public class DeleteAllReplacer implements Replacer{

  @Override
  public List<Chromosom> replace(List<Chromosom> oldPop, List<Chromosom> newPop, int size) {
    if(newPop.size()>size){
      newPop.subList(0, size);
    }else if (newPop.size()< size){
      newPop.addAll(oldPop.subList(0, size- newPop.size()));
    }
    return newPop;
  }
}
