package universite_paris8.iut.EtrangeEtrange.modele;

import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Statistique.Pv;
import universite_paris8.iut.EtrangeEtrange.modele.Statistique.Vitesse;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

/**
 * La classe abstraite Acteur représente une entité dynamique dans le monde du jeu.
 * Contient des informations sur la position, la direction, les statistiques de santé et de vitesse, ainsi que la hitbox de l'acteur.
 */
public abstract class Acteur
{
    protected Monde monde;
    protected Position position;
    protected Direction direction;

    protected Pv statsPv;
    protected Vitesse statsVitesse;
    protected Hitbox hitbox;

    protected boolean seDeplace;


    private final int ID;
    private static int iid = 0;




    /**
     * Constructeur avec paramètres pour initialiser un Acteur.
     *
     * @param monde     Le monde dans lequel l'acteur se situe.
     * @param x         La coordonnée en x de la position initiale.
     * @param y         La coordonnée en y de la position initiale.
     * @param direction La direction initiale de l'acteur.
     * @param pv        Les points de vie initiaux de l'acteur.
     * @param vitesse   La vitesse initiale de l'acteur.
     * @param hitbox    La hitbox de l'acteur.
     */
    public Acteur(Monde monde,double x,double y,Direction direction,double pv,double vitesse, Hitbox hitbox)
    {
        this.monde = monde;
        this.position = new Position(x,y);
        this.direction = direction;
        this.statsPv = new Pv(pv);
        this.statsVitesse = new Vitesse(vitesse);
        this.hitbox = hitbox;
        this.seDeplace = false;
        this.ID = iid++;
    }

    /**
     * Constructeur avec paramètres pour initialiser un Acteur.
     *
     * @param pv        Les points de vie initiaux de l'acteur.
     * @param vitesse   La vitesse initiale de l'acteur.
     * @param hitbox    La hitbox de l'acteur.
     */
    public Acteur(double pv,double vitesse,Hitbox hitbox)
    {
        this.monde = null;
        this.position = null;
        this.direction = null;
        this.statsPv = new Pv(pv);
        this.statsVitesse = new Vitesse(vitesse);
        this.hitbox = hitbox;
        this.ID = iid++;
    }

    /**
     * Méthode abstraite pour vérifier si l'acteur peut se déplacer dans le monde.
     * @return true si l'acteur peut se déplacer, false sinon.
     */
    public boolean peutSeDeplacer(){
        return !monde.estHorsMap(this) && !monde.collision(this);}


    /**
     * Déplace l'acteur dans le monde en fonction de sa direction et de sa vitesse.
     * Le coefficient fourni permet de moduler la vitesse du déplacement.
     *
     * @param coef Le coefficient de vitesse du déplacement.
     */
    public void seDeplace(double coef)
    {
        int x = this.direction.getX();
        int y = this.direction.getY();
        this.seDeplace=true;
        if(peutSeDeplacer())
        {
            position.setX(position.getX() + x * statsVitesse.getVitesse() * coef);
            position.setY(position.getY() + y * statsVitesse.getVitesse() * coef);
        }
    }

    /**
     * Méthode abstraite pour effectuer les actions de l'acteur lors de l'apelle dans la gameloop.
     */
    public abstract void unTour();

    /**
     * Méthode abstraite pour gérer les réactions de l'acteur lors d'une collision avec un autre acteur.
     *
     * @param acteur L'acteur avec lequel la collision s'est produite.
     */
    public abstract void subitCollision(Acteur acteur);

    public abstract String typeActeur();

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

    public int getID(){return this.ID;}

    public boolean isSeDeplace() {
        return seDeplace;
    }
}
