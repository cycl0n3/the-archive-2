package eu.telecomnancy.boggle.model;

import java.util.HashSet;
import java.util.Observable;
import java.util.Random;
import java.util.Set;

public class Boggle extends Observable {

  private static char[] voyelles = { 'A', 'E', 'I', 'O', 'U', 'Y' };
  private static char[] consonnes = { 'B', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T',
      'V', 'W', 'X', 'Z' };
  private char[][] lettres;
  private StringBuilder mot;
  private int score = 0;
  private int ligneChoisie, colonneChoisie; // dernière case choisie
  private Set<Coordonnee> coordSelectionnees;
  private Set<String> motsTrouves;

  /**
   * Des voyelles sur les lignes impaires ; des consonnes sur les lignes paires
   * 
   * @param taille
   */
  public Boggle(int taille) {
    this.lettres = new char[taille][taille];
    this.coordSelectionnees = new HashSet<Coordonnee>();
    this.motsTrouves = new HashSet<String>();
    Random gen = new Random();
    for (int lig = 0; lig < taille; lig++)
      if (lig % 2 == 0)
        for (int col = 0; col < taille; col++)
          lettres[lig][col] = voyelles[gen.nextInt(6)];
      else
        for (int col = 0; col < taille; col++)
          lettres[lig][col] = consonnes[gen.nextInt(20)];

    this.mot = new StringBuilder("");
    this.ligneChoisie = -1;
    this.colonneChoisie = -1;
    // Charger le dictionnaire avant de commencer
    Dictionnaire.getInstance();
  }

  /**
   * @return taille du jeu (carré)
   */
  public int size() {
    return this.lettres.length;
  }

  /**
   * @return score de la partie en cours
   */
  public int getScore() {
    return this.score;
  }

  /**
   * @param l
   * @param c
   * @return lettre de la case spécifiée
   */
  public char getLettre(int l, int c) {
    return this.lettres[l][c];
  }

  /**
   * @return mot en cours de construction
   */
  public String getMotChoisi() {
    return this.mot.toString();
  }

  /**
   * @param ligneChoisie
   * @param colonneChoisie
   * @param lig
   * @param col
   * @return vrai si les cases sont contigües
   */
  private boolean casesContigues(int ligneChoisie, int colonneChoisie, int lig, int col) {
    int diffL = Math.abs(ligneChoisie - lig);
    int diffC = Math.abs(colonneChoisie - col);
    return !((diffL == 0) && (diffC == 0)) && diffL <= 1 && diffC <= 1;
  }

  /**
   * Valider le mot en cours de construction s'il est dans le dictionnaire, le
   * score est incrémenté du nombre de lettres sinon, le score est décrementé de 1
   */
  public void valider() {
    Dictionnaire dico = Dictionnaire.getInstance();
    String motAVerifier = mot.toString();
    if (dico.contient(motAVerifier)) {
      if (motsTrouves.contains(motAVerifier)) {
        this.score -= 1;
        setChanged();
        notifyObservers(Message.MOT_DEJA_TROUVE);
      } else {
        this.score += this.mot.length();
        this.motsTrouves.add(motAVerifier);
        setChanged();
        notifyObservers(Message.MOT_VALIDE);
      }
    } else {
      this.score -= 1;
      setChanged();
      notifyObservers(Message.MOT_INVALIDE);
    }
    this.mot = new StringBuilder("");
    this.ligneChoisie = -1;
    this.colonneChoisie = -1;
    this.coordSelectionnees.clear();
    setChanged();
    notifyObservers(Message.VALIDATION_TERMINEE);
  }

  /**
   * Effacer le mot en cours de construction
   */
  public void effacer() {
    this.mot = new StringBuilder("");
    this.ligneChoisie = -1;
    this.colonneChoisie = -1;
    this.coordSelectionnees.clear();
    setChanged();
    notifyObservers(Message.EFFACEMENT_TERMINE);
  }

  public boolean lettreSectionnee(int lig, int col) {
    return coordSelectionnees.contains(new Coordonnee(lig, col));
  }

  /**
   * Indique si la lettre sélectionnée est valide. elle doit être dans une case
   * contiguë à la dernière lettre sélectionnée et ne doit pas être déjà
   * sélectionnée.
   * 
   * @param lig ligne
   * @param col colonne
   * @return true si la lettre est valide
   */
  private boolean lettreValide(int lig, int col) {
    return (this.ligneChoisie == -1
        || casesContigues(this.ligneChoisie, this.colonneChoisie, lig, col))
        && !coordSelectionnees.contains(new Coordonnee(lig, col));
  }

  /**
   * La lettre en case lig, col a été choisie On conserve la lettre si la case est
   * contigüe à la précédente
   * 
   * @param lig
   * @param col
   * @return true si la lettre était valide
   */
  public boolean ajouterLettre(int lig, int col) {
    boolean valide = false;
    if (lettreValide(lig, col)) {
      this.mot.append(this.getLettre(lig, col));
      this.ligneChoisie = lig;
      this.colonneChoisie = col;
      this.coordSelectionnees.add(new Coordonnee(lig, col));
      setChanged();
      notifyObservers();
      valide = true;
    }
    return valide;
  }

  /**
   * Énumération permettant de transférer des messages spécifiques aux observeurs
   * de Boggle.
   * 
   * @author loic
   */
  public static enum Message {
    EFFACEMENT_TERMINE,
    VALIDATION_TERMINEE,
    MOT_DEJA_TROUVE,
    MOT_INVALIDE,
    MOT_VALIDE
  }

}
