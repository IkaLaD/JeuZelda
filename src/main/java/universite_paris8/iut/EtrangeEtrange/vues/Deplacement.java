package universite_paris8.iut.EtrangeEtrange.vues;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.util.Duration;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;

import java.util.HashSet;
import java.util.Set;


/**
 * La classe deplacement permet de controller les déplacements du joueur :
 *      -> Elle stock les touches qui sont appuyées au clavier (touches de déplacements : Z, Q, S, D) et
 *      effectue les appels de méthode de déplacement nécessaire.
 */
public class Deplacement {
    private Timeline timeline;
    private Joueur joueur;
    private Set<Direction> directions;
    private BooleanProperty estEntrainDeCourir;

    private double delai;

    public Deplacement(Joueur joueur) {

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
       this.joueur.setDirection(Direction.calculeDirection(directions));
       this.joueur.seDeplace();

    }

    /**
     * Récupère la touche appuyée et l'ajoute à la liste
     * @param direction
     */
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

    public void entrainDeCourir(boolean estEntrainDeCourir)
    {
        this.estEntrainDeCourir.set(estEntrainDeCourir);
    }
}
