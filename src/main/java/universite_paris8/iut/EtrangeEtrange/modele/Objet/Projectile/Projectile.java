package universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Objet;
import universite_paris8.iut.EtrangeEtrange.modele.Statistique.Vitesse;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

public  abstract class Projectile extends Objet implements Dommageable
{
    private Monde monde;
    private Hitbox hitbox;
    private Position positionOrigine;
    private Position position;
    private Vitesse vitesse;

    private Direction direction;

    private BooleanProperty aToucherUneCible;

    public Projectile(Hitbox hitbox)
    {
        this.aToucherUneCible = new SimpleBooleanProperty(false);

        this.hitbox = hitbox;

        this.vitesse = new Vitesse(1);
        this.direction = null;
    }

     public void setPositionOrigine(Position position)
     {
         this.positionOrigine = position;
         this.position = new Position(positionOrigine.getX(),positionOrigine.getY());
     }

    public void setVitesse(double vitesse)
    {
        this.vitesse.setVitesse(vitesse);
    }

    public void setDirection(Direction direction)
    {
        this.direction = direction;
    }

    public void seDeplace()
    {
        int x = direction.getX();
        int y = direction.getY();

        position.setX(position.getX() + x * vitesse.getVitesse());
        position.setY(position.getY() + y * vitesse.getVitesse());
    }

    public Hitbox getHitbox()
    {
        return this.hitbox;
    }
    public final BooleanProperty getPropertyAtoucherUneCible()
    {
        return this.aToucherUneCible;
    }

    public boolean aToucherUneCible()
    {
        return this.aToucherUneCible.get();
    }

    public void toucher()
    {
        this.aToucherUneCible.set(true);
    }
    public double getX()
    {
        return this.position.getX();
    }

    public double getY()
    {
        return this.position.getY();
    }

    public double getLargeur()
    {
        return this.hitbox.getLargeur();
    }

    public double getHauteur()
    {
        return this.hitbox.getHauteur();
    }

    public Direction getDirection()
    {
        return this.direction;
    }

    public final Position getPosition() {
        return this.position;
    }
}
