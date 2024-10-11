package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.NPEs;

import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Position;

import java.util.ArrayList;
import java.util.List;

public class VerifierJoueurDansVision extends PatternAvecCondition
{

    private int rayon;

    public VerifierJoueurDansVision(Pattern pattern,int rayon) {
        super(new ArrayList<>(List.of(pattern)));
        this.rayon = rayon;
    }

    @Override
    public void effectue(NPEs npe) {
        Pattern pattern;

        if (joueurDetecter(npe)) {
            pattern = patterns.get(0);
            pattern.effectue(npe);
        }


    }

    private boolean joueurDetecter(NPEs npe)
    {
        Position positionPnj = npe.getPosition();
        Position positionJoueur = npe.getMonde().getJoueur().getPosition();

        double distance = Math.sqrt(Math.pow(positionJoueur.getX() - positionPnj.getX(), 2) +
                Math.pow(positionJoueur.getY() - positionPnj.getY(), 2));
        return distance <= rayon;
    }
}
