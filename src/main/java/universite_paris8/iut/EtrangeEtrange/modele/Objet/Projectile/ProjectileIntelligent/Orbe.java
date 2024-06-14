package universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.ProjectileIntelligent;

import universite_paris8.iut.EtrangeEtrange.modele.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Projectile;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;

public class Orbe extends Projectile
{
    public Orbe() {
        super(1,new Hitbox(0.2,0.2));
    }



    @Override
    public void subitCollision(Acteur acteur) {

    }

    @Override
    public void causeCollision(Acteur acteur) {

    }

    @Override
    public void subitAttaque(Dommageable causeDegat) {

    }


    @Override
    protected double subitDegatPhysique(double attaqueEntite, double degatArme) {
        return 0;
    }

    @Override
    protected double subitDegatSpecial(double attaqueSpecialEntite, double degatArme) {
        return 0;
    }

    @Override
    public String typeActeur() {
        return null;
    }

    @Override
    public void seFaitPousser(Acteur acteur) {

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
    public String getNom() {
        return "orbe";
    }

    @Override
    public int stackMax() {
        return 0;
    }

    @Override
    public double durabilitee() {
        return 0;
    }

    @Override
    public boolean peutSeDeplacer() {
        return false;
    }

    @Override
    public void dropApresMort() {

    }

    @Override
    public void unTour()
    {

    }
}
