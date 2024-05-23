package universite_paris8.iut.EtrangeEtrange.modele.Utilitaire;

public class Sommet {
    private int x;
    private int y;
    private double f;

    public Sommet(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getF() {
        return f;
    }

    public void setF(double f) {
        this.f = f;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Sommet sommet = (Sommet) obj;
        return x == sommet.x && y == sommet.y;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }
}
