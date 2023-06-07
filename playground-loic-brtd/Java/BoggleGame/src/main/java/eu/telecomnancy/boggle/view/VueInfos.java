package eu.telecomnancy.boggle.view;

import java.util.Observable;
import java.util.Observer;

import eu.telecomnancy.boggle.model.Boggle;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;

public class VueInfos extends TilePane implements Observer {

  private Boggle model;
  private Label labelMot;
  private Label labelScore;

  public VueInfos(Boggle boggle) {
    model = boggle;
    model.addObserver(this);

    // Instanciation
    labelMot = new Label();
    labelScore = new Label("SCORE: " + model.getScore());

    // Placement
    setHgap(0);
    setAlignment(Pos.CENTER);
    getChildren().add(labelMot);
    getChildren().add(labelScore);
    labelMot.setPrefWidth(250);
    labelScore.setPrefWidth(200);
    labelMot.setMaxWidth(Double.MAX_VALUE);
    labelScore.setMaxWidth(Double.MAX_VALUE);
    labelScore.setAlignment(Pos.CENTER);

    // Style
    this.getStyleClass().addAll("pane-message");
    labelMot.getStyleClass().addAll("label-mot");
    labelScore.getStyleClass().addAll("label-score");
  }

  @Override
  public void update(Observable o, Object arg) {
    labelMot.setText(model.getMotChoisi());
    labelScore.setText("SCORE: " + model.getScore());
  }
}
