package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.NPEs;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Aetoile;

public class SeDeplaceVersJoueur extends Pattern {

    private Aetoile aetoile;

    public SeDeplaceVersJoueur() {
        this.aetoile = new Aetoile();
    }
    @Override
    public void effectue(NPEs npe) {

    }
}
