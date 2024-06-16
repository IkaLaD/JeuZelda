package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Dommageable;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Rechargeable;
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


    private boolean peutTaper;
    private short cycle;
    private long derniereApelle;


    public Epee()
    {
        super(DURABILITE, VITESSE, HITBOX);
        this.peutTaper = true;
        this.cycle = 0;
        this.derniereApelle = 0;
    }


    @Override
    public void utilise(Entite entite)
    {
        if (peutTaper)
        {
            setPosition(entite.getPosition());
            setMonde(entite.getMonde());
            setDirection(entite.getDirection());

            setPositionAttaque();
            entite.getMonde().ajoutActeur(this);

            this.derniereApelle = System.currentTimeMillis();
            entite.getMonde().ajoutRechargeable(this);

            this.peutTaper = false;
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
        double x = this.direction.getX() ;
        double y = this.direction.getY() ;


        position.setX(position.getX() + x * VITESSE * coeff);
        position.setY(position.getY() + y * VITESSE * coeff);

    }


    @Override
    public void causeCollision(Acteur acteur)
    {
        acteur.subitAttaque(this);
        monde.ajoutActeurAsupprimer(this);
    }

    @Override
    public int prixAchat() {
        return PRIX_ACHAT;
    }
    @Override
    public boolean peutSeDeplacer() { return true; }

    @Override
    public String typeActeur() {
        return "epee";
    }
    @Override
    public long delaie() {
        return DELAIE_UTILISATION;
    }

    @Override
    public boolean cooldown()
    {
        boolean actionFait = false;
        long apelle = System.currentTimeMillis();

        if (apelle - derniereApelle >= delaie())
        {
            this.derniereApelle = -1;
            this.peutTaper = true;
            actionFait = true;
        }

        return actionFait;
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
    public boolean estUnEnemie() {
        return false;
    }

    @Override
    public void subitCollision(Acteur acteur) {/*NE FAIT RIEN*/}
    @Override
    public void subitAttaque(Dommageable causeDegat) {  /*  NE FAIT RIEN */ }
}
