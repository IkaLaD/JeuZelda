package universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import universite_paris8.iut.EtrangeEtrange.modele.Compétence.Competences;
import universite_paris8.iut.EtrangeEtrange.modele.Compétence.CreationArbre;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.LivreMagique;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Consommable;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Fleche.Fleche;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreAttaque.ActionAttaqueDistance.ParametreAttaqueArc;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreAttaque.ActionAttaqueMelee.ParametreAttaqueEpee;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Carquois;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreAttaque.ParametreActionAttaque;

import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreAttaque.ParametreLivreMagique.ParametreLivreMagique;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreAttaque.ParametreLivreMagique.ParametreLivreMagiqueTirBasique;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Humanoide;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Utilisable;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Arme;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMelee.Epée.Epee;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeTirable.Arc.Arc;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Sac.Sac;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Objet;

/**
 * Représente le joueur dans le jeu.
 */
public abstract class Joueur extends Humanoide
{
    private Competences competences;
    protected Carquois carquois;
    private BooleanProperty estEntrainDeCourir;

    /**
     * Crée un nouveau joueur.
     *
     * @param pv             Les points de vie du joueur.
     * @param attaque        La valeur de l'attaque du joueur.
     * @param defense        La valeur de la défense du joueur.
     * @param attaqueSpecial La valeur de l'attaque spéciale du joueur.
     * @param defenseSpecial La valeur de la défense spéciale du joueur.
     * @param vitesse        La vitesse de déplacement du joueur.
     * @param sac            Le sac à dos du joueur.
     * @param objetMainGauche   L'objet tenu dans la main gauche du joueur.
     * @param objetMainDroite L'objet tenu dans la main droite du joueur.
     * @param monde          Le monde dans lequel évolue le joueur.
     * @param x              La position horizontale du joueur dans le monde.
     * @param y              La position verticale du joueur dans le monde.
     * @param direction      La direction vers laquelle le joueur est orienté.
     * @param hitbox         La hitbox du joueur.
     */
    public Joueur(double pv, double attaque, double defense, double attaqueSpecial, double defenseSpecial, double vitesse, Sac sac, Objet objetMainGauche, Objet objetMainDroite, Monde monde, double x, double y, Direction direction, Hitbox hitbox) {
        super(monde, x, y, direction, pv,attaque,defense,attaqueSpecial,defenseSpecial,vitesse,hitbox,sac,objetMainGauche,objetMainDroite);
        this.competences = CreationArbre.arbres();
        this.estEntrainDeCourir = new SimpleBooleanProperty();
    }

    public void actionMainDroite()
    {
        if (getObjetMainDroite() != null)
        {
            if (getObjetMainDroite() instanceof Arme arme)
                attaque(arme);

            if (getObjetMainDroite() instanceof Consommable consommable)
                consommer(consommable);
        }
    }


    @Override
    public void lanceUnSort(int numSort)
    {
        if (objetMainDroite instanceof LivreMagique livreMagique)
            livreMagique.utilise(new ParametreLivreMagique(this,numSort));
    }

    @Override
    public String typeActeur(){ return "Joueur" ;}
    @Override
    public void attaque(Arme arme)
    {
        ParametreActionAttaque parametreAttaque = null;

        if (arme instanceof Epee)
        {
            parametreAttaque = new ParametreAttaqueEpee(this);
        }
        else if (arme instanceof LivreMagique)
        {
            parametreAttaque = new ParametreLivreMagiqueTirBasique(this);
        }
        else if (arme instanceof Arc)
        {
            Fleche flecheSimple = carquois.retourneUneFleche();

            if (flecheSimple != null)
                parametreAttaque = new ParametreAttaqueArc(this,flecheSimple);
        }

        if (parametreAttaque != null)
            arme.utilise(parametreAttaque);
    }

    public void estEntrainDeCourir(boolean bool) {this.estEntrainDeCourir.set(bool);}
    public Competences getCompetences() {return this.competences;}
    public final BooleanProperty estEntrainDeCourirProperty() {return this.estEntrainDeCourir;}

}
