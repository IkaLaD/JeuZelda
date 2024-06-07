package universite_paris8.iut.EtrangeEtrange.vues;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import universite_paris8.iut.EtrangeEtrange.modele.Compétence.Competences;
import universite_paris8.iut.EtrangeEtrange.modele.Compétence.CreationArbre;
import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetence;
import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetences.Competence;
import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetences.CompetenceGeneral.CompetenceStats.CompetenceUpPV;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.Constantes;

import java.util.ArrayList;

public class CompetenceView {
    private Competences competences;
    private Joueur joueur;
    private int tailleIcon = 100;
    private Pane pane;
    private ArrayList<ImageView> icons;

    public CompetenceView(Pane pane, Joueur joueur) {
        this.joueur = joueur;
        this.pane = pane;

        this.competences = joueur.getCompetences();
        this.icons = new ArrayList<>();

        TypeCompetence competence = competences.getRoot();
        int x = 200;
        int y = 20;

        ImageView imageView = spriteIcon(competence);
        imageView.setTranslateX(x);
        imageView.setTranslateY(y);

        this.pane.getChildren().add(imageView);

        constructionArbre(competences.getEnfants(competence), x, y + tailleIcon, tailleIcon);
    }

    public void constructionArbre(ArrayList<TypeCompetence> tronc, int x, int y, int ecartEntreIcon)
    {
        if (tronc.isEmpty())
            return;

        int xTmp = x;

        for (TypeCompetence competence : tronc)
        {
            ImageView imageView = spriteIcon(competence);
            imageView.setTranslateX(xTmp);
            imageView.setTranslateY(y);
            this.pane.getChildren().add(imageView);
            xTmp += ecartEntreIcon;

            constructionArbre(competences.getEnfants(competence), x, y +tailleIcon, -ecartEntreIcon);
        }
    }

    private ImageView spriteIcon(TypeCompetence competence)
    {

        Image image = null;
        switch (competence){
            case UP_ATTAQUE -> image = new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/Menus/Competences/force.png");
            case UP_DEFENSE -> image = new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/Menus/Competences/defense.png");
            case UP_PV -> image = new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/Menus/Competences/coeur.png");
            case UP_DEFENSE_SPECIAL -> image = new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/Menus/Competences/defensespeciale.png");
            case COURIR -> image = new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/Menus/Competences/sprint.png");
            case INVOQUER -> image = new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/Menus/Competences/invocation.png");
            case UP_ATTAQUE_SPECIAL -> image = new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/Menus/Competences/livremagique.png");
        }
        ImageView imageView = new ImageView();
        imageView.setScaleX(1);
        imageView.setScaleY(1);

        imageView.setOnMouseClicked(e ->{
            System.out.println("click");
            competence.getCompetence().monterDeNiveau(joueur);
            competence.getCompetence().debloquer();
        });
        imageView.setImage(image);
        return imageView;
    }


}
