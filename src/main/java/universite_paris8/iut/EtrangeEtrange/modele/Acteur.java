package universite_paris8.iut.EtrangeEtrange.modele;

import universite_paris8.iut.EtrangeEtrange.modele.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Statistique.Pv;
import universite_paris8.iut.EtrangeEtrange.modele.Statistique.Vitesse;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

import java.util.ArrayList;

import static universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction.*;

public abstract class Acteur
{
    protected Monde monde;
    protected Position position;
    protected Direction direction;

    protected Pv statsPv;
    protected Vitesse statsVitesse;
    protected Hitbox hitbox;

    protected boolean seDeplace;



    public Acteur(Monde monde,double x,double y,Direction direction,double pv,double vitesse, Hitbox hitbox)
    {
        this.monde = monde;
        this.position = new Position(x,y);
        this.direction = direction;
        this.statsPv = new Pv(pv);
        this.statsVitesse = new Vitesse(vitesse);
        this.hitbox = hitbox;
        System.out.println(direction);
    }

    public Acteur(Hitbox hitbox)
    {
        this.monde = null;
        this.position = null;
        this.direction = null;
        this.statsPv = null;
        this.statsVitesse = null;
        this.hitbox = hitbox;
    }



    public boolean peutSeDeplacer(){
        if(horsMap())
            return false;
        if(collision())
            return false;
        return true;
    }

    public void seDeplace(double coef)
    {
        int x = this.direction.getX();
        int y = this.direction.getY();

        if(peutSeDeplacer()) {
            position.setX(position.getX() + x * statsVitesse.getVitesse() * coef);
            position.setY(position.getY() + y * statsVitesse.getVitesse() * coef);
        }
    }

    public boolean horsMap()
    {
        boolean collision;

        if (direction == BAS) {
            collision = hitbox.getPointLePlusEnBas(position.getY()) + statsVitesse.getVitesse() >= Monde.getSizeMondeHauteur();
        } else if (direction == HAUT) {
            collision = hitbox.getPointLePlusEnHaut(position.getY()) - statsVitesse.getVitesse() < 0;
        } else if (direction == Direction.DROITE) {
            collision = hitbox.getPointLePlusADroite(position.getX()) + statsVitesse.getVitesse() >= Monde.getSizeMondeLargeur();
        } else if (direction == GAUCHE) {
            collision = hitbox.getPointLePlusAGauche(position.getX()) - statsVitesse.getVitesse() < 0;
        }
        else
        {
            collision = false;
        }

        return collision;
    }


    public boolean collision()
    {
        double x = position.getX()+ statsVitesse.getVitesse()*direction.getX();
        double y = position.getY()+ statsVitesse.getVitesse()*direction.getY();

        // Extremité de la hitbox, calculer dans le if en dessous en fonction de la direction (on prend extremite gauche et droite si on va vers le haut ou le bas)
        double extremite1;
        double extremite2;

        if (direction== BAS  || direction== HAUT){
            extremite1 = hitbox.getPointLePlusAGauche(x);
            extremite2 = hitbox.getPointLePlusADroite(x);
        }
        else {
            extremite1 = hitbox.getPointLePlusEnHaut(y);
            extremite2 = hitbox.getPointLePlusEnBas(y);
        }

        boolean colision = false;
        int cpt = (int) extremite1;

        int[][] nontraversable = monde.getNontraversable();

        while (cpt <= extremite2 && !colision) {
            if (direction == BAS) {
                colision = nontraversable[(int) (hitbox.getPointLePlusEnBas(y))][cpt] != -1;
            } else if (direction == HAUT) {
                colision = nontraversable[(int) (hitbox.getPointLePlusEnHaut(y))][cpt] != -1;
            } else if (direction == DROITE) {
                colision = nontraversable[cpt][(int) (hitbox.getPointLePlusADroite(x))] != -1;
            } else if (direction == GAUCHE) {
                colision = nontraversable[cpt][(int) (hitbox.getPointLePlusAGauche(x))] != -1;
            }
            cpt++;
        }
        if(colision)
            return colision;

        // Calcul de collision en fonction des autres entités

        // Entité principale
        Position hautDroite = new Position(this.hitbox.getPointLePlusADroite(x), this.hitbox.getPointLePlusEnHaut(y));
        Position hautGauche = new Position(this.hitbox.getPointLePlusAGauche(x), this.hitbox.getPointLePlusEnHaut(y));
        Position basDroite = new Position(this.hitbox.getPointLePlusADroite(x), this.hitbox.getPointLePlusEnBas(y));
        Position basGauche = new Position(this.hitbox.getPointLePlusAGauche(x), this.hitbox.getPointLePlusEnBas(y));
        ArrayList<Position> coins = new ArrayList<>();
        coins.add(hautDroite);
        coins.add(hautGauche);
        coins.add(basDroite);
        coins.add(basGauche);

        ArrayList<Entite> entitesAutour = monde.getEntites(position, Math.sqrt(2));
        entitesAutour.add(monde.getJoueur());

        for (Entite entite : entitesAutour) {
            if(!entite.equals(this)) {
                Hitbox hitboxEntite = entite.getHitbox();
                Position positionEntite = entite.getPosition();
                for (Position coin : coins)
                    if (coin.getX() <= hitboxEntite.getPointLePlusADroite(positionEntite.getX()) &&
                            coin.getX() >= hitboxEntite.getPointLePlusAGauche(positionEntite.getX()) &&
                            coin.getY() <= hitboxEntite.getPointLePlusEnBas(positionEntite.getY()) &&
                            coin.getY() >= hitboxEntite.getPointLePlusEnHaut(positionEntite.getY()))
                        return true;
            }
        }

        return false;
    }



    public abstract void unTour();
    public abstract void subitCollision(Acteur acteur);


    public void setSeDeplace(boolean seDeplace){ this.seDeplace = seDeplace;}
    public void setMonde(Monde monde) {this.monde = monde;}
    public void setDirection(Direction direction) {this.direction = direction;}
    public void setPosition(Position position){ this.position = position;}
    public Vitesse getStatsVitesse() {return statsVitesse;}
    public void seDeplace(boolean seDeplace) {this.seDeplace = seDeplace;}
    public Direction getDirection() {return this.direction;}
    public boolean getSeDeplace(){return this.seDeplace;}
    public Hitbox getHitbox() {return this.hitbox;}
    public Monde getMonde() {return this.monde;}
    public Position getPosition() {return this.position;}
    public Pv getStatsPv() {return this.statsPv;}

}
