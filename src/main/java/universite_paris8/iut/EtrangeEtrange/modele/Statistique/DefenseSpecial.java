package universite_paris8.iut.EtrangeEtrange.modele.Statistique;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import universite_paris8.iut.EtrangeEtrange.modele.Exeptions.StatistiqueInvalideExeption;

public class DefenseSpecial
{
    private DoubleProperty defenseSpecialMaximum;
    private DoubleProperty defenseSpecial;

    public DefenseSpecial(double defenseSpecial)
    {
        this.defenseSpecialMaximum = new SimpleDoubleProperty();
        this.defenseSpecial = new SimpleDoubleProperty();

        setDefenseSpecial(defenseSpecial);
        setDefenseSpecialMaximum(defenseSpecial);
    }

    public void setDefenseSpecial(double defenseActuelle)
    {
        if (defenseActuelle < 0)
            throw new StatistiqueInvalideExeption("Defense négatif interdis");

        this.defenseSpecial.set(defenseActuelle);
    }

    public void setDefenseSpecialMaximum(double defense)
    {
        if (defense < 0)
            throw new StatistiqueInvalideExeption("Defense négatif interdis");

        this.defenseSpecialMaximum.set(defense);
    }

    public double getDefenseSpecial() {
        return this.defenseSpecial.get();
    }

    public double getDefenseSpecialMaximum() {
        return this.defenseSpecialMaximum.get();
    }
}