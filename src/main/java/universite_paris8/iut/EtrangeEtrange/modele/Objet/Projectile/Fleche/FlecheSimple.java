package universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Fleche;

import universite_paris8.iut.EtrangeEtrange.modele.Acteur;

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
    public void unTour() {

    }

    @Override
    public void subitCollision(Acteur acteur) {

    }
}
