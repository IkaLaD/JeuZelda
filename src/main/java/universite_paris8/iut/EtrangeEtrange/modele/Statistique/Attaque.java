package universite_paris8.iut.EtrangeEtrange.modele.Statistique;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Attaque
{
    private DoubleProperty attaque;
    private DoubleProperty attaqueActuelle;

    public Attaque(double attaque)
    {
        this.attaque = new SimpleDoubleProperty(attaque);
        this.attaqueActuelle = new SimpleDoubleProperty(attaque);
    }

    public void setAttaqueActuelle(double attaqueActuelle) {
        this.attaqueActuelle.set(attaqueActuelle);
    }

    public void setAttaque(double attaque) {
        this.attaque.set(attaque);
    }

    public double getAttaqueActuelle() {
        return this.attaqueActuelle.get();
    }

    public double getAttaque() {
        return this.attaque.get();
    }
}
