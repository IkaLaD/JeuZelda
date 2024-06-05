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

public abstract class Entite {
    private static int staticIdEntité = 0;
    protected Pv statsPv;
    protected Defense statsDefense;
    protected DefenseSpecial statsDefenseSpecial;
    protected Vitesse statsVitesse;
    protected Position position;
    protected Direction direction;
    protected Monde monde;
    protected Hitbox hitbox;
    private int id;
    private boolean seDeplace;

    public Entite(double pv,double defense,double defenseSpecial,double vitesse,Monde monde,double x,double y,Direction direction, Hitbox hitbox)
    {
        this.id = staticIdEntité++;
        this.statsPv = new Pv(pv);
        this.statsDefense = new Defense(defense);
        this.statsDefenseSpecial = new DefenseSpecial(defenseSpecial);
        this.statsVitesse = new Vitesse(vitesse);
        this.seDeplace = false;

        this.monde = monde;
        this.position = new Position(x, y);
        this.direction = direction;
        this.hitbox = hitbox;
    }

    public void subitDegat(ActionDegat causeDegat)
    {

        if (causeDegat instanceof ActionDegatParEntite)
        {
            EntiteOffensif entiteOffensif = ((ActionDegatParEntite) causeDegat).getOrigineDegat();

            if (causeDegat instanceof ActionDegatParEpee)
            {
                enlevePv(subitDegatPhysique(causeDegat.getOrgineAttaque().degatPhysique(),((ActionDegatParEpee) causeDegat).getOrigineDegat().getAttaque()));
                enlevePv(subitDegatSpecial(causeDegat.getOrgineAttaque().degatSpecial(),((ActionDegatParEpee) causeDegat).getOrigineDegat().getAttaqueSpecial()));
            }
            else if (causeDegat instanceof ActionDegatParProjectile)
            {
                if (((ActionDegatParProjectile) causeDegat).getOrigineDegat() != this)
                {
                    enlevePv(subitDegatPhysique(causeDegat.getOrgineAttaque().degatPhysique(),((ActionDegatParProjectile) causeDegat).getOrigineDegat().getAttaque()));
                    enlevePv(subitDegatSpecial(causeDegat.getOrgineAttaque().degatSpecial(),((ActionDegatParProjectile) causeDegat).getOrigineDegat().getAttaqueSpecial()));
                    Projectile projectile = (Projectile) causeDegat.getOrgineAttaque();
                    projectile.toucher();
                }
            }

        }


    }

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

    public void setSeDeplace(boolean seDeplace) {
        this.seDeplace = seDeplace;
    }

    public void seDeplace(double coef)
    {
        int x = direction.getX();
        int y = direction.getY();

        if(peutSeDeplacer()) {
            position.setX(position.getX() + x * statsVitesse.getVitesse() * coef);
            position.setY(position.getY() + y * statsVitesse.getVitesse() * coef);
        }
    }

    public void seDeplace()
    {
        seDeplace(1);
    }


    public void soigner(double pv)
    {
        this.statsPv.ajoutPv(pv);
    }


    public Pv getStatsPv() {return this.statsPv;}
    public double getPv(){ return this.statsPv.getPv(); }
    public double getDefense(){ return this.statsDefense.getDefense();}
    public double getDefenseSpecial(){ return this.statsDefenseSpecial.getDefenseSpecial();}
    public double getVitesse(){ return this.statsVitesse.getVitesse();}
    public int getId(){
        return this.id;
    }
    public Defense getStatsDefense(){return this.statsDefense;}
    public DefenseSpecial getStatsDefenseSpecial(){return this.statsDefenseSpecial;}
    public Vitesse getStatsVitesse(){return this.statsVitesse;}
    public Position getPosition(){return this.position;}
    public Direction getDirection() {return this.direction;}
    public Monde getMonde(){return this.monde;}
    public Hitbox getHitbox(){
        return this.hitbox;
    }
    public void setDirection(Direction direction){
        this.direction = direction;
    }

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
