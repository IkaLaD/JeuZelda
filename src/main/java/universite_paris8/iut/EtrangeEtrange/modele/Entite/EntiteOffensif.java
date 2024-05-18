package universite_paris8.iut.EtrangeEtrange.modele.Entite;

import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMelee.Epée.Epee;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeTirable.Arc.Arc;
import universite_paris8.iut.EtrangeEtrange.modele.Statistique.Attaque;
import universite_paris8.iut.EtrangeEtrange.modele.Statistique.AttaqueSpecial;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;

public abstract class EntiteOffensif extends Entite
{
    private Attaque attaque;
    private AttaqueSpecial attaqueSpecial;
    public EntiteOffensif(double pv, double attaque, double defense, double attaqueSpecial , double defenseSpecial, double vitesse, Monde monde, double x, double y, Direction direction, Hitbox hitbox)
    {
        super(pv, defense, defenseSpecial, vitesse, monde, x, y, direction, hitbox);
        this.attaque = new Attaque(attaque);
        this.attaqueSpecial = new AttaqueSpecial(attaqueSpecial);
    }
    public abstract void attaque();




    public void setAttaque(double attaque) {this.attaque.setAttaque(attaque);}
    public void setAttaqueActuelle(double attaque) {this.attaque.setAttaqueActuelle(attaque);}

    public void setAttaqueSpecial(double attaqueSpecial){this.attaqueSpecial.setAttaqueSpecial(attaqueSpecial);}
    public void setAttaqueSpecialActuelle(double attaqueSpecial){this.attaqueSpecial.setAttaqueSpecialActuelle(attaqueSpecial);}

    public Attaque getAttaque(){return this.attaque;}
    public AttaqueSpecial getAttaqueSpecial(){return this.attaqueSpecial;}



}
