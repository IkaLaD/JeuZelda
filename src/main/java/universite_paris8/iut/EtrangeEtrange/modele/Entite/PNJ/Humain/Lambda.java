package universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Humain;


import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Utilisable;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.LivreMagique;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreAttaque.ActionAttaqueDistance.ParametreAttaqueArc;

import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreAttaque.ActionAttaqueMelee.ParametreAttaqueEpee;

import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreAttaque.ParametreActionAttaque;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreAttaque.ParametreLivreMagique.ParametreLivreMagique;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Aetoile;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Hitbox;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Humanoide;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.PNJ;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Arme;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMelee.Epée.Epee;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeTirable.Arc.Arc;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Fleche.FlecheSimple;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

public class Lambda extends Humanoide implements PNJ
{
    private long tourBoucle;
    private long derniereApelleAetoile;
    private Aetoile aetoile;
    public Lambda(Monde monde, double x, double y, Direction direction, Hitbox hitbox) {
        super(100, 5, 50, 1, 20, 0.1,null,null,null, monde, x, y, direction, hitbox);
        this.aetoile = new Aetoile(monde);
        this.tourBoucle = 0;
        this.derniereApelleAetoile = 0;
    }







    @Override
    public void action()
    {
        this.tourBoucle = ((tourBoucle + 1)%Long.MAX_VALUE-1);


        Position pos = monde.getJoueur().getPosition();

        if (aetoile.getChemin() == null || aetoile.getChemin().isEmpty() || (Math.abs(tourBoucle - derniereApelleAetoile) >= 2000))
        {
            if (pos.distance(position) <= 5)
            {
                derniereApelleAetoile = this.tourBoucle;

                aetoile.trouverChemin(position,pos);
            }
        }

        if (aetoile.getChemin() == null || aetoile.getChemin().isEmpty())
        {
            Position prochainePosition = aetoile.getChemin().get(0);

            double deltaX = prochainePosition.getX() - getPosition().getX();
            double deltaY = prochainePosition.getY() - getPosition().getY();


            if (Math.abs(deltaX) > Math.abs(deltaY))
                setDirection(deltaX > 0 ? Direction.DROITE : Direction.GAUCHE);
             else
                setDirection(deltaY > 0 ? Direction.BAS : Direction.HAUT);


            if (peutSeDeplacer())
                seDeplace();
        }


        if (objetMainDroite instanceof Utilisable)
        {

        }




    }

    @Override
    public void attaque(Arme arme)
    {
        ParametreActionAttaque actionAttaquer = null;

        if (arme instanceof Epee)
        {
            actionAttaquer = new ParametreAttaqueEpee(this);
        }
        else if (arme instanceof Arc)
        {
            actionAttaquer = new ParametreAttaqueArc(this,new FlecheSimple());
        }

        arme.utilise(actionAttaquer);
    }

    @Override
    public void lanceUnSort(int numSort)
    {
        if (objetMainDroite instanceof LivreMagique livreMagique)
        {
            livreMagique.utilise(new ParametreLivreMagique(this,numSort));
        }
    }



}
