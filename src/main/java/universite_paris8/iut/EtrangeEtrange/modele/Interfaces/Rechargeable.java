package universite_paris8.iut.EtrangeEtrange.modele.Interfaces;

public interface Rechargeable
{
    /**
     * Delaie de cooldown
     * @return
     */
    public long delaie();

    /**
     * Permet de gérer les cooldown de tous les objets qui en ont
     * @return
     */
    public boolean cooldown();

}
