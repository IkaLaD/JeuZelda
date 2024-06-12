package universite_paris8.iut.EtrangeEtrange.modele.Statistique;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import universite_paris8.iut.EtrangeEtrange.modele.Exeptions.StatistiqueInvalideExeption;

public class Attaque
{
    private DoubleProperty attaqueMaximum;
    private DoubleProperty attaque;

    public Attaque(double attaque)
    {
        this.attaqueMaximum = new SimpleDoubleProperty();
        this.attaque = new SimpleDoubleProperty();

        setAttaque(attaque);
        setAttaqueMaximum(attaque);
    }

    public void setAttaque(double attaque)
    {
        if (attaque < 0)
            throw new StatistiqueInvalideExeption("Attaque négatif interdis");

        this.attaque.set(attaque);
    }

    public void setAttaqueMaximum(double attaqueMaximum)
    {
        if (attaqueMaximum < 0)
            throw new StatistiqueInvalideExeption("Attaque négatif interdis");

        this.attaqueMaximum.set(attaqueMaximum);
    }

    public double getAttaque() {
        return this.attaque.get();
    }

    public double getAttaqueMaximum() {
        return this.attaqueMaximum.get();
    }
}