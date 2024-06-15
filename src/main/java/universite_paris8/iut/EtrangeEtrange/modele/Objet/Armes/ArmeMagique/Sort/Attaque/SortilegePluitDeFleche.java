package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Attaque;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Rechargeable;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Sortilege;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Fleche;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

public class SortilegePluitDeFleche extends Sortilege
{
    private final int NOMBRE_FLECHE = 15;

    @Override
    public void utilise(Entite utilisateur)
    {
        double x = utilisateur.getPosition().getX();
        double y = utilisateur.getPosition().getY();

        Direction direction = utilisateur.getDirection();

        for (int i = 0;i<NOMBRE_FLECHE;i++)
        {

            int delaie = i+1;
            Rechargeable rechargeable = new Rechargeable()
            {
                private long tourApelle = 0;
                @Override
                public long delaie() {
                    return delaie * 2 ;
                }

                @Override
                public void cooldown()
                {
                    Fleche flecheSimple = new Fleche();
                    flecheSimple.setDirection(direction);
                    flecheSimple.setPosition(positionAleaAutourDe(x,y, direction));
                    flecheSimple.setMonde(utilisateur.getMonde());
                    utilisateur.getMonde().ajoutActeur(flecheSimple);
                }

                @Override
                public void setTourApelle(long tourApelle)
                {
                    this.tourApelle = tourApelle;
                }

                @Override
                public long getTourApelle() {
                    return tourApelle;
                }
            };

            utilisateur.getMonde().ajoutRechargeable(rechargeable);

        }
    }

    private Position positionAleaAutourDe(double x, double y, Direction direction)
    {
        final double dispersionSurLesCotes = 3 ;
        final double dispersionFace = 3 ;

        double newX = x;
        double newY = y;

        if (direction == Direction.DROITE || direction == Direction.GAUCHE)
        {
            newX += ((Math.random() * dispersionFace) * direction.getX());
            newY += (Math.random() * dispersionSurLesCotes - dispersionSurLesCotes / 2);
        }
        else
        {
            newX += (Math.random() * dispersionSurLesCotes - dispersionSurLesCotes / 2);
            newY += ((Math.random() * dispersionFace) * direction.getY());
        }

        return new Position(newX,newY);
    }



}
