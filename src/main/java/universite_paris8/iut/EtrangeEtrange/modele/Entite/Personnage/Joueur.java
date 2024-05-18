package universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage;

import universite_paris8.iut.EtrangeEtrange.modele.ActionObjet.ActionObjetMainDroite.ActionAttaque.ActionAttaqueDistance.ActionAttaqueAvecArc;
import universite_paris8.iut.EtrangeEtrange.modele.ActionObjet.ActionObjetMainDroite.ActionAttaque.ActionAttaqueMelee.ActionAttaquerAvecEpee;
import universite_paris8.iut.EtrangeEtrange.modele.ActionObjet.ActionObjetMainDroite.ActionAttaque.ActionAttaquer;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Humanoide;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Guerrisable;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Utilisable;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Arme;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMelee.Epée.Epee;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeTirable.Arc.Arc;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Consommable.Mangeable;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Consommable.Soins.Soins;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Sac.Sac;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Objet;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Fleche.Fleche;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Fleche.FlecheSimple;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;

public abstract class Joueur extends Humanoide
{
    public Joueur(double pv, double attaque, double defense, double attaqueSpecial, double defenseSpecial, double vitesse,Sac sac, Objet objetMainGauche, Objet objetMainDroite, Monde monde, double x, double y, Direction direction, Hitbox hitbox) {
        super(pv, attaque, defense, attaqueSpecial, defenseSpecial,vitesse, sac, objetMainGauche, objetMainDroite, monde, x, y, direction, hitbox);
    }


    public boolean recupereObjet(Objet objet)
    {
        return getSac().ajoutItem(objet);
    }



    public void actionMainDroite()
    {
        Objet objet = getObjetMainDroite();

        if (objet != null)
        {
            if (objet instanceof Utilisable)
            {
                if (objet instanceof Arme)
                {
                    attaque();
                }

                if (objet instanceof Mangeable)
                {

                }


            }
        }
    }


    public void attaque()
    {
        Arme arme = (Arme) objetMainDroite;
        ActionAttaquer actionAttaquer = null;

        if (arme instanceof Epee)
        {
            actionAttaquer = new ActionAttaquerAvecEpee(this);
        }
        else if (arme instanceof Arc)
        {
            //Fleche fleche = carquois.retourneUneFleche();
            Fleche fleche = new FlecheSimple();
            
            if (fleche != null)
                actionAttaquer = new ActionAttaqueAvecArc(this,fleche);
        }
        
        arme.attaque(actionAttaquer);
        
    }



}
