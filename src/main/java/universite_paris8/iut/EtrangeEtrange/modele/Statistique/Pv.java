package universite_paris8.iut.EtrangeEtrange.modele.Statistique;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Pv
{
    private DoubleProperty pv;
    private DoubleProperty pvActuelle;

    public Pv(double pv)
    {
        this.pv = new SimpleDoubleProperty(pv);
        this.pvActuelle = new SimpleDoubleProperty(pv);
    }

    public void setPvActuelle(double pvActuelle)
    {
        if (pvActuelle <= pv.get())
            this.pvActuelle.set(pvActuelle);
    }

    public void setPv(double pvMax)
    {
        this.pv.set(pvMax);

        if (pvActuelle.get() < pv.get())
            pvActuelle.set(pvMax);
    }

    public void enleverPv(double pv)
    {
        if (pv >= pvActuelle.get())
            pvActuelle.set(0);
        else
            pvActuelle.set(pvActuelle.get()-pv);
    }

    public void ajoutPv(double pv)
    {
        if (pv + pvActuelle.get() > this.pv.get())
            this.pvActuelle.set(this.pv.get());
        else
            this.pvActuelle.set(this.pvActuelle.get()+pv);
    }


    public boolean pvAzero()
    {
        return this.pvActuelle.get() == 0;
    }

    public void remettreMaxPv()
    {
        this.pvActuelle.set(this.pv.get());
    }
    public double getPvActuelle() {
        return this.pvActuelle.get();
    }
    public double getPv() {
        return this.pv.get();
    }

    public DoubleProperty getPvActuelleProperty(){return this.pvActuelle;}
}
