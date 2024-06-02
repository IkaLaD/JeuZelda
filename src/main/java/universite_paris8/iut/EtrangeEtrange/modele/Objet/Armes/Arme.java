package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes;

import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.DommageableMultiCoup;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Utilisable;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Objet;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreActionMainDroite.ParametreActionAttaque.ParametreActionAttaque;

public abstract class Arme  extends Objet implements Utilisable, DommageableMultiCoup
{
    protected boolean peuxTaper;

    public Arme()
    {
        this.peuxTaper = true;
    }


    public abstract void attaque(ParametreActionAttaque action);


}
