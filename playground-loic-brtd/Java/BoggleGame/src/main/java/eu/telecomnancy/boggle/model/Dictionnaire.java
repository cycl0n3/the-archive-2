package eu.telecomnancy.boggle.model;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Dictionnaire {

  private static Dictionnaire instance = new Dictionnaire("/dico_clean.txt");
  private Set<String> dico;

  /**
   * Créer un dictionnaire sur la base d'un fichier texte
   * 
   * @param s nom de fichier
   */
  private Dictionnaire(String s) {
    this.dico = new HashSet<String>(4000);
    InputStream file = getClass().getResourceAsStream(s);
    Scanner sc = new Scanner(file);
    while (sc.hasNext()) {
      String mot = sc.next();
      this.dico.add(mot);
    }
    sc.close();
  }

  /**
   * @return dictionnaire de la langue française
   */
  public static Dictionnaire getInstance() {
    return instance;
  }

  /**
   * @param s
   * @return vrai si le mot s est dans le dictionnaire
   */
  public boolean contient(String s) {
    return dico.contains(s);
  }

}
