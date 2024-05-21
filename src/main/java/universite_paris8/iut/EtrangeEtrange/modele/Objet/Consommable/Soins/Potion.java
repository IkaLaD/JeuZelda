package universite_paris8.iut.EtrangeEtrange.modele.Objet.Consommable.Soins;


import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Guerrisable;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Utilisable;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Consommable.Consommable;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreActionMainDroite.ActionConsomable.ParametreActionConsomable;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreActionObjet;

public class Potion extends Consommable implements Guerrisable, Utilisable {

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
    public void utilise(ParametreActionObjet param)
    {
        if (param instanceof ParametreActionConsomable)
            consommer((ParametreActionConsomable) param);
    }


    @Override
    public void consommer(ParametreActionConsomable param)
    {
        param.getOrigineAction().soigner(restoration());
    }
}
