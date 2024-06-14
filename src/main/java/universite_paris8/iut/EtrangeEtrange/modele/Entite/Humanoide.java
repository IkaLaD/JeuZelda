package universite_paris8.iut.EtrangeEtrange.modele.Entite;

import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Contenant.Sac.Sac;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Objet;
import universite_paris8.iut.EtrangeEtrange.modele.Stockage.DropAuSol;


import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

import java.util.ArrayList;

/**
 * Représente un humanoïde dans le monde du jeu.
 * A deux mains pouvant stocker des objet.
 * Peut porter un sac.
 */
public abstract class Humanoide extends EntiteOffensif
{
    protected Objet objetMainGauche;
    protected Objet objetMainDroite;
    protected Sac sac;


    /**
     * Crée un nouvel humanoïde.
     *
     * @param monde            Le monde dans lequel l'humanoïde existe.
     * @param x                La position horizontale de l'humanoïde.
     * @param y                La position verticale de l'humanoïde.
     * @param direction        La direction dans laquelle l'humanoïde est orienté.
     * @param pv               Les points de vie de l'humanoïde.
     * @param attaque          La valeur de l'attaque de l'humanoïde.
     * @param defense          La valeur de la défense de l'humanoïde.
     * @param attaqueSpecial   La valeur de l'attaque spéciale de l'humanoïde.
     * @param defenseSpecial  La valeur de la défense spéciale de l'humanoïde.
     * @param vitesse          La vitesse de déplacement de l'humanoïde.
     * @param hitbox           La hitbox de l'humanoïde.
     * @param sac              Le sac à dos de l'humanoïde.
     * @param objetMainGauche  L'objet tenu dans la main gauche de l'humanoïde.
     * @param objetMainDroite L'objet tenu dans la main droite de l'humanoïde.
     */
    public Humanoide(Monde monde, double x, double y, Direction direction,double pv, double attaque, double defense, double attaqueSpecial, double defenseSpecial, double vitesse,Hitbox hitbox,Sac sac, Objet objetMainGauche, Objet objetMainDroite)
    {
        super(monde, x, y, direction, pv, attaque, defense, attaqueSpecial, defenseSpecial, vitesse, hitbox);
        this.sac = sac;
        this.objetMainGauche = objetMainGauche;
        this.objetMainDroite = objetMainDroite;
    }

    @Override
    protected double subitDegatPhysique(double attaque,double forceEntite) {
        return (attaque * forceEntite) / (getDefense() - (attaque/6));
    }

    @Override
    protected double subitDegatSpecial(double attaqueSpecial,double forceEntite) {
        return (attaqueSpecial * forceEntite) / (getDefense() - (attaqueSpecial/6));
    }


    public Sac getSac()
    {
        return this.sac;
    }


    public Objet retournerObjetMainDroite()
    {
        Objet objet = this.objetMainDroite;
        this.objetMainDroite = null;
        return objet;
    }
    public void setObjetMainGauche(Objet objet){
        this.objetMainGauche = objet;
    }

    public Objet retournerObjetMainGauche(){
        Objet objet = this.objetMainGauche;
        this.objetMainGauche=null;
        return objet;
    }
    public Objet getObjetMainDroite(){
        return this.objetMainDroite;
    }
    public Objet getObjetMainGauche(){
        return this.objetMainGauche;
    }

    public void setObjetMainDroite(Objet objet){
        this.objetMainDroite = objet;
    }
    public void ramasserObjet() {
        ArrayList<DropAuSol> dropAuSols = getMonde().getDropAuSol();

        for(int i = 0 ; i < dropAuSols.size() ; i++){
            Position position1 = dropAuSols.get(i).getPosition();
            if(Math.abs(getPosition().getX()+getDirection().getX()-position1.getX())<1)
                if(Math.abs(getPosition().getY()+getDirection().getY()-position1.getY())<1) {
                    if (sac.ajoutItem(dropAuSols.get(i).getObjet())) {
                        getMonde().enleverDropAuSol(dropAuSols.get(i));
                        i++;
                    }
                }
        }
    }
}
