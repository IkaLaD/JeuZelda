package universite_paris8.iut.EtrangeEtrange.modele.ActionJoueur.ActionLanceSort;

import universite_paris8.iut.EtrangeEtrange.modele.ActionJoueur.ActionJoueur;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.ParametreActionSurObjet.ParametreActionMainDroite.ParametreActionAttaque.ParametreActionLivreMagique.ParametreActionLivreMagique;

public abstract class ActionUtiliserSortilege extends ActionJoueur
{
    public abstract int sortVoulue();

    @Override
    public void executer(Joueur joueur) {
        joueur.lanceUnSort(new ParametreActionLivreMagique(joueur,sortVoulue()));
    }
}
