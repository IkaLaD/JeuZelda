package universite_paris8.iut.EtrangeEtrange.controller;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import universite_paris8.iut.EtrangeEtrange.modele.Action.Action;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Interagisable.Prompte.GestionPrompt;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.PNJ;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.vues.AfficheBulleConversation;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerInteractionPNJ implements Initializable
{


    private AfficheBulleConversation afficheBulleConversation;
    private GestionPrompt gestionPrompt;


    private Label label;
    private ListView<String> listProposition;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {


        this.listProposition =  this.afficheBulleConversation.getListProposition();


    }

    private void defile(int scroll)
    {
        int index = listProposition.getSelectionModel().getSelectedIndex();
        int indexSuivant = index + scroll;

        if (indexSuivant >= 0 && indexSuivant < listProposition.getItems().size())
        {
            listProposition.getSelectionModel().select(indexSuivant);
            listProposition.scrollTo(indexSuivant);
        }
    }

    private String choixSelectionner()
    {
        return listProposition.getSelectionModel().getSelectedItem();
    }

    public void onKeyPressed(KeyEvent keyEvent)
    {
        switch (keyEvent.getCode())
        {
            case ENTER :
                String choix = choixSelectionner();

                if (choix != null)
                {
                    Action action = gestionPrompt.getPrompt().getAction();

                    if (action != null)
                        action.execute();

                    gestionPrompt.promptSuivant(choix);

                    if (gestionPrompt.getChoixPossible() != null && !gestionPrompt.getChoixPossible().isEmpty())
                        afficheBulleConversation.affichePrompt(gestionPrompt.getPrompt());


                }
                break;

            case Z,D:
                defile(1);
                break;

            case Q,S:
                defile(-1);
                break;

        }

    }

}
