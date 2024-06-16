package universite_paris8.iut.EtrangeEtrange.controller;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import universite_paris8.iut.EtrangeEtrange.Runner;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.ConstantesAffichage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMenuDebut implements Initializable {
    public Pane pane;
    public Button jouer;
    public Button quitter;
    public Pane fondImage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ImageView imageView = new ImageView(new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/Menus/Menu Debut/fond.png"));
        imageView.setX(0);
        imageView.setY(0);
        fondImage.getChildren().add(imageView);
    }

    public void jouerClick(MouseEvent mouseEvent) throws IOException {
        SwitchScene switchScene = SwitchScene.getSwitchScene();
        FXMLLoader fxmlLoaderCompetences = new FXMLLoader(Runner.class.getResource("ChoixClasseView.fxml"));
        Scene sceneCompetences = new Scene(fxmlLoaderCompetences.load(), ConstantesAffichage.largeurEcran, ConstantesAffichage.hauteurEcran);
        switchScene.getStage().setScene(sceneCompetences);
        switchScene.getStage().show();
    }

    public void quitterClick(MouseEvent mouseEvent) {
        Platform.exit();
        System.exit(0);
    }
}
