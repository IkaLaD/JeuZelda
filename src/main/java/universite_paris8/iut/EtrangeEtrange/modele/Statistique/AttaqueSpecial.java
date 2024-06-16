package universite_paris8.iut.EtrangeEtrange.modele.Statistique;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import universite_paris8.iut.EtrangeEtrange.modele.Exeptions.StatistiqueInvalideExeption;

public class AttaqueSpecial
{
    private DoubleProperty attaqueSpecialMaximum;
    private DoubleProperty attaqueSpecial;

    public AttaqueSpecial(double attaqueSpecial)
    {
        this.attaqueSpecialMaximum = new SimpleDoubleProperty();
        this.attaqueSpecial = new SimpleDoubleProperty();

        setAttaqueSpecial(attaqueSpecial);
        setAttaqueSpecialMaximum(attaqueSpecial);
    }

    public void setAttaqueSpecial(double attaqueSpecialActuelle)
    {
        if (attaqueSpecialActuelle < 0)
            throw new StatistiqueInvalideExeption("Attaque spécial négatif interdis");

        this.attaqueSpecial.set(attaqueSpecialActuelle);
    }

    public void setAttaqueSpecialMaximum(double attaqueSpecialMaximum)
    {
        if (attaqueSpecialMaximum < 0)
            throw new StatistiqueInvalideExeption("Attaque spécial négatif interdis");

        this.attaqueSpecialMaximum.set(attaqueSpecialMaximum);
    }

    public double getAttaqueSpecial() {
        return this.attaqueSpecial.get();
    }

    public double getAttaqueSpecialMaximum() {
        return this.attaqueSpecialMaximum.get();
    }
}