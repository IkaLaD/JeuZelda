package universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import universite_paris8.iut.EtrangeEtrange.modele.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Objet;
import universite_paris8.iut.EtrangeEtrange.modele.Statistique.Vitesse;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

public abstract class Projectile extends Acteur implements Dommageable,Objet
{

    private BooleanProperty aToucherUneCible;

    public Projectile(Hitbox hitbox)
    {
        super(hitbox);
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
