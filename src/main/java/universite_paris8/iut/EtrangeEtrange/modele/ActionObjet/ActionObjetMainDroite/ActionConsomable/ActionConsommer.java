package universite_paris8.iut.EtrangeEtrange.modele.ActionObjet.ActionObjetMainDroite.ActionConsomable;

import universite_paris8.iut.EtrangeEtrange.modele.ActionObjet.ActionSurObjet;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Entite;

public abstract class ActionConsommer extends ActionSurObjet {
    public ActionConsommer(Entite origineAction) {
        super(origineAction);
    }

    @Override
    public void action() {
        consommer();
    }

    protected abstract void consommer();
}
