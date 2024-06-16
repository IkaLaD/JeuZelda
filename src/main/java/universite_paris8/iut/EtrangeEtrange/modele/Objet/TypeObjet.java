package universite_paris8.iut.EtrangeEtrange.modele.Objet;

import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Objet;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.LivreMagique;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Epee;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Arc;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Soins.Potion;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Fleche;

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
