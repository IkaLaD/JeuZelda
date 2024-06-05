package universite_paris8.iut.EtrangeEtrange.modele.ActionDegat;

import universite_paris8.iut.EtrangeEtrange.modele.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Surface;

public abstract class ActionDegat
{
    private Dommageable orgineDegat;


    public ActionDegat(Dommageable origineDegat)
    {
        this.orgineDegat = origineDegat;
    }

    public Dommageable getOrgineAttaque() {
        return this.orgineDegat;
    }

    public abstract Position getPosition();

    public abstract Hitbox getHitbox();

    public Surface surfaceDegat()
    {
        return new Surface(getPosition(),getHitbox());
    }

    public abstract void miseAjour();


    public void executeAction(Entite entite) {
        entite.subitDegat(this);

    }
}
