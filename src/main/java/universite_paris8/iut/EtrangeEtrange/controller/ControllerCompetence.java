package universite_paris8.iut.EtrangeEtrange.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import universite_paris8.iut.EtrangeEtrange.modele.Compétence.Competences;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.vues.CompetenceView;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerCompetence implements Initializable
{
    private SwitchScene switchScene;
    private Joueur joueur;
    private Competences competences;
    @FXML
    private Pane pane;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        this.switchScene = SwitchScene.getSwitchScene();
        this.switchScene.setControllerCompetence(this);
        this.joueur = this.switchScene.getJoueur();
        this.competences = this.joueur.getCompetences();

        new CompetenceView(pane,joueur);
    }


    public void onKeyPressed(KeyEvent keyEvent)
    {
        if (keyEvent.getCode() == KeyCode.ESCAPE)
        {
            switchScene.getStage().setScene(switchScene.getSceneJeu());
        }
    }

    public void requestFocus(){
        this.pane.requestFocus();
    }

    public void onMouseClicked(MouseEvent mouseEvent)
    {
        this.pane.requestFocus();
    }


}
