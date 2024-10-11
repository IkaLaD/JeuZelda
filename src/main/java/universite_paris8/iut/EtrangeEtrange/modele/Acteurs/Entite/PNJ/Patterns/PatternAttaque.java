package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Monstre.Boss;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Monstre.Monstre;
import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.NPEs;


public class PatternAttaque extends Pattern
{

    @Override
    public void effectue(NPEs npe) {
        Monstre boss = (Monstre) npe;
        boss.getArme().utiliseePar(boss);
    }
}
