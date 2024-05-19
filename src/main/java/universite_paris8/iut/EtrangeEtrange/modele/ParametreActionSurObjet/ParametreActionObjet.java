package universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet;

import universite_paris8.iut.EtrangeEtrange.modele.Entite.Entite;

public abstract class ParametreActionObjet
{
    protected Entite origineAction;


    public ParametreActionObjet(Entite origineAction)
    {
        this.origineAction = origineAction;
    }

    public Entite getOrigineAction()
    {
        return this.origineAction;
    }

}
