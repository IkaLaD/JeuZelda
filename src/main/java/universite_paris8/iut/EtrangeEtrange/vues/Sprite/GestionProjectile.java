package universite_paris8.iut.EtrangeEtrange.vues.Sprite;

import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Projectile;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.Constantes;

public class GestionProjectile implements ListChangeListener<Projectile>
{

    private Pane pane;

    public GestionProjectile(Pane pane)
    {
        this.pane = pane;
    }

    @Override
    public void onChanged(Change<? extends Projectile> change)
    {
        while(change.next())
        {
            for (Projectile projectile : change.getAddedSubList())
                initSpriteProjectile(projectile);


        }
    }

    private void initSpriteProjectile(Projectile projectile)
    {

        Rectangle rectangle = new Rectangle(projectile.getX(),projectile.getY(),projectile.getLargeur()* Constantes.tailleTile,projectile.getHauteur()*Constantes.tailleTile);
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
