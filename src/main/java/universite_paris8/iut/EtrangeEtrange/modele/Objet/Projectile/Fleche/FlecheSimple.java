package universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Fleche;

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
    public long delaieEntreCoup() {
        return 0;
    }



    @Override
    public void cooldown() {

    }

    @Override
    public String getNom() {
        return "Fleche";
    }
}
