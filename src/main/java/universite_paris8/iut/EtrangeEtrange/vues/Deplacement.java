package universite_paris8.iut.EtrangeEtrange.vues;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;

import java.util.HashSet;
import java.util.Set;

import static javafx.scene.input.KeyCode.*;

/**
 * La classe deplacement permet de contrôler les déplacements du joueur :
 *      -> Elle stocke les touches qui sont appuyées au clavier (touches de déplacements : Z, Q, S, D) et
 *      effectue les appels de méthode de déplacement nécessaire.
 */
public class Deplacement {
    private Timeline timeline;
    private Joueur joueur;
    private Set<KeyCode> keyCode;

    public Deplacement(Joueur joueur) {
        this.joueur = joueur;
        this.keyCode = new HashSet<>();

        initAnimationTimer();
        this.timeline.setCycleCount(Timeline.INDEFINITE);
        this.timeline.play();
    }

    private void initAnimationTimer() {
        this.timeline = new Timeline(new KeyFrame(Duration.seconds(0.020), event -> {
            seDeplace();
        }));
    }

    private void seDeplace() {
        // Modifie l'état de déplacement du joueur en fonction des touches appuyées
        if (keyCode.isEmpty())
            joueur.setSeDeplace(false);
        else
            joueur.setSeDeplace(true);

        // Effectue les déplacements
        if (keyCode.contains(Q)) {
            joueur.setDirection(Direction.GAUCHE);
            joueur.seDeplace();
        }
        if (keyCode.contains(D)) {
            joueur.setDirection(Direction.DROITE);
            joueur.seDeplace();
        }
        if (keyCode.contains(Z)) {
            joueur.setDirection(Direction.HAUT);
            joueur.seDeplace();
        }
        if (keyCode.contains(S)) {
            joueur.setDirection(Direction.BAS);
            joueur.seDeplace();
        }
    }

    /**
     * Récupère la touche appuyée et l'ajoute à la liste
     * @param kc
     */
    public void addKeyCode(KeyCode kc) {
        this.keyCode.add(kc);
    }

    /**
     * Récupère la touche relâchée et l'enlève de la liste
     * @param kc
     */
    public void removeKeyCode(KeyCode kc) {
        this.keyCode.remove(kc);
    }
}
