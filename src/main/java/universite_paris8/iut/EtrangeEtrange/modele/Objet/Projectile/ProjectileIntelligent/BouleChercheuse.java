package universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.ProjectileIntelligent;

import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Projectile;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;

public class BouleChercheuse extends Projectile {
    public BouleChercheuse() {
        super(new Hitbox(0.2,0.2));
    }

    @Override
    public double degatPhysique() {
        return 0;
    }

    @Override
    public double degatSpecial() {
        return 0;
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
    public long delaieEntreCoup() {
        return 0;
    }

    @Override
    public void cooldown() {

    }

    @Override
    public String getNom() {
        return null;
    }

    @Override
    public int stackMax() {
        return 0;
    }
}
