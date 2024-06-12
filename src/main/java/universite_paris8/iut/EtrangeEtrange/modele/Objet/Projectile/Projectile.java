package universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import universite_paris8.iut.EtrangeEtrange.modele.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Objet;

public abstract class Projectile extends Acteur implements Dommageable,Objet
{
    private BooleanProperty aToucherUneCible;

    public Projectile(double vitesse,Hitbox hitbox)
    {
        super(1,vitesse,hitbox);
        aToucherUneCible = new SimpleBooleanProperty(false);
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


}
