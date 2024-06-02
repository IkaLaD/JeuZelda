package universite_paris8.iut.EtrangeEtrange.modele.Interfaces;

public interface DommageableMultiCoup extends Dommageable
{
    public abstract long delaieEntreCoup();

    public abstract void cooldown();
}
