package universite_paris8.iut.EtrangeEtrange.vues;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.vues.Sprite.AnimationSprite;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static javafx.scene.input.KeyCode.*;

public class Deplacement {
    private Timeline timeline;
    private Joueur joueur;
    private Set<KeyCode> keyCode;
    private AnimationSprite spriteJoueur;

    public Deplacement(Joueur joueur, AnimationSprite spriteJoueur) {
        this.joueur = joueur;
        this.spriteJoueur = spriteJoueur;
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
    private void seDeplace()
    {

        if (keyCode.contains(Q))
        {
            joueur.setDirection(Direction.GAUCHE);
            spriteJoueur.debutAnimationMarche();
            joueur.seDeplace();
        }

        if (keyCode.contains(D))
        {
            joueur.setDirection(Direction.DROITE);
            spriteJoueur.debutAnimationMarche();
            joueur.seDeplace();
        }

        if (keyCode.contains(Z))
        {
            joueur.setDirection(Direction.HAUT);
            spriteJoueur.debutAnimationMarche();
            joueur.seDeplace();
        }

        if (keyCode.contains(S))
        {
            joueur.setDirection(Direction.BAS);
            spriteJoueur.debutAnimationMarche();
            joueur.seDeplace();
        }
    }

    public void addKeyCode(KeyCode kc) {
        this.keyCode.add(kc);
    }

    public void removeKeyCode(KeyCode kc) {
        this.keyCode.remove(kc);
    }
}
