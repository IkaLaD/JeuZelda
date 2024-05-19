package universite_paris8.iut.EtrangeEtrange.modele.GestionDegat;

import universite_paris8.iut.EtrangeEtrange.modele.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMelee.Epée.Epee;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

public class DegatParEpee extends  DegatParEntite
{

    public DegatParEpee(EntiteOffensif origineDegat, Epee arme)
    {
        super(origineDegat,arme);
    }


    @Override
    public Position getPosition() {
        return getOrigineDegat().getPosition();
    }

    @Override
    public Hitbox getHitbox() {
        Epee orgineAttaque = (Epee) getOrgineAttaque();
        return orgineAttaque.getHitbox();
    }

    @Override
    public void miseAjour() {
        getOrigineDegat().getMonde().enleveCauseDegat(this);
    }


}
