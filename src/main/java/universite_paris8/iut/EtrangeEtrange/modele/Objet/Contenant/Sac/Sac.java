package universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Sac;

import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.ObjetConteneur;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Objet;

public abstract class Sac extends ObjetConteneur
{

    public Sac(int taille) {
        super(taille);
    }

    @Override
    public String getNom() {
        return null;
    }

    public int stackMax()
    {
        return 1;
    }


}
