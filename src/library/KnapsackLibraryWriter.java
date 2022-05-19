package library;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Klasse die eine KnapsackLibrary in serialisierter Form in eine Datei schreibt
 */

public class KnapsackLibraryWriter {

  /**
   * Schreibt die übergebene KnapsackLibrary in eine existierende Datei
   *
   * @param library Die KnapsackLibrary
   * @param filepath  Der Dateipfad
   */
  public static void writeLibrary(KnapsackLibrary library, String filepath){
    try{
      FileOutputStream fileOut = new FileOutputStream(filepath);
      ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
      objectOut.writeObject(library);
      objectOut.close();
      System.out.println("Written Successfully");
    }catch (Exception ex){
      ex.printStackTrace();
    }
  }

  /**
   * Schreibt eine übergebende KnapsackLibrary in eine neue Datei
   *
   * @param library Die zu speichernde KnapsackLibrary
   */
  public static void writeLibrary(KnapsackLibrary library){
    try{
      FileOutputStream fileOut = new FileOutputStream(new File("knapsacks.ser"));
      ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
      objectOut.writeObject(library);
      objectOut.close();
      fileOut.close();
      System.out.println("Written Successfully");
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
    } catch (IOException e) {
      System.out.println("Error initializing stream");
    }
  }
}
