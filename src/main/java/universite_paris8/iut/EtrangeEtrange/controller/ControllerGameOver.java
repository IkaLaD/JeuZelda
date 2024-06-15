package universite_paris8.iut.EtrangeEtrange.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import universite_paris8.iut.EtrangeEtrange.Runner;

import java.io.IOException;

public class ControllerGameOver {

    @FXML
    private Text gameOverText;

    @FXML
    private Button restartButton;

    @FXML
    void handleRestart(ActionEvent event) throws IOException {
        Runner.restartGame(SwitchScene.getSwitchScene().getStage());
    }

    @FXML
    public void quitterClick(MouseEvent mouseEvent) {
        Platform.exit();
        System.exit(0);
    }
}
