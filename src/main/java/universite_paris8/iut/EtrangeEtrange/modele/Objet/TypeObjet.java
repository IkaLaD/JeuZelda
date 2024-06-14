package universite_paris8.iut.EtrangeEtrange.modele.Objet;

import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Objet;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.LivreMagique;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMelee.Epée.Epee;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeTirable.Arc.Arc;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Consommable.Soins.Potion;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Fleche.Fleche;

public enum TypeObjet
{
    EPEE,
    ARC,
    LIVRE_MAGIQUE,


    FLECHE,

    POTION;




    public static Objet nouvelleInstance(TypeObjet typeObjet)
    {
        Objet objet = null;

        switch (typeObjet)
        {
            case EPEE -> objet = new Epee();
            case ARC -> objet = new Arc();
            case LIVRE_MAGIQUE -> objet = new LivreMagique();
            case POTION -> objet = new Potion();
            case FLECHE -> objet = new Fleche();
        }


        return objet;
    }
}
