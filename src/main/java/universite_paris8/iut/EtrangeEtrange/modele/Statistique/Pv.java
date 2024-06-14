package universite_paris8.iut.EtrangeEtrange.modele.Statistique;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Pv
{
    private DoubleProperty pvMaximum;
    private DoubleProperty pv;

    public Pv(double pv)
    {
        this.pvMaximum = new SimpleDoubleProperty(pv);
        this.pv = new SimpleDoubleProperty(pv);
    }

    public void setPv(double pv)
    {
        if (pv <= pvMaximum.get())
            this.pv.set(pv);
    }

    public void setPvMaximum(double pvMax)
    {
        this.pvMaximum.set(pvMax);

        if (pv.get() < pvMaximum.get())
            pv.set(pvMax);
    }

    public void enleverPv(double pv)
    {
        if (pv >= this.pv.get())
            this.pv.set(0);
        else
            this.pv.set(this.pv.get()-pv);
    }

    public void ajoutPv(double pv)
    {
        if (pv + this.pv.get() > this.pvMaximum.get())
            this.pv.set(this.pvMaximum.get());
        else
            this.pv.set(this.pv.get()+pv);
    }

    public void enleveToutPv()
    {
        this.pv.set(0);
    }

    public boolean pvAzero()
    {
        return this.pv.get() == 0;
    }

    public void remettreMaxPv()
    {
        this.pv.set(this.pvMaximum.get());
    }
    public double getPv() {
        return this.pv.get();
    }
    public double getPvMaximum() {
        return this.pvMaximum.get();
    }

    public DoubleProperty getPvActuelleProperty(){return this.pv;}

    public int nbCoeurs() {
        return (int) Math.ceil(getPv() / 20);
    }

    public int nbCoeursMax() {
        return (int) Math.ceil(getPvMaximum() / 20);
    }


}