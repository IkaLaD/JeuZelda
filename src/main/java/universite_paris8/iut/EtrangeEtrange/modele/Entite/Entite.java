package universite_paris8.iut.EtrangeEtrange.modele.Entite;

import universite_paris8.iut.EtrangeEtrange.modele.ActionDegat.ActionDegat;
import universite_paris8.iut.EtrangeEtrange.modele.ActionDegat.ActionDegatParEntite;
import universite_paris8.iut.EtrangeEtrange.modele.ActionDegat.ActionDegatParEpee;
import universite_paris8.iut.EtrangeEtrange.modele.ActionDegat.ActionDegatParProjectile;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Consommable.Consommable;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Projectile;
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

public abstract class Entite extends Acteur{

    protected Defense statsDefense;
    protected DefenseSpecial statsDefenseSpecial;


    public Entite(double pv,double defense,double defenseSpecial,double vitesse,Monde monde,double x,double y,Direction direction, Hitbox hitbox)
    {
        super(new Pv(pv) ,new Vitesse(vitesse) , new Position(x, y), direction, monde, hitbox, false);
        this.statsDefense = new Defense(defense);
        this.statsDefenseSpecial = new DefenseSpecial(defenseSpecial);
    }


    public void setDefenseMaximum(double statsDefense){this.statsDefense.setDefenseMaximum(statsDefense);}
    public void setDefense(double defense){this.statsDefense.setDefense(defense);}


    public void setDefenseSpecialMaximum(double statsDefenseSpecial) {this.statsDefenseSpecial.setDefenseSpecialMaximum(statsDefenseSpecial);}
    public void setDefenseSpecial(double defenseSpecial) {this.statsDefenseSpecial.setDefenseSpecial(defenseSpecial);}




    public double getDefense(){ return this.statsDefense.getDefense();}
    public double getDefenseSpecial(){ return this.statsDefenseSpecial.getDefenseSpecial();}

    public Defense getStatsDefense(){return this.statsDefense;}
    public DefenseSpecial getStatsDefenseSpecial(){return this.statsDefenseSpecial;}




    public void consommer(Consommable consommable)
    {
        consommable.consommer(new ParametreActionConsomable(this));
    }

}
