package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMelee.Epée;

import universite_paris8.iut.EtrangeEtrange.modele.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Arme;
import universite_paris8.iut.EtrangeEtrange.modele.GestionDegat.DegatParEpee;

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

    public void coupDepee(EntiteOffensif attaquant)
    {
        attaquant.getMonde().ajoutCauseDegat(new DegatParEpee(attaquant,this));
    }


    @Override
    public int stackMax() {
        return 1;
    }
}
