package universite_paris8.iut.EtrangeEtrange.vues.Sprite;

import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import universite_paris8.iut.EtrangeEtrange.modele.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Projectile;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.Constantes;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;


public class GestionActionDegat implements ListChangeListener<Acteur>
{

    private Pane pane;

    public GestionActionDegat(Pane pane)
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
        Rectangle rectangle;

        Direction direction = projectile.getDirection();
        Position pos = projectile.getPosition();
        Hitbox hitbox = projectile.getHitbox();

        if (direction == Direction.BAS || direction == Direction.HAUT)
            rectangle = new Rectangle(pos.getX(),pos.getY(),hitbox.getLargeur()* Constantes.tailleTile,hitbox.getHauteur()*Constantes.tailleTile);
        else
            rectangle = new Rectangle(pos.getX(),pos.getY(),hitbox.getHauteur()* Constantes.tailleTile,hitbox.getLargeur()*Constantes.tailleTile);


        rectangle.setFill(Color.RED);

        rectangle.setTranslateX(projectile.getPosition().getX()*Constantes.tailleTile-32);
        rectangle.setTranslateY(projectile.getPosition().getY()*Constantes.tailleTile-64);

        rectangle.setId(projectile.getID());
        this.pane.getChildren().add(rectangle);



        projectile.getPosition().getXProperty().addListener((obs, old, nouv)->{
            rectangle.setTranslateX(projectile.getPosition().getX()* Constantes.tailleTile-32);
        });

        projectile.getPosition().getYProperty().addListener((obs, old, nouv)->{
            rectangle.setTranslateY(projectile.getPosition().getY()* Constantes.tailleTile-64);
        });

    }








}
