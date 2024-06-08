package universite_paris8.iut.EtrangeEtrange.modele;

import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Statistique.Pv;
import universite_paris8.iut.EtrangeEtrange.modele.Statistique.Vitesse;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

public abstract class Acteur
{
    protected Monde monde;
    protected Position position;
    protected Direction direction;

    protected Pv statsPv;
    protected Vitesse statsVitesse;
    protected Hitbox hitbox;

    protected boolean seDeplace;


    private final String ID;
    private static int iid = 0;

    public Acteur(Monde monde,double x,double y,Direction direction,double pv,double vitesse, Hitbox hitbox)
    {
        this.monde = monde;
        this.position = new Position(x,y);
        this.direction = direction;
        this.statsPv = new Pv(pv);
        this.statsVitesse = new Vitesse(vitesse);
        this.hitbox = hitbox;
        this.ID = "acteur:"+iid++;
    }

    public Acteur(double pv,double vitesse,Hitbox hitbox)
    {
        this.monde = null;
        this.position = null;
        this.direction = null;
        this.statsPv = new Pv(pv);
        this.statsVitesse = new Vitesse(vitesse);
        this.hitbox = hitbox;
        this.ID = "acteur"+iid++;
    }

    public abstract boolean peutSeDeplacer();
    public void seDeplace(double coef)
    {
        int x = this.direction.getX();
        int y = this.direction.getY();

        if(peutSeDeplacer())
        {
            position.setX(position.getX() + x * statsVitesse.getVitesse() * coef);
            position.setY(position.getY() + y * statsVitesse.getVitesse() * coef);
        }
    }

    public abstract void unTour();
    public abstract void subitCollision(Acteur acteur);
    public void setSeDeplace(boolean seDeplace){ this.seDeplace = seDeplace;}
    public void setMonde(Monde monde) {this.monde = monde;}
    public void setDirection(Direction direction) {this.direction = direction;}
    public void setPosition(double x,double y){ this.position = new Position(x,y);}
    public void setPosition(Position pos){ this.position = new Position(pos.getX(),pos.getY());}
    public void setVitesseMaximum(double statsVitesse) {this.statsVitesse.setVitesseMaximum(statsVitesse);}
    public void soigner(double pv)
    {
        this.statsPv.ajoutPv(pv);
    }
    public void setVitesse(double vitesse) {this.statsVitesse.setVitesse(vitesse);}
    public Vitesse getStatsVitesse() {return statsVitesse;}
    public void seDeplace(boolean seDeplace) {this.seDeplace = seDeplace;}
    public Direction getDirection() {return this.direction;}
    public boolean getSeDeplace(){return this.seDeplace;}
    public Hitbox getHitbox() {return this.hitbox;}
    public Monde getMonde() {return this.monde;}
    public Position getPosition() {return this.position;}
    public Pv getStatsPv() {return this.statsPv;}
    public double getPv(){return this.statsPv.getPv();}
    public boolean plusDePv(){ return statsPv.pvAzero();}
    public void setPv(double pv) {this.statsPv.setPv(pv);}
    public void setPvMaximum(double statsPv) {this.statsPv.setPvMaximum(statsPv);}
    public void augmentePvMaximum(double pv)
    {
        setPvMaximum(getStatsPv().getPvMaximum() + pv);
    }

    public double getVitesse() { return this.statsVitesse.getVitesse();}
    public void enleveToutPv(){ this.statsPv.enleveToutPv();}
    public void enlevePv(double pv){this.statsPv.enleverPv(pv);}

    public String getID(){return this.ID;}

}
