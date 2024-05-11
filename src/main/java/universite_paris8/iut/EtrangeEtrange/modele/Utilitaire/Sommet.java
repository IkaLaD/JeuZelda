package universite_paris8.iut.EtrangeEtrange.modele.Utilitaire;

public class Sommet
{
    private double x,y;
    private double poids;
    private double heuristique;

    public Sommet(double x, double y,double poids)
    {
        this.x = x;
        this.y = y;
        this.poids = poids;
    }


    public void setHeuristique(double heuristique)
    {
        this.heuristique = heuristique;
    }

    public double getY() {
        return y;
    }

    public double getX()
    {
        return x;
    }


    public double getPoids()
    {
        return this.poids;
    }

    public double getHeuristique()
    {
        return this.heuristique;
    }
}

