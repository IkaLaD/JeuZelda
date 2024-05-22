package universite_paris8.iut.EtrangeEtrange.modele.Statistique;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class DefenseSpecial
{
    private DoubleProperty defenseSpecialMaximum;
    private DoubleProperty defenseSpecial;

    public DefenseSpecial(double defenseSpecial)
    {
        this.defenseSpecialMaximum = new SimpleDoubleProperty(defenseSpecial);
        this.defenseSpecial = new SimpleDoubleProperty(defenseSpecial);
    }

    public void setDefenseSpecial(double defenseActuelle) {
        this.defenseSpecial.set(defenseActuelle);
    }

    public void setDefenseSpecialMaximum(double defense) {
        this.defenseSpecialMaximum.set(defense);
    }

    public double getDefenseSpecial() {
        return this.defenseSpecial.get();
    }

    public double getDefenseSpecialMaximum() {
        return this.defenseSpecialMaximum.get();
    }
}
