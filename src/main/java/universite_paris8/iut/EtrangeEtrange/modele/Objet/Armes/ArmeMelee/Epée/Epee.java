package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMelee.Epée;

import universite_paris8.iut.EtrangeEtrange.modele.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Rechargeable;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreAction;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreAttaque.ActionAttaqueMelee.ParametreAttaqueEpee;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Arme;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.TimerAction;

import java.util.TimerTask;

public class Epee extends Acteur implements Dommageable,Rechargeable,Arme
{
    private boolean peuTaper;
    private short cycle;


    public Epee() {
        super(25, 1, new Hitbox(0.2,0.1));
        this.peuTaper = true;
        this.cycle = 0;
    }

    @Override
    public boolean peutSeDeplacer() {
        return false;
    }

    @Override
    public void unTour()
    {
        if (cycle <= 3)
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
        int x = direction.getX();
        int y = direction.getY();

        double largeur;
        double hauteur;

        if (x != 0)
        {
            largeur = hitbox.getLargeur();
            hauteur = hitbox.getHauteur();
        }
        else
        {
            largeur = hitbox.getHauteur();
            hauteur = hitbox.getLargeur();
        }

        position.setX(position.getX() + x * largeur * coeff);
        position.setY(position.getY() + y * hauteur * coeff);
    }

    @Override
    public void subitCollision(Acteur acteur)
    {
        enlevePv(getStatsPv().getPvMaximum()/10);
        this.getMonde().enleveActeur(this);
    }



    @Override
    public void utilise(ParametreAction param)
    {
        if (param instanceof ParametreAttaqueEpee parametre)
        {
            if (peuTaper)
            {
                EntiteOffensif e = parametre.getOrigineAction();

                setMonde(e.getMonde());
                setDirection(e.getDirection());
                setPosition(e.getPosition().getX(),e.getPosition().getY());
                setPositionAttaque();

                param.getOrigineAction().getMonde().ajoutActeur(this);

                this.peuTaper = false;
                cooldown();
            }

        }
    }


    @Override
    public long delaie() {
        return 0;
    }

    @Override
    public void cooldown()
    {
        TimerAction.addAction(new TimerTask() {
            @Override
            public void run() {
                peuTaper = true;
            }
        },delaie());
    }


    @Override
    public String getNom() {
        return null;
    }

    @Override
    public int stackMax() {
        return 1;
    }


    private void setPositionAttaque()
    {
        double x = position.getX();
        double y = position.getY();

        switch (direction)
        {
            case HAUT:
                x = hitbox.getPointLePlusADroite(x);
                y = hitbox.getPointLePlusEnHaut(y);
                break;
            case BAS:
                x = hitbox.getPointLePlusADroite(x);
                y = hitbox.getPointLePlusEnBas(y);
                break;
            case DROITE:
                x = hitbox.getPointLePlusEnBas(x);
                y = hitbox.getPointLePlusADroite(y);
                break;
            case GAUCHE:
                x = hitbox.getPointLePlusADroite(x);
                y = hitbox.getPointLePlusAGauche(y);
                break;
        }

        this.position = new Position(x,y);
    }

    @Override
    public double degatPhysique() {
        return 0;
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
