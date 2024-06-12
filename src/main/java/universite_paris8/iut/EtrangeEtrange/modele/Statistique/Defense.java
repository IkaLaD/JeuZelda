package universite_paris8.iut.EtrangeEtrange.modele.Statistique;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import universite_paris8.iut.EtrangeEtrange.modele.Exeptions.StatistiqueInvalideExeption;

public class Defense
{
    private DoubleProperty defenseMaximum;
    private DoubleProperty defense;

    public Defense(double defense)
    {
        this.defenseMaximum = new SimpleDoubleProperty();
        this.defense = new SimpleDoubleProperty();

        setDefense(defense);
        setDefenseMaximum(defense);
    }

    public void setDefense(double defenseActuelle)
    {
        if (defenseActuelle < 0)
            throw new StatistiqueInvalideExeption("Defense négatif interdis");

        this.defense.set(defenseActuelle);
    }

    public void setDefenseMaximum(double defenseMaximum)
    {
        if (defenseMaximum < 0)
            throw new StatistiqueInvalideExeption("Defense négatif interdis");

        this.defenseMaximum.set(defenseMaximum);
    }

    public double getDefense() {
        return this.defense.get();
    }

    public double getDefenseMaximum() {
        return this.defenseMaximum.get();
    }
}