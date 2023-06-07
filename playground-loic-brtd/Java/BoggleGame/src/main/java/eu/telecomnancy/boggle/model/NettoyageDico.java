package eu.telecomnancy.boggle.model;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class NettoyageDico {

  public void nettoyer(Path input, Path output) throws IOException {

    // Suppression des accents, des doublons, des mots
    // avec tiret(s) et mise en lettres majuscules
    Set<String> set = new HashSet<String>();
    Scanner sc = new Scanner(input, "UTF-8");
    while (sc.hasNext()) {
      String mot = sc.next();
      mot = enleverAccents(mot).toUpperCase();
      if (!mot.contains("-")) {
        set.add(mot);
      }
    }
    sc.close();

    // Restoration de l'ordre alphabétique
    List<String> liste = new ArrayList<String>(set);
    liste.sort((a, b) -> a.compareTo(b));

    // Écriture du fichier de sortie
    PrintWriter writer = new PrintWriter(output.toFile());
    for (String s : liste) {
      writer.println(s);
    }
    writer.close();
  }

  private String enleverAccents(String mot) {
    mot = Normalizer.normalize(mot, Normalizer.Form.NFD);
    mot = mot.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
    return mot;
  }

  public static void main(String[] args) throws IOException {
    URL inputURL = Boggle.class.getResource("/ressources/dico.txt");
    Path inputPath = Paths.get(inputURL.getPath());
    System.out.println("in : " + inputPath);

    URL rootURL = Boggle.class.getClass().getResource("/");
    Path outputPath = Paths.get(rootURL.getPath() + "/ressources/dico_clean.txt");
    File outputFile = outputPath.toFile();
    outputFile.createNewFile();
    System.out.println("out: " + outputPath);

    // Attention, décommenter cette ligne risque d'écraser le fichier
    // new NettoyageDico().nettoyer(inputPath, outputPath);
  }

}
