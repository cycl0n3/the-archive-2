package eu.telecomnancy.boggle.view;

import java.util.Observable;
import java.util.Observer;

import eu.telecomnancy.boggle.model.Boggle;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class PanneauControle extends VBox implements Observer {

  private Boggle model;
  private Button btnValider;
  private Button btnEffacer;
  private Button btnTheme;
  private Button btnQuitter;

  public PanneauControle(Boggle boggle) {
    model = boggle;
    model.addObserver(this);

    // Instanciation
    btnValider = new Button("VALIDER");
    btnEffacer = new Button("EFFACER");
    btnQuitter = new Button("QUITTER");
    btnTheme = new Button("THEME");

    // Ecouteurs
    btnValider.setOnAction(e -> model.valider());
    btnEffacer.setOnAction(e -> model.effacer());
    btnTheme.setOnAction(e -> GestionnaireThemes.getInstance().changerTheme());
    btnQuitter.setOnAction(e -> Platform.exit());

    // Placement
    setAlignment(Pos.CENTER);
    setSpacing(12);
    getChildren().add(btnValider);
    getChildren().add(btnEffacer);
    getChildren().add(btnTheme);
    getChildren().add(btnQuitter);
    btnValider.setPrefWidth(110);
    btnEffacer.setPrefWidth(110);
    btnQuitter.setPrefWidth(110);
    btnTheme.setPrefWidth(110);

    // Style
    this.getStyleClass().addAll("pane-controle");
    btnValider.getStyleClass().addAll("btn", "btn-success");
    btnEffacer.getStyleClass().addAll("btn", "btn-warning");
    btnTheme.getStyleClass().addAll("btn", "btn-accent");
    btnQuitter.getStyleClass().addAll("btn");
  }

  @Override
  public void update(Observable o, Object arg) {

  }
}
