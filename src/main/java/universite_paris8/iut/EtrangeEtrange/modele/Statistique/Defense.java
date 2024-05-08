package universite_paris8.iut.EtrangeEtrange.modele.Statistique;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Defense
{
    private DoubleProperty defense;
    private DoubleProperty defenseActuelle;

    public Defense(double defense)
    {
        this.defense = new SimpleDoubleProperty(defense);
        this.defenseActuelle = new SimpleDoubleProperty(defense);
    }

    public void setDefenseActuelle(double defenseActuelle) {
        this.defenseActuelle.set(defenseActuelle);
    }

    public void setDefense(double defense) {
        this.defense.set(defense);
    }

    public double getDefenseActuelle() {
        return this.defenseActuelle.get();
    }

    public double getDefense() {
        return this.defense.get();
    }
}
