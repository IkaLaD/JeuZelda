package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Invocation;

import universite_paris8.iut.EtrangeEtrange.modele.Compétence.TypeCompetence;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.EntiteOffensif;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.Personnage.Joueur;
import universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort.Sortilege;

public abstract class SortilegeInvocation extends Sortilege {
    @Override
    public void action(EntiteOffensif utilisateur)
    {
        if (peuInvoquer(utilisateur))
            sort();
    }

    private boolean peuInvoquer(EntiteOffensif utilisateur)
    {
        boolean peuInvoquer = true;

        if (utilisateur instanceof  Joueur joueur && !joueur.getCompetences().contientCompetence(TypeCompetence.INVOQUER))
            peuInvoquer = false;

        return peuInvoquer;
    }
    public abstract void sort();
}
