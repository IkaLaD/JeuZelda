package universite_paris8.iut.EtrangeEtrange.vues;

import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Interaction.Prompte.Prompt;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.ConstantesAffichage;

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

        this.listProposition.setTranslateY((joueur.getPosition().getY()+2)*(ConstantesAffichage.tailleTile-32));
        this.listProposition.setTranslateX((joueur.getPosition().getX()+1)* ConstantesAffichage.tailleTile);
        this.listProposition.setMaxSize(900,100);

        textePnj.minWidthProperty().bind(textePnj.widthProperty());
        textePnj.minHeightProperty().bind(textePnj.heightProperty());


        this.textePnj.setTranslateY((pnj.getPosition().getY()-1) * (ConstantesAffichage.tailleTile-32));
        this.textePnj.setTranslateX((pnj.getPosition().getX()-1)* ConstantesAffichage.tailleTile);
        this.textePnj.setBackground(new Background(new BackgroundFill(Color.WHITE,null,null)));
        this.pane.getChildren().add(textePnj);


        this.pane.getChildren().add(listProposition);
    }



    private void afficherMessagePNJ(String texte)
    {
        this.textePnj.setText(texte);
    }

    private void afficherPropositionReponse(ArrayList<String> propositions)
    {
        if (propositions == null || propositions.isEmpty())
        {
            this.listProposition.setVisible(false);
        }
        else
        {
            this.listProposition.setVisible(true);
            this.listProposition.setItems(FXCollections.observableList(propositions));
        }
    }

    public ListView<String> getListProposition() {return this.listProposition;}

    public void affichePrompt(Prompt prompt)
    {
        afficherMessagePNJ(prompt.getTextePrompt());
        afficherPropositionReponse(prompt.getChoixPossible());
    }

    public Label getTextePnj(){ return this.textePnj;}











}
