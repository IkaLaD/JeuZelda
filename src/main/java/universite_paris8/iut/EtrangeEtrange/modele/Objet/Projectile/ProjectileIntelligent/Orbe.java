package universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.ProjectileIntelligent;

import universite_paris8.iut.EtrangeEtrange.modele.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Projectile;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;

public class Orbe extends Projectile {
    public Orbe() {
        super(1,new Hitbox(0.2,0.2));
    }



    @Override
    public void subitCollision(Acteur acteur) {

    }

    @Override
    public String typeActeur() {
        return null;
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
    public String getNom() {
        return null;
    }

    @Override
    public int stackMax() {
        return 0;
    }

    @Override
    public boolean peutSeDeplacer() {
        return false;
    }

    @Override
    public void unTour()
    {

    }
}