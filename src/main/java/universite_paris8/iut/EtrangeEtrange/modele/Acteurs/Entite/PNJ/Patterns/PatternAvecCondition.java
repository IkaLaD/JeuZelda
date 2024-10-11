package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns;

import universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Pnj;

import java.util.ArrayList;

public abstract class PatternAvecCondition extends Pattern {

    protected ArrayList<Pattern> patterns;

    public PatternAvecCondition(ArrayList<Pattern> patterns) {
        this.patterns = patterns;
    }




}
