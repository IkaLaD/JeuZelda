package universite_paris8.iut.EtrangeEtrange.vues.Sprite;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.Constantes;

public class AnimationSprite {
    private Entite entite;
    private ImageView sprite;
    int image = 1;

    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.25), event -> {
        String face = switch (entite.getDirection()){
            case BAS -> "bas";
            case HAUT -> "haut";
            case DROITE -> "droite";
            case GAUCHE -> "gauche";
        };

        if(image == 1){
            sprite.setImage(new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/sprite/"+face+image+".png"));
            image=2;
        }
        else if(image == 2){
            sprite.setImage(new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/sprite/"+face+image+".png"));
            image=3;
        }
        else {
            sprite.setImage(new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/sprite/"+face+image+".png"));
            image=2;
        }

    }));

    /**
     * La class est uniquement adapté pour le joueur pour le moment (les sprites de chevalier)
     * @param spriteDefaut
     * @param entite
     */
    public AnimationSprite(ImageView spriteDefaut, Entite entite){
        this.sprite = spriteDefaut;
        this.entite = entite;

        entite.getPosition().getXProperty().addListener((obs, old, nouv)->
                sprite.setTranslateX(entite.getPosition().getX()* Constantes.tailleTile-32)
        );
        entite.getPosition().getYProperty().addListener((obs, old, nouv)->
                sprite.setTranslateY(entite.getPosition().getY()*Constantes.tailleTile-64)
        );

        // Paramètres cercle pour visualiser le joueur
        sprite.setTranslateX(entite.getPosition().getX()*Constantes.tailleTile-32);
        sprite.setTranslateY(entite.getPosition().getY()*Constantes.tailleTile-64);

        timeline.setCycleCount(Animation.INDEFINITE);
    }

    public void debutAnimationMarche(){
        timeline.play();
    }

    public void finAnimationMarche(){
        timeline.stop();
        String face = switch (entite.getDirection()){
            case BAS -> "bas";
            case HAUT -> "haut";
            case DROITE -> "droite";
            case GAUCHE -> "gauche";
        };
        this.sprite.setImage(new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/sprite/"+face+1+".png"));
    }

    public ImageView getSprite(){
        return sprite;
    }
}
