package universite_paris8.iut.EtrangeEtrange.modele.Interfaces;

public interface Dommageable
{
    public abstract double degatPhysique();
    public abstract double degatSpecial();
    public abstract double portee();
    public abstract double angle();
    public abstract long delaieEntreCoup();

    public abstract void cooldown();

}
