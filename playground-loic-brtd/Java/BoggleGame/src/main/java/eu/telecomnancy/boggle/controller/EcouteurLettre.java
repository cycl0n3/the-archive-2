package eu.telecomnancy.boggle.controller;

import eu.telecomnancy.boggle.model.Boggle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class EcouteurLettre implements EventHandler<ActionEvent> {

  private Boggle model;
  private Button btn;
  private int i, j;

  public EcouteurLettre(Button btn, Boggle model, int i, int j) {
    this.model = model;
    this.btn = btn;
    this.i = i;
    this.j = j;
  }

  @Override
  public void handle(ActionEvent event) {
    if (model.ajouterLettre(i, j)) {
      btn.getStyleClass().add("lettre-on");
    }
  }
}
