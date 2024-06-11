package universite_paris8.iut.EtrangeEtrange.vues.Sprite;

import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import universite_paris8.iut.EtrangeEtrange.modele.ActionDegat.ActionDegat;
import universite_paris8.iut.EtrangeEtrange.modele.ActionDegat.ActionDegatParEpee;
import universite_paris8.iut.EtrangeEtrange.modele.ActionDegat.ActionDegatParProjectile;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Projectile;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.Constantes;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;

import java.io.File;


public class GestionActionDegat implements ListChangeListener<ActionDegat>
{

    private Pane pane;

    public GestionActionDegat(Pane pane)
    {
        this.pane = pane;
    }

    @Override
    public void onChanged(Change<? extends ActionDegat> change)
    {
        while(change.next())
        {
            for (ActionDegat causeDegat : change.getAddedSubList()) {

                if (causeDegat instanceof ActionDegatParProjectile) {
                    AudioClip mediaPlayer = new AudioClip(new File("src/main/resources/universite_paris8/iut/EtrangeEtrange/sons/fleche.mp3").toURI().toString());
                    mediaPlayer.play();
                    initSpriteProjectile((Projectile) causeDegat.getOrgineAttaque());
                }
                if(causeDegat instanceof ActionDegatParEpee){
                    AudioClip mediaPlayer = new AudioClip(new File("src/main/resources/universite_paris8/iut/EtrangeEtrange/sons/epee.mp3").toURI().toString());
                    mediaPlayer.play();
                    System.out.println("test");
                }
            }
        }
    }

    private void initSpriteProjectile(Projectile projectile)
    {
        ImageView image = new ImageView(new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/objet/Projectile/fleche.png"));
        image.setTranslateX(projectile.getX()*Constantes.tailleTile-32);
        image.setTranslateY(projectile.getY()*Constantes.tailleTile-64);

        Direction direction = projectile.getDirection();

        int rotation;
        if(direction==Direction.HAUT)
            rotation = 0;
        else if (direction==Direction.BAS)
            rotation = 2;
        else if(direction==Direction.DROITE)
            rotation = 1;
        else
            rotation = 3;

        image.setRotate(rotation*90);
        this.pane.getChildren().add(image);

        projectile.getPropertyAtoucherUneCible().addListener((obs, old, nouv)->{
            pane.getChildren().remove(image);
        });


        projectile.getPosition().getXProperty().addListener((obs, old, nouv)->{
            image.setTranslateX(projectile.getPosition().getX()* Constantes.tailleTile-32);
        });

        projectile.getPosition().getYProperty().addListener((obs, old, nouv)->{
            image.setTranslateY(projectile.getPosition().getY()* Constantes.tailleTile-64);
        });

    }








}
