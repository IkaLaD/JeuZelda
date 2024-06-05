package universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import universite_paris8.iut.EtrangeEtrange.modele.ActionJoueur.ActionJoueur;

import universite_paris8.iut.EtrangeEtrange.modele.Compétence.Competences;
import universite_paris8.iut.EtrangeEtrange.modele.Compétence.CreationArbre;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.LivreMagique;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Consommable.Consommable;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Fleche.Fleche;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreActionMainDroite.ParametreActionAttaque.ActionAttaqueDistance.ParametreActionAttaqueArc;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreActionMainDroite.ParametreActionAttaque.ActionAttaqueMelee.ParametreActionAttaqueEpee;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Carquois;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreActionMainDroite.ParametreActionAttaque.ParametreActionAttaque;

import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreActionMainDroite.ParametreActionAttaque.ParametreActionLivreMagique.ParametreActionLivreMagique;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Humanoide;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Utilisable;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Arme;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMelee.Epée.Epee;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeTirable.Arc.Arc;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Sac.Sac;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Objet;


public abstract class Joueur extends Humanoide
{
    private Competences competences;
    protected Carquois carquois;
    private BooleanProperty estEntrainDeCourir;

    public Joueur(double pv, double attaque, double defense, double attaqueSpecial, double defenseSpecial, double vitesse, Sac sac, Objet objetMainGauche, Objet objetMainDroite, Monde monde, double x, double y, Direction direction, Hitbox hitbox) {
        super(pv, attaque, defense, attaqueSpecial, defenseSpecial,vitesse, sac, objetMainGauche, objetMainDroite, monde, x, y, direction, hitbox);
        this.competences = CreationArbre.arbres();
        this.estEntrainDeCourir = new SimpleBooleanProperty();
    }


    public boolean recupereObjet(Objet objet)
    {
        return getSac().ajoutItem(objet);
    }



    @Override
    public void actionMainDroite()
    {
        Objet objet = getObjetMainDroite();

        if (objet != null)
        {
            if (objet instanceof Utilisable)
            {
                if (objet instanceof Arme arme)
                {
                    attaque(arme);
                }

                if (objet instanceof Consommable consommable)
                {
                    consommer(consommable);
                }


            }
        }
    }

    @Override
    public void lanceUnSort(ParametreActionLivreMagique param)
    {
        if (objetMainDroite instanceof LivreMagique livreMagique)
            livreMagique.utilise(param);

    }




    @Override
    public void attaque(Arme arme)
    {

        ParametreActionAttaque parametreActionAttaque;

        if (arme instanceof Epee)
        {
            parametreActionAttaque = new ParametreActionAttaqueEpee(this);
            arme.utilise(parametreActionAttaque);
        }
        else if (arme instanceof Arc)
        {
            Fleche flecheSimple = carquois.retourneUneFleche();

            if (flecheSimple != null)
            {
                flecheSimple.setPositionOrigine(position);
                flecheSimple.setDirection(direction);
                parametreActionAttaque = new ParametreActionAttaqueArc(this,flecheSimple);
                arme.utilise(parametreActionAttaque);
            }

        }
    }

    public void estEntrainDeCourir(boolean bool)
    {
        this.estEntrainDeCourir.set(bool);
    }

    public Competences getCompetences()
    {
        return this.competences;
    }

    public final BooleanProperty estEntrainDeCourirProperty()
    {
        return this.estEntrainDeCourir;
    }


}
