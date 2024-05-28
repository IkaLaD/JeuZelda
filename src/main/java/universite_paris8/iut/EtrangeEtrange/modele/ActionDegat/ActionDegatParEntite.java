package universite_paris8.iut.EtrangeEtrange.modele.ActionDegat;

import universite_paris8.iut.EtrangeEtrange.modele.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;

public abstract class ActionDegatParEntite extends ActionDegat
{
    private EntiteOffensif origineAttaque;

    public ActionDegatParEntite(EntiteOffensif origineAttaque, Dommageable orgineDegat)
    {
        super(orgineDegat);
        this.origineAttaque = origineAttaque;
    }

    public EntiteOffensif getOrigineDegat()
    {
        return this.origineAttaque;
    }
}
