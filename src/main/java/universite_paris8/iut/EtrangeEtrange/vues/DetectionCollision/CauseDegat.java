package universite_paris8.iut.EtrangeEtrange.vues.DetectionCollision;

import universite_paris8.iut.EtrangeEtrange.modele.Entite.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Surface;

public abstract class CauseDegat
{
    private Dommageable orgineDegat;


    public CauseDegat(Dommageable origineDegat)
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
}
