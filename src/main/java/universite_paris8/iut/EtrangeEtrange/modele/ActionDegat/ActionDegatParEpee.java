package universite_paris8.iut.EtrangeEtrange.modele.ActionDegat;

import universite_paris8.iut.EtrangeEtrange.modele.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMelee.Epée.Epee;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

public class ActionDegatParEpee extends ActionDegatParEntite
{
    private Epee epee;
    public ActionDegatParEpee(EntiteOffensif origineDegat, Epee arme)
    {
        super(origineDegat,arme);
        this.epee = arme;
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
    public void miseAjour() {
        getOrigineDegat().getMonde().enleveActionDegat(this);
    }




}
