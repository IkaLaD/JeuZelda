package universite_paris8.iut.EtrangeEtrange.modele.Statistique;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class DefenseSpecial
{
    private DoubleProperty defenseSpecial;
    private DoubleProperty defenseSpecialActuelle;

    public DefenseSpecial(double defenseSpecial)
    {
        this.defenseSpecial = new SimpleDoubleProperty(defenseSpecial);
        this.defenseSpecialActuelle = new SimpleDoubleProperty(defenseSpecial);
    }

    public void setDefenseSpecialActuelle(double defenseActuelle) {
        this.defenseSpecialActuelle.set(defenseActuelle);
    }

    public void setDefenseSpecial(double defense) {
        this.defenseSpecial.set(defense);
    }

    public double getDefenseSpecialActuelle() {
        return this.defenseSpecialActuelle.get();
    }

    public double getDefenseSpecial() {
        return this.defenseSpecial.get();
    }
}
