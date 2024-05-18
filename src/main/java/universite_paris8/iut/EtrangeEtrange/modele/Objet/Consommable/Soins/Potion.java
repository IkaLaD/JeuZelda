package universite_paris8.iut.EtrangeEtrange.modele.Objet.Consommable.Soins;

import universite_paris8.iut.EtrangeEtrange.modele.ActionObjet.ActionSurObjet;

public class Potion extends Soins
{

    @Override
    public double restoration() {
        return 20;
    }

    @Override
    public String getNom() {
        return null;
    }

    @Override
    public int stackMax() {
        return 6;
    }


    @Override
    public void utilise(ActionSurObjet action) {

    }
}
