package universite_paris8.iut.EtrangeEtrange.modele.Compétence;

public abstract class Competence
{
    private boolean estDebloquer;

    public Competence()
    {
        estDebloquer = false;
    }

    public boolean estDebloquer()
    {
        return this.estDebloquer;
    }

    public void debloquer()
    {
        this.estDebloquer = true;
        activeEffet();
    }

    protected abstract void activeEffet();
}
