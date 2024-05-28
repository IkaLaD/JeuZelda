package universite_paris8.iut.EtrangeEtrange.modele.Objet.Armes.ArmeMagique.Sort;

import universite_paris8.iut.EtrangeEtrange.modele.Entite.EntiteOffensif;

public class SortilegeDeSoins extends Sortilege
{
    private final int pvSoigner = 15;
    @Override
    public void action(EntiteOffensif utilisateur)
    {
        utilisateur.soigner(pvSoigner);
    }
}
