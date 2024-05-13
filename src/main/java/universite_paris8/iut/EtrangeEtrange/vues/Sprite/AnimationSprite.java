package universite_paris8.iut.EtrangeEtrange.vues.Sprite;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.Constantes;

public class AnimationSprite {
    private Entite entite;
    private String skin;
    private ImageView SpriteEntite;
    private Rectangle SpriteVie;
    private int id;
    int image;

    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.15), event -> {
        String face = switch (entite.getDirection()){
            case BAS -> "bas";
            case HAUT -> "haut";
            case DROITE -> "droite";
            case GAUCHE -> "gauche";
        };
        SpriteEntite.setImage(new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/sprite/"+ skin +"/"+face+image+".png"));
        miseAJourAnimation();

    }));

    /**
     * La class est uniquement adapté pour le joueur pour le moment (les sprites de chevalier)
     */
    public AnimationSprite(Entite entite, String skin){
        this.image = 1;
        this.SpriteEntite = new ImageView("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/sprite/"+skin+"/bas1.png");
        this.entite = entite;
        this.skin = skin;
        this.id = entite.getId();
        this.SpriteVie = new Rectangle();

        // On lie le sprite et l'entité par un même identifiant
        this.SpriteEntite.setId(entite.getId()+"");

        entite.getPosition().getXProperty().addListener((obs, old, nouv)->
                SpriteEntite.setTranslateX(entite.getPosition().getX()* Constantes.tailleTile-32)
        );
        entite.getPosition().getYProperty().addListener((obs, old, nouv)->
                SpriteEntite.setTranslateY(entite.getPosition().getY()*Constantes.tailleTile-64)
        );

        // Paramètres cercle pour visualiser le joueur
        SpriteEntite.setTranslateX(entite.getPosition().getX()*Constantes.tailleTile-32);
        SpriteEntite.setTranslateY(entite.getPosition().getY()*Constantes.tailleTile-64);

        timeline.setCycleCount(Animation.INDEFINITE);
    }

    public void debutAnimationMarche(){
        timeline.play();
    }

    public Rectangle ajoutBarrePv(){
        // Listener pour que la couleur et la taille de la barre change
        entite.getPv().getPvActuelleProperty().addListener((obs, old, nouv) -> {
            SpriteVie.setWidth(Constantes.tailleTile * (entite.getPv().getPvActuelle() / entite.getPv().getPv()));
            if(entite.getPv().getPvActuelle() / entite.getPv().getPv() > 0.70)
                SpriteVie.setFill(Color.GREEN);
            else if(entite.getPv().getPvActuelle() / entite.getPv().getPv() > 0.30)
                SpriteVie.setFill(Color.YELLOW);
            else
                SpriteVie.setFill(Color.RED);
        });

        // Listener pour que la barre d'hp suivent l'entité
        entite.getPosition().getXProperty().addListener((obs, old, nouv)->
                SpriteVie.setTranslateX(entite.getPosition().getX()* Constantes.tailleTile-32)
        );
        entite.getPosition().getYProperty().addListener((obs, old, nouv)->
                SpriteVie.setTranslateY(entite.getPosition().getY()*Constantes.tailleTile-48)
        );


        // On execute une première fois le code du listener pour qu'il sapplique dès le début du lancement du jeu
        SpriteVie.setTranslateX(entite.getPosition().getX()* Constantes.tailleTile-64);
        SpriteVie.setTranslateY(entite.getPosition().getY()*Constantes.tailleTile-96);
        SpriteVie.setHeight(5);
        SpriteVie.setWidth(Constantes.tailleTile * (entite.getPv().getPvActuelle() / entite.getPv().getPv()));

        if(entite.getPv().getPvActuelle() / entite.getPv().getPv() > 2/3)
            SpriteVie.setFill(Color.GREEN);
        else if(entite.getPv().getPvActuelle() / entite.getPv().getPv() > 1/3)
            SpriteVie.setFill(Color.YELLOW);
        else
            SpriteVie.setFill(Color.RED);

        return SpriteVie;
    }

    public void miseAJourAnimation(){
        if(image==6)
            image=2;
        else
            image++;
    }

    public void finAnimationMarche(){
        timeline.stop();
        image=1;
        String face = switch (entite.getDirection()){
            case BAS -> "bas";
            case HAUT -> "haut";
            case DROITE -> "droite";
            case GAUCHE -> "gauche";
        };
        this.SpriteEntite.setImage(new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/sprite/"+ skin +"/"+face+1+".png"));
    }

    public ImageView getSpriteEntite(){
        return SpriteEntite;
    }

    public Rectangle getSpriteVie(){
        return SpriteVie;
    }

    public int getId(){
        return this.id;
    }
}
