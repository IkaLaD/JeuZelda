package universite_paris8.iut.EtrangeEtrange.vues.Sprite;

import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import universite_paris8.iut.EtrangeEtrange.modele.ActionDegat.ActionDegat;
import universite_paris8.iut.EtrangeEtrange.modele.ActionDegat.ActionDegatParProjectile;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Projectile;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.Constantes;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;


public class GestionCauseDegat implements ListChangeListener<ActionDegat>
{

    private Pane pane;

    public GestionCauseDegat(Pane pane)
    {
        this.pane = pane;
    }

    @Override
    public void onChanged(Change<? extends ActionDegat> change)
    {
        while(change.next())
        {
            for (ActionDegat causeDegat : change.getAddedSubList()) {
                if (causeDegat instanceof ActionDegatParProjectile)
                    initSpriteProjectile((Projectile) causeDegat.getOrgineAttaque());
            }


        }
    }

    private void initSpriteProjectile(Projectile projectile)
    {
        Rectangle rectangle;

        Direction direction = projectile.getDirection();

        if (direction == Direction.BAS || direction == Direction.HAUT)
            rectangle = new Rectangle(projectile.getX(),projectile.getY(),projectile.getLargeur()* Constantes.tailleTile,projectile.getHauteur()*Constantes.tailleTile);
        else
            rectangle = new Rectangle(projectile.getX(),projectile.getY(),projectile.getHauteur()* Constantes.tailleTile,projectile.getLargeur()*Constantes.tailleTile);


        rectangle.setFill(Color.RED);

        rectangle.setTranslateX(projectile.getPosition().getX()*Constantes.tailleTile-32);
        rectangle.setTranslateY(projectile.getPosition().getY()*Constantes.tailleTile-64);

        this.pane.getChildren().add(rectangle);

        projectile.getPropertyAtoucherUneCible().addListener((obs, old, nouv)->{
            pane.getChildren().remove(rectangle);
        });


        projectile.getPosition().getXProperty().addListener((obs, old, nouv)->{
            rectangle.setTranslateX(projectile.getPosition().getX()* Constantes.tailleTile-32);
        });

        projectile.getPosition().getYProperty().addListener((obs, old, nouv)->{
            rectangle.setTranslateY(projectile.getPosition().getY()* Constantes.tailleTile-64);
        });

    }








}
