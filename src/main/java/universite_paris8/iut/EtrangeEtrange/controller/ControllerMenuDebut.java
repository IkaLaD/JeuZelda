package universite_paris8.iut.EtrangeEtrange.controller;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import universite_paris8.iut.EtrangeEtrange.Runner;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.Constantes;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMenuDebut implements Initializable {
    public Pane pane;
    public Button jouer;
    public Button quitter;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void jouerClick(MouseEvent mouseEvent) throws IOException {
        SwitchScene switchScene = SwitchScene.getSwitchScene();
        FXMLLoader fxmlLoaderCompetences = new FXMLLoader(Runner.class.getResource("ChoixClasseView.fxml"));
        Scene sceneCompetences = new Scene(fxmlLoaderCompetences.load(), Constantes.largeurEcran, Constantes.hauteurEcran);
        switchScene.getStage().setScene(sceneCompetences);
        switchScene.getStage().show();
    }

    public void quitterClick(MouseEvent mouseEvent) {
        Platform.exit();
        System.exit(0);
    }
}
