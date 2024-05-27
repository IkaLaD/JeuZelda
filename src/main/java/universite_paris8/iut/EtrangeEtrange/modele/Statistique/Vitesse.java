package universite_paris8.iut.EtrangeEtrange.modele.Statistique;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Vitesse
{
    private DoubleProperty vitesseMaximum;
    private DoubleProperty vitesse;

    public Vitesse(double vitesse)
    {
        this.vitesseMaximum = new SimpleDoubleProperty(vitesse);
        this.vitesse = new SimpleDoubleProperty(vitesse);
    }

    public void setVitesse(double pvActuelle) {
        this.vitesse.set(pvActuelle);
    }

    public void setVitesseMaximum(double pvMax) {
        this.vitesseMaximum.set(pvMax);
    }

    public double getVitesse() {
        return this.vitesse.get();
    }

    public double getVitesseMaximum() {
        return this.vitesseMaximum.get();
    }
}
