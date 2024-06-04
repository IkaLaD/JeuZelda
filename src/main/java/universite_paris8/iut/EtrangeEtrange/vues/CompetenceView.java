package universite_paris8.iut.EtrangeEtrange.vues;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import universite_paris8.iut.EtrangeEtrange.modele.Compétence.Competences;
import universite_paris8.iut.EtrangeEtrange.modele.Compétence.CreationArbre;
import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetences.Competence;

import java.util.ArrayList;

public class CompetenceView {
    private Competences gestionCompetence;
    private int largeur = 1920;
    private int hauteur = 1080;

    private int tailleIcon = 50;

    private Pane pane;
    private ArrayList<ImageView> icons = new ArrayList<>();

    public CompetenceView(Pane pane) {
        this.pane = pane;
        this.pane.setMinWidth(largeur);
        this.pane.setMinHeight(hauteur);
        gestionCompetence = CreationArbre.arbres();

        Competence competence = gestionCompetence.getRoot();

        int x = largeur / 2 - tailleIcon;
        int y = (int) (hauteur * 0.7);

        Rectangle comp = spriteIcon(competence);
        comp.setTranslateX(x);
        comp.setTranslateY(y);
        pane.getChildren().add(comp);

        constructionArbre(gestionCompetence.getEnfants(competence), x, y + 2 * tailleIcon, 2 * tailleIcon);
    }

    public void constructionArbre(ArrayList<Competence> tronc, int x, int y, int ecartEntreIcon) {
        if (tronc.isEmpty()) {
            return;
        }

        int xTmp = x;

        for (Competence competence : tronc) {
            Rectangle rectangle = spriteIcon(competence);
            rectangle.setTranslateX(xTmp);
            rectangle.setTranslateY(y);
            pane.getChildren().add(rectangle);
            xTmp += ecartEntreIcon;

            constructionArbre(gestionCompetence.getEnfants(competence), x, y + 2 * tailleIcon, -ecartEntreIcon);
        }
    }

    private Rectangle spriteIcon(Competence competence) {
        return new Rectangle(tailleIcon, tailleIcon);
    }
}
