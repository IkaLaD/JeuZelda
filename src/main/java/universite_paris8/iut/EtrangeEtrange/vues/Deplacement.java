package universite_paris8.iut.EtrangeEtrange.vues;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
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

import static javafx.scene.input.KeyCode.*;


/**
 * La classe deplacement permet de controller les déplacements du joueur :
 *      -> Elle stock les touches qui sont appuyées au clavier (touches de déplacements : Z, Q, S, D) et
 *      effectue les appels de méthode de déplacement nécessaire.
 */
public class Deplacement {
    private Timeline timeline;
    private Joueur joueur;
    private Set<Direction> directions;

    public Deplacement(Joueur joueur) {
        this.joueur = joueur;
        this.directions = new HashSet<>();

        initAnimationTimer();
        this.timeline.setCycleCount(Timeline.INDEFINITE);
        this.timeline.play();
    }

    private void initAnimationTimer() {
        this.timeline = new Timeline(new KeyFrame(Duration.seconds(0.020), event -> {
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


    public void enleveDirection(Direction direction) {
        this.directions.remove(direction);
    }
}
