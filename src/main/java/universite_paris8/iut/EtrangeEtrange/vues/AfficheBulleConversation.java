package universite_paris8.iut.EtrangeEtrange.vues;

import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.EtrangeEtrange.modele.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Interagisable.Prompte.GestionPrompt;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Interagisable.Prompte.Prompt;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.PNJ;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;

import java.util.ArrayList;

public class AfficheBulleConversation
{

    private Pane pane;
    private Label textePnj;
    private ListView<String> listProposition;


    public AfficheBulleConversation(Joueur joueur, Acteur pnj, Pane pane)
    {
        this.pane = pane;

        this.textePnj = new Label();
        this.listProposition = new ListView<>();

        this.textePnj.setLayoutY(pnj.getPosition().getY()-1);
        this.pane.getChildren().add(textePnj);

        this.listProposition.setLayoutY(joueur.getPosition().getY()-1);
        this.pane.getChildren().add(listProposition);
    }



    private void afficherMessagePNJ(String texte)
    {
        this.textePnj = new Label(texte);
    }

    private void afficherPropositionReponse(ArrayList<String> propositions)
    {
        this.listProposition = new ListView<>(FXCollections.observableList(propositions));
    }

    public ListView<String> getListProposition() {return this.listProposition;}

    public void affichePrompt(Prompt prompt)
    {
        afficherMessagePNJ(prompt.getTextePrompt());
        afficherPropositionReponse(prompt.getChoixPossible());
    }

    public Label getTextePnj(){ return this.textePnj;}











}
