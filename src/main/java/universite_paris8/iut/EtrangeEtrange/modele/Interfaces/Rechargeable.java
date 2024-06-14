package universite_paris8.iut.EtrangeEtrange.modele.Interfaces;

public interface Rechargeable
{
    public long delaie();

    public void cooldown();

    public void setTourApelle(long tourApelle);

    long getTourApelle();
}
