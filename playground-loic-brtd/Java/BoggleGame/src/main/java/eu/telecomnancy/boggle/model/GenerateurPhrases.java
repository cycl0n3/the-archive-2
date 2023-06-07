package eu.telecomnancy.boggle.model;

public class GenerateurPhrases {

  private String[] felicitations;
  private int felicitationIndex = 0;
  private String[] erreurs;
  private int erreurIndex = 0;

  private static GenerateurPhrases instance;

  private GenerateurPhrases() {
    felicitations = new String[] {
        "Bravo !",
        "Super ;)",
        "Cool :D",
        "J'aime bien ce mot xD",
        "Super ! Un autre ?",
        "Vous ête doué !"
    };
    erreurs = new String[] {
        "Ce mot n'est pas dans mon dictionnaire :/",
        "Est-ce vraiment un mot ? :O",
        "Je n'ai pas le souvenir de ce mot ^^'",
        "Je crois que ça n'existe pas...",
        "Vous l'avez inventé ce mot? xD",
        "Je ne connais pas ce mot :("
    };
  }

  public static synchronized GenerateurPhrases getInstance() {
    if (instance == null) {
      instance = new GenerateurPhrases();
    }
    return instance;
  }

  public String getFelicitation() {
    String res = felicitations[felicitationIndex];
    felicitationIndex = (felicitationIndex + 1) % felicitations.length;
    return res;
  }

  public String getErreur() {
    String res = erreurs[erreurIndex];
    erreurIndex = (erreurIndex + 1) % erreurs.length;
    return res;
  }

}
