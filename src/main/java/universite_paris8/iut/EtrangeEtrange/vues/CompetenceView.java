package universite_paris8.iut.EtrangeEtrange.vues;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import universite_paris8.iut.EtrangeEtrange.modele.Compétence.Competences;
import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetence;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Personnage.Joueur;

import java.util.ArrayList;

public class CompetenceView {
    private Competences competences;
    private Joueur joueur;
    private int tailleIcon = 100;
    private Pane pane;
    private ColorAdjust colorAdjust;
    private ArrayList<ImageView> icons;

    public CompetenceView(Pane pane, Joueur joueur) {
        this.joueur = joueur;
        this.pane = pane;
        this.colorAdjust = new ColorAdjust();
        this.colorAdjust.setBrightness(-0.8);
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
        if(!competence.getCompetence().estDebloquer())
            imageView.setEffect(colorAdjust);

        imageView.setOnMouseClicked(e ->
        {
            competence.getCompetence().monterDeNiveau(joueur);
            competence.getCompetence().debloquer();
            imageView.setEffect(null);
        });
        imageView.setImage(image);
        return imageView;
    }


}
