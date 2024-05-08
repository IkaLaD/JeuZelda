package universite_paris8.iut.EtrangeEtrange.modele.Statistique;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class AttaqueSpecial
{
    private DoubleProperty attaqueSpecial;
    private DoubleProperty attaqueActuelleSpecial;

    public AttaqueSpecial(double attaqueSpecial)
    {
        this.attaqueSpecial = new SimpleDoubleProperty(attaqueSpecial);
        this.attaqueActuelleSpecial = new SimpleDoubleProperty(attaqueSpecial);
    }

    public void setAttaqueSpecialActuelle(double attaqueSpecialActuelle) {
        this.attaqueActuelleSpecial.set(attaqueSpecialActuelle);
    }

    public void setAttaqueSpecial(double attaqueSpecial) {
        this.attaqueSpecial.set(attaqueSpecial);
    }

    public double getAttaqueSpecialActuelle() {
        return this.attaqueActuelleSpecial.get();
    }

    public double getAttaqueSpecial() {
        return this.attaqueSpecial.get();
    }
}
