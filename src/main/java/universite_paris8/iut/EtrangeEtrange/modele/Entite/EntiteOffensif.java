package universite_paris8.iut.EtrangeEtrange.modele.Entite;

import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Arme;
import universite_paris8.iut.EtrangeEtrange.modele.Statistique.Attaque;
import universite_paris8.iut.EtrangeEtrange.modele.Statistique.AttaqueSpecial;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;

/**
 * Représente un être-vivant dans le monde du jeu pouvant attaquer.
 */
public abstract class EntiteOffensif extends Entite
{
    protected Attaque statsAttaque;
    protected AttaqueSpecial statsAttaqueSpecial;


    /**
     * Crée une nouvelle entité offensif.
     *
     * @param monde           Le monde dans lequel l'entité existe.
     * @param x               La position horizontale de l'entité.
     * @param y               La position verticale de l'entité.
     * @param direction       La direction dans laquelle l'entité est orientée.
     * @param pv              Les points de vie de l'entité.
     * @param attaque         La valeur de l'attaque de l'entité.
     * @param defense         La valeur de la défense de l'entité.
     * @param attaqueSpecial  La valeur de l'attaque spéciale de l'entité.
     * @param defenseSpecial La valeur de la défense spéciale de l'entité.
     * @param vitesse         La vitesse de déplacement de l'entité.
     * @param hitbox          La hitbox de l'entité.
     */
    public EntiteOffensif(Monde monde, double x, double y, Direction direction, double pv, double attaque, double defense, double attaqueSpecial , double defenseSpecial, double vitesse,Hitbox hitbox)
    {
        super(monde,x,y,direction,pv,defense,defenseSpecial,vitesse,hitbox);
        this.statsAttaque = new Attaque(attaque);
        this.statsAttaqueSpecial = new AttaqueSpecial(attaqueSpecial);
    }

    /**
     * Effectue une attaque avec une arme.
     * @param arme L'arme utilisée pour l'attaque.
     */
    public abstract void attaque(Arme arme);

    /**
     * Lance un sort.
     * @param numSort Le numéro du sort à lancer.
     */
    public abstract void lanceUnSort(int numSort);
    public void setAttaqueMaximum(double attaque) {this.statsAttaque.setAttaqueMaximum(attaque);}
    public void setAttaque(double attaque) {this.statsAttaque.setAttaque(attaque);}
    public void setAttaqueSpecialMaximum(double attaqueSpecial){this.statsAttaqueSpecial.setAttaqueSpecialMaximum(attaqueSpecial);}
    public void setAttaqueSpecial(double attaqueSpecial){this.statsAttaqueSpecial.setAttaqueSpecial(attaqueSpecial);}
    public void augmenterAttaqueMaximum(double attaque)
    {
        this.statsAttaque.setAttaqueMaximum(getAttaque()+attaque);
    }
    public Attaque getStatsAttaque(){return this.statsAttaque;}
    public AttaqueSpecial getStatsAttaqueSpecial(){return this.statsAttaqueSpecial;}
    public double getAttaque(){ return this.statsAttaque.getAttaque();}
    public double getAttaqueSpecial(){ return this.statsAttaqueSpecial.getAttaqueSpecial();}




}
