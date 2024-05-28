package universite_paris8.iut.EtrangeEtrange.modele.ActionJoueur;

import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;

public class ActionUtiliserMainDroite extends ActionJoueur{
    @Override
    public void executer(Joueur joueur) {
        joueur.actionMainDroite();
    }
}
