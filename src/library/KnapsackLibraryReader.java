package library;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import org.jetbrains.annotations.NotNull;

/**
 * Liest eine Serialisierte Datei als KnapsackLibrary ein und erzeugt eine KnapsackLibrary daraus
 */

public class KnapsackLibraryReader {

  /**
   * Methode die eine Datei übergeben bekommt, in welcher eine serialisierte KnapsackLibrary geschrieben ist und erzeugt ein KnapsackLibrary Objekt daraus. Wenn die Datei keine
   * KnapsackLibrary enthält wird ein AssertionError geworfen
   *
   * @param filePath  Der Dateipfad
   * @return  KnapsackLibrary
   */
  public static KnapsackLibrary readFile(@NotNull String filePath){
    try {
      //Versuche die übergebene Datei zu lesen
      FileInputStream fileIn = new FileInputStream(filePath);
      ObjectInputStream objectIn = new ObjectInputStream(fileIn);
      //Und eine KnapsackLibrary daraus zu erzeugen
      KnapsackLibrary lib =  (KnapsackLibrary) objectIn.readObject();
      assert lib != null;
      return lib;
    }catch(Exception e){
      e.printStackTrace();
    }
    return null;
  }
}
