package universite_paris8.iut.EtrangeEtrange.vues;

import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import universite_paris8.iut.EtrangeEtrange.modele.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Interagisable.Prompte.GestionPrompt;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Interagisable.Prompte.Prompt;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.PNJ;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.Constantes;

import java.util.ArrayList;

public class AfficheBulleConversation
{

    private Pane pane;
    private Label textePnj;
    private ListView<String> listProposition;


    public AfficheBulleConversation(Joueur joueur, Acteur pnj, Pane pane)
    {
        this.pane = pane;

        this.listProposition = new ListView<>();
        this.textePnj = new Label();

        this.listProposition.setTranslateY((joueur.getPosition().getY()-1) * Constantes.tailleTile);
        this.listProposition.setTranslateX(joueur.getPosition().getX() * Constantes.tailleTile);
        this.listProposition.setMaxSize(30,20);



        this.textePnj.setMinSize(100,100);
        this.textePnj.setTranslateY((pnj.getPosition().getY()-1)* Constantes.tailleTile);
        this.textePnj.setTranslateX(pnj.getPosition().getX()* Constantes.tailleTile);
        this.pane.getChildren().add(textePnj);


        this.pane.getChildren().add(listProposition);
    }



    private void afficherMessagePNJ(String texte)
    {
        this.textePnj.setText(texte);
    }

    private void afficherPropositionReponse(ArrayList<String> propositions)
    {
        this.listProposition.setItems(FXCollections.observableList(propositions));
    }

    public ListView<String> getListProposition() {return this.listProposition;}

    public void affichePrompt(Prompt prompt)
    {
        afficherMessagePNJ(prompt.getTextePrompt());
        afficherPropositionReponse(prompt.getChoixPossible());
    }

    public Label getTextePnj(){ return this.textePnj;}











}
