package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMelee.Epée;

import universite_paris8.iut.EtrangeEtrange.modele.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.EventC.EventCollision;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Acteur;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Projectile;
import universite_paris8.iut.EtrangeEtrange.modele.Statistique.Vitesse;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

public class AttaqueEpee implements Acteur
{
    private Monde monde;
    private EntiteOffensif attaquant;
    private Epee arme;

    private Position position;
    private Direction direction;
    private Hitbox hitbox;

    private short tourBoucle;

    public AttaqueEpee(EntiteOffensif attaquant,Epee arme)
    {
        this.attaquant = attaquant;
        this.arme = arme;
        this.monde = attaquant.getMonde();

        this.direction = attaquant.getDirection();
        this.hitbox = arme.getHitbox();
        setPremierePosition();
        this.tourBoucle = 0;
   }

    private void setPremierePosition()
    {
        double x = attaquant.getPosition().getX();
        double y = attaquant.getPosition().getY();

        switch (direction)
        {
            case HAUT:
                x = attaquant.getHitbox().getPointLePlusADroite(x);
                y = attaquant.getHitbox().getPointLePlusEnHaut(y);
                break;
            case BAS:
                x = attaquant.getHitbox().getPointLePlusADroite(x);
                y = attaquant.getHitbox().getPointLePlusEnBas(y);
                break;
            case DROITE:
                x = attaquant.getHitbox().getPointLePlusEnBas(x);
                y = attaquant.getHitbox().getPointLePlusADroite(y);
                break;
            case GAUCHE:
                x = attaquant.getHitbox().getPointLePlusADroite(x);
                y = attaquant.getHitbox().getPointLePlusAGauche(y);
                break;
        }
        this.position = new Position(x,y);
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
    public void unTour()
    {
        seDeplace(1);

        if (tourBoucle++ >= 3)
            monde.enleveActeur(this);

    }

    @Override
    public void subitCollisions(EventCollision e)
    {
        if (! (e.getActeur() instanceof Projectile))
            this.monde.enleveActeur(this);
    }

    @Override
    public Hitbox getHitbox() {
        return hitbox ;
    }

    @Override
    public Position getPosition() {
        return null;
    }

    @Override
    public Monde getMonde() {
        return null;
    }


    public Epee getArme(){ return this.arme; }
    public EntiteOffensif getAttaquant(){ return this.attaquant;}
}
