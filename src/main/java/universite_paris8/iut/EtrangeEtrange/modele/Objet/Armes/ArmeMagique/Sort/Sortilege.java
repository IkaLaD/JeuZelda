package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort;

import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Rechargeable;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Utilisable;

public abstract class Sortilege implements Utilisable, Rechargeable
{
    protected long derniereApelle;
    protected boolean peutLancerSort;

    public Sortilege()
    {
        this.derniereApelle = 0;
        this.peutLancerSort = true;
    }

    @Override
    public boolean cooldown()
    {
        boolean actionFait = false;
        long apelle = System.currentTimeMillis();

        if (apelle - derniereApelle >= delaie())
        {
            this.derniereApelle = -1;
            this.peutLancerSort = true;
            actionFait = true;
        }

        return actionFait;
    }
}
