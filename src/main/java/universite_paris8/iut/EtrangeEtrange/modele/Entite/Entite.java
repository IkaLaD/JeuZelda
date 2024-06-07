package universite_paris8.iut.EtrangeEtrange.modele.Entite;

import universite_paris8.iut.EtrangeEtrange.modele.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Consommable.Consommable;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreConsomable.ParametreActionConsomable;
import universite_paris8.iut.EtrangeEtrange.modele.Statistique.Defense;
import universite_paris8.iut.EtrangeEtrange.modele.Statistique.DefenseSpecial;
import universite_paris8.iut.EtrangeEtrange.modele.Statistique.Pv;
import universite_paris8.iut.EtrangeEtrange.modele.Statistique.Vitesse;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Surface;


import java.util.ArrayList;

import static universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction.*;

public abstract class Entite extends Acteur {
    private static int staticIdEntité = 0;

    protected Defense statsDefense;
    protected DefenseSpecial statsDefenseSpecial;




    protected Hitbox hitbox;
    private int id;
    private boolean seDeplace;

    public Entite(Monde monde,double x,double y,Direction direction,double pv,double defense,double defenseSpecial,double vitesse, Hitbox hitbox)
    {
        super(monde,x,y,direction,pv,vitesse,hitbox);
        this.statsDefense = new Defense(defense);
        this.statsDefenseSpecial = new DefenseSpecial(defenseSpecial);
    }

    public abstract void subitDegat(Dommageable causeDegat);

    protected abstract double subitDegatPhysique(double degat,double forceEntite);
    protected abstract double subitDegatSpecial(double attaqueSpecial,double forceEntite);

    public void enlevePv(double pv)
    {
        this.statsPv.enleverPv(pv);
    }

    public void setPvMaximum(double statsPv) {this.statsPv.setPvMaximum(statsPv);}
    public void setPv(double pv) {this.statsPv.setPv(pv);}

    public void setDefenseMaximum(double statsDefense){this.statsDefense.setDefenseMaximum(statsDefense);}
    public void setDefense(double defense){this.statsDefense.setDefense(defense);}

    public void setDefenseSpecialMaximum(double statsDefenseSpecial) {this.statsDefenseSpecial.setDefenseSpecialMaximum(statsDefenseSpecial);}
    public void setDefenseSpecial(double defenseSpecial) {this.statsDefenseSpecial.setDefenseSpecial(defenseSpecial);}


    public void setVitesseMaximum(double statsVitesse) {this.statsVitesse.setVitesseMaximum(statsVitesse);}
    public void setVitesse(double vitesse) {this.statsVitesse.setVitesse(vitesse);}


    public void setPosition(double x, double y)
    {
        this.position.setX(x);
        this.position.setY(y);
    }

    public boolean isSeDeplace() {
        return seDeplace;
    }



    public void seDeplace()
    {
        seDeplace(1);
    }


    public void soigner(double pv)
    {
        this.statsPv.ajoutPv(pv);
    }

    public double getDefense(){ return this.statsDefense.getDefense();}
    public double getDefenseSpecial(){ return this.statsDefenseSpecial.getDefenseSpecial();}

    public int getId(){
        return this.id;
    }
    public Defense getStatsDefense(){return this.statsDefense;}
    public DefenseSpecial getStatsDefenseSpecial(){return this.statsDefenseSpecial;}


    /**
     * Méthode qui vérifie si la prochaine position ou souhaite aller l'entité ne soit pas hors map ou un mur
     * @return
     */
    public boolean peutSeDeplacer(){
        if(horsMap())
            return false;
        if(collision())
            return false;
        return true;
    }

    /**
     * Renvoie "true" si la prochaine position de l'entite est hors map
     * @return
     */


    public void augmentePvMaximum(double pv)
    {
        setPvMaximum(getStatsPv().getPvMaximum() + pv);
    }

    public Surface getSurface()
    {
        return new Surface(position,hitbox);
    }


    public void consommer(Consommable consommable)
    {
        consommable.consommer(new ParametreActionConsomable(this));
    }

}
