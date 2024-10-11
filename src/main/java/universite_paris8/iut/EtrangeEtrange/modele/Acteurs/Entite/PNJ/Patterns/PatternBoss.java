package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.NPEs;

import java.util.ArrayList;

public class PatternBoss extends Pattern
{
    private VerifierJoueurDansVision verifierJoueurDansVision;

    public PatternBoss() {
        verifierJoueurDansVision = new VerifierJoueurDansVision(new VerifierJoueurDansRayonAttaque());
    }

    @Override
    public void effectue(NPEs npe) {

    }
}
