package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMelee.Epée;

import universite_paris8.iut.EtrangeEtrange.modele.ActionDegat.ActionDegatParEpee;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreActionMainDroite.ParametreActionAttaque.ActionAttaqueMelee.ParametreActionAttaqueEpee;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreActionMainDroite.ParametreActionAttaque.ParametreActionAttaque;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreActionObjet;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Arme;

public abstract class Epee extends Arme implements Dommageable
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
    public void utilise(ParametreActionObjet param)
    {
        if (param instanceof ParametreActionAttaqueEpee)
            attaque((ParametreActionAttaque) param);
    }

    @Override
    public void attaque(ParametreActionAttaque param)
    {
        ParametreActionAttaqueEpee paramEpee = (ParametreActionAttaqueEpee) param;

        paramEpee.getOrigineAction().getMonde().ajoutActionDegat(new ActionDegatParEpee(paramEpee.getOrigineAction(),this));
    }

    @Override
    public int stackMax() {
        return 1;
    }
}
