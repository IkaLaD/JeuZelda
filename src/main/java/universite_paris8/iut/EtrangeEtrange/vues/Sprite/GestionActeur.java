package universite_paris8.iut.EtrangeEtrange.vues.Sprite;

import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import universite_paris8.iut.EtrangeEtrange.modele.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Projectile;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.Constantes;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;


public class GestionActeur implements ListChangeListener<Acteur>
{

    private Pane pane;

    public GestionActeur(Pane pane)
    {
        this.pane = pane;
    }

    @Override
    public void onChanged(Change<? extends Acteur> change) {
        while (change.next()) {
            if (change.wasAdded()) {
                for (Acteur acteur : change.getAddedSubList()) {

                    if (acteur instanceof Projectile) {
                        initSpriteProjectile((Projectile) acteur);
                    }
                }
            } else if (change.wasRemoved()) {
                for (Acteur acteur : change.getRemoved()) {
                    suppSpriteActeur(acteur);
                }
            }
        }
    }

    private void suppSpriteActeur(Acteur acteur)
    {
        Node node = pane.lookup("#"+acteur.getID());

        if (node != null)
            this.pane.getChildren().remove(node);
    }

    private void initSpriteProjectile(Projectile projectile)
    {
        ImageView imageView = new ImageView(new Image("file:src/main/resources/universite_paris8/iut/EtrangeEtrange/texture/objet/Projectile/fleche.png"));
        imageView.setTranslateX(projectile.getPosition().getX()*Constantes.tailleTile-32);
        imageView.setTranslateY(projectile.getPosition().getY()*Constantes.tailleTile-64);

        imageView.setId(projectile.getID()+"");
        Direction direction = projectile.getDirection();

        int rotation;
        if(direction==Direction.HAUT)
            rotation=0;
        else if(direction==Direction.BAS)
            rotation=2;
        else if(direction==Direction.DROITE)
            rotation=1;
        else
            rotation=3;

        imageView.setRotate(rotation*90);
        this.pane.getChildren().add(imageView);

        projectile.getPropertyAtoucherUneCible().addListener((obs, old, nouv)->{
            pane.getChildren().remove(imageView);
        });

        projectile.getPosition().getXProperty().addListener((obs, old, nouv)->
        {
            imageView.setTranslateX(projectile.getPosition().getX()*Constantes.tailleTile-32);
        });
        projectile.getPosition().getYProperty().addListener((obs, old, nouv)->
        {
            imageView.setTranslateY(projectile.getPosition().getY()*Constantes.tailleTile-64);
        });

    }








}
