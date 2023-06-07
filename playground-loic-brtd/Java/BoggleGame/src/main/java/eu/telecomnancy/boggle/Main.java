package eu.telecomnancy.boggle;

import eu.telecomnancy.boggle.model.Boggle;
import eu.telecomnancy.boggle.view.GestionnaireThemes;
import eu.telecomnancy.boggle.view.PanneauControle;
import eu.telecomnancy.boggle.view.VueInfos;
import eu.telecomnancy.boggle.view.VueLettres;
import eu.telecomnancy.boggle.view.VueMessage;
import eu.telecomnancy.boggle.view.VuePrincipale;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    // Modèle
    Boggle model = new Boggle(5);

    // Vues
    VuePrincipale root = new VuePrincipale(model);
    VueLettres vueLettres = new VueLettres(model);
    PanneauControle panneauControle = new PanneauControle(model);
    VueInfos vueInfos = new VueInfos(model);
    VueMessage vueMessage = new VueMessage(model);

    // Placement
    root.setCenter(vueLettres);
    root.setRight(panneauControle);
    root.setBottom(vueInfos);
    root.setTop(vueMessage);

    // Theme
    GestionnaireThemes.getInstance().init(root);

    // Fenetre
    primaryStage.setTitle("Boggle");
    primaryStage.setScene(new Scene(root, 650, 450));
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
