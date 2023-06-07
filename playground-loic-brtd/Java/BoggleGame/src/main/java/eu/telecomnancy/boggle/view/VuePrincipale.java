package eu.telecomnancy.boggle.view;

import java.util.Observable;
import java.util.Observer;

import eu.telecomnancy.boggle.model.Boggle;
import javafx.scene.layout.BorderPane;

public class VuePrincipale extends BorderPane implements Observer {

  private Boggle model;

  public VuePrincipale(Boggle boggle) {
    model = boggle;
    model.addObserver(this);

    // Style
    this.getStyleClass().addAll("root");
  }

  @Override
  public void update(Observable o, Object arg) {

  }

}
