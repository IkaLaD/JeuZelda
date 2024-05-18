package universite_paris8.iut.EtrangeEtrange.modele.ActionObjet;

import universite_paris8.iut.EtrangeEtrange.modele.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Objet;

public abstract class ActionSurObjet
{
    private Entite origineAction;


    public ActionSurObjet(Entite origineAction)
    {
        this.origineAction = origineAction;

    }

    public Entite getOrigineAction()
    {
        return this.origineAction;
    }
}
