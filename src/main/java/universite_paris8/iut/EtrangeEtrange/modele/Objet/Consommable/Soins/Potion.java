package universite_paris8.iut.EtrangeEtrange.modele.Objet.Consommable.Soins;


import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Guerrisable;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Consommable;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreAction;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreConsomable.ParametreActionConsomable;

public class Potion implements Consommable,Guerrisable
{
    private int durabilitee;

    public Potion(){this.durabilitee = 1;}

    @Override
    public double restoration() {
        return 20;
    }

    @Override
    public String getNom() {
        return "Potion";
    }

    @Override
    public int stackMax() {
        return 6;
    }

    @Override
    public double durabilitee() {
        return 1;
    }


    @Override
    public void utilise(ParametreAction param)
    {
        if (param instanceof ParametreActionConsomable parametre)
            consommer(parametre);
    }


    @Override
    public void consommer(ParametreActionConsomable param)
    {
        param.getOrigineAction().soigner(restoration());
        durabilitee--;
    }
}
