package universite_paris8.iut.EtrangeEtrange.modele.Statistique;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Vitesse
{
    private DoubleProperty vitesse;
    private DoubleProperty vitesseActuelle;

    public Vitesse(double vitesse)
    {
        this.vitesse = new SimpleDoubleProperty(vitesse);
        this.vitesseActuelle = new SimpleDoubleProperty(vitesse);
    }

    public void setVitesseActuelle(double pvActuelle) {
        this.vitesseActuelle.set(pvActuelle);
    }

    public void setVitesse(double pvMax) {
        this.vitesse.set(pvMax);
    }

    public double getVitesseActuelle() {
        return this.vitesseActuelle.get();
    }

    public double getVitesse() {
        return this.vitesse.get();
    }
}
