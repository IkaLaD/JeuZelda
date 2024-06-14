package universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Fleche;

import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;

public class FlecheSimple extends Fleche
{
    public FlecheSimple()
    {
        super();
    }

    @Override
    public double degatPhysique() {
        return 10;
    }

    @Override
    public double degatSpecial() {
        return 0;
    }


    @Override
    public String getNom() {
        return "Fleche";
    }
    @Override
    public String typeActeur(){
        return "fleche";
    }

    @Override
    public boolean peutSeDeplacer() {
        return true;
    }

    @Override
    public void dropApresMort() {

    }

    @Override
    public void subitDegat(Dommageable causeDegat) {

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


}
