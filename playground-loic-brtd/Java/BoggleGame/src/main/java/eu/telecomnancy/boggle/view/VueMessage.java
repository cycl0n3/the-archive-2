package eu.telecomnancy.boggle.view;

import java.util.Observable;
import java.util.Observer;

import eu.telecomnancy.boggle.model.Boggle;
import eu.telecomnancy.boggle.model.GenerateurPhrases;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;

public class VueMessage extends TilePane implements Observer {

  private Boggle model;
  private Label labelMessage;

  public VueMessage(Boggle boggle) {
    model = boggle;
    model.addObserver(this);

    // Instanciation
    labelMessage = new Label("Formez un mot ;)");
    getChildren().add(labelMessage);

    // Style
    this.setAlignment(Pos.CENTER);
    this.getStyleClass().addAll("pane-message");
    labelMessage.getStyleClass().addAll("label-message");
  }

  @Override
  public void update(Observable o, Object arg) {
    if (arg == Boggle.Message.MOT_DEJA_TROUVE) {
      labelMessage.setText("Oups, ce mot a déjà été trouvé :/");
    } else if (arg == Boggle.Message.MOT_VALIDE) {
      labelMessage.setText(GenerateurPhrases.getInstance().getFelicitation());
    } else if (arg == Boggle.Message.MOT_INVALIDE) {
      labelMessage.setText(GenerateurPhrases.getInstance().getErreur());
    }
  }

}
