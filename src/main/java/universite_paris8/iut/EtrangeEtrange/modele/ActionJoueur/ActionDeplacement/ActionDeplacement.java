package universite_paris8.iut.EtrangeEtrange.modele.ActionJoueur.ActionDeplacement;

import universite_paris8.iut.EtrangeEtrange.modele.ActionJoueur.ActionJoueur;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Direction;

public abstract class ActionDeplacement extends ActionJoueur
{
    public abstract Direction direction();
    @Override
    public void executer(Joueur joueur) {
        joueur.setDirection(direction());
        joueur.seDeplace();
    }
}
