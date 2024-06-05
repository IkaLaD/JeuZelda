package universite_paris8.iut.EtrangeEtrange.vues;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import universite_paris8.iut.EtrangeEtrange.modele.Compétence.Competences;
import universite_paris8.iut.EtrangeEtrange.modele.Compétence.CreationArbre;
import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetence;
import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetences.Competence;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;

import java.util.ArrayList;

public class CompetenceView {
    private Competences competences;
    private Joueur joueur;
    private int largeur = 1920;
    private int hauteur = 1080;

    private int tailleIcon = 100;

    private Pane pane;
    private ArrayList<ImageView> icons = new ArrayList<>();

    public CompetenceView(Pane pane, Joueur joueur) {
        this.joueur = joueur;
        this.pane = pane;
        this.pane.setMinWidth(largeur);
        this.pane.setMinHeight(hauteur);
        this.competences = joueur.getCompetences();

        TypeCompetence competence = competences.getRoot();

        int x = largeur / 2 - tailleIcon;
        int y =  100;

        Rectangle comp = spriteIcon(competence);
        comp.setTranslateX(x);
        comp.setTranslateY(y);
        pane.getChildren().add(comp);

        constructionArbre(competences.getEnfants(competence), x, y + 2 * tailleIcon, 2 * tailleIcon);
    }

    public void constructionArbre(ArrayList<TypeCompetence> tronc, int x, int y, int ecartEntreIcon)
    {
        if (tronc.isEmpty())
            return;

        int xTmp = x;

        for (TypeCompetence competence : tronc)
        {

            Rectangle rectangle = spriteIcon(competence);
            rectangle.setTranslateX(xTmp);
            rectangle.setTranslateY(y);
            pane.getChildren().add(rectangle);
            xTmp += ecartEntreIcon;

            constructionArbre(competences.getEnfants(competence), x, y + 2 * tailleIcon, -ecartEntreIcon);
        }
    }

    private Rectangle spriteIcon(TypeCompetence competence)
    {
        Rectangle rectangle = new Rectangle(tailleIcon,tailleIcon);
        rectangle.setOnMouseClicked(e ->{
            competence.getCompetence().monterDeNiveau(joueur);
            competence.getCompetence().debloquer();            System.out.println(competence.toString());
        });
        return rectangle;
    }
}
