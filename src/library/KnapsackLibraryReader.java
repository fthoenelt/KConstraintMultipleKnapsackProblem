package library;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class KnapsackLibraryReader {
  public static KnapsackLibrary readFile(String filePath){
    try {
      FileInputStream fileIn = new FileInputStream(filePath);
      ObjectInputStream objectIn = new ObjectInputStream(fileIn);
      KnapsackLibrary lib =  (KnapsackLibrary) objectIn.readObject();
      assert lib != null;
      return lib;
    }catch(Exception e){
      e.printStackTrace();
    }
    return null;
  }
}
