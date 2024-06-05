package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMelee.Epée;

import universite_paris8.iut.EtrangeEtrange.modele.ActionDegat.ActionDegatParEpee;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.DommageableMultiCoup;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreAction;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreAttaque.ActionAttaqueMelee.ParametreAttaqueEpee;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Arme;

public abstract class Epee extends Arme implements DommageableMultiCoup
{
    private Hitbox hitbox;

    public Epee(Hitbox hitbox)
    {
        this.hitbox = hitbox;
    }

    public Hitbox getHitbox()
    {
        return this.hitbox;
    }


    @Override
    public void utilise(ParametreAction param)
    {
        if (param instanceof ParametreAttaqueEpee)
        {
            ParametreAttaqueEpee paramEpee = (ParametreAttaqueEpee) param;
            paramEpee.getOrigineAction().getMonde().ajoutActionDegat(new ActionDegatParEpee(paramEpee.getOrigineAction(),this));
        }
    }



    @Override
    public int stackMax() {
        return 1;
    }
}
