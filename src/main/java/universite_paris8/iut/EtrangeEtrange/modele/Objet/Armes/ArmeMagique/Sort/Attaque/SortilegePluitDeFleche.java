package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Attaque;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Entite;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.Interfaces.Rechargeable;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Sortilege;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Projectile.Fleche;
import universite_paris8.iut.EtrangeEtrange.modele.Parametres.ConstantesSortilege;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

public class SortilegePluitDeFleche extends Sortilege {
    private final int NOMBRE_FLECHE = ConstantesSortilege.NOMBRE_FLECHE_PLUIT_DE_FLECHES;


    @Override
    public void utilise(Entite utilisateur)
    {
        if (peutLancerSort)
        {
            double x = utilisateur.getPosition().getX();
            double y = utilisateur.getPosition().getY();

            Direction direction = utilisateur.getDirection();

            for (int i = 0;i<NOMBRE_FLECHE;i++)
            {

                int delaie = i+1;
                Rechargeable rechargeable = new Rechargeable()
                {
                    private long derniereApelle = 0;
                    @Override
                    public long delaie() {
                        return delaie * 2 ;
                    }

                    @Override
                    public boolean cooldown()
                    {
                        boolean actionFait = false;
                        long apelle = System.currentTimeMillis();

                        if (apelle - derniereApelle >= delaie())
                        {
                            Fleche flecheSimple = new Fleche();
                            flecheSimple.setDirection(direction);
                            flecheSimple.setPosition(positionAleaAutourDe(x,y, direction));
                            flecheSimple.setMonde(utilisateur.getMonde());
                            flecheSimple.setUtilisateur(utilisateur);
                            utilisateur.getMonde().ajoutActeur(flecheSimple);
                            actionFait = true;
                        }
                        return actionFait;
                    }

                };

                utilisateur.getMonde().ajoutRechargeable(rechargeable);
                this.peutLancerSort = false;
                this.derniereApelle = System.currentTimeMillis();
                utilisateur.getMonde().ajoutRechargeable(this);
            }
        }
    }

    private Position positionAleaAutourDe(double x, double y, Direction direction) {
        final double dispersionSurLesCotes = 3;
        final double dispersionFace = 3;

        double newX = x;
        double newY = y;

        if (direction == Direction.DROITE || direction == Direction.GAUCHE) {
            newX += ((Math.random() * dispersionFace) * direction.getX());
            newY += (Math.random() * dispersionSurLesCotes - dispersionSurLesCotes / 2);
        } else {
            newX += (Math.random() * dispersionSurLesCotes - dispersionSurLesCotes / 2);
            newY += ((Math.random() * dispersionFace) * direction.getY());
        }

        return new Position(newX, newY);
    }


    @Override
    public long delaie() {
        return ConstantesSortilege.DELAIE_PLUIT_DE_FLECHES;
    }



}