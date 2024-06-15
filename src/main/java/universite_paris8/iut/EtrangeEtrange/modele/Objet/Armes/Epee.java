package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Rechargeable;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreAction;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreAttaque.ActionAttaqueMelee.ParametreAttaqueEpee;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.ConstanteObjet;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Arme;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

public class Epee extends Acteur implements Dommageable,Rechargeable,Arme
{

    private static final int DURABILITE = ConstanteObjet.DURABILITE_EPEE;
    private static final double DEGAT_PHYSIQUE = ConstanteObjet.DEGAT_PHYSIQUE_EPEE;
    private static final double DEGAT_SPECIAL = ConstanteObjet.DEGAT_SPECIAL_EPEE;
    private static final double VITESSE = ConstanteObjet.VITESSE_EPEE;
    private static final Hitbox HITBOX = ConstanteObjet.HITBOX_EPEE;
    private static final long DELAIE_UTILISATION = ConstanteObjet.DELAIE_UTILISATION_EPEE;
    private final int PRIX_ACHAT = ConstanteObjet.PRIX_ACHAT_EPEE;
    private final int STACK_MAX = ConstanteObjet.STACK_MAX_EPEE;


    private boolean peuTaper;
    private short cycle;
    private long tourApelle;


    public Epee()
    {
        super(DURABILITE, VITESSE, HITBOX);
        this.peuTaper = true;
        this.cycle = 0;
        this.tourApelle = 0;
    }


    @Override
    public void utilise(Entite entite)
    {
        if (peuTaper)
        {
            setPosition(entite.getPosition());
            setMonde(entite.getMonde());
            setDirection(entite.getDirection());

            setPositionAttaque();
            entite.getMonde().ajoutActeur(this);
            entite.getMonde().ajoutRechargeable(this);

            this.peuTaper = false;
        }
    }


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
    public void seDeplace(double coeff)
    {
        double x = this.direction.getX() * 0.2;
        double y = this.direction.getY() * 0.2;


        position.setX(position.getX() + x * VITESSE * coeff);
        position.setY(position.getY() + y * VITESSE * coeff);

    }


    @Override
    public void causeCollision(Acteur acteur)
    {
        acteur.subitAttaque(this);
        enlevePv((double) DURABILITE /10);
    }

    @Override
    public int prixAchat() {
        return PRIX_ACHAT;
    }
    @Override
    public boolean peutSeDeplacer() { return true; }

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
    public String typeActeur() {
        return "epee";
    }
    @Override
    public long delaie() {
        return DELAIE_UTILISATION;
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
        return STACK_MAX;
    }

    @Override
    public double durabilitee() {
        return getPv();
    }


    @Override
    public double degatPhysique() {
        return DEGAT_PHYSIQUE;
    }

    @Override
    public double degatSpecial() {
        return DEGAT_SPECIAL;
    }

    @Override
    public void seFaitPousser(Acteur acteur) {/*NE FAIT RIEN*/}

    @Override
    public void subitCollision(Acteur acteur) {/*NE FAIT RIEN*/}
    @Override
    public void subitAttaque(Dommageable causeDegat) {  /*  NE FAIT RIEN */ }
}
