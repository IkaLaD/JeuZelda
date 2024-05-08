package universite_paris8.iut.EtrangeEtrange.modele.Entité;

import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import universite_paris8.iut.EtrangeEtrange.modele.Statistique.Defense;
import universite_paris8.iut.EtrangeEtrange.modele.Statistique.DefenseSpecial;
import universite_paris8.iut.EtrangeEtrange.modele.Statistique.Pv;
import universite_paris8.iut.EtrangeEtrange.modele.Statistique.Vitesse;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

public abstract class Entite {
    private static int staticIdEntité = 0;
    private Pv pv;
    private Defense defense;
    private DefenseSpecial defenseSpecial;
    private Vitesse vitesse;
    private Position position;
    private Direction direction;
    private Monde monde;
    private Hitbox hitbox;
    private int id;

    public Entite(double pv,double defense,double defenseSpecial,double vitesse,Monde monde,double x,double y,Direction direction, Hitbox hitbox)
    {
        this.id = staticIdEntité++;

        this.pv = new Pv(pv);
        this.defense = new Defense(defense);
        this.defenseSpecial = new DefenseSpecial(defenseSpecial);
        this.vitesse = new Vitesse(vitesse);

        this.monde = monde;
        this.position = new Position(x, y);
        this.direction = direction;
        this.hitbox = hitbox;
    }

    public void subitDegat(double attaque,double attaqueSpecial)
    {
        double pvPerdu = subitDegatPhysique(attaque)+subitDegatSpecial(attaqueSpecial);
        this.pv.enleverPv(pvPerdu);
    }

    protected abstract double subitDegatPhysique(double attaque);
    protected abstract double subitDegatSpecial(double attaqueSpecial);


    public void enlevePv(double pv)
    {
        this.pv.enleverPv(pv);
    }




    public void setPv(double pv) {this.pv.setPv(pv);}
    public void setPvActuelle(double pv) {this.pv.setPvActuelle(pv);}

    public void setDefense(double defense){this.defense.setDefense(defense);}
    public void setDefenseActuelle(double defense){this.defense.setDefenseActuelle(defense);}


    public void setDefenseSpecial(double defenseSpecial) {this.defenseSpecial.setDefenseSpecial(defenseSpecial);}
    public void setDefenseSpecialActuelle(double defenseSpecial) {this.defenseSpecial.setDefenseSpecialActuelle(defenseSpecial);}


    public void setVitesse(double vitesse) {this.vitesse.setVitesse(vitesse);}
    public void setVitesseActuelle(double vitesse) {this.vitesse.setVitesseActuelle(vitesse);}


    public void setPosition(double x, double y)
    {
        this.position.setX(x);
        this.position.setY(y);
    }

    public void seDeplace(Direction direction)
    {
        int x = direction.getX();
        int y = direction.getY();

        position.setX(position.getX() + x*vitesse.getVitesse());
        position.setY(position.getY() + y*vitesse.getVitesse());
    }




    public Pv getPv() {return this.pv;}
    public Defense getDefense(){return this.defense;}
    public DefenseSpecial getDefenseSpecial(){return this.defenseSpecial;}
    public Vitesse getVitesse(){return this.vitesse;}
    public Position getPosition(){return this.position;}
    public Direction getDirection() {return this.direction;}
    public Monde getMonde(){return this.monde;}
    public Hitbox getHitbox(){
        return this.hitbox;
    }
    public void setDirection(Direction direction){
        this.direction = direction;
    }













    public boolean provoqueUnecollision(double x, double y)
    {
        return getMonde().getTileType((int)x, (int)y) == 3;
    }
}