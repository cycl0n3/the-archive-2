package eu.telecomnancy.boggle.view;

import javafx.scene.Parent;

public class GestionnaireThemes {

  private String[] themes;
  private int index;
  private Parent root;

  private static GestionnaireThemes instance;

  private GestionnaireThemes() {
    // Constructeur privé
  }

  public static synchronized GestionnaireThemes getInstance() {
    if (instance == null) {
      instance = new GestionnaireThemes();
    }
    return instance;
  }

  public void init(Parent rootParent) {
    root = rootParent;
    root.getStylesheets().add("/default.css");
    themes = new String[] {
        null, // Symbolise le thème par défaut
            "/dark.css" // Thème sombre
    };
    index = 0;
  }

  public void changerTheme() {
    // Si un thème est appliqué sur le thème par défaut
    if (themes[index] != null) {
      // Enlever ce thème
      root.getStylesheets().remove(themes[index]);
    }
    // Changement de thème
    index = (index + 1) % themes.length;
    // Si le nouveau thème n'est pas le thème pas défaut
    if (themes[index] != null) {
      // Ajouter ce thème à la liste
      root.getStylesheets().add(themes[index]);
    }
  }

}
