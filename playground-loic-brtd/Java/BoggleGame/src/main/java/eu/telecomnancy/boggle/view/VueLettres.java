package eu.telecomnancy.boggle.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import eu.telecomnancy.boggle.controller.EcouteurLettre;
import eu.telecomnancy.boggle.model.Boggle;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class VueLettres extends GridPane implements Observer {

  private Boggle model;
  private List<Button> boutons;

  public VueLettres(Boggle boggle) {
    model = boggle;
    model.addObserver(this);
    boutons = new ArrayList<>();

    // Placement des boutons en grille
    setAlignment(Pos.CENTER);
    setHgap(6);
    setVgap(6);
    for (int i = 0; i < model.size(); i++) {
      for (int j = 0; j < model.size(); j++) {
        char lettre = this.model.getLettre(i, j);

        // Instanciation
        Button btn = new Button(lettre + "");
        btn.getStyleClass().addAll("lettre");
        btn.setPrefSize(50, 50);

        // Ecouteurs
        btn.setOnAction(new EcouteurLettre(btn, model, i, j));

        // Stocke dans la liste
        boutons.add(btn);

        // Ajoute dans la grille
        add(btn, i, j);
      }
    }
  }

  @Override
  public void update(Observable o, Object arg) {
    if (arg == Boggle.Message.EFFACEMENT_TERMINE
        || arg == Boggle.Message.VALIDATION_TERMINEE) {
      for (Button b : boutons) {
        b.getStyleClass().remove("lettre-on");
      }
    }
  }
}
