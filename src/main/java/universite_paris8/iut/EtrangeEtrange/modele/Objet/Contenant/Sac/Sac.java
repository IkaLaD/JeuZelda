package universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Sac;

import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.ObjetConteneur;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Objet;

public class Sac extends ObjetConteneur<Objet>
{
    public Sac() {
        super(15);
    }
    @Override
    public String getNom() {
        return null;
    }

    public int stackMax()
    {
        return 1;
    }

    @Override
    public double durabilitee() {
        return -1;
    }

    @Override
    public int prixAchat() {
        return 0;
    }

}
