package universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Fleche;

import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Projectile;

public abstract class Fleche extends Projectile
{


    public Fleche() {
        super(new Hitbox(0.5,0.2));
    }

    @Override
    public double portee() {
        return 0;
    }

    @Override
    public double angle() {
        return 0;
    }

    @Override
    public int stackMax() {
        return 128;
    }
}
