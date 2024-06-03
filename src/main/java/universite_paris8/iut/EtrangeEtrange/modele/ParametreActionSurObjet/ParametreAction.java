package universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet;

import universite_paris8.iut.EtrangeEtrange.modele.Entite.Entite;

public abstract class ParametreAction
{
    protected Entite origineAction;


    public ParametreAction(Entite origineAction)
    {
        this.origineAction = origineAction;
    }

    public Entite getOrigineAction()
    {
        return this.origineAction;
    }

}
