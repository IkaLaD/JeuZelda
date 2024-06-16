package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Statistique.Defense;
import universite_paris8.iut.EtrangeEtrange.modele.Statistique.DefenseSpecial;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
/**
 * Représente un être vivant dans le monde du jeu.
 * Cette classe étend la classe abstraite Acteur
 */
public abstract class Entite extends Acteur
{
    protected Defense statsDefense;
    protected DefenseSpecial statsDefenseSpecial;

    /**
     * Crée une nouvelle instance d'Entite avec les paramètres spécifiés.
     *
     * @param monde            Le monde dans lequel l'entité évolue.
     * @param x                La position horizontale de l'entité dans le monde.
     * @param y                La position verticale de l'entité dans le monde.
     * @param direction        La direction initiale de l'entité.
     * @param pv               Les points de vie de l'entité.
     * @param defense          La défense de l'entité.
     * @param defenseSpecial   La défense spéciale de l'entité.
     * @param vitesse          La vitesse de déplacement de l'entité.
     * @param hitbox           La hitbox de l'entité.
     */
    public Entite(Monde monde,double x,double y,Direction direction,double pv,double defense,double defenseSpecial,double vitesse, Hitbox hitbox)
    {
        super(monde,x,y,direction,pv,vitesse,hitbox);
        this.statsDefense = new Defense(defense);
        this.statsDefenseSpecial = new DefenseSpecial(defenseSpecial);
    }

    /**
     * Subit des dégâts infligés par une source dommageable.
     * @param causeDegat La source de dégâts.
     */
    public void subitAttaque(Dommageable causeDegat) {enlevePv((subitDegatPhysique(causeDegat.degatPhysique(),0)+subitDegatSpecial(causeDegat.degatSpecial(),0))/2);}

    public void subitCollision(Acteur acteur) {acteur.causeCollision(this);}
    public void causeCollision(Acteur acteur) {acteur.seFaitPousser(this);}

    @Override
    public void seFaitPousser(Acteur acteur) {}



    /**
     * Calcule les dégâts physiques subis par l'entité.
     * @param attaqueEntite       Les dégâts physiques infligés.
     * @param degatArme La force de l'entité qui inflige les dégâts.
     * @return Les dégâts physiques subis.
     */
    protected double subitDegatPhysique(double attaqueEntite,double degatArme) {return Math.abs(attaqueEntite+degatArme - statsDefense.getDefense());}

    /**
     * Calcule les dégâts spéciaux subis par l'entité.
     *
     * @param attaqueSpecialEntite Les dégâts spéciaux infligés.
     * @param degatArme    La force de l'entité qui inflige les dégâts spéciaux.
     * @return Les dégâts spéciaux subis.
     */
    protected double subitDegatSpecial(double attaqueSpecialEntite,double degatArme)
    {
        return Math.abs(attaqueSpecialEntite+degatArme - statsDefenseSpecial.getDefenseSpecial());
    }
    public void setDefenseMaximum(double statsDefense){this.statsDefense.setDefenseMaximum(statsDefense);}
    public void setDefense(double defense){this.statsDefense.setDefense(defense);}
    public void setDefenseSpecialMaximum(double statsDefenseSpecial) {this.statsDefenseSpecial.setDefenseSpecialMaximum(statsDefenseSpecial);}
    public void setDefenseSpecial(double defenseSpecial) {this.statsDefenseSpecial.setDefenseSpecial(defenseSpecial);}
    public double getDefense(){ return this.statsDefense.getDefense();}
    public double getDefenseSpecial(){ return this.statsDefenseSpecial.getDefenseSpecial();}
    public Defense getStatsDefense(){return this.statsDefense;}
    public DefenseSpecial getStatsDefenseSpecial(){return this.statsDefenseSpecial;}




}
