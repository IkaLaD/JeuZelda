package universite_paris8.iut.EtrangeEtrange.modele.ActionDegat;

import universite_paris8.iut.EtrangeEtrange.modele.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMelee.Epée.Epee;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

public class ActionDegatParEpee extends ActionDegatParEntite
{
    private int cycle;
    private Epee epee;
    public ActionDegatParEpee(EntiteOffensif origineDegat, Epee arme)
    {
        super(origineDegat,arme);
        this.epee = arme;
        this.cycle = 0;
    }

    @Override
    public Position getPosition() {
        return getOrigineDegat().getPosition();
    }
    @Override
    public Hitbox getHitbox() {
        return this.epee.getHitbox();
    }
    @Override
    public void miseAjour(){ if (cycle++ >= 3) getOrigineDegat().getMonde().enleveActionDegat(this);}

}
