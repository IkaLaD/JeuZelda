package universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Humain;


import universite_paris8.iut.EtrangeEtrange.modele.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Utilisable;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.LivreMagique;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Consommable.Soins.Potion;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreAttaque.ActionAttaqueDistance.ParametreAttaqueArc;

import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreAttaque.ActionAttaqueMelee.ParametreAttaqueEpee;

import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreAttaque.ParametreActionAttaque;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreAttaque.ParametreLivreMagique.ParametreLivreMagique;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.Constantes;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.ConstantesPersonnages;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.*;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Humanoide;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.PNJ;
import universite_paris8.iut.EtrangeEtrange.modele.Map.Monde;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.Arme;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMelee.Epée.Epee;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeTirable.Arc.Arc;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Fleche.FlecheSimple;

import java.util.ArrayList;

public class Lambda extends Humanoide implements PNJ
{
    private ArrayList<Potion> potions;
    private long tourBoucle;
    private long derniereApelleAetoile;
    private Aetoile aetoile;
    public Lambda(Monde monde, double x, double y, Direction direction) {
        super(100, 5, 50, 1, 20, 0.1,null,new Arc(),null, monde, x, y, direction, new Hitbox(0.50,0.50));
        this.aetoile = new Aetoile(monde);
        this.tourBoucle = 0;
        this.derniereApelleAetoile = 0;
        this.potions = new ArrayList<>();

        for (int i = 0;i<5;i++)
            this.potions.add(new Potion());
    }







    @Override
    public void action()
    {
        this.tourBoucle = (tourBoucle + 1) % Long.MAX_VALUE;


        Position pos = monde.getJoueur().getPosition();

        if (aetoile.getChemin() == null || aetoile.getChemin().isEmpty() || (Math.abs(tourBoucle - derniereApelleAetoile) >= 100))
        {
            if (pos.distance(position) <= 10)
            {
                derniereApelleAetoile = this.tourBoucle;

                aetoile.trouverChemin(position,pos);
            }
        }

        if (aetoile.getChemin() != null && !aetoile.getChemin().isEmpty())
        {
            Position prochainePosition = aetoile.getChemin().get(0);

            double deltaX = prochainePosition.getX() - getPosition().getX();
            double deltaY = prochainePosition.getY() - getPosition().getY();


            if (Math.abs(deltaX) > Math.abs(deltaY))
                setDirection(deltaX > 0 ? Direction.DROITE : Direction.GAUCHE);
             else
                setDirection(deltaY > 0 ? Direction.BAS : Direction.HAUT);

            if (peutSeDeplacer()) {
                seDeplace();

                if (Math.abs(getPosition().getX() - position.getX()) < 0.1 && Math.abs(getPosition().getY() - position.getY()) < 0.1) {
                    aetoile.getChemin().remove(0);
                    setPosition(roundToTenth(getPosition().getX()), roundToTenth(getPosition().getY()));
                }
            }
            else
            {
                aetoile.trouverChemin(position,pos);
            }



            if (tourBoucle%100 == 0)
            {
                if (getPv() <= getStatsPv().getPvMaximum()/2)
                {
                    if (!potions.isEmpty())
                        consommer(potions.remove(0));


                }
            }


            if (getPv() <= getStatsPv().getPvMaximum()/2 && tourBoucle%100 == 0)
            {
                FabriquePnj.fabriquePnj(Squelette.class,2,monde,position);
            }







        }


        if (objetMainDroite instanceof Utilisable)
        {

        }




    }
    private double roundToTenth(double value) {
        return Math.round(value * 10.0) / 10.0;
    }

    private Direction chercheJoueur(Entite entite)
    {
        Surface surfaceEnemie = new Surface(entite.getPosition(),entite.getHitbox());
        Surface me = new Surface(position,hitbox);

        for (Direction d : Direction.values())
        {
            if (me.enFace(surfaceEnemie,d))
                return d;
        }

        return null;
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
