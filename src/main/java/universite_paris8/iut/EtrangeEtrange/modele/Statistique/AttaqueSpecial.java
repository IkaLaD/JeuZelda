package universite_paris8.iut.EtrangeEtrange.modele.Statistique;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class AttaqueSpecial
{
    private DoubleProperty attaqueSpecialMaximum;
    private DoubleProperty attaqueSpecial;

    public AttaqueSpecial(double attaqueSpecial)
    {
        this.attaqueSpecialMaximum = new SimpleDoubleProperty(attaqueSpecial);
        this.attaqueSpecial = new SimpleDoubleProperty(attaqueSpecial);
    }

    public void setAttaqueSpecial(double attaqueSpecialActuelle) {
        this.attaqueSpecial.set(attaqueSpecialActuelle);
    }

    public void setAttaqueSpecialMaximum(double attaqueSpecialMaximum) {
        this.attaqueSpecialMaximum.set(attaqueSpecialMaximum);
    }

    public double getAttaqueSpecial() {
        return this.attaqueSpecial.get();
    }

    public double getAttaqueSpecialMaximum() {
        return this.attaqueSpecialMaximum.get();
    }
}
