package universite_paris8.iut.EtrangeEtrange.modele.Statistique;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Vitesse {
    private DoubleProperty vitesse;
    private DoubleProperty vitesseActuelle;

    public Vitesse(double vitesse) {
        this.vitesse = new SimpleDoubleProperty(vitesse);
        this.vitesseActuelle = new SimpleDoubleProperty(vitesse);
    }

    public double getVitesse() {
        return vitesse.get();
    }

    public void setVitesse(double vitesse) {
        this.vitesse.set(vitesse);
    }

    public DoubleProperty vitesseProperty() {
        return vitesse;
    }

    public double getVitesseActuelle() {
        return vitesseActuelle.get();
    }

    public void setVitesseActuelle(double vitesseActuelle) {
        this.vitesseActuelle.set(vitesseActuelle);
    }

    public DoubleProperty vitesseActuelleProperty() {
        return vitesseActuelle;
    }
}
