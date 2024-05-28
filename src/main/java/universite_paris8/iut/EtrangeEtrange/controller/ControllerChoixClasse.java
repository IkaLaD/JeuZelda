package universite_paris8.iut.EtrangeEtrange.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import java.net.URL;
import java.util.ResourceBundle;





public class ControllerChoixClasse implements Initializable
{
    @FXML
    private Label nomClasse;
    @FXML
    private Label descriptionClasse;


    @FXML
    private ProgressBar statPv;
    @FXML
    private ProgressBar statAttaque;
    @FXML
    private ProgressBar statDefense;
    @FXML
    private ProgressBar statAttaqueSpecial;
    @FXML
    private ProgressBar statDefenseSpecial;
    @FXML
    private ProgressBar statVitesse;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
