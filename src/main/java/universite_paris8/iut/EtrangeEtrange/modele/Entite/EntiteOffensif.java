package universite_paris8.iut.EtrangeEtrange.modele.Entite;

import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Arme;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.LivreMagique;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Objet;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreActionMainDroite.ParametreActionAttaque.ParametreActionLivreMagique.ParametreActionLivreMagique;
import universite_paris8.iut.EtrangeEtrange.modele.Statistique.Attaque;
import universite_paris8.iut.EtrangeEtrange.modele.Statistique.AttaqueSpecial;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;

public abstract class EntiteOffensif extends Entite
{
    protected Attaque statsAttaque;
    protected AttaqueSpecial statsAttaqueSpecial;
    public EntiteOffensif(double pv, double attaque, double defense, double attaqueSpecial , double defenseSpecial, double vitesse, Monde monde, double x, double y, Direction direction, Hitbox hitbox)
    {
        super(pv, defense, defenseSpecial, vitesse, monde, x, y, direction, hitbox);
        this.statsAttaque = new Attaque(attaque);
        this.statsAttaqueSpecial = new AttaqueSpecial(attaqueSpecial);
    }
    public abstract void attaque(Arme arme);

    public void lanceUnSort(ParametreActionLivreMagique param)
    {

    }
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
