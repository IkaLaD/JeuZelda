package universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreActionMainDroite.ParametreCompetence;

import universite_paris8.iut.EtrangeEtrange.modele.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.PNJ;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreAction;

import java.util.ArrayList;

public class ParametreInvocation extends ParametreAction
{
    private Class<? extends PNJ> typePnj;
    private int nombreInstance;
    public ParametreInvocation(Entite origineAction,Class<? extends PNJ> typePnj,int nombreInstance) {
        super(origineAction);
        this.typePnj = typePnj;
        this.nombreInstance = nombreInstance;

    }

    public Class<? extends PNJ> getTypePnj()
    {
        return this.typePnj;
    }

    public int getNombreInstance()
    {
        return this.nombreInstance;
    }

}
