package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Epée;

import universite_paris8.iut.EtrangeEtrange.modele.Entite.Hitbox;
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

    public void utilise()
    {

    }


    @Override
    public int stackMax() {
        return 1;
    }
}
