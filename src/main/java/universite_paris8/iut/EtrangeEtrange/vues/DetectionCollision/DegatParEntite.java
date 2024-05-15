package universite_paris8.iut.EtrangeEtrange.vues.DetectionCollision;

import universite_paris8.iut.EtrangeEtrange.modele.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;

public abstract class DegatParEntite extends CauseDegat
{
    private EntiteOffensif origineAttaque;

    public DegatParEntite(EntiteOffensif origineAttaque, Dommageable orgineDegat)
    {
        super(orgineDegat);
        this.origineAttaque = origineAttaque;
    }

    public EntiteOffensif getOrigineDegat()
    {
        return this.origineAttaque;
    }
}
