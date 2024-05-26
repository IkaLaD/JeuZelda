package universite_paris8.iut.EtrangeEtrange.vues;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.util.Duration;
import universite_paris8.iut.EtrangeEtrange.modele.ActionJoueur.ActionDeplacement.ActionDeplacementBas;
import universite_paris8.iut.EtrangeEtrange.modele.ActionJoueur.ActionDeplacement.ActionDeplacementDroite;
import universite_paris8.iut.EtrangeEtrange.modele.ActionJoueur.ActionDeplacement.ActionDeplacementGauche;
import universite_paris8.iut.EtrangeEtrange.modele.ActionJoueur.ActionDeplacement.ActionDeplacementHaut;
import universite_paris8.iut.EtrangeEtrange.modele.ActionJoueur.ActionJoueur;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;
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
        this.directions = new HashSet<>();
        this.delai = 0.020;
        this.estEntrainDeCourir = new SimpleBooleanProperty(false);
        initAnimationTimer();
        this.estEntrainDeCourir.addListener((observable, oldValue, newValue) ->
        {
            if (newValue)
                delai = 0.010;
            else
                delai = 0.020;

            this.timeline.stop();
            this.timeline.getKeyFrames().set(0, new KeyFrame(Duration.seconds(delai), event -> seDeplace()));
            this.timeline.play();
        });

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
        ActionJoueur actionJoueur;

        if(directions.isEmpty())
            joueur.setSeDeplace(false);
        else
            joueur.setSeDeplace(true);


        if (directions.contains(Direction.GAUCHE))
        {
            actionJoueur = new ActionDeplacementGauche();
            joueur.action(actionJoueur);
        }

        if (directions.contains(Direction.DROITE))
        {
            actionJoueur = new ActionDeplacementDroite();
            joueur.action(actionJoueur);
        }

        if (directions.contains(Direction.HAUT))
        {
            actionJoueur = new ActionDeplacementHaut();
            joueur.action(actionJoueur);
        }

        if (directions.contains(Direction.BAS))
        {
            actionJoueur = new ActionDeplacementBas();
            joueur.action(actionJoueur);
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

    public void entrainDeCourir(boolean estEntrainDeCourir) {
        this.estEntrainDeCourir.set(estEntrainDeCourir);
    }


}
