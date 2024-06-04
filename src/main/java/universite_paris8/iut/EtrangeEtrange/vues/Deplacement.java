package universite_paris8.iut.EtrangeEtrange.vues;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.util.Duration;
import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetence;
import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetences.CompetenceActif;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreActionMainDroite.ParametreCompetence.ParametreCourrir;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;

import java.util.HashSet;
import java.util.Set;


public class Deplacement
{
    private Timeline timeline;
    private Joueur joueur;
    private Set<Direction> directions;

    private BooleanProperty estEntrainDeCourir;

    private double delai;



    public Deplacement(Joueur joueur)
    {
        this.joueur = joueur;
        this.estEntrainDeCourir = new SimpleBooleanProperty();
        this.joueur.estEntrainDeCourirProperty().bind(estEntrainDeCourir);
        this.directions = new HashSet<>();
        this.delai = 0.020;
        initAnimationTimer();
        this.timeline.setCycleCount(Timeline.INDEFINITE);
        this.timeline.play();
    }

    private void initAnimationTimer() {
        this.timeline = new Timeline(new KeyFrame(Duration.seconds(delai), event -> {
            seDeplace();

        }));
    }

    private void seDeplace()
    {
        if(directions.isEmpty())
            joueur.setSeDeplace(false);
        else
            joueur.setSeDeplace(true);


        for (Direction direction : directions)
        {
            if (estEntrainDeCourir.get() && joueur.getCompetences().contientCompetence(TypeCompetence.COURIR))
            {
                CompetenceActif competence = (CompetenceActif) TypeCompetence.COURIR.getCompetence();
                competence.utilise(new ParametreCourrir(joueur,direction));
            }
            else
            {
                joueur.setDirection(direction);
                joueur.seDeplace();
            }
        }

    }


    public void ajoutDirection(Direction direction) {
        this.directions.add(direction);
    }

    /**
     * Récupère la touche relachée et l'enlève de la liste
     * @param direction
     */
    public void enleveDirection(Direction direction) {
        this.directions.remove(direction);
    }


}
