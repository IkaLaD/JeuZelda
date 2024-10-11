package universite_paris8.iut.EtrangeEtrange.modele.Acteurs.Entite.PNJ.Patterns;

import java.util.ArrayList;

public abstract class PatternTechnique extends Pattern
{
    protected ArrayList<Pattern> patterns;

    public PatternTechnique(ArrayList<Pattern> patterns)
    {
        this.patterns = new ArrayList<>(patterns);
    }


}
