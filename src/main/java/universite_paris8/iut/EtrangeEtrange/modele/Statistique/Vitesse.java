package universite_paris8.iut.EtrangeEtrange.modele.Statistique;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import universite_paris8.iut.EtrangeEtrange.modele.Exeptions.StatistiqueInvalideExeption;

public class Vitesse
{
    private DoubleProperty vitesseMaximum;
    private DoubleProperty vitesse;

    public Vitesse(double vitesse)
    {
        this.vitesseMaximum = new SimpleDoubleProperty();
        this.vitesse = new SimpleDoubleProperty();

        setVitesse(vitesse);
        setVitesseMaximum(vitesse);
    }

    public void setVitesse(double vitesse)
    {
        if (vitesse < 0)
            throw new StatistiqueInvalideExeption("vitesse négatif interdis");

        this.vitesse.set(vitesse);
    }

    public void setVitesseMaximum(double vitesseMaximum)
    {
        if (vitesseMaximum < 0)
            throw new StatistiqueInvalideExeption("VitesseMaximum négatif interdis");

        this.vitesseMaximum.set(vitesseMaximum);
    }

    public double getVitesse() {
        return this.vitesse.get();
    }

    public double getVitesseMaximum() {
        return this.vitesseMaximum.get();
    }
}