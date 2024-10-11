package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.NPEs;
import universite_paris8.iut.EtrangeEtrange.modele.Utilitaire.Aetoile;

import java.util.ArrayList;

public class VerifierJoueurDansRayonAttaque extends PatternAvecCondition
{
    private Aetoile aetoile;
    private long dernierCalculeDeChemin;
    private final int delaieCalcule = 1500;


    private int patternAutiliser;

    public VerifierJoueurDansRayonAttaque(ArrayList<Pattern> patterns) {
        super(patterns);
    }


    @Override
    public void effectue(NPEs npe) {

        Pattern pattern = null;

        if (aPorterDeJoueur(npe))
            pattern = patterns.get(0); // attaque
        else
            pattern = patterns.get(1); // se deplacer vers Joueur

        pattern.effectue(npe);
    }


    private boolean aPorterDeJoueur(NPEs pnj)
    {
        return true;
    }

    private boolean doitRecalculerLeChemin()
    {
        long current = System.currentTimeMillis();

        return  (dernierCalculeDeChemin == -1 || (current - dernierCalculeDeChemin >= delaieCalcule));
    }
}
