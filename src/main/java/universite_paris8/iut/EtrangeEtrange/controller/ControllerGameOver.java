package universite_paris8.iut.EtrangeEtrange.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import universite_paris8.iut.EtrangeEtrange.Runner;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerGameOver {

    @FXML
    private Text gameOverText;

    @FXML
    private Button restartButton;



    @FXML
    void handleRestart(ActionEvent event) throws IOException {
        Runner.restartGame(SwitchScene.getSwitchScene().getStage());
        SwitchScene switchScene = SwitchScene.getSwitchScene();
        switchScene.getGestionSon().stopMusique();
    }

    @FXML
    public void quitterClick(ActionEvent mouseEvent) {
        Platform.exit();
        System.exit(0);
    }
}
