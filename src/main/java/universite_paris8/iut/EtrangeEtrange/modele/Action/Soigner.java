package universite_paris8.iut.EtrangeEtrange.modele.Action;

import universite_paris8.iut.EtrangeEtrange.modele.Entite.PNJ.Interagisable.Prompte.Prompt;
import universite_paris8.iut.EtrangeEtrange.modele.Entite.Personnage.Joueur;

public class Soigner extends Action
{
    private Joueur joueur;

    public Soigner(Joueur joueur)
    {
        this.joueur = joueur;
    }
    @Override
    public Prompt execute() {
        joueur.getStatsPv().ajoutPv(joueur.getStatsPv().getPvMaximum());
        return null;
    }
}
