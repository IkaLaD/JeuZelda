package universite_paris8.iut.EtrangeEtrange.vues.Sprite;

import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Projectile;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.Constantes;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.GestionDegat.CauseDegat;
import universite_paris8.iut.EtrangeEtrange.modele.GestionDegat.DegatParProjectile;

public class GestionCauseDegat implements ListChangeListener<CauseDegat>
{

    private Pane pane;

    public GestionCauseDegat(Pane pane)
    {
        this.pane = pane;
    }

    @Override
    public void onChanged(Change<? extends CauseDegat> change)
    {
        while(change.next())
        {
            for (CauseDegat causeDegat : change.getAddedSubList()) {
                if (causeDegat instanceof DegatParProjectile)
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
