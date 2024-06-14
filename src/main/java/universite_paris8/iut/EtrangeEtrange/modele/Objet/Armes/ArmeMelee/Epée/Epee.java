package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMelee.Epée;

import universite_paris8.iut.EtrangeEtrange.modele.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Rechargeable;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreAction;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreAttaque.ActionAttaqueMelee.ParametreAttaqueEpee;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Arme;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

public class Epee extends Acteur implements Dommageable,Rechargeable,Arme
{
    private boolean peuTaper;
    private short cycle;
    private long tourApelle;

    private EntiteOffensif utilisateur;


    public Epee()
    {
        super(10, 1.2, new Hitbox(1,1));
        this.peuTaper = true;
        this.cycle = 0;
        this.tourApelle = 0;
    }

    @Override
    public boolean peutSeDeplacer() {return true;}

    @Override
    public void unTour()
    {
        if (cycle <= 2)
        {
            seDeplace(1);
            cycle++;
        }
        else
        {
            this.getMonde().enleveActeur(this);
            cycle = 0;
        }
    }



    @Override
    public void seDeplace(double coeff)
    {
        double x = this.direction.getX() * 0.2;
        double y = this.direction.getY() * 0.2;


        position.setX(position.getX() + x * statsVitesse.getVitesse() * coeff);
        position.setY(position.getY() + y * statsVitesse.getVitesse() * coeff);

    }



   /*
   *  @Override
    public void seDeplace(double coeff)
    {
        int x = direction.getX();
        int y = direction.getY();

        double largeur;
        double hauteur;

        if (x == 0)
        {
            largeur = hitbox.getHauteur();
            position.setX(position.getX() + x + largeur * coeff);
        }
        else
        {
            hauteur = hitbox.getHauteur();
            position.setY(position.getY() + y + hauteur * coeff);
        }



    }*/

    @Override
    public void subitCollision(Acteur acteur) { /*  NE FAIT RIEN */  }

    @Override
    public void causeCollision(Acteur acteur) {
        acteur.subitAttaque(this);
        enlevePv(getStatsPv().getPvMaximum()/10);
    }

    @Override
    public void subitAttaque(Dommageable causeDegat) {  /*  NE FAIT RIEN */ }

    @Override
    public void subitDegat(Dommageable causeDegat) {

    }

    @Override
    protected double subitDegatPhysique(double attaqueEntite, double degatArme) {
        return 0;
    }

    @Override
    protected double subitDegatSpecial(double attaqueSpecialEntite, double degatArme) {
        return 0;
    }

    @Override
    public void dropApresMort() {

    }

    @Override
    public String typeActeur() {
        return "epee";
    }


    @Override
    public void utilise(ParametreAction param)
    {
        if (param instanceof ParametreAttaqueEpee parametre)
        {
            if (peuTaper)
            {
                utilisateur = parametre.getOrigineAction();

                setPosition(utilisateur.getPosition());
                setMonde(utilisateur.getMonde());
                setDirection(utilisateur.getDirection());
                this.peuTaper = false;


                setPositionAttaque();
                param.getOrigineAction().getMonde().ajoutActeur(this);
                param.getOrigineAction().getMonde().ajoutRechargeable(this);




            }

        }
    }


    @Override
    public long delaie() {
        return 3;
    }

    @Override
    public void cooldown()
    {
        peuTaper = true;
    }

    @Override
    public void setTourApelle(long tourApelle) {
        this.tourApelle = tourApelle;
    }

    @Override
    public long getTourApelle() {
        return this.tourApelle;
    }


    @Override
    public String getNom() {
        return "epee";
    }

    @Override
    public int stackMax() {
        return 1;
    }

    @Override
    public double durabilitee() {
        return getPv();
    }


    private void setPositionAttaque()
    {
        double x = position.getX();
        double y = position.getY();

        double posX = 0;
        double posY = 0;

        switch (direction)
        {
            case HAUT:
                x = hitbox.getPointLePlusADroite(x);
                y = hitbox.getPointLePlusEnHaut(y);
                posX = 0;
                posY = -hitbox.getHauteur();
                break;
            case BAS:
                x = hitbox.getPointLePlusADroite(x);
                y = hitbox.getPointLePlusEnBas(y);
                posX = 0;
                posY = hitbox.getHauteur();
                break;
            case DROITE:
                x = hitbox.getPointLePlusEnBas(x);
                y = hitbox.getPointLePlusADroite(y);
                posX = hitbox.getLargeur();
                posY = 0;
                break;
            case GAUCHE:
                posX = -hitbox.getLargeur();
                posY = 0;
                break;
        }

        this.position = new Position(x+posX,y+posY);
    }

    @Override
    public double degatPhysique() {
        return 100;
    }

    @Override
    public double degatSpecial() {
        return 0;
    }

    @Override
    public double portee() {
        return 0;
    }

    @Override
    public double angle() {
        return 0;
    }
}
