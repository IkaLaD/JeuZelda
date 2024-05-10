package universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Sac;

import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.ObjetConteneur;

public abstract class Sac extends ObjetConteneur
{

    public Sac(int taille) {
        super(taille);
    }

    public int stackMax()
    {
        return 1;
    }

}
