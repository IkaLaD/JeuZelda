package universite_paris8.iut.EtrangeEtrange.modele.Statistique;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Attaque
{
    private DoubleProperty attaqueMaximum;
    private DoubleProperty attaque;

    public Attaque(double attaque)
    {
        this.attaqueMaximum = new SimpleDoubleProperty(attaque);
        this.attaque = new SimpleDoubleProperty(attaque);
    }

    public void setAttaque(double attaque) {
        this.attaque.set(attaque);
    }

    public void setAttaqueMaximum(double attaqueMaximum) {
        this.attaqueMaximum.set(attaqueMaximum);
    }

    public double getAttaque() {
        return this.attaque.get();
    }

    public double getAttaqueMaximum() {
        return this.attaqueMaximum.get();
    }
}
